package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.Bill;
import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.models.User;
import com.group2.smart_cafe_backend.models.emum.BillStatus;
import com.group2.smart_cafe_backend.repositories.IBillRepository;
import com.group2.smart_cafe_backend.repositories.IUserRepository;
import com.group2.smart_cafe_backend.services.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class BillService implements IBillService {

    @Autowired
    private IBillRepository billRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Bill createBill(Tables table) {
        Bill bill = new Bill();

        bill.setTable(table);

        // Tạo mã hóa đơn theo định dạng BIxxx
        bill.setCode(generateBillCode());

        bill.setDateCreated(LocalDateTime.now());
        bill.setStatus(BillStatus.pending);

        // Lấy người tạo ngẫu nhiên từ bảng User
        bill.setCreator(getRandomUser());

        // Lưu hóa đơn vào cơ sở dữ liệu
        return billRepository.save(bill);
    }

    // Tạo mã hóa đơn theo định dạng BIxxx
    private String generateBillCode() {
        Long count = billRepository.count();
        Long nextNumber = count + 1;
        return "BI" + String.format("%03d", nextNumber);
    }

    // Lấy ngẫu nhiên một User từ bảng User
    private User getRandomUser() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users available");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(users.size());
        return users.get(randomIndex);
    }
}
