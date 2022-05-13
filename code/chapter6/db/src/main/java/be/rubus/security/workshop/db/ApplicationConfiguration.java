package be.rubus.security.workshop.db;


import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@DatabaseIdentityStoreDefinition(
        callerQuery = "select password from users where name = ?",
        groupsQuery = "select group_name from user_groups where user_name = ?"
)

@BasicAuthenticationMechanismDefinition

@DeclareRoles({"foo", "bar"})

@ApplicationScoped
public class ApplicationConfiguration {
}
