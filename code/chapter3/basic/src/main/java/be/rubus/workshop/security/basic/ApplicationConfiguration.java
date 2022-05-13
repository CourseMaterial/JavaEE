package be.rubus.workshop.security.basic;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

@BasicAuthenticationMechanismDefinition(
        realmName = "workshop"
)

@DeclareRoles({"foo", "bar", "kaz"})
@ApplicationScoped
public class ApplicationConfiguration {
}
