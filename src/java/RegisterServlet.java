

import com.artgallery.util.DBConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String msg = (String) request.getSession().getAttribute("message");
        request.getSession().removeAttribute("message");

        out.println("<!DOCTYPE html><html lang='en'><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Register | Art Gallery</title>");
        out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap' rel='stylesheet'>");

        // CSS
        out.println("<style>");
        out.println("body{margin:0;font-family:'Poppins',sans-serif;");
        out.println("background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url('https://static.vecteezy.com/system/resources/thumbnails/069/922/846/small/blurred-cinematic-view-of-a-dimly-lit-art-gallery-with-abstract-sculptures-and-paintings-photo.jpeg') no-repeat center center fixed;");
        out.println("background-size:cover;height:100vh;display:flex;justify-content:center;align-items:center;}");
        out.println(".navbar{background-color:rgba(0,0,0,0.85);}");
        out.println(".navbar-brand{font-weight:700; color:#ffcc00 !important;}");
        out.println(".navbar-nav .nav-link{color:white !important; margin-left:15px;}");
        out.println(".navbar-nav .nav-link:hover{color:#ffcc00 !important;}");
        out.println(".register-box{width:600px; max-width:95%; background:rgba(255,255,255,0.95); padding:50px; border-radius:25px;");
        out.println("box-shadow:0 25px 60px rgba(0,0,0,0.5); transition:0.3s;}");
        out.println(".register-box:hover{transform: translateY(-5px); box-shadow:0 35px 80px rgba(0,0,0,0.6);}");
        out.println(".register-box h3{font-weight:700; margin-bottom:30px; color:#222; text-align:center;}");
        out.println(".form-control{height:50px; border-radius:30px; padding-left:20px; font-size:16px; margin-bottom:15px;}");
        out.println(".btn-register{background: linear-gradient(90deg,#ffcc00,#ff9900); color:#000; font-weight:700; border-radius:30px; height:50px; font-size:16px; transition:.3s; width:100%;}");
        out.println(".btn-register:hover{background: linear-gradient(90deg,#ff9900,#ffcc00);}");
        out.println("@media(max-width:700px){.register-box{width:95%;padding:35px;}}");
        out.println("</style></head><body>");

        // Navbar
        out.println("<nav class='navbar navbar-expand-lg navbar-dark fixed-top'>");
        out.println("<div class='container'>");
        out.println("<a class='navbar-brand' href='index.html'>Art Gallery</a>");
        out.println("<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarNav'>");
        out.println("<span class='navbar-toggler-icon'></span></button>");
        out.println("<div class='collapse navbar-collapse' id='navbarNav'>");
        out.println("<ul class='navbar-nav ml-auto'>");
        out.println("<li class='nav-item'><a class='nav-link' href='index.html'>Home</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='LoginServlet'>Login</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='EventServlet'>Events</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='ContactServlet'>Contact</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='AboutServlet'>About</a></li>");
        out.println("</ul></div></div></nav>");

        // Register box
        out.println("<div class='register-box'>");

        if(msg != null){
            out.println("<div class='alert alert-warning text-center'>" + msg + "</div>");
        }

        out.println("<h3>Event Registration</h3>");
        out.println("<form method='POST' action='RegisterServlet' autocomplete='off'>");
        out.println("<input class='form-control' name='name' placeholder='Full Name' value='' autocomplete='off' autocorrect='off' autocapitalize='off' required>");
        out.println("<input type='email' class='form-control' name='email' placeholder='Email Address' value='' autocomplete='off' required>");
        out.println("<input class='form-control' name='phone' placeholder='Phone Number' value='' autocomplete='off' required>");
        out.println("<input type='password' class='form-control' name='password' placeholder='Password' value='' autocomplete='new-password' required>");
        out.println("<select class='form-control' name='event' required>");
        out.println("<option value=''>Select Event</option>");
        out.println("<option>Art Expo 2025</option>");
        out.println("<option>Sculpture Workshop</option>");
        out.println("<option>Digital Art Course</option>");
        out.println("<option>Photography Exhibition</option>");
        out.println("</select>");
        out.println("<button class='btn btn-register btn-block'>Register</button>");
        out.println("</form>");

        out.println("</div>"); // register-box
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String event = request.getParameter("event");

        try (Connection conn = DBConnection.getConnection()) {

            PreparedStatement check = conn.prepareStatement(
                    "SELECT email FROM register WHERE email=?");
            check.setString(1, email);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                request.getSession().setAttribute("message", "Email already registered!");
                response.sendRedirect("RegisterServlet");
            } else {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO register(name,email,phone,password,event) VALUES (?,?,?,?,?)");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, phone);
                ps.setString(4, password);
                ps.setString(5, event);
                ps.executeUpdate();

                request.getSession().setAttribute("message", "Registration successful. Please login.");
                response.sendRedirect("ContactServlet");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Server error occurred!");
            response.sendRedirect("RegisterServlet");
            
        }
    }
}
