import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ArtsServlet")
public class ArtsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Arts | Online Art Gallery</title>");

        /* ================= CSS ================= */
        out.println("<style>");
        out.println("body{margin:0;font-family:Arial,Helvetica,sans-serif;");
        out.println("background:linear-gradient(rgba(0,0,0,.55),rgba(0,0,0,.55)),");
        out.println("url('https://static.vecteezy.com/system/resources/thumbnails/069/922/846/small/blurred-cinematic-view-of-a-dimly-lit-art-gallery-with-abstract-sculptures-and-paintings-photo.jpeg') center/cover fixed no-repeat;}");

        out.println(".navbar{background:#000;position:fixed;top:0;width:100%;z-index:1000;padding:15px 0;}");
        out.println(".navbar ul{list-style:none;margin:0;padding:0;display:flex;justify-content:center;}");
        out.println(".navbar ul li a{color:#fff;text-decoration:none;padding:10px 18px;font-size:18px;}");
        out.println(".navbar ul li a:hover{color:#ffcc00;}");

        out.println(".welcome{margin-top:100px;text-align:center;color:#fff;font-size:30px;font-weight:bold;}");

        out.println(".container{max-width:1300px;margin:40px auto;padding:20px;");
        out.println("display:grid;grid-template-columns:repeat(auto-fit,minmax(250px,1fr));gap:25px;}");

        out.println(".art-box{background:#fff;border-radius:15px;overflow:hidden;");
        out.println("box-shadow:0 10px 25px rgba(0,0,0,.4);transition:.3s;}");

        out.println(".art-box:hover{transform:translateY(-8px);}");
        out.println(".art-box img{width:100%;height:180px;object-fit:cover;}");
        out.println(".art-content{padding:20px;text-align:center;}");
        out.println(".art-content h3{margin:10px 0;font-size:20px;}");
        out.println(".art-content p{color:#555;font-size:15px;}");

        out.println(".btn-group{display:flex;gap:10px;margin-top:15px;}");
        out.println(".btn{flex:1;padding:10px;border:none;border-radius:6px;");
        out.println("font-size:15px;cursor:pointer;}");

        out.println(".order{background:#ff6f61;color:#fff;}");
        out.println(".order:hover{background:#ff3b2e;}");
        out.println(".details{background:#333;color:#fff;}");
        out.println(".details:hover{background:#000;}");

        out.println("footer{text-align:center;color:#fff;background:#000;padding:15px;}");
        out.println("</style>");
        out.println("</head>");

        out.println("<body>");

        /* ================= NAVBAR ================= */
        out.println("<div class='navbar'>");
        out.println("<ul>");
        out.println("<li><a href='index.jsp'>Home</a></li>");
        out.println("<li><a href='LoginServlet'>Login</a></li>");
        out.println("<li><a href='ArtsServlet'>Arts</a></li>");
        out.println("<li><a href='EventServlet'>Events</a></li>");
        out.println("<li><a href='ContactServlet'>Contact</a></li>");
        out.println("<li><a href='AboutServlet'>About</a></li>");
        out.println("</ul>");
        out.println("</div>");

        /* ================= WELCOME ================= */
        out.println("<div class='welcome'>Welcome to Our Online Art Gallery</div>");

        /* ================= ART DATA ================= */
        String[] titles = {
            "Sunset in Venice","Mountain Majesty","Ocean Breeze","City Lights",
            "Autumn Leaves","Golden Horizon","Tranquil Forest","Desert Mirage"
        };

        String[] images = {
            "https://img.freepik.com/free-photo/vintage-landscape-with-gondolas_1160-162.jpg",
            "https://images.unsplash.com/photo-1501785888041-af3ef285b470",
            "https://images.unsplash.com/photo-1507525428034-b723cf961d3e",
            "https://images.unsplash.com/photo-1494526585095-c41746248156",
            "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee",
            "https://images.unsplash.com/photo-1500534314209-a25ddb2bd429",
            "https://images.unsplash.com/photo-1441974231531-c6227db76b6e",
            "https://images.unsplash.com/photo-1500534623283-312aade485b7"
        };

        /* ================= ART GRID ================= */
        out.println("<div class='container'>");

        for (int i = 0; i < titles.length; i++) {
            out.println("<div class='art-box'>");
            out.println("<img src='" + images[i] + "'>");
            out.println("<div class='art-content'>");
            out.println("<h3>" + titles[i] + "</h3>");
            out.println("<p>Exclusive artwork from our premium collection.</p>");

            out.println("<div class='btn-group'>");
            out.println("<form action='OrderServlet' method='get'>");
            out.println("<input type='hidden' name='artTitle' value='" + titles[i] + "'>");
            out.println("<button class='btn order'>Order</button>");
            out.println("</form>");

            out.println("<form action='DetailsServlet' method='get'>");
            out.println("<input type='hidden' name='artTitle' value='" + titles[i] + "'>");
            out.println("<button class='btn details'>Details</button>");
            out.println("</form>");
            out.println("</div>");

            out.println("</div></div>");
        }

        out.println("</div>");

        /* ================= FOOTER ================= */
        out.println("<footer>&copy; 2025 Online Art Gallery | All Rights Reserved</footer>");

        out.println("</body>");
        out.println("</html>");
    }
}
