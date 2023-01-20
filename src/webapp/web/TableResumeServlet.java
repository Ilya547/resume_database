package webapp.web;


import webapp.main.Config;
import webapp.model.Resume;
import webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TableResumeServlet extends HttpServlet {
    private Storage storage = Config.get().getStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Resume> list = storage.getAllSorted();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");

        pw.println("<style>\n" +
                "table, th, td {\n" +
                "  border:1px solid black;\n" +
                "}\n" +
                "</style>");

        pw.println("<body>\n" +
                "<h3>Resumesцц</h3>" +
                "<table style=\"width:100%\">\n" +
                "<tr>\n" +
                "<th>UUID</th>\n" +
                "<th>Full Name</th>\n" +
                "</tr>");

        for (Resume r : list) {
            pw.println("<tr>");
            pw.println("<td>" + r.getUuid() + "</td>");
            pw.println("<td>" + r.getFullName() + "</td>");
            pw.println("</tr>");
        }
        pw.println("</table>\n" +
                "</body>");
        pw.println("</html>");
        pw.close();
    }
}
