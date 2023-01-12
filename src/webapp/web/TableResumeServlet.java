package webapp.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class TableResumeServlet extends HttpServlet {
//    private Storage sqlStorage;
//
//    @Override
//    public void init(ServletConfig config) {
//        try {
//            super.init(config);
//        } catch (ServletException e) {
//            throw new RuntimeException(e);
//        }
//        sqlStorage = Config.get().getStorage();
//    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter pw = response.getWriter();
        pw.println("<html>");

        pw.println("<style>\n" +
                "table, th, td {\n" +
                "  border:1px solid black;\n" +
                "}\n" +
                "</style>");

        pw.println("<body>\n" +
                "<h2>Resumes</h2>" +
                "<table style=\"width:100%\">\n" +
                "<tr>\n" +
                "<th>UUID</th>\n" +
                "<th>Full Name</th>\n" +
                "</tr>");

//        List<Resume> list = sqlStorage.getAllSorted();
        pw.println("</table>\n" + "</body>");
        pw.println("</html>");

//        for (Resume r : list) {
//            pw.println("<tr>");
//            pw.println("<td>" + r.getUuid() + "</td>");
//            pw.println("<td>" + r.getFullName() + "</td>");
//            pw.println("</tr>");
//        }
        pw.println("</table>\n" + "</tbody>");
    }
}
