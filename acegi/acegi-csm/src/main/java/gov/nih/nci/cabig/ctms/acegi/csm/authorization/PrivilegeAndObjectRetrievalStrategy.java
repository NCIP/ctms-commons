/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.csm.authorization;

import org.aspectj.lang.ProceedingJoinPoint;

public interface PrivilegeAndObjectRetrievalStrategy {
	
	PrivilegeAndObject retrieve(ProceedingJoinPoint pjp);

}
