package be.rubus.security.workshop.ldap;

import javax.security.enterprise.identitystore.LdapIdentityStoreDefinition;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */

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
        resp.getWriter().write("web user has role \"deptGRP1\":" + req.isUserInRole("deptGRP1") + "\n");
        resp.getWriter().write("web user has role \"deptGRP2\":" + req.isUserInRole("deptGRP2") + "\n");
        resp.getWriter().write("web user has role \"deptGRP3\":" + req.isUserInRole("deptGRP3") + "\n");
    }
}
