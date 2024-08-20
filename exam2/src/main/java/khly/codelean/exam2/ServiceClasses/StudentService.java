package khly.codelean.exam2.ServiceClasses;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import khly.codelean.exam2.EntityClasses.Student;

import java.util.List;


public class StudentService {
    @PersistenceContext
    private EntityManager em;

    public void addStudent(Student student) {
        em.persist(student);
    }

    public List<Student> getAllStudents() {
        return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }
}