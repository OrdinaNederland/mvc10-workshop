package org.jcp.hol.mvc.sessions;

import org.jcp.hol.mvc.users.LoggedIn;
import org.jcp.hol.mvc.entities.User;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Ivan St. Ivanov
 */
@Controller
@Path("/session")
public class SessionController {

    @Inject
    private SessionManager sessionManager;

    @Inject
    @LoggedIn
    private User currentUser;

    @Inject
    private Models models;

    @GET
    public String showAllSessions() {
        this.models.put("submissions", this.sessionManager.getAllSessions());
        return "sessions.jsp";
    }

    @GET
    @Path("/currentUser")
    public String showAllSessionsForCurrentUser() {
        this.models.put("submissions", this.sessionManager.getSessionsForUser(this.currentUser));
        return "sessions.jsp";
    }

}
