package khly.codelean.project2.controller;

import khly.codelean.project2.dao.RoleRepository;
import khly.codelean.project2.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import khly.codelean.project2.login.User;

import khly.codelean.project2.entity.Role;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class AdminAccountInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Kiểm tra nếu tài khoản admin đã tồn tại
        Optional<User> adminUserOpt = Optional.ofNullable(userRepository.findByEmail("admin@example.com"));

        if (adminUserOpt.isEmpty()) {
            // Nếu chưa tồn tại, tạo tài khoản admin
            User adminUser = new User();
            adminUser.setFirstName("Admin");
            adminUser.setLastName("User");
            adminUser.setEmail("admin@example.com");
            adminUser.setPassword(passwordEncoder.encode("admin123")); // Mã hóa mật khẩu

            // Tạo vai trò ADMIN nếu chưa có trong cơ sở dữ liệu
            Role adminRole = roleRepository.findByName("ADMIN");
            if (adminRole == null) {
                adminRole = new Role("ADMIN");
                roleRepository.save(adminRole);
            }

            // Gán vai trò ADMIN cho người dùng admin
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            adminUser.setRoles(roles);

            // Lưu người dùng admin vào cơ sở dữ liệu
            userRepository.save(adminUser);
            System.out.println("Admin account created: admin@example.com");
        } else {
            System.out.println("Admin account already exists.");
        }
    }
}
