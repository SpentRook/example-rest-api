package co.edu.unisabana.usuario.service;

import co.edu.unisabana.usuario.service.library.RegisterBookLibrary;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.model.CategoryBook;
import co.edu.unisabana.usuario.service.library.port.AddBookPort;
import co.edu.unisabana.usuario.service.library.port.RegisterBookPort;
import co.edu.unisabana.usuario.service.library.port.SearchBookPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class RegisterBookLibraryTest {
    @InjectMocks
    private RegisterBookLibrary service;
    @Mock
    private SearchBookPort searchBookPort;
    @Mock
    private AddBookPort addBookPort;
    @Mock
    private RegisterBookPort registerBookPort;

    private Book book;

    @BeforeEach
    public void setUp(){
        CategoryBook category = CategoryBook.fromString("digital");
        book = new Book("Libro A", 2022, "Jonathan B", false, category);
    }

    @Test
    public void Given_existingBook_When_registerBook_Then_return1(){
        Mockito.when(searchBookPort.validateExistsBook(book.getName())).thenReturn(true);
        int result = service.registerBook(book);
        Mockito.verify(addBookPort).addBook(book.getName());
        Assertions.assertEquals(1, result);
    }

    @Test
    public void Given_nonExistingBook_When_registerBook_Then_return2(){
        Mockito.when(searchBookPort.validateExistsBook(book.getName())).thenReturn(false);
        int result = service.registerBook(book);
        Mockito.verify(registerBookPort).registerBook(book);
        Assertions.assertEquals(2, result);
    }
}
