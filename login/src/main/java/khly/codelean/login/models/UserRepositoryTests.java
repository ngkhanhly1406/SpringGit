package khly.codelean.login.models;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;



@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository repo;

    // Đây là phương thức kiểm tra để lưu trữ đối tượng Người dùng vào cơ sở dữ liệu
    @Test
    public void testCreateUser() {
        // Tạo đối tượng User
        User user = new User();
        user.setEmail("ravikumar@gmail.com");
        user.setPassword("ravi2020");
        user.setFirstName("Ravi");
        user.setLastName("Kumar");

        // Lưu người dùng vào kho lưu trữ
        User savedUser = repo.save(user);

        // Kiểm tra xem người dùng đã được lưu vào cơ sở dữ liệu hay chưa
        User existUser = entityManager.find(User.class, savedUser.getId());

        // Sử dụng AssertJ để xác nhận rằng email của người dùng đã được lưu đúng
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }
}
