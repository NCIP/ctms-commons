/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.csm.authorization;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public interface CSMPrivilegeGenerator {
	
	/**
	 * Returns a CSM privilege name, given an object.
	 * 
	 * @param object from which to determine privilege
	 * @return CSM privilege name
	 */
	String generatePrivilege(Object object);

}
