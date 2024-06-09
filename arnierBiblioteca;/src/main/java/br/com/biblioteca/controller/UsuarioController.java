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

import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.service.UsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

@RestController
@RequestMapping("/biblioteca")
public class UsuarioController {

    @Autowired UsuarioService meuService;

    //Endpoint para cadastro de Livro (Post) = localhost:8080/biblioteca/usuario
    @PostMapping("/usuario")
    public ResponseEntity<Usuario> cadastrarLivro(@RequestBody Usuario usuario) {
        if (!meuService.camposObrigatoriosPreenchidos(usuario)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Se os campos obrigatórios não estiverem preenchidos, retorna status '400 Bad Request'
        }

        // Se os campos obrigatórios estiverem preenchidos, cadastra o novo usuário
        Usuario usuarioSalvo = meuService.cadastrarUsuario(usuario); 
        return ResponseEntity.ok().body(usuarioSalvo);

    }

    //Endpoint para buscar usuario por ID (Get) = localhost:8080/biblioteca/usuario/{idUsuario}
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Optional<Usuario>> buscaLivroPorId(@PathVariable("idUsuario") Long idUsuario) {
        Optional<Usuario> usuario = meuService.buscarUsuarioPorID(idUsuario);
        if (usuario.isPresent()) 
            return ResponseEntity.ok().body(usuario);

        return ResponseEntity.notFound().build();
    }

    //Endpoint para buscar usuario por email (Get) = localhost:8080/biblioteca/usario/email/{email}
    @GetMapping("/usuario/email/{email}")
    public ResponseEntity<List<Usuario>> buscarLivroPorTitulo(@PathVariable("email") String email) {
        List<Usuario> usuario = meuService.buscarUsuarioPorEmail(email);
        if (!usuario.isEmpty())
            return ResponseEntity.ok().body(usuario);
        else
            return ResponseEntity.ok().body(Collections.emptyList());
        }


    //Endpoint para buscar todos os livros (Get) = localhost:8080/biblioteca/usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = meuService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
     }
    
     

}
