/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.grid.authentication;

import org.globus.gsi.CertificateRevocationLists;
import org.globus.gsi.GlobusCredential;
import org.globus.gsi.TrustedCertificates;
import org.globus.gsi.proxy.ProxyPathValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.security.cert.X509Certificate;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class GridProxyValidatorImpl implements GridProxyValidator {

    private static final Logger logger = LoggerFactory.getLogger(GridProxyValidatorImpl.class);

    private String trustedCertsLocations;

    private String crlLocations;

    public String getCrlLocations() {
        return crlLocations;
    }

    public void setCrlLocations(String crlLocations) {
        this.crlLocations = crlLocations;
    }

    public String getTrustedCertsLocations() {
        return trustedCertsLocations;
    }

    public void setTrustedCertsLocations(String trustedCertsLocations) {
        this.trustedCertsLocations = trustedCertsLocations;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sso.GridProxyValidator#validate(java.lang.String)
     */
    public boolean validate(String proxy) throws GridProxyValidationException {
        boolean valid = false;

        GlobusCredential cred = null;
        try {
            cred = new GlobusCredential(new ByteArrayInputStream(proxy.getBytes()));
        } catch (Exception ex) {
            throw new GridProxyValidationException("Error instantiating GlobusCredential: "
                            + ex.getMessage(), ex);
        }

        try {

            X509Certificate[] proxyChain = cred.getCertificateChain();
            X509Certificate[] trustedCerts = null;
            CertificateRevocationLists crls = null;

            String tcLoc = getTrustedCertsLocations();
            if (tcLoc != null) {
                trustedCerts = TrustedCertificates.loadCertificates(tcLoc);
            } else {
                trustedCerts = TrustedCertificates.getDefaultTrustedCertificates()
                                .getCertificates();
            }

            String crlLoc = getCrlLocations();
            if (crlLoc != null) {
                crls = CertificateRevocationLists.getCertificateRevocationLists(crlLoc);
            } else {
                crls = CertificateRevocationLists.getDefaultCertificateRevocationLists();
            }

            ProxyPathValidator ppv = new ProxyPathValidator();
            ppv.validate(proxyChain, trustedCerts, crls);

            valid = true;

        } catch (Exception ex) {
            logger.debug("Proxy validation failed: " + ex.getMessage());
        }

        return valid;
    }

}
