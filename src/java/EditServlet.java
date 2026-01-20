import com.artgallery.util.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // ================= GET : SHOW EDIT FORM =================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String orderId = request.getParameter("id");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (orderId == null || orderId.isEmpty()) {
            out.println("<h3>Invalid Order ID</h3>");
            return;
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM orders2 WHERE id=?")) {

            ps.setInt(1, Integer.parseInt(orderId));
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                out.println("<h3>No order found</h3>");
                return;
            }

            /* ================= HTML + CSS ================= */
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Edit Order</title>");

            out.println("<style>");
            out.println("body{margin:0;font-family:'Segoe UI',sans-serif;");
            out.println("background:url('https://static.vecteezy.com/system/resources/thumbnails/069/922/846/small/blurred-cinematic-view-of-a-dimly-lit-art-gallery-with-abstract-sculptures-and-paintings-photo.jpeg') no-repeat center/cover fixed;}");

            out.println(".overlay{min-height:100vh;background:rgba(0,0,0,0.6);");
            out.println("display:flex;justify-content:center;align-items:center;padding:40px;}");

            out.println(".card{width:100%;max-width:720px;background:#fff;");
            out.println("padding:30px 35px;border-radius:12px;");
            out.println("box-shadow:0 20px 40px rgba(0,0,0,.4);}");            

            out.println("h2{text-align:center;margin-bottom:25px;color:#222;}");

            out.println("label{font-weight:600;font-size:14px;color:#333;margin-bottom:6px;display:block;}");

            out.println("input,textarea{width:100%;padding:12px;");
            out.println("border:1px solid #ccc;border-radius:8px;");
            out.println("font-size:14px;background:#fafafa;margin-bottom:15px;}");

            out.println("textarea{resize:none;height:100px;}");

            out.println("button{width:100%;padding:13px;");
            out.println("background:linear-gradient(135deg,#007bff,#0056b3);");
            out.println("color:#fff;font-size:16px;font-weight:bold;");
            out.println("border:none;border-radius:8px;cursor:pointer;}");

            out.println("button:hover{opacity:.9;}");
            out.println("</style>");

            out.println("</head><body>");
            out.println("<div class='overlay'>");
            out.println("<div class='card'>");

            /* ================= FORM ================= */
            out.println("<h2>Edit Order</h2>");
            out.println("<form method='post' action='EditServlet'>");

            out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");

            out.println("<label>Artwork Name</label>");
            out.println("<input type='text' name='artwork_name' value='" + rs.getString("artwork_name") + "' required>");

            out.println("<label>Artist Name</label>");
            out.println("<input type='text' name='artist_name' value='" + rs.getString("artist_name") + "' required>");

            out.println("<label>Customer Name</label>");
            out.println("<input type='text' name='customer_name' value='" + rs.getString("customer_name") + "' required>");

            out.println("<label>Customer Email</label>");
            out.println("<input type='email' name='customer_email' value='" + rs.getString("customer_email") + "' required>");

            out.println("<label>Delivery Address</label>");
            out.println("<textarea name='delivery_address' required>" + rs.getString("delivery_address") + "</textarea>");

            out.println("<label>Contact Number</label>");
            out.println("<input type='text' name='contact_number' value='" + rs.getString("contact_number") + "' required>");

            out.println("<label>Order Notes</label>");
            out.println("<textarea name='order_notes'>" + rs.getString("order_notes") + "</textarea>");

            out.println("<button type='submit'>Update Order</button>");
            out.println("</form>");

            out.println("</div></div></body></html>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error loading order</h3>");
        }
    }

    // ================= POST : UPDATE ORDER =================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE orders2 SET artwork_name=?,artist_name=?,customer_name=?,customer_email=?,delivery_address=?,contact_number=?,order_notes=? WHERE id=?")) {

            ps.setString(1, request.getParameter("artwork_name"));
            ps.setString(2, request.getParameter("artist_name"));
            ps.setString(3, request.getParameter("customer_name"));
            ps.setString(4, request.getParameter("customer_email"));
            ps.setString(5, request.getParameter("delivery_address"));
            ps.setString(6, request.getParameter("contact_number"));
            ps.setString(7, request.getParameter("order_notes"));
            ps.setInt(8, Integer.parseInt(request.getParameter("id")));

            ps.executeUpdate();

            // redirect back to search page
            response.sendRedirect("fetchingServlet");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Update Failed</h3>");
        }
    }
}
