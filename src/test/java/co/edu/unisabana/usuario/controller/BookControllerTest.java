package co.edu.unisabana.usuario.controller;

import co.edu.unisabana.usuario.AbstractTest;
import co.edu.unisabana.usuario.dto.BookDto;
import co.edu.unisabana.usuario.dto.BookReponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookControllerTest extends AbstractTest {

    private static final String PATH_REGISTER_BOOK = "/book/register";
    private static final String PATH_DELETER_BOOK = "/book/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void Given_BasicTest_When_callData_Then_RegisterData() {
        BookDto dto = new BookDto("Book 1", 2003, "Pedro", "Commercial", "suave");

        ResponseEntity<BookReponse> result =
                restTemplate.postForEntity(PATH_REGISTER_BOOK, dto, BookReponse.class);
        assertEquals("Nuevo libro registrado", result.getBody().getData());
    }

    @Test
    public void Given_BasicTest_When_callData_Then_UpdateData() {
        BookDto dto = new BookDto("Book 2", 2003, "Pedro", "Commercial", "suave");
        restTemplate.postForEntity(PATH_REGISTER_BOOK, dto, BookReponse.class);

        ResponseEntity<BookReponse> result2 = restTemplate.postForEntity(PATH_REGISTER_BOOK, dto, BookReponse.class);
        assertEquals("Cantidad actualizada", result2.getBody().getData());

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

}
