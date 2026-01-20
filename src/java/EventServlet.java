import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {

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
        out.println("<title>Art Gallery Events</title>");

        /* ================= CSS ================= */
        out.println("<style>");
        out.println("html, body { height: 100%; margin: 0; }");

        out.println("body {");
        out.println("  display: flex;");
        out.println("  flex-direction: column;");
        out.println("  font-family: 'Poppins', Arial, sans-serif;");
        out.println("  background: url('https://static.vecteezy.com/system/resources/thumbnails/069/922/846/small/blurred-cinematic-view-of-a-dimly-lit-art-gallery-with-abstract-sculptures-and-paintings-photo.jpeg') no-repeat center fixed;");
        out.println("  background-size: cover;");
        out.println("}");

        /* Navbar */
        out.println(".navbar {");
        out.println("  position: fixed;");
        out.println("  top: 0;");
        out.println("  width: 100%;");
        out.println("  background: rgba(0,0,0,0.9);");
        out.println("  padding: 12px 0;");
        out.println("  z-index: 1000;");
        out.println("}");
        out.println(".navbar ul { list-style: none; display: flex; justify-content: center; margin: 0; padding: 0; }");
        out.println(".navbar ul li { margin: 0 18px; }");
        out.println(".navbar ul li a { color: white; text-decoration: none; font-size: 18px; }");
        out.println(".navbar ul li a:hover { color: #ffcc00; }");

        /* Main content */
        out.println(".main { flex: 1; padding-top: 100px; }");

        /* Heading */
        out.println(".heading-box {");
        out.println("  text-align: center;");
        out.println("  background: rgba(255,255,255,0.95);");
        out.println("  padding: 25px;");
        out.println("  border-radius: 15px;");
        out.println("  width: 85%;");
        out.println("  margin: auto;");
        out.println("  font-size: 30px;");
        out.println("  font-weight: bold;");
        out.println("}");

        /* Event grid */
        out.println(".container {");
        out.println("  display: grid;");
        out.println("  grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));");
        out.println("  gap: 35px;");
        out.println("  padding: 50px 80px;");
        out.println("}");

        /* Event card */
        out.println(".event-box {");
        out.println("  background: white;");
        out.println("  border-radius: 20px;");
        out.println("  box-shadow: 0 15px 35px rgba(0,0,0,0.35);");
        out.println("  padding: 25px;");
        out.println("  text-align: center;");
        out.println("  transition: transform 0.4s, box-shadow 0.4s;");
        out.println("}");
        out.println(".event-box:hover { transform: translateY(-12px); box-shadow: 0 25px 50px rgba(0,0,0,0.45); }");

        out.println(".event-box img {");
        out.println("  width: 100%;");
        out.println("  height: 230px;");
        out.println("  object-fit: cover;");
        out.println("  border-radius: 15px;");
        out.println("}");

        out.println(".event-box h3 { color: #222; margin: 18px 0 10px; font-size: 22px; }");
        out.println(".event-box p { color: #555; font-size: 16px; margin: 6px 0; }");

        /* Button */
        out.println(".register-btn {");
        out.println("  margin-top: 18px;");
        out.println("  padding: 14px 32px;");
        out.println("  background: linear-gradient(135deg, #ff9800, #ff5722);");
        out.println("  color: white;");
        out.println("  border: none;");
        out.println("  border-radius: 30px;");
        out.println("  font-size: 16px;");
        out.println("  cursor: pointer;");
        out.println("}");
        out.println(".register-btn:hover { background: #e64a19; }");

        /* Footer */
        out.println("footer {");
        out.println("  background: black;");
        out.println("  color: white;");
        out.println("  text-align: center;");
        out.println("  padding: 18px;");
        out.println("}");

        out.println("</style>");
        out.println("</head>");

        out.println("<body>");

        /* Navbar */
        out.println("<div class='navbar'><ul>");
        out.println("<li><a href='HomeServlet'>Home</a></li>");
        out.println("<li><a href='LoginServlet'>Login</a></li>");
        out.println("<li><a href='ArtsServlet'>Arts</a></li>");
        out.println("<li><a href='EventServlet'>Events</a></li>");
        out.println("<li><a href='ContactServlet'>Contact</a></li>");
        out.println("<li><a href='AboutServlet'>About</a></li>");
        out.println("</ul></div>");

        out.println("<div class='main'>");

        out.println("<div class='heading-box'>Upcoming Art Gallery Events</div>");

        out.println("<div class='container'>");

        out.println(eventBox("Art Expo 2025", "Feb 15, 2025", "10:00 AM - 5:00 PM",
                "Explore modern and contemporary masterpieces.",
                "https://tse4.mm.bing.net/th/id/OIP.cqX1IOPIfamrDfZdNVLCCwHaE8"));

        out.println(eventBox("Sculpture Workshop", "Mar 10, 2025", "1:00 PM - 4:00 PM",
                "Hands-on sculpting with expert artists.",
                "https://www.centeredceramics.com.au/wp-content/uploads/2020/03/Freda_Edited.jpg"));

        out.println(eventBox("Digital Art Course", "Apr 12, 2025", "9:00 AM - 12:00 PM",
                "Learn professional digital illustration.",
                "https://tse4.mm.bing.net/th?id=OIP.Vwdf9o9ygdxZYbBZh1oPAgHaE8"));

      out.println(eventBox("Photography Exhibition", "May 20, 2025", "11:00 AM - 6:00 PM",
                "Stunning visual stories by top photographers.",
                "https://media.istockphoto.com/id/526927807/photo/outdoor-art-gallery-on-union-square-san-francisco.jpg?s=612x612&w=0&k=20&c=5pEW8tcAfY4Vvp7CYC8PqjFiAz4fK8kN91NJj5cCFTA="));

        out.println("</div></div>");

        out.println("<footer>© 2025 Online Art Gallery | All Rights Reserved</footer>");

        out.println("</body></html>");
    }

    private String eventBox(String name, String date, String time, String desc, String img) {
        return "<div class='event-box'>"
                + "<img src='" + img + "'>"
                + "<h3>" + name + "</h3>"
                + "<p><b>Date:</b> " + date + "</p>"
                + "<p><b>Time:</b> " + time + "</p>"
                + "<p>" + desc + "</p>"
                + "<form action='RegisterServlet'>"
                + "<input type='hidden' name='event' value='" + name + "'>"
                + "<button class='register-btn'>Register</button>"
                + "</form>"
                + "</div>";
    }
}
