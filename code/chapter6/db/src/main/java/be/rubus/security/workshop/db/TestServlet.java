package be.rubus.security.workshop.db;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ServletSecurity(@HttpConstraint(rolesAllowed = "foo"))
@WebServlet("/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("This is a servlet \n");
        String webName = null;
        if (req.getUserPrincipal() != null) {
            webName = req.getUserPrincipal().getName();
        }

        resp.getWriter().write("web username: " + webName + "\n");
    }
}
