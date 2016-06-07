package bg.jug.website.sessions;

import javax.enterprise.inject.Model;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * @author Ivan St. Ivanov
 */
@Model
public class JugSubmission {

    @Size(min = 8, max = 100)
    @FormParam("title")
    private String title;

    @FormParam("description")
    private String description;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
