/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.csm.vote;

import gov.nih.nci.cabig.ctms.acegi.csm.authorization.CSMAuthorizationCheck;

import org.acegisecurity.Authentication;
import org.acegisecurity.vote.AccessDecisionVoter;

public class CSMBasicAccessDecisionVoter extends AbstractCSMAccessDecisionVoter {

	private CSMAuthorizationCheck authorizationCheck;
	
	public CSMAuthorizationCheck getAuthorizationCheck() {
		return authorizationCheck;
	}

	public void setAuthorizationCheck(CSMAuthorizationCheck authorizationCheck) {
		this.authorizationCheck = authorizationCheck;
	}

	@Override
	protected int checkAuthorization(Authentication authentication, Object object) {

		if(getAuthorizationCheck().checkAuthorization(authentication, null, object)){
			return AccessDecisionVoter.ACCESS_GRANTED;
		}else{
			return AccessDecisionVoter.ACCESS_DENIED;
		}

	}

}
