/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.ccts.grid.service;

import gov.nih.nci.ccts.grid.common.RegistrationConsumerI;
import org.globus.wsrf.config.ContainerConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;

/** 
 * TODO:I am the service side implementation class.  IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.2
 * 
 */
public class RegistrationConsumerImpl extends RegistrationConsumerImplBase {
	private static final String SPRING_CLASSPATH_EXPRESSION = "springClasspathExpression";

    private static final String DEFAULT_SPRING_CLASSPATH_EXPRESSION = "classpath:applicationContext-grid.xml";

    private static final String REGISTRATION_CONSUMER_BEAN_NAME = "registrationConsumerBeanName";

    private static final String DEFAULT_REGISTRATION_CONSUMER_BEAN_NAME = "registrationConsumer";
	
    private RegistrationConsumerI consumer;
    
	public RegistrationConsumerImpl() throws RemoteException {
		super();
		String exp = ContainerConfig.getConfig().getOption(SPRING_CLASSPATH_EXPRESSION,
                DEFAULT_SPRING_CLASSPATH_EXPRESSION);
        String bean = ContainerConfig.getConfig().getOption(REGISTRATION_CONSUMER_BEAN_NAME,
                DEFAULT_REGISTRATION_CONSUMER_BEAN_NAME);
        ApplicationContext ctx = new ClassPathXmlApplicationContext(exp);
        this.consumer = (RegistrationConsumerI) ctx.getBean(bean);
	}
	
  public void rollback(gov.nih.nci.cabig.ccts.domain.Registration registration) throws RemoteException, gov.nih.nci.ccts.grid.stubs.types.InvalidRegistrationException {
    //TODO: Implement this autogenerated method
	  this.consumer.rollback(registration);
  }

  public void commit(gov.nih.nci.cabig.ccts.domain.Registration registration) throws RemoteException, gov.nih.nci.ccts.grid.stubs.types.InvalidRegistrationException {
    //TODO: Implement this autogenerated method
	  this.consumer.rollback(registration);
  }

  public gov.nih.nci.cabig.ccts.domain.Registration register(gov.nih.nci.cabig.ccts.domain.Registration registration) throws RemoteException, gov.nih.nci.ccts.grid.stubs.types.InvalidRegistrationException, gov.nih.nci.ccts.grid.stubs.types.RegistrationConsumptionException {
    //TODO: Implement this autogenerated method
	  return this.consumer.register(registration);
  }

}

