package com.group2.smart_cafe_backend.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VNPayService {

    @Value("${vnpay.tmnCode}")
    private String tmnCode;

    @Value("${vnpay.secretKey}")
    private String hashSecret;

    @Value("${vnpay.url}")
    private String paymentUrl;

    @Value("${vnpay.returnUrl}")
    private String returnUrl;

    public String createPaymentUrl(long amount, String orderInfo, String txnRef, String ipAddr) {
        Map<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", tmnCode);
        vnpParams.put("vnp_Amount", String.valueOf(amount * 100));
        vnpParams.put("vnp_CurrCode", "VND");
        vnpParams.put("vnp_TxnRef", txnRef);
        vnpParams.put("vnp_OrderInfo", orderInfo);
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_ReturnUrl", returnUrl);
        vnpParams.put("vnp_IpAddr", ipAddr);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        vnpParams.put("vnp_CreateDate", formatter.format(cal.getTime()));
        cal.add(Calendar.MINUTE, 15);
        vnpParams.put("vnp_ExpireDate", formatter.format(cal.getTime()));

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            if (!first) {
                hashData.append('&');
                query.append('&');
            }
            first = false;
            String key = entry.getKey();
            String value = entry.getValue();
            hashData.append(key).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII));
            query.append(URLEncoder.encode(key, StandardCharsets.US_ASCII))
                 .append('=')
                 .append(URLEncoder.encode(value, StandardCharsets.US_ASCII));
        }

        String secureHash = hmacSHA512(hashSecret, hashData.toString());
        query.append("&vnp_SecureHash=").append(secureHash);

        return paymentUrl + "?" + query;
    }

    public boolean verifyPayment(Map<String, String> params) {
        String vnpSecureHash = params.remove("vnp_SecureHash");
        params.remove("vnp_SecureHashType");
        Map<String, String> sortedParams = new TreeMap<>(params);

        StringBuilder hashData = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            if (!first) hashData.append('&');
            first = false;
            hashData.append(entry.getKey()).append('=')
                    .append(URLEncoder.encode(entry.getValue(), StandardCharsets.US_ASCII));
        }

        String calculatedHash = hmacSHA512(hashSecret, hashData.toString());
        return calculatedHash.equalsIgnoreCase(vnpSecureHash);
    }

    private String hmacSHA512(String key, String data) {
        try {
            Mac hmac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            hmac.init(secretKey);
            byte[] hmacBytes = hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hmacBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error computing HMAC-SHA512", e);
        }
    }
}
