/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.web.tabs;

/**
 * A flow factory which always returns the same {@link Flow}.
 *
 * @author Rhett Sutphin
 */
public class StaticFlowFactory<C> implements FlowFactory<C> {
    private Flow<C> flow;

    public StaticFlowFactory() { }

    public StaticFlowFactory(Flow<C> flow) {
        this.flow = flow;
    }

    ////// LOGIC

    public Flow<C> createFlow(C command) {
        return getFlow();
    }

    ////// BEAN PROPERTIES

    public Flow<C> getFlow() {
        return flow;
    }

    public void setFlow(Flow<C> flow) {
        this.flow = flow;
    }
}
