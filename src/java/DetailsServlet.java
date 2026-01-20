
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
@WebServlet("/DetailsServlet")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String artTitle = request.getParameter("artTitle");  // Retrieve the art title from the request
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String[] titles = {
            "Sunset in Venice", "Mountain Majesty", "Ocean Breeze", "The City Lights", "Autumn Leaves",
            "Tranquil Forest", "Cityscape", "Golden Horizon", "Lush Green Fields", "Desert Mirage"
        };
        String[] descriptions = {
            "A breathtaking sunset over Venice.", "Majestic snow-covered mountains.",
            "Calm ocean waves during sunset.", "Glittering city lights at night.",
            "Golden hues of autumn leaves.", "Serenity of a tranquil forest.", 
            "A beautiful cityscape during sunset.", "Golden hues of a horizon at dusk.", 
            "Endless lush green fields.", "A desert mirage that fascinates the eye."
        };
        String[] artistNames = {
            "John Doe", "Jane Smith", "Mary Johnson", "James Brown", "Alice Davis",
            "Robert Wilson", "Emily Clark", "Michael Lewis", "David Lee", "Linda Walker"
        };
        String[] artHistories = {
            "This artwork depicts the beauty of Venice at sunset, showcasing the serene ambiance of the city.",
            "The majestic mountains in this piece symbolize the power and grandeur of nature.",
            "The ocean breeze captures the peacefulness of a quiet evening by the sea.",
            "The city lights represent the ever-changing and bustling life of the urban jungle.",
            "Autumn leaves are portrayed in vibrant hues, capturing the essence of fall.",
            "A tranquil forest scene that invites the viewer to relax and reflect.",
            "A cityscape during sunset with a blend of nature and architecture.",
            "Golden horizon at dusk, representing the transition from day to night.",
            "The vast green fields convey a sense of freedom and endless possibilities.",
            "A mirage in the desert, embodying both illusion and reality."
        };
        String[] creationYears = {
            "2020", "2019", "2018", "2021", "2022", "2015", "2017", "2016", "2014", "2013"
        };
        String[] mediums = {
            "Oil on Canvas", "Acrylic on Canvas", "Watercolor", "Digital Art", "Pastels",
            "Mixed Media", "Oil on Canvas", "Acrylic on Wood", "Charcoal and Ink", "Oil on Board"
        };
        String[] dimensions = {
            "24x36 inches", "30x40 inches", "36x48 inches", "18x24 inches", "16x20 inches",
            "40x60 inches", "22x28 inches", "48x60 inches", "25x35 inches", "32x42 inches"
        };
        String[] prices = {
            "$2,500", "$3,000", "$1,800", "$4,000", "$2,200", "$3,500", "$5,000", "$4,500", "$2,800", "$3,300"
        };

        String artDescription = "";
        String artistName = "";
        String artHistory = "";
        String creationYear = "";
        String medium = "";
        String dimension = "";
        String price = "";
        for (int i = 0; i < titles.length; i++) {
            if (titles[i].equals(artTitle)) {
                artDescription = descriptions[i];
                artistName = artistNames[i];
                artHistory = artHistories[i];
                creationYear = creationYears[i];
                medium = mediums[i];
                dimension = dimensions[i];
                price = prices[i];
                break;
            }
        }
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Art Details</title>");
out.println("<style>");
out.println("body { font-family: 'Arial', sans-serif; background: #f7f7f7; padding: 20px; margin: 0; color: #333; }");
out.println("header { text-align: center; padding: 10px; background-color: #333; color: white; font-size: 2em; letter-spacing: 1px; }");
out.println(".art-detail { text-align: center; background: #ffffff; border-radius: 10px; padding: 40px; box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1); max-width: 900px; margin: 30px auto; font-size: 18px; background-color: #FFC0CB; }");  // Baby pink color applied here
out.println(".art-detail h3 { font-size: 36px; margin-top: 20px; color: #333; font-weight: 600; text-transform: uppercase; }");
out.println(".art-detail p { font-size: 18px; color: #555; line-height: 1.7; margin-bottom: 20px; }");
out.println(".art-detail .artist { font-weight: bold; margin-top: 20px; font-size: 20px; color: #333; }");
out.println(".art-detail .history, .art-detail .year, .art-detail .medium, .art-detail .dimension, .art-detail .price { margin-top: 20px; font-size: 18px; color: #555; line-height: 1.6; }");
out.println(".art-detail .price { color: #e74c3c; font-weight: bold; font-size: 24px; margin-top: 30px; }");
out.println(".back-link { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #333; color: white; text-decoration: none; border-radius: 5px; }");
out.println(".back-link:hover { background-color: #555; }");
out.println("</style>");
        out.println("<header>Art Details</header>");
        out.println("<div class='art-detail'>");
        out.println("<h3>" + artTitle + "</h3>");
        out.println("<p><strong>Description:</strong> " + artDescription + "</p>");
        out.println("<p class='artist'><strong>Artist:</strong> " + artistName + "</p>");
        out.println("<p class='history'><strong>Art History:</strong> " + artHistory + "</p>");
        out.println("<p class='year'><strong>Year of Creation:</strong> " + creationYear + "</p>");
        out.println("<p class='medium'><strong>Medium Used:</strong> " + medium + "</p>");
        out.println("<p class='dimension'><strong>Dimensions:</strong> " + dimension + "</p>");
        out.println("<p class='price'><strong>Price:</strong> " + price + "</p>");
        out.println("<a href='ArtsServlet' class='back-link'>Back to Arts Gallery</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
