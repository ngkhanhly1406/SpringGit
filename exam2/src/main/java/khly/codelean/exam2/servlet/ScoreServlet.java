package khly.codelean.exam2.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import khly.codelean.exam2.EntityClasses.Student;
import khly.codelean.exam2.EntityClasses.StudentScore;
import khly.codelean.exam2.EntityClasses.Subject;
import khly.codelean.exam2.ServiceClasses.StudentScoreService;

import java.io.IOException;

@WebServlet("/addScore")
public class ScoreServlet extends HttpServlet {
    @Inject
    private StudentScoreService studentScoreService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        int subjectId = Integer.parseInt(req.getParameter("subjectId"));
        double score1 = Double.parseDouble(req.getParameter("score1"));
        double score2 = Double.parseDouble(req.getParameter("score2"));

        StudentScore studentScore = new StudentScore();
        studentScore.setStudent(new Student());  // Assuming you have a constructor for Student
        studentScore.setSubject(new Subject());  // Assuming you have a constructor for Subject
        studentScore.setScore1(score1);
        studentScore.setScore2(score2);

        studentScoreService.addStudentScore(studentScore);

        resp.sendRedirect("scoreList.jsp");
    }
}
