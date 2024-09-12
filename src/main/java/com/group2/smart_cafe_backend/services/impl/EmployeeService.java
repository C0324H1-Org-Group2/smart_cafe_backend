package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.dtos.EmployeeDTO;
import com.group2.smart_cafe_backend.dtos.UserDTO;
import com.group2.smart_cafe_backend.models.Employee;
import com.group2.smart_cafe_backend.models.User;
import com.group2.smart_cafe_backend.repositories.IEmployeeRepository;
import com.group2.smart_cafe_backend.repositories.IUserRepository;
import com.group2.smart_cafe_backend.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerEmployee(EmployeeDTO employeeDTO, UserDTO userDTO) {
        Employee employee = new Employee();
        employee.setFullName(employeeDTO.getFullName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAddress(employeeDTO.getAddress());
        employee.setTel(employeeDTO.getTel());
        employee.setBirthday(employeeDTO.getBirthday());
        employee.setGender(employeeDTO.getGender());
        employee.setNote(employeeDTO.getNote());
        employee.setImageUrl(employeeDTO.getImageUrl());
        employee.setSalary(new BigDecimal("0.00")); // Cung cấp giá trị mặc định

        // Lưu Employee vào cơ sở dữ liệu
        employee = employeeRepository.save(employee);

        // Tạo đối tượng User từ UserDTO
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Mã hóa mật khẩu
        user.setEmployee(employee); // Liên kết với Employee
        user.setVerificationToken(generateVerificationToken()); // Tạo mã xác thực nếu cần
        user.setPasswordExpiryDate(calculatePasswordExpiryDate()); // Tính ngày hết hạn mật khẩu

        // Lưu User vào cơ sở dữ liệu
        userRepository.save(user);
    }


    private String generateVerificationToken() {
        // Tạo mã xác thực (ví dụ: UUID hoặc bất kỳ phương thức nào khác)
        return java.util.UUID.randomUUID().toString();
    }

    private Date calculatePasswordExpiryDate() {
        // Tính ngày hết hạn mật khẩu (ví dụ: 90 ngày kể từ hôm nay)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 90);
        return calendar.getTime();
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            // Chuyển đổi từ Employee sang EmployeeDTO

            employeeDTO.setFullName(employee.getFullName());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setAddress(employee.getAddress());
            employeeDTO.setTel(employee.getTel());
            employeeDTO.setBirthday(employee.getBirthday());
            employeeDTO.setGender(employee.getGender());
            employeeDTO.setNote(employee.getNote());
            employeeDTO.setImageUrl(employee.getImageUrl());
            return employeeDTO;
        }
        return null;
    }


    @Override
    public void updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        existingEmployee.setFullName(employeeDTO.getFullName());
        existingEmployee.setEmail(employeeDTO.getEmail());
        existingEmployee.setAddress(employeeDTO.getAddress());
        existingEmployee.setTel(employeeDTO.getTel());
        existingEmployee.setBirthday(employeeDTO.getBirthday());
        existingEmployee.setGender(employeeDTO.getGender());
        existingEmployee.setNote(employeeDTO.getNote());
        existingEmployee.setImageUrl(employeeDTO.getImageUrl());

        employeeRepository.save(existingEmployee);
    }



    @Override
    public void deleteEmployee(Long employeeId) {
        // Tìm kiếm Employee
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        // Tìm kiếm User liên kết với Employee
        User user = userRepository.findByEmployee(employee);
        if (user != null) {
            // Xóa User
            userRepository.delete(user);
        }

        // Xóa Employee
        employeeRepository.delete(employee);
    }


}
