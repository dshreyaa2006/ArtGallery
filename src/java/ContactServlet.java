import com.artgallery.util.DBConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String msg = (String) session.getAttribute("message");
        session.removeAttribute("message");

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Contact Us | Art Gallery</title>");
        out.println("<link href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css' rel='stylesheet'>");

        // CSS
        out.println("<style>");
        out.println("body{margin:0;font-family:'Poppins',sans-serif;");
        out.println("background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)),");
        out.println("url('https://static.vecteezy.com/system/resources/thumbnails/069/922/846/small/blurred-cinematic-view-of-a-dimly-lit-art-gallery-with-abstract-sculptures-and-paintings-photo.jpeg') no-repeat center center fixed;");
        out.println("background-size:cover;height:100vh;display:flex;justify-content:center;align-items:center;}");
        out.println(".navbar{background-color:rgba(0,0,0,0.85);}");
        out.println(".navbar-brand{font-weight:700;color:#ffcc00 !important;}");
        out.println(".navbar-nav .nav-link{color:white !important;margin-left:15px;}");
        out.println(".navbar-nav .nav-link:hover{color:#ffcc00 !important;}");
        out.println(".contact-box{width:600px; max-width:95%; background:rgba(255,255,255,0.95); padding:50px; border-radius:25px;");
        out.println("box-shadow:0 25px 60px rgba(0,0,0,0.5); transition:0.3s;}");
        out.println(".contact-box:hover{transform: translateY(-5px); box-shadow:0 35px 80px rgba(0,0,0,0.6);}");
        out.println(".contact-box h2{font-weight:700; margin-bottom:30px; color:#222; text-align:center;}");
        out.println(".form-control{height:50px; border-radius:30px; padding-left:20px; font-size:16px; margin-bottom:15px;}");
        out.println("textarea.form-control{height:120px; padding-top:10px;}");
        out.println(".btn-contact{background: linear-gradient(90deg,#ffcc00,#ff9900); color:#000; font-weight:700; border-radius:30px; height:50px; font-size:16px; transition:.3s; width:100%;}");
        out.println(".btn-contact:hover{background: linear-gradient(90deg,#ff9900,#ffcc00);}");

        // 🔹 TOP MESSAGE FIX (ADDED ONLY)
        out.println(".top-alert{position:fixed; top:80px; left:50%; transform:translateX(-50%);");
        out.println("z-index:9999; width:90%; max-width:500px;}");

        out.println("@media(max-width:700px){.contact-box{width:95%;padding:35px;}}");
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
        out.println("<li class='nav-item'><a class='nav-link' href='ArtsServlet'>Arts</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='EventServlet'>Events</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='ContactServlet'>Contact</a></li>");
        out.println("<li class='nav-item'><a class='nav-link' href='AboutServlet'>About</a></li>");
        out.println("</ul></div></div></nav>");

        // 🔹 TOP MESSAGE DISPLAY
        if (msg != null) {
            out.println("<div class='alert alert-success text-center top-alert'>" + msg + "</div>");
            out.println("<script>");
            out.println("setTimeout(function(){document.querySelector('.top-alert').style.display='none';},3000);");
            out.println("</script>");
        }

        // Contact form
        out.println("<div class='contact-box'>");
        out.println("<h2>Contact Us</h2>");
        out.println("<form method='POST' action='ContactServlet'>");
        out.println("<input type='text' name='name' class='form-control' placeholder='Full Name' required>");
        out.println("<input type='email' name='email' class='form-control' placeholder='Email Address' required>");
        out.println("<input type='text' name='phone' class='form-control' placeholder='Phone Number' required>");
        out.println("<textarea name='message' class='form-control' placeholder='Message' required></textarea>");
        out.println("<button type='submit' class='btn btn-contact'>Submit</button>");
        out.println("</form></div>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO contact_form (name, email, phone, message) VALUES (?, ?, ?, ?)")) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, message);
            ps.executeUpdate();

            session.setAttribute("message", "Form submitted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "Database Error occurred!");
        }

        response.sendRedirect("ContactServlet");
    }
}
