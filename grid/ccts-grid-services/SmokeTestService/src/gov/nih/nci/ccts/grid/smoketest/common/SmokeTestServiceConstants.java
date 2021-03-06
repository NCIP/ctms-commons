/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.ccts.grid.smoketest.common;

import javax.xml.namespace.QName;


public interface SmokeTestServiceConstants {
	public static final String SERVICE_NS = "http://smoketest.grid.ccts.nci.nih.gov/SmokeTestService";
	public static final QName RESOURCE_KEY = new QName(SERVICE_NS, "SmokeTestServiceKey");
	public static final QName RESOURCE_PROPERTY_SET = new QName(SERVICE_NS, "SmokeTestServiceResourceProperties");

	//Service level metadata (exposed as resouce properties)
	
}
