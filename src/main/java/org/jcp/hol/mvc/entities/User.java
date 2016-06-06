package org.jcp.hol.mvc.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
    @NamedQuery(name = "getAllUsers", query = "SELECT u FROM User u"),
    @NamedQuery(name = "findUserByNameAndPassword", query = "SELECT u FROM User u WHERE u.userName = :userName AND u.password = :password")})
@Table(name = "Users")
public class User implements Serializable {

    private static final long serialVersionUID = -1774213830687034509L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private int version;

    @Column
    @NotNull
    private String userName;

    @Column
    @NotNull
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @OneToMany(mappedBy = "byUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JugSession> submissions = new ArrayList<>();

    public User() {
    }

    public User(final String userName, final String password, final String firstName, final String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public List<JugSession> getSubmissions() {
        return this.submissions;
    }

    public void setSubmissions(final List<JugSession> submissions) {
        this.submissions = submissions;
    }

    @Override
    public String toString() {
        return "User{" + "userName='" + this.userName + '\'' + ", firstName='" + this.firstName + '\'' + ", lastName='"
               + this.lastName + '\'' + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        final User user = (User) o;
        return Objects.equals(this.userName, user.userName) && Objects.equals(this.password, user.password)
               && Objects.equals(this.firstName, user.firstName) && Objects.equals(this.lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userName, this.password, this.firstName, this.lastName);
    }

}
