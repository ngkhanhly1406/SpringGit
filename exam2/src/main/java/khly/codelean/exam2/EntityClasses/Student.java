package khly.codelean.exam2.EntityClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private String studentCode;
    private String fullName;
    private String address;

    public void setStudentCode(String studentCode) {
    }

    public void setFullName(String fullName) {
    }

    public void setAddress(String address) {

    }

    // Getters and Setters
}