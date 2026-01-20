

import com.artgallery.util.DBConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String msg = (String) request.getSession().getAttribute("message");
        request.getSession().removeAttribute("message");

        out.println("<!DOCTYPE html><html lang='en'><head>");
        out.println("<meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Login | Art Gallery</title>");
        out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap' rel='stylesheet'>");
        out.println("<style>");
        out.println("body{margin:0;font-family:'Poppins',sans-serif;");
        out.println("background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url('https://static.vecteezy.com/system/resources/thumbnails/069/922/846/small/blurred-cinematic-view-of-a-dimly-lit-art-gallery-with-abstract-sculptures-and-paintings-photo.jpeg') center/cover no-repeat;");
        out.println("height:100vh; display:flex; justify-content:center; align-items:center;}");
        out.println(".navbar{background-color:rgba(0,0,0,0.85);}");
        out.println(".navbar-brand{font-weight:700; color:#ffcc00 !important;}");
        out.println(".navbar-nav .nav-link{color:white !important; margin-left:15px;}");
        out.println(".navbar-nav .nav-link:hover{color:#ffcc00 !important;}");
        out.println(".login-box{width:480px; max-width:95%; background:rgba(255,255,255,0.95); padding:50px; border-radius:25px;");
        out.println("box-shadow:0 25px 60px rgba(0,0,0,0.5); transition:0.3s;}");
        out.println(".login-box:hover{transform: translateY(-5px); box-shadow:0 35px 80px rgba(0,0,0,0.6);}");
        out.println(".login-box h3{font-weight:700; margin-bottom:30px; color:#222; text-align:center;}");
        out.println(".form-control{height:50px; border-radius:30px; padding-left:20px; font-size:16px;}"); 
        out.println(".btn-login{background: linear-gradient(90deg,#ffcc00,#ff9900); color:#000; font-weight:700; border-radius:30px; height:50px; font-size:16px; transition:.3s;}");
        out.println(".btn-login:hover{background: linear-gradient(90deg,#ff9900,#ffcc00); color:#111;}");
        out.println(".register-link{margin-top:25px; text-align:center;}");
        out.println(".register-link a{color:#ffcc00; font-weight:600; text-decoration:none;}");
        out.println(".register-link a:hover{text-decoration:underline;}");
        out.println("@media(max-width:500px){.login-box{width:90%;padding:35px;}}");
        out.println("</style></head><body>");

        // Navbar
        out.println("<nav class='navbar navbar-expand-lg navbar-dark fixed-top'><div class='container'>");
        out.println("<a class='navbar-brand' href='index.jsp'>Art Gallery</a>");
        out.println("<div class='collapse navbar-collapse'><ul class='navbar-nav ml-auto'>");
        out.println("<li class='nav-item'><a class='nav-link' href='index.html'>Home</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='LoginServlet'>Login</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='ArtsServlet'>Arts</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='EventServlet'>Events</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='ContactServlet'>Contact</a></li>");
                out.println("<li class='nav-item'><a class='nav-link' href='AboutServlet'>About</a></li>");

        out.println("</ul></div></div></nav>");

        // Login form
        out.println("<div class='login-box'>");
        if(msg != null){
            out.println("<div class='alert alert-warning text-center'>" + msg + "</div>");
        }
        out.println("<h3>Login</h3>");
        out.println("<form method='POST' action='LoginServlet' autocomplete='off'>"); // <- autocomplete off
        out.println("<input class='form-control mb-3' type='text' name='username' placeholder='Username' value='' autocomplete='off' autocorrect='off' autocapitalize='off'>");
        out.println("<input class='form-control mb-4' type='password' name='password' placeholder='Password' value='' autocomplete='new-password'>");
        out.println("<button class='btn btn-login btn-block'>Login</button>");
        out.println("</form>");
        out.println("<div class='register-link'>New user? <a href='LoginRegisterServlet'>Register</a></div>");
        out.println("</div></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM login WHERE username=? AND password=?")){

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                response.sendRedirect("ArtsServlet");
            } else {
                request.getSession().setAttribute("message","Invalid login. Please register first.");
                response.sendRedirect("LoginServlet");
            }

        } catch(Exception e){
            throw new ServletException(e);
        }
    }
}
