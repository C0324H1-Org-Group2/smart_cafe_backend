package com.group2.smart_cafe_backend.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendResetPasswordEmail(String toEmail, String resetUrl) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String htmlContent = "<!DOCTYPE html>"
                + "<html lang=\"vi\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                + "    <title>Đặt lại mật khẩu</title>"
                + "</head>"
                + "<body style=\"margin: 0; padding: 0; background-color: #6F4F28;\">"
                + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"
                + "        <tr>"
                + "            <td style=\"padding: 20px 0 30px 0;\">"
                + "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" style=\"border-collapse: collapse;\">"
                + "                    <!-- Header -->"
                + "                    <tr>"
                + "                        <td align=\"center\" bgcolor=\"#6F4F28\" style=\"padding: 15px; color: white;\">"
                + "                            <h2 style=\"margin: 0; font-size: 24px;\">Smart Cafe</h2>"
                + "                        </td>"
                + "                    </tr>"
                + "                    <!-- Body -->"
                + "                    <tr>"
                + "                        <td bgcolor=\"#ffffff\" style=\"padding: 40px 30px;\">"
                + "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"
                + "                                <tr>"
                + "                                    <td style=\"color: #333333; font-family: Arial, sans-serif; font-size: 16px; line-height: 24px; text-align: center;\">"
                + "                                        <div style=\"background-color: #6F4F28; color: white; padding: 20px; border-radius: 5px;\">"
                + "                                            <p style=\"margin: 0;\">Nhấn vào liên kết dưới đây để đặt lại mật khẩu của bạn:</p>"
                + "                                            <p style=\"margin: 20px 0;\">"
                + "                                                <a href=\"" + resetUrl + "\" style=\"color: #ffffff; text-decoration: none; font-weight: bold; padding: 10px 20px; border: 2px solid #ffffff; border-radius: 5px; display: inline-block;\">"
                + "                                                    Đặt lại mật khẩu"
                + "                                                </a>"
                + "                                            </p>"
                + "                                            <p>Liên kết này sẽ hết hạn trong 24 giờ.</p>"
                + "                                        </div>"
                + "                                    </td>"
                + "                                </tr>"
                + "                            </table>"
                + "                        </td>"
                + "                    </tr>"
                + "                    <!-- Footer -->"
                + "                    <tr>"
                + "                        <td bgcolor=\"#6F4F28\" style=\"padding: 20px; color: white; text-align: center; font-family: Arial, sans-serif; font-size: 14px;\">"
                + "                            &copy; 2024 Smart Cafe. Tất cả quyền được bảo lưu."
                + "                        </td>"
                + "                    </tr>"
                + "                </table>"
                + "            </td>"
                + "        </tr>"
                + "    </table>"
                + "</body>"
                + "</html>";

        helper.setTo(toEmail);
        helper.setSubject("Đặt lại mật khẩu");
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }

    public void sendResetPasswordEmail30Days(String email, String resetUrl) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String htmlContent = "<!DOCTYPE html>"
                + "<html lang=\"vi\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                + "    <title>Yêu cầu thay đổi mật khẩu</title>"
                + "</head>"
                + "<body style=\"margin: 0; padding: 0; background-color: #6F4F28;\">"
                + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"
                + "        <tr>"
                + "            <td style=\"padding: 20px 0 30px 0;\">"
                + "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\" style=\"border-collapse: collapse;\">"
                + "                    <!-- Header -->"
                + "                    <tr>"
                + "                        <td align=\"center\" bgcolor=\"#6F4F28\" style=\"padding: 15px; color: white;\">"
                + "                            <h2 style=\"margin: 0; font-size: 24px;\">Smart Cafe</h2>"
                + "                        </td>"
                + "                    </tr>"
                + "                    <!-- Body -->"
                + "                    <tr>"
                + "                        <td bgcolor=\"#ffffff\" style=\"padding: 40px 30px;\">"
                + "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"
                + "                                <tr>"
                + "                                    <td style=\"color: #333333; font-family: Arial, sans-serif; font-size: 16px; line-height: 24px; text-align: center;\">"
                + "                                        <div style=\"background-color: #6F4F28; color: white; padding: 20px; border-radius: 5px;\">"
                + "                                            <p style=\"margin: 0;\">Mật khẩu của bạn đã quá hạn 30 ngày. Vui lòng thay đổi mật khẩu của bạn tại liên kết dưới đây:</p>"
                + "                                            <p style=\"margin: 20px 0;\">"
                + "                                                <a href=\"" + resetUrl + "\" style=\"color: #ffffff; text-decoration: none; font-weight: bold; padding: 10px 20px; border: 2px solid #ffffff; border-radius: 5px; display: inline-block;\">"
                + "                                                    Thay đổi mật khẩu"
                + "                                                </a>"
                + "                                            </p>"
                + "                                        </div>"
                + "                                    </td>"
                + "                                </tr>"
                + "                            </table>"
                + "                        </td>"
                + "                    </tr>"
                + "                    <!-- Footer -->"
                + "                    <tr>"
                + "                        <td bgcolor=\"#6F4F28\" style=\"padding: 20px; color: white; text-align: center; font-family: Arial, sans-serif; font-size: 14px;\">"
                + "                            &copy; 2024 Smart Cafe. Tất cả quyền được bảo lưu."
                + "                        </td>"
                + "                    </tr>"
                + "                </table>"
                + "            </td>"
                + "        </tr>"
                + "    </table>"
                + "</body>"
                + "</html>";

        helper.setTo(email);
        helper.setSubject("Yêu cầu thay đổi mật khẩu");
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
