package bg.jug.website.sessions;

import bg.jug.website.entities.JugSession;
import bg.jug.website.entities.User;
import bg.jug.website.users.LoggedIn;

import javax.inject.Inject;
import javax.mvc.annotation.Controller;
import javax.mvc.binding.BindingResult;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.stream.Collectors;

/**
 * @author Ivan St. Ivanov
 */
@Controller
@Path("/submit")
public class SubmissionController {

    @Inject
    private SessionManager sessionManager;

    @LoggedIn
    @Inject
    private User currentUser;

    @GET
    public String showNewSubmissionForm() {
        return "newSession.jsp";
    }

    @Inject
    private BindingResult br;

    @Inject
    private MessagesBean messagesBean;

    @POST
    @ValidateOnExecution(type = ExecutableType.NONE)
    public Response submitSessionProposal(@Valid @BeanParam JugSubmission submission) {
        if (br.isFailed()) {
            String errorMessage = br.getAllViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("<br>"));
            messagesBean.setMessage(errorMessage);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("newSession.jsp").build();
        }
        JugSession newSession = new JugSession(submission.getTitle(), submission.getDescription(), currentUser);
        sessionManager.submitSession(newSession);
        return Response.seeOther(URI.create("session")).build();
    }


}
