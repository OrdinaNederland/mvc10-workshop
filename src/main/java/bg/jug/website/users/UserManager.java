package bg.jug.website.users;

import bg.jug.website.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Ivan St. Ivanov`
 */
@Stateless
public class UserManager {

    @PersistenceContext
    private EntityManager em;

    public List<User> getAllUsers() {
        return this.em.createNamedQuery("getAllUsers", User.class)
                      .getResultList();
    }

    public User getUser(final String userName, final String password) {
        final TypedQuery<User> query = this.em.createNamedQuery("findUserByNameAndPassword", User.class);
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        try {
            return query.getSingleResult();
        } catch (final NoResultException nre) {
            return null;
        }
    }
}
