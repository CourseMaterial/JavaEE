package be.rubus.security.workshop.ldap;


import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.LdapIdentityStoreDefinition;

@LdapIdentityStoreDefinition(
        url = "ldap://www.zflexldap.com/",
        bindDn = "cn=ro_admin,ou=sysadmins,dc=zflexsoftware,dc=com",
        bindDnPassword = "zflexpass",
        callerSearchBase = "ou=users,ou=guests,dc=zflexsoftware,dc=com",
        groupSearchBase = "ou=group,ou=guests,dc=zflexsoftware,dc=com"
)

@ApplicationScoped
public class ApplicationConfiguration {
}
