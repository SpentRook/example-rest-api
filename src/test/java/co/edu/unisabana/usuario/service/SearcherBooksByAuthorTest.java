package co.edu.unisabana.usuario.service;


import co.edu.unisabana.usuario.exception.NoContentException;
import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import co.edu.unisabana.usuario.service.library.SearcherBooksByAuthor;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.model.CategoryBook;
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
import org.springframework.util.Assert;


import java.lang.reflect.Array;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class SearcherBooksByAuthorTest {

    @InjectMocks
    private SearcherBooksByAuthor service;

    @Mock
    private SearchBookPort searchBookPort;

    @Mock
    private RegisterBookPort registerBookPort;

    private Book book;
    private ArrayList<BookEntity> books = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        CategoryBook category = CategoryBook.fromString("digital");
        book = new Book("Libro A", 2022, "Jonathan B", false, category);
    }

    @Test
    public void Given_existing_books_for_author_When_searchBooksByAuthor_Then_return_1_book(){
        BookEntity bookEntity = BookEntity.fromModel(book);
        books.add(bookEntity);
        Mockito.when(searchBookPort.searchBooksByAuthor(book.getAuthor())).thenReturn(books);
        ArrayList<BookEntity> result = service.searchBooksByAuthor(book.getAuthor());
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void Given_non_existing_books_for_author_When_searchBooksByAuthor_Then_throwException(){
        Mockito.when(searchBookPort.searchBooksByAuthor(book.getAuthor())).thenReturn(new ArrayList<>());
        Assertions.assertThrows(NoContentException.class, () -> {
            service.searchBooksByAuthor(book.getAuthor());
        });
    }
}
