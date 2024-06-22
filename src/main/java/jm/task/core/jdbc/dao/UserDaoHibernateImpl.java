package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
   private static final SessionFactory sf = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String createUsersTableSQL = "CREATE TABLE IF NOT EXISTS users (Id INT AUTO_INCREMENT PRIMARY KEY , Name VARCHAR(20) NOT NULL,LastName VARCHAR(20) NOT NULL, Age SMALLINT NOT NULL);";
        try(Session session = sf.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(createUsersTableSQL).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = sf.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery ("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = sf.getCurrentSession()){
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = sf.getCurrentSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        }
        }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try(Session session = sf.getCurrentSession()){
            session.beginTransaction();
            users = session.createQuery("from User").list();
            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = sf.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
