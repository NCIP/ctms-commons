/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.acls.dao.impl;

import gov.nih.nci.cabig.ctms.acegi.acls.dao.AclCache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;

import org.acegisecurity.acls.MutableAcl;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.springframework.util.Assert;



/**
 * Simple implementation of {@link AclCache} that delegates to EH-CACHE.
 *
 * @author Ben Alex
 * @version $Id: EhCacheBasedAclCache.java 1754 2006-11-17 02:01:21Z benalex $
 */
public class EhCacheBasedAclCache implements AclCache {
    //~ Instance fields ================================================================================================

    private Cache cache;

    //~ Constructors ===================================================================================================

    public EhCacheBasedAclCache(Cache cache) {
        Assert.notNull(cache, "Cache required");
        this.cache = cache;
    }

    //~ Methods ========================================================================================================

    public void evictFromCache(Serializable pk) {
        Assert.notNull(pk, "Primary key (identifier) required");

        MutableAcl acl = getFromCache(pk);

        if (acl != null) {
            cache.remove(acl.getId());
            cache.remove(acl.getObjectIdentity());
        }
    }

    public void evictFromCache(ObjectIdentity objectIdentity) {
        Assert.notNull(objectIdentity, "ObjectIdentity required");

        MutableAcl acl = getFromCache(objectIdentity);

        if (acl != null) {
            cache.remove(acl.getId());
            cache.remove(acl.getObjectIdentity());
        }
    }

    public MutableAcl getFromCache(ObjectIdentity objectIdentity) {
        Assert.notNull(objectIdentity, "ObjectIdentity required");

        Element element = null;

        try {
            element = cache.get(objectIdentity);
        } catch (CacheException ignored) {}

        if (element == null) {
            return null;
        }

        return (MutableAcl) element.getValue();
    }

    public MutableAcl getFromCache(Serializable pk) {
        Assert.notNull(pk, "Primary key (identifier) required");

        Element element = null;

        try {
            element = cache.get(pk);
        } catch (CacheException ignored) {}

        if (element == null) {
            return null;
        }

        return (MutableAcl) element.getValue();
    }

    public void putInCache(MutableAcl acl) {
        Assert.notNull(acl, "Acl required");
        Assert.notNull(acl.getObjectIdentity(), "ObjectIdentity required");
        Assert.notNull(acl.getId(), "ID required");

        if ((acl.getParentAcl() != null) && (acl.getParentAcl() instanceof MutableAcl)) {
            putInCache((MutableAcl) acl.getParentAcl());
        }

        cache.put(new Element(acl.getObjectIdentity(), acl));
        cache.put(new Element(acl.getId(), acl));
    }
}
