package br.com.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.service.LivroService;

import java.util.List;
import java.util.Optional;
import java.util.Collections;


@RestController
@RequestMapping("/biblioteca")
public class LivroController {

    @Autowired
    private LivroService meuService;

    //Endpoint para cadastro de Livro (Post) = localhost:8080/biblioteca/livro
    @PostMapping("/livro")
    public ResponseEntity<Livro> cadastrarLivro(@RequestBody Livro livro) {
        if (!meuService.camposObrigatoriosPreenchidos(livro)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Se os campos obrigatórios não estiverem preenchidos, retorna status '400 Bad Request'
    }
    
        // Se os campos obrigatórios estiverem preenchidos, cadastra o novo livro
        Livro livroSalvo = meuService.cadastrarLivro(livro);
        return ResponseEntity.ok().body(livroSalvo);
    }

    //Endpoint para buscar o livro por ID (GET) = localhost:8080/biblioteca/livro/{idlLivro}
    @GetMapping("/livro/{idLivro}")
    public ResponseEntity<Optional<Livro>> buscaLivroPorId(@PathVariable("idLivro") Long idLivro) {
        Optional<Livro> livro = meuService.buscarLivroPorId(idLivro);
        if (livro.isPresent()) 
            return ResponseEntity.ok().body(livro);

        return ResponseEntity.notFound().build();    
    }

    //Endpoint para buscar o livro por titulo (GET) = localhost:8080/biblioteca/livro/titulo/{titulo}
    @GetMapping("/livro/titulo/{titulo}")
    public ResponseEntity<List<Livro>> buscarLivroPorTitulo(@PathVariable("titulo") String titulo) {
        List<Livro> livros = meuService.buscarLivroPorTitulo(titulo);
        if (!livros.isEmpty())
            return ResponseEntity.ok().body(livros);
        else
            return ResponseEntity.ok().body(Collections.emptyList());
        }


     //Endpoint para buscar todos os livros (GET) = localhost:8080/biblioteca/livros
    @GetMapping("/livros")
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> livros = meuService.listarLivros();
        return ResponseEntity.ok(livros);
     }
    
}
