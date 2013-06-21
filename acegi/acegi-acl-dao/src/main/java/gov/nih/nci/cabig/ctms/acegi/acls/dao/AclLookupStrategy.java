/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.acls.dao;

import java.util.Map;

import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.acls.sid.Sid;

public interface AclLookupStrategy {
    public Map readAclsById(ObjectIdentity[] objects, Sid[] sids);
}
