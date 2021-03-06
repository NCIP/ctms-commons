/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.csm.authentication;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.exceptions.CSInsufficientAttributesException;
import gov.nih.nci.security.exceptions.CSLoginException;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.InsufficientAuthenticationException;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.dao.AbstractUserDetailsAuthenticationProvider;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSMAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private Logger log = LoggerFactory.getLogger(getClass());

    private AuthenticationManager csmAuthenticationManager;
    private UserDetailsService userDetailsService;

    @Override
    protected void additionalAuthenticationChecks(
        UserDetails user, UsernamePasswordAuthenticationToken token
    ) throws AuthenticationException {
        log.debug("Authenticating " + user.getUsername() + "...");
        try {
            getCsmAuthenticationManager().authenticate(user.getUsername(), (String) token.getCredentials());
        } catch (CSInsufficientAttributesException ex) {
            log.debug("authentication failed: insufficient attributes", ex);
            throw new InsufficientAuthenticationException(ex.getMessage(), ex);
        } catch (CSLoginException ex) {
            log.debug("authentication failed: login exception", ex);
            throw new BadCredentialsException(ex.getMessage(), ex);
        } catch (Exception ex) {
            log.debug("authentication failed: unknown", ex);
            throw new AuthenticationServiceException("Error authenticating: " + ex.getMessage(), ex);
        }
        log.debug("authentication succeeded");
    }

    @Override
    protected UserDetails retrieveUser(
        String userName, UsernamePasswordAuthenticationToken token
    ) throws AuthenticationException {
        return getUserDetailsService().loadUserByUsername(userName);
    }

    public AuthenticationManager getCsmAuthenticationManager() {
        return csmAuthenticationManager;
    }

    public void setCsmAuthenticationManager(
        AuthenticationManager csmAuthenticationManager) {
        this.csmAuthenticationManager = csmAuthenticationManager;
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

}
