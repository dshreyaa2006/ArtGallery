

import com.artgallery.util.DBConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

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
        out.println("<title>Order Artwork | Art Gallery</title>");
        out.println("<link href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css' rel='stylesheet'>");

        // CSS
        out.println("<style>");
        out.println("body{margin:0;font-family:'Poppins',sans-serif;");
        out.println("background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)),");
        out.println("url('https://static.vecteezy.com/system/resources/thumbnails/069/922/846/small/blurred-cinematic-view-of-a-dimly-lit-art-gallery-with-abstract-sculptures-and-paintings-photo.jpeg') center/cover no-repeat fixed;");
        out.println("background-size:cover; display:flex; justify-content:center; align-items:center; min-height:100vh;}");

        out.println(".navbar{background-color:rgba(0,0,0,0.85);}"); 
        out.println(".navbar-brand{font-weight:700;color:#ffcc00 !important;}"); 
        out.println(".navbar-nav .nav-link{color:white !important;margin-left:15px;}"); 
        out.println(".navbar-nav .nav-link:hover{color:#ffcc00 !important;}"); 

        // form container
        out.println(".contact-box{width:700px; max-width:95%; background:rgba(255,255,255,0.95); padding:50px 40px; border-radius:25px;");
        out.println("box-shadow:0 25px 60px rgba(0,0,0,0.5); transition:0.3s;}"); 
        out.println(".contact-box:hover{transform: translateY(-5px); box-shadow:0 35px 80px rgba(0,0,0,0.6);}"); 
        out.println(".contact-box h2{font-weight:700; margin-bottom:30px; color:#222; text-align:center;}"); 

        out.println(".form-control{height:50px; border-radius:30px; padding-left:20px; font-size:16px; margin-bottom:15px;}"); 
        out.println("textarea.form-control{height:120px; padding-top:10px;}"); 
        out.println(".btn-contact{background: linear-gradient(90deg,#ffcc00,#ff9900); color:#000; font-weight:700; border-radius:30px; height:50px; font-size:16px; transition:.3s; width:100%;}"); 
        out.println(".btn-contact:hover{background: linear-gradient(90deg,#ff9900,#ffcc00);}"); 
        out.println(".alert-box{background-color:#d4edda; color:#155724; padding:12px; border-radius:6px; margin-bottom:20px; text-align:center;}"); 
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

        // Show success/error message if exists
        if (msg != null) {
            out.println("<div class='alert-box'>" + msg + "</div>");
        }

        // Order form
        out.println("<div class='contact-box'>");
        out.println("<h2>Order Artwork</h2>");
        out.println("<form method='POST' action='OrderServlet'>");
        out.println("<input type='text' class='form-control' name='artworkName' placeholder='Artwork Name' required>");
        out.println("<input type='text' class='form-control' name='artistName' placeholder='Artist Name' required>");
        out.println("<input type='text' class='form-control' name='customerName' placeholder='Your Name' required>");
        out.println("<input type='email' class='form-control' name='customerEmail' placeholder='Your Email' required>");
        out.println("<textarea class='form-control' name='deliveryAddress' placeholder='Delivery Address' required></textarea>");
        out.println("<input type='tel' class='form-control' name='contactNumber' placeholder='Contact Number' required>");
        out.println("<textarea class='form-control' name='orderNotes' placeholder='Order Notes'></textarea>");
        out.println("<button type='submit' class='btn btn-contact'>Place Order</button>");
        out.println("</form></div>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String artworkName = request.getParameter("artworkName");
        String artistName = request.getParameter("artistName");
        String customerName = request.getParameter("customerName");
        String customerEmail = request.getParameter("customerEmail");
        String deliveryAddress = request.getParameter("deliveryAddress");
        String contactNumber = request.getParameter("contactNumber");
        String orderNotes = request.getParameter("orderNotes");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO orders2 (artwork_name, artist_name, customer_name, customer_email, delivery_address, contact_number, order_notes) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            pstmt.setString(1, artworkName);
            pstmt.setString(2, artistName);
            pstmt.setString(3, customerName);
            pstmt.setString(4, customerEmail);
            pstmt.setString(5, deliveryAddress);
            pstmt.setString(6, contactNumber);
            pstmt.setString(7, orderNotes);
            pstmt.executeUpdate();

            session.setAttribute("message", "Order placed successfully!");
            response.sendRedirect("fetchingServlet");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "Database Error: " + e.getMessage());
            response.sendRedirect("OrderServlet");
        }
    }
}
