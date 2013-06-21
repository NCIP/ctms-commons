/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.grid.authorization;

import gov.nih.nci.cagrid.gridgrouper.client.GridGrouper;
import gov.nih.nci.cagrid.gridgrouper.grouper.GrouperI;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public class DefaultGridGrouperClientFactory implements
		GridGrouperClientFactory {

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.web.security.GridGrouperClientFactory#newGridGrouperClient(java.lang.String)
	 */
	public GrouperI newGridGrouperClient(String url) {
		return new GridGrouper(url);
	}

}
