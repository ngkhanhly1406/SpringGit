package khly.codelean.project2.controller;

import khly.codelean.project2.dao.UserAccountRepository;
import khly.codelean.project2.entity.UserAccount;

import khly.codelean.project2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class UserAccountController {


    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Cần PasswordEncoder để mã hóa mật khẩu

    // Phương thức để lấy UserAccount theo ID
    public UserAccount getUserAccountById(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản người dùng với ID: " + id));
    }

    // Phương thức để cập nhật thông tin tài khoản
    public void updateAccount(Long id, UserAccount updatedAccount) {
        UserAccount existingAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản người dùng với ID: " + id));

        existingAccount.setFirstName(updatedAccount.getFirstName());
        existingAccount.setLastName(updatedAccount.getLastName());
        existingAccount.setEmail(updatedAccount.getEmail());

        userAccountRepository.save(existingAccount);
    }

    // Phương thức để cập nhật mật khẩu
    public void updatePassword(Long id, String currentPassword, String newPassword) {
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản người dùng với ID: " + id));

        // Kiểm tra mật khẩu hiện tại
        if (!passwordEncoder.matches(currentPassword, userAccount.getPassword())) {
            throw new RuntimeException("Mật khẩu hiện tại không chính xác");
        }

        // Mã hóa và lưu mật khẩu mới
        userAccount.setPassword(passwordEncoder.encode(newPassword));
        userAccountRepository.save(userAccount);
    }
}