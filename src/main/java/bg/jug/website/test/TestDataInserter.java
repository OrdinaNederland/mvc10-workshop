package bg.jug.website.test;

import bg.jug.website.entities.JugSession;
import bg.jug.website.entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ivan St. Ivanov
 */
@Singleton
@Startup
public class TestDataInserter {

    public static User DEFAULT_USER;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void insertTestData() {
        this.em.createQuery("DELETE FROM JugSession")
               .executeUpdate();
        this.em.createQuery("DELETE FROM User")
               .executeUpdate();

        final User mitya = new User("mitya", "mitya", "Dmitriy", "Alexandrov");
        final User vassil = new User("vassil", "vassil", "Vassil", "Dichev");
        final User nayden = new User("nayden", "nayden", "Nayden", "Gochev");
        final User martin = new User("martin", "martin", "Martin", "Toshev");

        DEFAULT_USER = nayden;

        final JugSession nashorn = new JugSession("Nashorn: JavaScript runtime for JVM",
                                                  "- Java(Script) - overview of Nashorn\n" +
                                                  "- Description of the IDE for developing Nashorn applications\n" +
                                                  "- Project Avatar", mitya);
        final JugSession scala = new JugSession("Scala- one step ahead",
                                                "Did you know that the current javac compiler is written by the "
                                                + "creator of the Scala language?", vassil);
        final JugSession springMvc = new JugSession("Introduction to Spring & Spring MVC",
                                                    "The lecture will explain the idea of Spring and IoC conrainers, "
                                                    + "then we will look at Spring MVC which "
                                                    + "is the most commonly used MVC framework in the Java space.",
                                                    nayden);
        final JugSession activeMq = new JugSession("The RabbitMQ message broker",
                                                   "The session will provide an overview of the Rabbit messaging "
                                                   + "broker along with a demonstration on "
                                                   + "the various types of messaging patterns implemented in terms of"
                                                   + " the broker.", martin);

        final JugSession mvc10 = new JugSession("JSR 371: MVC 1.0",
                                                "Hi friends! We kick off the 2015/2016 activities with a workshop on "
                                                + "MVC 1.0. It is a new spec, that" +
                                                " is coming in Java EE 8 and is being defined by JSR 371 so we will "
                                                + "kick in the adopt a JSR " +
                                                "initiative as well.", nayden);

        mitya.getSubmissions()
             .add(nashorn);
        vassil.getSubmissions()
              .add(scala);
        nayden.getSubmissions()
              .add(springMvc);
        martin.getSubmissions()
              .add(activeMq);
        nayden.getSubmissions()
              .add(mvc10);

        this.em.persist(mitya);
        this.em.persist(vassil);
        this.em.persist(nayden);
        this.em.persist(martin);
    }

}
