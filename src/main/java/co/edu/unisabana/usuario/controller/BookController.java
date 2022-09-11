package co.edu.unisabana.usuario.controller;

import co.edu.unisabana.usuario.dto.BookDto;
import co.edu.unisabana.usuario.dto.BookReponse;
import co.edu.unisabana.usuario.service.library.DeleterBookLibrary;
import co.edu.unisabana.usuario.service.library.RegisterBookLibrary;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private final RegisterBookLibrary registerBookLibrary;
    private final DeleterBookLibrary deleterBookLibrary;


    public BookController(RegisterBookLibrary registerBookLibrary, DeleterBookLibrary deleterBookLibrary) {
        this.registerBookLibrary = registerBookLibrary;
        this.deleterBookLibrary = deleterBookLibrary;
    }

    // Pendiente validar uso de exception handler
    // explicar camel case
    @PostMapping("/register")
    @ResponseBody
    public BookReponse registerBook(@RequestBody BookDto bookDto) {
        int result = registerBookLibrary.registerBook(bookDto.toModel());
        if (result == 1) {
            return new BookReponse("Cantidad actualizada");
        }
        return new BookReponse("Nuevo libro registrado");


    }

    @DeleteMapping("/{name}")
    @ResponseBody
    public BookReponse deleterBook(@PathVariable String name) {
        int result = deleterBookLibrary.deleteBook(name);
        if (result == 1) {
            return new BookReponse("Libro eliminado");
        }
        return new BookReponse("No se ha podido eliminar el libro debido a que no se ha encontrado");
    }


    @GetMapping("/search")
    public String registerBook() {
        return "hola";
    }
}
