package org.example.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        String requestURI = request.getRequestURI();

        // URLs that don't require authentication
        if (requestURI.endsWith("login.do") ||
                requestURI.endsWith("login.jsp") ||
                requestURI.contains("public/")) {
            chain.doFilter(request, response);
            return;
        }

        // Check if user is logged in
        if (session == null || session.getAttribute("isLoggedIn") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // Check session timeout
        if (isSessionExpired(session)) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=sessionExpired");
            return;
        }

        // Optional: Check user permissions for specific actions
        if (!hasPermission(requestURI, session)) {
            response.sendRedirect(request.getContextPath() + "/accessDenied.jsp");
            return;
        }

        // Update last activity time
        session.setAttribute("lastActivity", System.currentTimeMillis());
        chain.doFilter(request, response);
    }

    private boolean isSessionExpired(HttpSession session) {
        Long lastActivity = (Long) session.getAttribute("lastActivity");
        if (lastActivity == null) return false;

        long currentTime = System.currentTimeMillis();
        long inactiveInterval = currentTime - lastActivity;

        // Consider expired if inactive for more than 30 minutes
        return inactiveInterval > (30 * 60 * 1000);
    }

    private boolean hasPermission(String requestURI, HttpSession session) {
        // Implement permission logic based on user role
        String userRole = (String) session.getAttribute("userRole");
        // Check if user role has access to requested resource
        return true; // Simplified
    }

    @Override public void init(FilterConfig filterConfig) throws ServletException {}
    @Override public void destroy() {}
}