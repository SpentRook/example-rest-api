package co.edu.unisabana.usuario.repository.dao;

import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.model.CategoryBook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookDaoTest {
    private final BookDao bookDao = new BookDao();
    @Test
    public void Given_Book_When_RegisterBook_Then_Add_new_book_to_listBooks(){
        CategoryBook category = CategoryBook.fromString("duro");
        Book book = new Book("El señor de los anillos 1", 1954, "J. R. R. Tolkien", false, category);
        int sizeOfListBookBeforeInsert = BookDao.listBooks.size();
        bookDao.registerBook(book);
        assertEquals(sizeOfListBookBeforeInsert+1, BookDao.listBooks.size());
    }

    @Test
    public void Given_book_name_When_exists_In_listBooks_Then_Return_True(){
        CategoryBook category = CategoryBook.fromString("duro");
        Book book = new Book("El señor de los anillos 2", 1955, "J. R. R. Tolkien", false, category);
        bookDao.registerBook(book);
        assertTrue(bookDao.addBook(book.getName()));
    }

    @Test
    public void Given_book_name_When_not_exists_In_listBooks_Then_Throw_IllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->{
            bookDao.addBook("El psicoanalista");
        });
    }

    @Test
    public void Given_book_name_When_validateExistsBook_and_Not_exists_Then_Return_False(){
        assertFalse(bookDao.validateExistsBook("La caceria"));
    }

    @Test
    public void Given_book_name_When_validateExistsBook_and_exists_Then_Return_True(){
        CategoryBook category = CategoryBook.fromString("duro");
        Book book = new Book("El señor de los anillos 3", 1955, "J. R. R. Tolkien", false, category);
        bookDao.registerBook(book);
        assertTrue(bookDao.validateExistsBook(book.getName()));
    }

    @Test
    public void Given_BookListSize_When_Validate_Quantity_Is_Greater_Than_15_Then_Throw_IllegalArgumentException() {
        CategoryBook category = CategoryBook.fromString("duro");
        Book book1 = new Book("Book 1", 2000, "Test", false, category);
        bookDao.registerBook(book1);
        for (int i = 1; i <= 15;i = i + 1) {
            bookDao.addBook("Book 1");
        }
        assertThrows(IllegalArgumentException.class, ()->{
            bookDao.addBook("Book 1");
        });
    }

}
