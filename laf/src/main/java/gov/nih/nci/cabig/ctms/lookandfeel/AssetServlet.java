package gov.nih.nci.cabig.ctms.lookandfeel;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Servlet which supports serving static assets (images, stylesheets, scripts, etc.)
 * off of the classpath.
 *
 * @author Rhett Sutphin
 */
public class AssetServlet extends HttpServlet {
    private static final String DEFAULT_RESOURCE_BASE = "/gov/nih/nci/cabig/ctms/lookandfeel/assets/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().contains("..")) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Illegal path");
            return;
        }
        String resource = resourcePath(req.getPathInfo());
        // TODO: this is primitive.  Should add content-length at least
        InputStream resStream = getClass().getResourceAsStream(resource);
        if (resStream == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            IOUtils.copy(resStream, resp.getOutputStream());
        }
    }

    private String resourcePath(String pathInfo) {
        StringBuilder res = new StringBuilder(getResourceBase());
        if (res.charAt(res.length() - 1) != '/') res.append('/');
        if (pathInfo.startsWith("/")) {
            res.append(pathInfo.substring(1));
        } else {
            res.append(pathInfo);
        }
        return res.toString();
    }

    private String getResourceBase() {
        // TODO: make this configurable
        return DEFAULT_RESOURCE_BASE;
    }

    // TODO: implement this.
    @Override
    protected long getLastModified(HttpServletRequest req) {
        return super.getLastModified(req);
    }
}
