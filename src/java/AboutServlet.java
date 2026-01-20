

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;

@WebServlet("/AboutServlet")
public class AboutServlet extends HttpServlet {

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
        out.println("<title>About Us - Art Gallery</title>");

        /* ================= CSS ================= */
        out.println("<style>");
        out.println("*{box-sizing:border-box;}");

        out.println("body{");
        out.println(" margin:0;");
        out.println(" min-height:100vh;");
        out.println(" display:flex;");
        out.println(" flex-direction:column;");
        out.println(" font-family:'Poppins',Arial,sans-serif;");
        out.println(" background:linear-gradient(rgba(0,0,0,0.55),rgba(0,0,0,0.55)),");
        out.println(" url('https://static.vecteezy.com/system/resources/thumbnails/069/922/846/small/blurred-cinematic-view-of-a-dimly-lit-art-gallery-with-abstract-sculptures-and-paintings-photo.jpeg') no-repeat center fixed;");
        out.println(" background-size:cover;");
        out.println("}");

        /* Navbar */
        out.println(".navbar{");
        out.println(" position:fixed;");
        out.println(" top:0;");
        out.println(" width:100%;");
        out.println(" background:rgba(0,0,0,0.95);");
        out.println(" padding:14px 0;");
        out.println(" z-index:1000;");
        out.println("}");
        out.println(".navbar ul{");
        out.println(" list-style:none;");
        out.println(" display:flex;");
        out.println(" justify-content:center;");
        out.println(" margin:0;");
        out.println(" padding:0;");
        out.println("}");
        out.println(".navbar ul li{margin:0 18px;}");
        out.println(".navbar ul li a{");
        out.println(" color:#fff;");
        out.println(" text-decoration:none;");
        out.println(" font-size:18px;");
        out.println(" font-weight:500;");
        out.println("}");
        out.println(".navbar ul li a:hover{color:#ffcc00;}");

        /* Main Content */
        out.println(".main{");
        out.println(" flex:1;");
        out.println(" display:flex;");
        out.println(" align-items:center;");
        out.println(" justify-content:center;");
        out.println(" padding-top:140px;");
        out.println(" padding-bottom:40px;");
        out.println("}");

        out.println(".about-box{");
        out.println(" background:rgba(255,255,255,0.96);");
        out.println(" width:75%;");
        out.println(" max-width:900px;");
        out.println(" padding:50px;");
        out.println(" border-radius:22px;");
        out.println(" box-shadow:0 25px 45px rgba(0,0,0,0.5);");
        out.println(" animation:fadeIn 1.2s ease-in-out;");
        out.println("}");

        out.println(".about-box h1{");
        out.println(" text-align:center;");
        out.println(" font-size:42px;");
        out.println(" margin-bottom:25px;");
        out.println(" color:#222;");
        out.println(" letter-spacing:3px;");
        out.println("}");

        out.println(".about-box p{");
        out.println(" font-size:18px;");
        out.println(" line-height:1.9;");
        out.println(" color:#333;");
        out.println(" text-align:justify;");
        out.println(" margin-bottom:18px;");
        out.println("}");

        out.println("@keyframes fadeIn{");
        out.println(" from{opacity:0;transform:translateY(25px);}"); 
        out.println(" to{opacity:1;transform:translateY(0);}"); 
        out.println("}");

        /* Footer */
        out.println("footer{");
        out.println(" background:#000;");
        out.println(" color:#fff;");
        out.println(" text-align:center;");
        out.println(" padding:14px;");
        out.println(" font-size:14px;");
        out.println("}");

        out.println("</style>");
        out.println("</head>");

        out.println("<body>");

        /* Navbar */
        out.println("<div class='navbar'>");
        out.println("<ul>");
        out.println("<li><a href='HomeServlet'>Home</a></li>");
        out.println("<li><a href='LoginServlet'>Login</a></li>");
        out.println("<li><a href='ArtsServlet'>Arts</a></li>");
        out.println("<li><a href='EventServlet'>Events</a></li>");
        out.println("<li><a href='ContactServlet'>Contact</a></li>");
        out.println("<li><a href='AboutServlet'>About</a></li>");
        out.println("</ul>");
        out.println("</div>");

        /* Content */
        out.println("<div class='main'>");
        out.println("<div class='about-box'>");
        out.println("<h1>About Our Art Gallery</h1>");
        out.println("<p>Welcome to our Online Art Gallery — a place where creativity meets passion. We showcase inspiring artworks created by talented artists from around the world.</p>");
        out.println("<p>Our collections include paintings, sculptures, and digital art that combine modern innovation with timeless elegance. Each artwork tells a unique story.</p>");
        out.println("<p>Our mission is to make art accessible, support creative minds, and connect art lovers globally. Whether you are a collector or a beginner, we have something special for you.</p>");
        out.println("<p>Thank you for being part of our artistic journey.</p>");
        out.println("</div>");
        out.println("</div>");

        /* Footer */
        out.println("<footer>");
        out.println("© 2025 Online Art Gallery | All Rights Reserved");
        out.println("</footer>");

        out.println("</body>");
        out.println("</html>");
    }
}
