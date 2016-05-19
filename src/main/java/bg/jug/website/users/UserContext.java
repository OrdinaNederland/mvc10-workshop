package bg.jug.website.users;

import bg.jug.website.entities.User;
import bg.jug.website.test.TestDataInserter;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import java.io.Serializable;

/**
 * @author Ivan St. Ivanov
 */
@SessionScoped
public class UserContext implements Serializable {

    private static final long serialVersionUID = -7093851207387888951L;

    private User currentUser;

    public UserContext() {
        this.currentUser = TestDataInserter.DEFAULT_USER;
    }

    @Produces
    @LoggedIn
    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }
}
