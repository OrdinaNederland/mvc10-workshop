package org.jcp.hol.mvc.sessions;

import org.jcp.hol.mvc.entities.JugSession;
import org.jcp.hol.mvc.entities.User;
import org.jcp.hol.mvc.users.LoggedIn;

import javax.inject.Inject;
import javax.mvc.annotation.Controller;
import javax.mvc.binding.BindingResult;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
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
    @Inject
    private BindingResult br;
    @Inject
    private MessagesBean messagesBean;

    @GET
    public String showNewSubmissionForm() {
        return "newSession.jsp";
    }

    @POST
    @ValidateOnExecution(type = ExecutableType.NONE)
    public Response submitSessionProposal(@Valid @BeanParam final JugSubmission submission) {
        if (this.br.isFailed()) {
            final String errorMessage = this.br.getAllViolations()
                                               .stream()
                                               .map(ConstraintViolation::getMessage)
                                               .collect(Collectors.joining("<br>"));
            this.messagesBean.setMessage(errorMessage);
            return Response.status(Status.BAD_REQUEST)
                    .entity("newSession.jsp").build();
        }
        final JugSession newSession = new JugSession(submission.getTitle(), submission.getDescription(),
                                                     this.currentUser);
        this.sessionManager.submitSession(newSession);
        return Response.seeOther(URI.create("session")).build();
    }


}
