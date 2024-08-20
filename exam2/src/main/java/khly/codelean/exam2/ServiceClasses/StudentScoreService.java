package khly.codelean.exam2.ServiceClasses;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import khly.codelean.exam2.EntityClasses.StudentScore;

import java.util.List;


public class StudentScoreService {
    @PersistenceContext
    private EntityManager em;

    public void addStudentScore(StudentScore studentScore) {
        em.persist(studentScore);
    }

    public List<StudentScore> getScoresForStudent(int studentId) {
        return em.createQuery("SELECT s FROM StudentScore s WHERE s.student.studentId = :studentId", StudentScore.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }
}