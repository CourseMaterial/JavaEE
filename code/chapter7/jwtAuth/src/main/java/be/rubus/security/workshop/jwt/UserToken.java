package be.rubus.security.workshop.jwt;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class UserToken implements Serializable {

    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
