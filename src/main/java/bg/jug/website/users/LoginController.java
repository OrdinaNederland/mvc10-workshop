package bg.jug.website.users;

import bg.jug.website.entities.User;

import javax.inject.Inject;
import javax.mvc.annotation.Controller;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author Ivan St. Ivanov
 */
@Controller
@Path("/login")
public class LoginController {

    @Inject
    private UserContext userContext;

    @Inject
    private UserManager userManager;

    @GET
    public String showLoginForm() {
        return "login.jsp";
    }

    @POST
    public String login(@FormParam("userName") final String userName, @FormParam("password") final String password) {
        final User foundUser = this.userManager.getUser(userName, password);
        if (foundUser == null) {
            return "redirect:/login";
        } else {
            this.userContext.setCurrentUser(foundUser);
            return "redirect:/";
        }
    }
}
