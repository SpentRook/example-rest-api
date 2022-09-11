package co.edu.unisabana.usuario.controller;

import co.edu.unisabana.usuario.AbstractTest;
import co.edu.unisabana.usuario.dto.BookDto;
import co.edu.unisabana.usuario.dto.BookListResponse;
import co.edu.unisabana.usuario.dto.BookReponse;
import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookControllerTest extends AbstractTest {

    private static final String PATH_REGISTER_BOOK = "/book/register";
    private static final String PATH_DELETER_BOOK = "/book/";
    private static final String PATH_SEARCH_BOOKS = "/book/search?author=";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void Given_bookData_When_registerBook_Then_add_book_to_list() {
        BookDto dto = new BookDto("Book 1", 2003, "Pedro", "Commercial", "suave");
        ResponseEntity<BookReponse> result = restTemplate.postForEntity(PATH_REGISTER_BOOK, dto, BookReponse.class);
        assertEquals("Nuevo libro registrado", result.getBody().getData());
    }

    @Test
    public void Given_Book1_When_registerBook_Then_increase_quantity_in_one() {
        BookDto dto = new BookDto("Book 1", 2003, "Pedro", "Commercial", "suave");
        restTemplate.postForEntity(PATH_REGISTER_BOOK, dto, BookReponse.class);
        ResponseEntity<BookReponse> result = restTemplate.postForEntity(PATH_REGISTER_BOOK, dto, BookReponse.class);
        assertEquals("Actualizada cantidad", result.getBody().getData());

    }

    @Test
    public void Given_ExistingName_When_callData_Then_DeleteData(){
        BookDto dto = new BookDto("Book 3", 2003, "Pedro", "Commercial", "suave");
        String pathWithVariable = PATH_DELETER_BOOK + dto.getName();
        restTemplate.postForEntity(PATH_REGISTER_BOOK, dto, BookReponse.class);

        ResponseEntity<BookReponse> result2 = restTemplate.exchange(pathWithVariable, HttpMethod.DELETE, null ,BookReponse.class);
        assertEquals("Libro eliminado", result2.getBody().getData());
    }

    @Test
    public void Given_NotExistingName_When_callData_Then_AlertNotContent(){
        String pathWithVariable = PATH_DELETER_BOOK + "Book 4";
        ResponseEntity<BookReponse> result2 = restTemplate.exchange(pathWithVariable, HttpMethod.DELETE, null ,BookReponse.class);
        assertEquals("No se ha podido eliminar el libro debido a que no se ha encontrado", result2.getBody().getData());
    }

    @Test
    public void Given_Pedro_as_author_When_searchBooksByAuthor_then_return_2_records(){
        BookDto dto = new BookDto("Book 1", 2003, "Pedro", "Commercial", "suave");
        BookDto dto2 = new BookDto("Book 2", 2003, "Pedro", "Commercial", "suave");
        restTemplate.postForEntity(PATH_REGISTER_BOOK, dto, BookReponse.class);
        restTemplate.postForEntity(PATH_REGISTER_BOOK, dto2, BookReponse.class);
        ResponseEntity<BookListResponse> result = restTemplate.getForEntity(PATH_SEARCH_BOOKS + "Pedro", BookListResponse.class);
        assertEquals(2, result.getBody().getData().size());
    }

}
