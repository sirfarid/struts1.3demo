package org.example.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.example.form.LoginForm;

public class LoginAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LoginForm loginForm = (LoginForm) form;

        // Validate credentials (simplified example)
        if (authenticateUser(loginForm.getUsername(), loginForm.getPassword())) {
            // Create session
            HttpSession session = request.getSession();

            // Store user information in session
            session.setAttribute("username", loginForm.getUsername());
            session.setAttribute("userRole", getUserRole(loginForm.getUsername()));
            session.setAttribute("isLoggedIn", true);

            // Set session timeout (optional, defaults to container settings)
            session.setMaxInactiveInterval(30 * 60); // 30 minutes

            return mapping.findForward("success");
        } else {
            // Add error message
            request.setAttribute("errorMessage", "Invalid username or password");
            return mapping.findForward("failure");
        }
    }

    private boolean authenticateUser(String username, String password) {
        // Implement your authentication logic
        // Check against database, LDAP, etc.
        return true; // Simplified
    }

    private String getUserRole(String username) {
        // Get user role from database
        return "user"; // Simplified
    }
}
