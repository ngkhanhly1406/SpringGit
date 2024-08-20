package khly.codelean.exam1406.repository;


import khly.codelean.exam1406.entity.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer> {
}