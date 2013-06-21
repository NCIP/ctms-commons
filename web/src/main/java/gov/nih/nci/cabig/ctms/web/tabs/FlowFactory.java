/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.web.tabs;

/**
 * @author Rhett Sutphin
 */
public interface FlowFactory<C> {
    /**
     * Create and return a flow, optionally basing its construction on the context of the
     * request.
     */
    Flow<C> createFlow(C command);
}
