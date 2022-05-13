package be.rubus.security.workshop.oauth2;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


/**
 *
 */
@WebServlet(Constants.CALLBACK_SERVLET_PATH)
public class CallbackServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String originalCsrfToken = session.getAttribute(Constants.CSRF_TOKEN).toString();
        String csrfToken = request.getParameter("state");

        if (!originalCsrfToken.equals(csrfToken)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "CSRF token doesn't match");
        }

        String code = request.getParameter("code"); // The Authentication code
        OAuth20Service authService = AuthenticationFilter.getOAuthService();
        try {
            OAuth2AccessToken token = authService.getAccessToken(code);
            session.setAttribute(Constants.USER_TOKEN, token);

            String originalURL = session.getAttribute(Constants.ORIGINAL_URL).toString();

            // Redirect to original Page.
            response.sendRedirect(originalURL);

        } catch (InterruptedException | ExecutionException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }
}
