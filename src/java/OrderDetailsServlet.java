

import com.artgallery.util.DBConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

@WebServlet("/OrderDetailsServlet")
public class OrderDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Order & Payment Details</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0; }");
        out.println("table { width: 90%; margin: 20px auto; border-collapse: collapse; background: white; }");
        out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
        out.println("th { background-color: #007bff; color: white; }");
        out.println("h2 { text-align: center; margin: 20px; color: #333; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<h2>All Order & Payment Details</h2>");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM orderdetailsview");
             ResultSet rs = pstmt.executeQuery()) {

            out.println("<table>");
            out.println("<tr><th>Order ID</th><th>Artwork Name</th><th>Artist Name</th>"
                    + "<th>Customer Name</th><th>Customer Email</th><th>Delivery Address</th>"
                    + "<th>Payment Amount</th><th>Payment Date</th></tr>");

            boolean hasOrders = false;
            while (rs.next()) {
                hasOrders = true;
                out.println("<tr>");
                out.println("<td>" + rs.getInt("OrderID") + "</td>");
                out.println("<td>" + rs.getString("artwork_name") + "</td>");
                out.println("<td>" + rs.getString("artist_name") + "</td>");
                out.println("<td>" + rs.getString("customer_name") + "</td>");
                out.println("<td>" + rs.getString("customer_email") + "</td>");
                out.println("<td>" + rs.getString("delivery_address") + "</td>");
                out.println("<td>" + rs.getDouble("PaymentAmount") + "</td>");
                out.println("<td>" + rs.getTimestamp("payment_date") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

            if (!hasOrders) {
                out.println("<h3 style='text-align:center;'>No orders found.</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 style='text-align:center;'>Error retrieving order details. Please try again later.</h3>");
        }

        out.println("</body></html>");
    }
}
