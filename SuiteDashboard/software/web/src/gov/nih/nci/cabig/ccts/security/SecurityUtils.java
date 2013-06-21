/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ccts.security;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.User;

public final class SecurityUtils {

	private SecurityUtils() {
	}

	public static String getUserLoginName(){
		return getUserLoginName(SecurityContextHolder.getContext().getAuthentication());
	}

	public static String getUserLoginName(Authentication authentication){
		Object principal  =  authentication.getPrincipal();
		String userName = "";
		if (principal instanceof User) {
			userName = ((User)principal).getUsername();
		} else {
			userName = principal.toString();
		}

		return userName;
	}

	public static GrantedAuthority[] getGrantedAuthorities() {
		return getGrantedAuthorities(SecurityContextHolder.getContext().getAuthentication());
	}

	public static GrantedAuthority[] getGrantedAuthorities(Authentication authentication) {
		return authentication.getAuthorities();
	}

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
