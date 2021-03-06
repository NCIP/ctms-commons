/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.csm.aop;

import org.acegisecurity.intercept.method.aspectj.AspectJCallback;
import org.acegisecurity.intercept.method.aspectj.AspectJSecurityInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;

public class SecurityInterceptorAspect {

    private AspectJSecurityInterceptor securityInterceptor;

    public Object advise(ProceedingJoinPoint pjp) throws Throwable {
        if (getSecurityInterceptor() != null) {
            return getSecurityInterceptor().invoke(pjp, new Callback(pjp));
        } else {
            return pjp.proceed();
        }
    }

    private class Callback implements AspectJCallback {
        private ProceedingJoinPoint pjp;

        public Callback(ProceedingJoinPoint pjp) {
            this.pjp = pjp;
        }

        public Object proceedWithObject() {
            try {
                return pjp.proceed();
            } catch (RuntimeException re) {
                throw re;
            } catch (Error e) {
                throw e;
            } catch (Throwable t) {
                throw new RuntimeException("Error proceeding: "
                    + t.getMessage(), t);
            }
        }

    }

    public AspectJSecurityInterceptor getSecurityInterceptor() {
        return securityInterceptor;
    }

    public void setSecurityInterceptor(
        AspectJSecurityInterceptor securityInterceptor) {
        this.securityInterceptor = securityInterceptor;
    }

}
