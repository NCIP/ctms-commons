/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.csm.aop;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;

import org.acegisecurity.context.SecurityContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;

public class DomainObjectOwnershipAssigner {
	
	
	private AuthorizationManager csmAuthorizationManager;

	public Object assignOwner(ProceedingJoinPoint pjp, Object id) throws Throwable{
		Object domainObject = pjp.getArgs()[0];
//		Object id = pjp.proceed();
		
		String owner = SecurityContextHolder.getContext().getAuthentication().getName();
		String objectId = generateId(domainObject, id);
		
		ProtectionElement pe = new ProtectionElement();
		pe.setProtectionElementName(objectId);
		pe.setObjectId(objectId);
		getCsmAuthorizationManager().createProtectionElement(pe);
		getCsmAuthorizationManager()
				.setOwnerForProtectionElement(objectId,
						new String[] { owner });
		
		
		return id;
	}
	
	protected String generateId(Object object, Object id){
		return object.getClass().getName() + ":" + id;
	}

	public AuthorizationManager getCsmAuthorizationManager() {
		return csmAuthorizationManager;
	}

	public void setCsmAuthorizationManager(
			AuthorizationManager csmAuthorizationManager) {
		this.csmAuthorizationManager = csmAuthorizationManager;
	}

}
