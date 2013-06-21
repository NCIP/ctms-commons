/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.csm.authorization;

import org.acegisecurity.Authentication;

public interface ObjectResultHandler {
	
	Object handle(Authentication authentication, Object secureObject, Object returnedObject);

}
