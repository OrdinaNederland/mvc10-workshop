package org.jcp.hol.mvc.sessions;

import org.jcp.hol.mvc.entities.JugSession;
import org.jcp.hol.mvc.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Ivan St. Ivanov
 */
@Stateless
public class SessionManager {

    @PersistenceContext
    private EntityManager em;

    public List<JugSession> getAllSessions() {
        return this.em.createNamedQuery("getAllSessions", JugSession.class)
                      .getResultList();
    }

    public List<JugSession> getSessionsForUser(final User user) {
        final TypedQuery<JugSession> query = this.em.createNamedQuery("findSessionsByUser", JugSession.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public JugSession submitSession(final JugSession newSession) {
        this.em.persist(newSession);
        return newSession;
    }
}
