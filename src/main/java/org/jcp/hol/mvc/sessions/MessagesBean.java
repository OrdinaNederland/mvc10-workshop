package org.jcp.hol.mvc.sessions;

import javax.enterprise.inject.Model;

/**
 * @author Ivan St. Ivanov
 */
@Model
public class MessagesBean {

    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
