package bg.jug.website.sessions;

import javax.enterprise.inject.Model;

/**
 * @author Ivan St. Ivanov
 */
@Model
public class MessagesBean {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
