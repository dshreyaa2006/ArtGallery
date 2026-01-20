
import com.artgallery.util.DBConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String orderId = request.getParameter("id");

        if (orderId != null && !orderId.isEmpty()) {
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement pstmt = con.prepareStatement("DELETE FROM orders2 WHERE id = ?")) {

                pstmt.setInt(1, Integer.parseInt(orderId));
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    response.sendRedirect("fetchingServlet");
                } else {
                    response.getWriter().println("<h3>Error deleting the order. Order ID may not exist.</h3>");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("<h3>Error deleting the order. Please try again later.</h3>");
            }
        } else {
            response.getWriter().println("<h3>Invalid order ID.</h3>");
        }
    }
}
