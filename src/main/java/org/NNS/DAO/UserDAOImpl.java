package org.NNS.DAO;

import org.NNS.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
public class UserDAOImpl implements UserDAO {

    @Override
    public void saveUser(User user) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        session.persist(user);

        t.commit();
        factory.close();
        session.close();
    }

    @Override
    public User getUserByID(int userid) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        String hql = "from User where id=:id";
        TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
        typedQuery.setParameter("id", userid);
        User user = typedQuery.getSingleResult();

        t.commit();
        factory.close();
        session.close();
        return user;
    }

    @Override
    public void getUserByID() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        String hql = "FROM User E WHERE E.id > 2 ORDER BY E.salary DESC";
        TypedQuery query = session.createQuery(hql);
        List<User> results = query.getResultList();
        for (User u : results) {
            System.out.println("User Id: " +u.getId() + "|" +
                    " Full name:" + u.getFirstName() + " " + u.getLastName() +"|"+
                    "Email: " + u.getEmail() +"|"+ "password" + u.getPassword());
        }

    }

    @Override
    public void printUserNameAndSalary() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        TypedQuery<Object[]> queryy = session.createQuery(
                "SELECT U.firstName,U.lastName,U.salary FROM User AS U", Object[].class);
        List<Object[]> resultss = queryy.getResultList();
        for (Object[] a : resultss) {
            System.out.println("First Name: "+a[0]+" LastName: "+a[1]+" Salary: " + a[2]);
        }

    }

    @Override
    public void  getmaxSalary()
    {
        SessionFactory factory = new   Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        String hql = "SELECT max(U.salary) FROM User U";
        TypedQuery query = session.createQuery(hql);
        double result =(double) query.getSingleResult();
        System.out.println(result);
    }

    @Override
    public void getmaxSalaryGroupBy()
    {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        String hql = "SELECT U.city,SUM(U.salary) FROM User U GROUP BY U.city";
        TypedQuery query = session.createQuery(hql);
        List<Object[]> result =query.getResultList();
        for (Object[] o : result) {
            System.out.println("City: " +o[0] +" | Total salary: "+ o[1] );
        }
    }


    @Override
    public void updateUserRecord(User user) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        if (getUserByID(user.getId()) != null) {
            session.merge(user);
        }
        t.commit();
        factory.close();
        session.close();
    }


    @Override
    public void deleteUserRecord(int userid) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        User currentuser = getUserByID(userid);
        if (currentuser != null) {
            session.remove(currentuser);
        }
        t.commit();
        factory.close();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        String hql = "from User";
        TypedQuery<User> typedQuery = session.createQuery(hql);
        List<User> userList = typedQuery.getResultList();

        factory.close();
        session.close();

        return userList;
    }

    @Override
    public void printAllUsers() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        String hql = "select u from User u";
        TypedQuery<User> typedQuery = session.createQuery(hql);
        List<User> userList = typedQuery.getResultList();

        factory.close();
        session.close();

        for (User u : userList) {
            System.out.println("User Id: " + u.getId() + "|" +
                    " Full name:" + u.getFirstName() + " " + u.getLastName() + "|" +
                    "Email: " + u.getEmail() + "|" + "password" + u.getPassword());

        }


    }

}