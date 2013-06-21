/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.ccts.grid.common;

import javax.xml.namespace.QName;


public interface RegistrationConsumerConstants {
	public static final String SERVICE_NS = "http://grid.ccts.nci.nih.gov/RegistrationConsumer";
	public static final QName RESOURCE_KEY = new QName(SERVICE_NS, "RegistrationConsumerKey");
	public static final QName RESOURCE_PROPERTY_SET = new QName(SERVICE_NS, "RegistrationConsumerResourceProperties");

	//Service level metadata (exposed as resouce properties)
	
}
