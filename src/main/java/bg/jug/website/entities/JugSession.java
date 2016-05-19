package bg.jug.website.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
    @NamedQuery(name = "getAllSessions", query = "SELECT s FROM JugSession s"),
    @NamedQuery(name = "findSessionsByUser", query = "SELECT s FROM JugSession s WHERE s.byUser = :user")
})
public class JugSession implements Serializable {

    private static final long serialVersionUID = -8061042352342915142L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private int version;

    @Column
    @Size(min = 8, max = 100)
    private String title;

    @Column(length = 3000)
    @Lob
    private String description;

    @ManyToOne
    private User byUser;

    public JugSession() {
    }

    public JugSession(final String title, final String description, final User byUser) {
        this.title = title;
        this.description = description;
        this.byUser = byUser;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

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

    public User getByUser() {
        return this.byUser;
    }

    public void setByUser(final User byUser) {
        this.byUser = byUser;
    }

    @Override
    public String toString() {
        return "JugSession{" + "byUser=" + this.byUser + ", description='" + this.description + '\'' + ", title='"
               + this.title + '\'' + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        final JugSession that = (JugSession) o;
        return Objects.equals(this.title, that.title) && Objects.equals(this.description, that.description)
               && Objects.equals(this.byUser, that.byUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.description, this.byUser);
    }

}
