package be.rubus.security.workshop.openid;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static javax.security.enterprise.identitystore.IdentityStore.ValidationType.PROVIDE_GROUPS;

@ApplicationScoped
public class DemoGroupsIdentityStore implements IdentityStore {

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        // Or retrieve groups based on validationResult.getCallerPrincipal()
        return Collections.singleton("all");

    }

    @Override
    public Set<ValidationType> validationTypes() {
        return Collections.singleton(PROVIDE_GROUPS);

    }
}
