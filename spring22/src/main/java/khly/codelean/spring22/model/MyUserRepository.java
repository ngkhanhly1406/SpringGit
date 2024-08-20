package khly.codelean.spring22.model;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MyUserRepository extends JpaRepository<khly.codelean.spring22.MyUser, Long> {
    Optional<khly.codelean.spring22.MyUser> findByUsername(String username);
}