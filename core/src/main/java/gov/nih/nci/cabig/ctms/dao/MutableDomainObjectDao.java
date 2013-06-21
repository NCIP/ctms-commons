/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.dao;

import gov.nih.nci.cabig.ctms.domain.MutableDomainObject;

/**
 * @author Rhett Sutphin
 */
public interface MutableDomainObjectDao<T extends MutableDomainObject>
    extends DomainObjectDao<T>, GridIdentifiableDao<T>
{
    /**
     * Save (or update) the given object.
     */
    void save(T obj);
}
