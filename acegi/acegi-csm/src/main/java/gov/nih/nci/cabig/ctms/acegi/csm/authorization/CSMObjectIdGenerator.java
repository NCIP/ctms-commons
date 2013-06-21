/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.csm.authorization;

public interface CSMObjectIdGenerator {

	/**
	 * Returns a CSM objectId, given an object.
	 * 
	 * @param object from which ID should be generated
	 * @return CSM objectId
	 */
	String generateId(Object object);
	
}
