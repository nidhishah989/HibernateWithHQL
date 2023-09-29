package org.NNS.DAO;



import org.NNS.DTO.BookDTO;
import org.NNS.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookDAOImpl implements BookDAO{


    @Override
    public void saveBook(Book book) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {

            getBookByISBN(book.getISBN());

        }catch (Exception e)
        {
            session.persist(book);
            t.commit();
        }

        factory.close();
        session.close();

    }


    @Override
    public List<Book> getAllBooks() {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        String hql = "from Book";
        TypedQuery<Book> typedQuery = session.createQuery(hql, Book.class);
        List<Book> bookList = typedQuery.getResultList();

        factory.close();
        session.close();

        return  bookList;
    }

    @Override
    public Book getBookByISBN(String ISBN) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        String hql = "from Book where ISBN=:ISBN";
        TypedQuery<Book> typedQuery = session.createQuery(hql, Book.class);
        typedQuery.setParameter("ISBN", ISBN);
        Book book= typedQuery.getSingleResult();

        t.commit();
        factory.close();
        session.close();

        return book;
    }

    @Override
    public BookDTO getBookById(int id) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        TypedQuery<Object[]> typedQuery = session.getNamedQuery("findBookById");
        typedQuery.setParameter("id", id);

        BookDTO bookDTO= new BookDTO();
        List<Object[]> results = typedQuery.getResultList();
        for(Object[] o : results)
        {
            System.out.println(o[0] + " "+ o[1]);
            bookDTO.setISBN((String) o[1]);
            bookDTO.setName((String) o[0]);
        }

        factory.close();
        session.close();

        return bookDTO;
    }

    @Override
    public void deleteBook(int id) {

    }


    @Override
    public void updateBook(Book book) {

    }

    @Override
    public List<Book> findBookByName(String name) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Query typedQuery = session.getNamedQuery("searchBookByName");
        typedQuery.setParameter("name", '%'+name+'%');
        List<Book> resultList= typedQuery.getResultList();

        factory.close();
        session.close();

        return resultList;
    }
}