package be.rubus.security.workshop.oauth2;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 *
 */

@RequestScoped
@Named
public class UserBean {

    private OAuth2AccessToken token;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();

        token = (OAuth2AccessToken) sessionMap.get(Constants.USER_TOKEN);
    }

    public String getCalendarInfo() {
        OAuthRequest oReq = new OAuthRequest(Verb.GET, "https://www.googleapis.com/calendar/v3/calendars/primary/events");
        OAuth20Service authService = AuthenticationFilter.getOAuthService();
        authService.signRequest(token, oReq);

        String result;
        try (Response resourceResponse = authService.execute(oReq)) {
            result = resourceResponse.getBody();

        } catch (IOException | ExecutionException | InterruptedException e) {
            result = e.getMessage();
        }
        return result;
    }
}

