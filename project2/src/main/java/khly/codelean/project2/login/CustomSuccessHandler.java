//package khly.codelean.project2.login;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class CustomSuccessHandler implements AuthenticationSuccessHandler {
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//        // Kiểm tra các vai trò của người dùng sau khi đăng nhập thành công
//        boolean isAdmin = authentication.getAuthorities().stream()
//                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
//
//        if (isAdmin) {
//            // Nếu người dùng có vai trò ADMIN, chuyển hướng đến trang admin
//            response.sendRedirect("/admin/index");
//        } else {
//            // Nếu không phải ADMIN, chuyển hướng đến trang chính
//            response.sendRedirect("/");
//        }
//    }
//}
