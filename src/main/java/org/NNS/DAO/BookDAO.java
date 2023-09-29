package org.NNS.DAO;



import org.NNS.DTO.BookDTO;
import org.NNS.entity.Book;


import java.util.List;

public interface BookDAO {

    List<Book> getAllBooks();

    Book getBookByISBN(String ISBN);

    BookDTO getBookById(int id);

    void deleteBook(int id);

    void saveBook(Book book);

    void updateBook(Book book);

    List<Book> findBookByName(String name);

}
