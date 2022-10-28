package co.edu.unisabana.usuario.service;

import co.edu.unisabana.usuario.service.library.DeleterBookLibrary;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.model.CategoryBook;
import co.edu.unisabana.usuario.service.library.port.DeleteBookPort;
import co.edu.unisabana.usuario.service.library.port.SearchBookPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DeleterBookLibraryTest {
    @InjectMocks
    private DeleterBookLibrary service;
    @Mock
    private SearchBookPort searchBookPort;
    @Mock
    private DeleteBookPort deleteBookPort;

    @Test
    public void Given_ExistingBook_When_deleteBook_Then_Return_1(){
        CategoryBook category = CategoryBook.fromString("digital");
        Book book = new Book("Libro Ejemplo 1", 2022, "Richard G.", false, category);

        Mockito.when(searchBookPort.validateExistsBook(book.getName())).thenReturn(true);
        int result = service.deleteBook(book.getName());
        Mockito.verify(deleteBookPort).deleteBook(book.getName());
        assertEquals(1, result);
    }

    @Test
    public void Given_ExistingBook_When_deleteBook_Then_Return_2(){

    }
}
