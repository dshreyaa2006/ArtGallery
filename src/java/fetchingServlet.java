import com.artgallery.util.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fetchingServlet")
public class fetchingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String customerName = request.getParameter("customerName");

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Search Orders | Art Gallery</title>");

        /* ================= CSS ================= */
        out.println("<style>");
        out.println("body{margin:0;font-family:Poppins,sans-serif;");
        out.println("background:linear-gradient(rgba(0,0,0,.6),rgba(0,0,0,.6)),");
        out.println("url('https://static.vecteezy.com/system/resources/thumbnails/069/922/846/small/blurred-cinematic-view-of-a-dimly-lit-art-gallery-with-abstract-sculptures-and-paintings-photo.jpeg') center/cover no-repeat fixed;");
        out.println("color:#fff;}");

        out.println(".navbar{background:#000;padding:15px 40px;display:flex;justify-content:space-between;align-items:center;}");
        out.println(".navbar a{color:white;text-decoration:none;margin-left:20px;font-weight:500;}");
        out.println(".navbar a:hover{color:#ffcc00;}");

        out.println(".box{background:#fff;color:#000;width:75%;margin:40px auto;padding:30px;border-radius:20px;}");
        out.println("h2{text-align:center;margin-bottom:20px;}");

        out.println("input{width:100%;padding:15px;border-radius:30px;border:1px solid #ccc;font-size:16px;}");
        out.println("button{width:100%;margin-top:15px;padding:14px;border-radius:30px;border:none;");
        out.println("background:#ffb300;font-size:18px;font-weight:bold;cursor:pointer;}");
        out.println("button:hover{background:#ff9900;}");

        out.println("table{width:100%;border-collapse:collapse;margin-top:25px;font-size:15px;}");
        out.println("th,td{border:1px solid #ddd;padding:10px;text-align:left;}");
        out.println("th{background:#007bff;color:white;}");
        out.println("td{color:#000;}");

        out.println(".btn{padding:6px 10px;border-radius:5px;color:white;text-decoration:none;font-size:14px;}");
        out.println(".update{background:orange;}");
        out.println(".delete{background:red;}");
        out.println(".confirm{background:green;}");

        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        /* ================= NAVBAR ================= */
        out.println("<div class='navbar'>");
        out.println("<div style='color:#ffcc00;font-weight:bold;'>Art Gallery</div>");
        out.println("<div>");
        out.println("<a href='index.html'>Home</a>");
        out.println("<a href='LoginServlet'>Login</a>");
        out.println("<a href='ArtsServlet'>Arts</a>");
        out.println("<a href='EventServlet'>Events</a>");
        out.println("<a href='ContactServlet'>Contact</a>");
        out.println("<a href='AboutServlet'>About</a>");
        out.println("</div>");
        out.println("</div>");

        /* ================= SEARCH BOX ================= */
        out.println("<div class='box'>");
        out.println("<h2>Search Your Orders</h2>");
        out.println("<form method='get' action='fetchingServlet'>");
        out.println("<input type='text' name='customerName' placeholder='Enter Customer Name' value='" +
                (customerName != null ? customerName : "") + "' required>");
        out.println("<button type='submit'>Search</button>");
        out.println("</form>");
        out.println("</div>");

        /* ================= FETCH DATA ================= */
        if (customerName != null && !customerName.trim().isEmpty()) {

            out.println("<div class='box'>");
            out.println("<h2>Orders for: " + customerName + "</h2>");

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(
                         "SELECT * FROM orders2 WHERE LOWER(customer_name)=LOWER(?) ORDER BY id DESC")) {

                ps.setString(1, customerName);
                ResultSet rs = ps.executeQuery();

                boolean found = false;

                out.println("<div style='overflow-x:auto;'>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>Artwork</th><th>Artist</th><th>Name</th><th>Email</th>");
                out.println("<th>Address</th><th>Contact</th><th>Notes</th><th>Actions</th>");
                out.println("</tr>");

                while (rs.next()) {
                    found = true;
                    out.println("<tr>");
                    out.println("<td>" + rs.getString("artwork_name") + "</td>");
                    out.println("<td>" + rs.getString("artist_name") + "</td>");
                    out.println("<td>" + rs.getString("customer_name") + "</td>");
                    out.println("<td>" + rs.getString("customer_email") + "</td>");
                    out.println("<td>" + rs.getString("delivery_address") + "</td>");
                    out.println("<td>" + rs.getString("contact_number") + "</td>");
                    out.println("<td>" + rs.getString("order_notes") + "</td>");
                    out.println("<td>");
                    out.println("<a class='btn update' href='EditServlet?id=" + rs.getInt("id") + "'>Update</a><br><br>");
                    out.println("<a class='btn delete' href='DeleteServlet?id=" + rs.getInt("id") + "'>Delete</a><br><br>");
                    out.println("<a class='btn confirm' href='PaymentServlet?id=" + rs.getInt("id") + "'>Confirm</a>");
                    out.println("</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
                out.println("</div>");

                if (!found) {
                    out.println("<p style='text-align:center;'>No orders found.</p>");
                }

            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p style='color:red;text-align:center;'>Database Error</p>");
            }

            out.println("</div>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
