package khly.codelean.project2.login;

import khly.codelean.project2.dao.UserAccountRepository;
import khly.codelean.project2.entity.UserAccount;
import khly.codelean.project2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired

    private PasswordEncoder passwordEncoder; // Tiêm PasswordEncoder từ Spring


    private UserAccountRepository userAccountRepository;

    public UserAccount updateAccount(Long id, UserAccount updatedAccount) {
        UserAccount existingAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản người dùng"));




            existingAccount.setFirstName(updatedAccount.getFirstName());
        existingAccount.setLastName(updatedAccount.getLastName());
        existingAccount.setEmail(updatedAccount.getEmail());

        return userAccountRepository.save(existingAccount);
    }

    public void updatePassword(Long id, String currentPassword, String newPassword) {
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản người dùng"));

        if (!passwordEncoder.matches(currentPassword, userAccount.getPassword())) {
            throw new RuntimeException("Mật khẩu hiện tại không chính xác");
        }

        userAccount.setPassword(passwordEncoder.encode(newPassword));
        userAccountRepository.save(userAccount);
    }
}

