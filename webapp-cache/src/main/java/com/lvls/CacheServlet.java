package com.lvls;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class CacheServlet extends HttpServlet {

    private static final long serialVersionUID = 6903760815195218553L;

    private Logger log = LoggerFactory.getLogger(getClass());

    private static CacheManager cacheManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Process request
        log(req.getRequestURI());
        Cache cache = getCache();
        // Process response
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html");
        final String html = "" +
        		"<html>" +
        		"<title>Cache Test</title>" +
        		"<body>" +
        		"<h3>Cache test</h3>" +
        		"<p>Cache: " + cache.getName() + ":" + cache.getSize() +
        		"</p>" +
        		"</body>" +
        		"</html>";
        resp.setContentLength(html.length());
        PrintWriter writer = resp.getWriter();
        writer.write(html);
        writer.close();
    }

    private Cache getCache() {
        return cacheManager.getCache("testcache");
    }

    @Override
    public void destroy() {
        if (cacheManager != null) {
            log("EHCache manager shutdown");
            cacheManager.shutdown();
        }
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        if (cacheManager == null) {
            log("EHCache manager create");
            log.info("EHCache manager create");
            cacheManager = CacheManager.create();
            log.info("EHCache manager created");
        }

    }

}
