/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.csm.vote;

import gov.nih.nci.cabig.ctms.acegi.csm.authorization.AuthorizationSwitch;
import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.vote.AccessDecisionVoter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * If the configured AuthorizationSwitch is on, the vote method
 * returns ACCESS_ABSTAIN, otherwise it returns ACCESS_GRANTED.
 * 
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public class AuthorizationSwitchVoter implements AccessDecisionVoter {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationSwitchVoter.class);
	
	private AuthorizationSwitch authorizationSwitch;
	
	private boolean requiresAuthentication = true;

	public boolean isRequiresAuthentication() {
		return requiresAuthentication;
	}

	public void setRequiresAuthentication(boolean requiresAuthentication) {
		this.requiresAuthentication = requiresAuthentication;
	}

	public AuthorizationSwitch getAuthorizationSwitch() {
		return authorizationSwitch;
	}

    @Required
    public void setAuthorizationSwitch(AuthorizationSwitch authorizationSwitch) {
		this.authorizationSwitch = authorizationSwitch;
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.vote.AccessDecisionVoter#supports(org.acegisecurity.ConfigAttribute)
	 */
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.vote.AccessDecisionVoter#supports(java.lang.Class)
	 */
	public boolean supports(Class arg0) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.vote.AccessDecisionVoter#vote(org.acegisecurity.Authentication, java.lang.Object, org.acegisecurity.ConfigAttributeDefinition)
	 */
	public int vote(Authentication arg0, Object arg1,
			ConfigAttributeDefinition arg2) {
		int retVal = AccessDecisionVoter.ACCESS_ABSTAIN;
		if(!getAuthorizationSwitch().isOn()){
			
			logger.warn("###### AuthorizationSwitch is OFF #####");
			if(!isRequiresAuthentication() ||
					isRequiresAuthentication() && 
					SecurityContextHolder.getContext().getAuthentication() != null){
				logger.warn("###### AuthorizationSwitch ACCESS_GRANTED #####");
				retVal = AccessDecisionVoter.ACCESS_GRANTED;
			}
			
		}
		return retVal;
	}

}
