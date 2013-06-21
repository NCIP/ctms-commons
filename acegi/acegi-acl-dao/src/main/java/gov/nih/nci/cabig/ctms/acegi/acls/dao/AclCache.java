/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.acls.dao;

import java.io.Serializable;

import org.acegisecurity.acls.MutableAcl;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;

public interface AclCache {
    public void evictFromCache(Serializable pk);

    public void evictFromCache(ObjectIdentity objectIdentity);

    public MutableAcl getFromCache(ObjectIdentity objectIdentity);

    public MutableAcl getFromCache(Serializable pk);

    public void putInCache(MutableAcl acl);
}
