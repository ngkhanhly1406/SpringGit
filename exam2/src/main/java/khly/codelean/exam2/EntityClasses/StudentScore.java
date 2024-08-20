package khly.codelean.exam2.EntityClasses;

import jakarta.persistence.*;

@Entity
public class StudentScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentScoreId;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Subject subject;

    private double score1;
    private double score2;

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setSubject(Subject subject) {
    }

    public void setScore1(double score1) {
    }

    public void setScore2(double score2) {

    }

    // Getters and Setters
}