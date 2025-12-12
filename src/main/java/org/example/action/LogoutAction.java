package org.example.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction extends BaseAction {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Invalidate session
        invalidateSession(request);

        // Optional: Clear cookies
        // Cookie[] cookies = request.getCookies();
        // if (cookies != null) {
        //     for (Cookie cookie : cookies) {
        //         cookie.setMaxAge(0);
        //         response.addCookie(cookie);
        //     }
        // }

        // Redirect to login page
        return mapping.findForward("logout");
    }
}
