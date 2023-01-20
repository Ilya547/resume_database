package webapp.web;

import webapp.main.Config;
import webapp.model.ContactType;
import webapp.model.Resume;
import webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    private Storage storage; // = Config.get().getStorage();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "add":
                r = Resume.EMPTY;
                break;
            case "view":
            case "edit":
                r = storage.get(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");

//        final boolean isCreate = (uuid == null || uuid.length() == 0);
//        Resume r;
//        if (isCreate) {
//            r = new Resume(fullName);
//            storage.save(r);
//        } else {
//            r = storage.get(uuid);
//            r.setFullName(fullName);
//        }
//
//        for (ContactType type : ContactType.values()) {
//            String value = request.getParameter(type.name());
//            if (value != null && value.trim().length() != 0) {
//                r.getContacts().remove(type);
//            } else {
//                r.setContact(type, value);
//            }
//        }


        Resume newResume = new Resume(uuid, fullName);
        if (storage.getAllSorted().contains(newResume)) {
            storage.save(newResume);
        } else {
            Resume r = storage.get(uuid);
            r.setFullName(fullName);
            for (ContactType type : ContactType.values()) {
                String value = request.getParameter(type.name());
                if (value != null && value.trim().length() != 0) {
                    r.addContact(type, value);
                } else {
                    r.getContacts().remove(type);
                }
            }
            storage.update(r);
        }
        response.sendRedirect("resume");
    }
}
