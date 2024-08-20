package khly.codelean.exam2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import khly.codelean.exam2.EntityClasses.Student;
import khly.codelean.exam2.ServiceClasses.StudentService;

import java.io.IOException;

@WebServlet("/addStudent")
public class StudentServlet extends HttpServlet {
    @Inject
    private StudentService studentService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentCode = req.getParameter("studentCode");
        String fullName = req.getParameter("fullName");
        String address = req.getParameter("address");

        Student student = new Student();
        student.setStudentCode(studentCode);
        student.setFullName(fullName);
        student.setAddress(address);

        studentService.addStudent(student);

        resp.sendRedirect("studentList.jsp");
    }
}
