package br.com.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.service.EmprestimoService;
import br.com.biblioteca.service.EmprestimoService.EmprestimoNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/biblioteca")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;


    // Endpoint para cadastrar um novo empréstimo (Post): localhost:8080/biblioteca/emprestimo
    @PostMapping("/emprestimo")
    public ResponseEntity<?> cadastrarEmprestimo(@RequestBody Emprestimo emprestimo) {
        try {
            ResponseEntity<?> resposta = this.emprestimoService.cadastrarEmprestimo(emprestimo);
            return resposta;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro interno no servidor.");
        }
    }

    // Endpoint para buscar um empréstimo pelo ID (Get): localhost:8080/biblioteca/emprestimo/{idEmprestimo}
    @GetMapping("/emprestimo/{idEmprestimo}")
    public ResponseEntity<Emprestimo> buscarEmprestimoPorId(@PathVariable("idEmprestimo") Long idEmprestimo) {
        Optional<Emprestimo> emprestimo = this.emprestimoService.buscarEmprestimoPorId(idEmprestimo);
        return emprestimo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para listar todos os empréstimos (Get): localhost:8080/biblioteca/emprestimos
    @GetMapping("/emprestimos")
    public ResponseEntity<List<Emprestimo>> listarEmprestimos() {
        List<Emprestimo> emprestimos = this.emprestimoService.listarEmprestimo();
        return ResponseEntity.ok(emprestimos);
    }

    
    // Endpoint para atualizar um empréstimo (Put): localhost:8080/biblioteca/emprestimo/{idEmprestimo}
    @PutMapping("/emprestimo/{idEmprestimo}")
    public ResponseEntity<Emprestimo> atualizarEmprestimo(@PathVariable("idEmprestimo") Long idEmprestimo) {
        try {
            Emprestimo emprestimo = this.emprestimoService.atualizarEmprestimo(idEmprestimo);
            return ResponseEntity.ok(emprestimo);
        } catch (EmprestimoNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    
}