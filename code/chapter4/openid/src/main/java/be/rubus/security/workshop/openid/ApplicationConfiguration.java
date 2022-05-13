package be.rubus.security.workshop.openid;

import fish.payara.security.annotations.OpenIdAuthenticationDefinition;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@OpenIdAuthenticationDefinition(
        providerURI = "https://rubus.eu.auth0.com"
)

@DeclareRoles("all")

@ApplicationScoped
public class ApplicationConfiguration {
}
