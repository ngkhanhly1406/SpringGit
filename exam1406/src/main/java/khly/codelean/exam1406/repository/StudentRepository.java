package khly.codelean.exam1406.repository;



import khly.codelean.exam1406.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}