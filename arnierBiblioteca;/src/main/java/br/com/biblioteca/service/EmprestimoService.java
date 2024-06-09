package br.com.biblioteca.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.repository.EmprestimoRepository;
import br.com.biblioteca.utils.ErrorMessage;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    
    // Método para criação de cadastro de Emprestimo;
    public ResponseEntity<?> cadastrarEmprestimo(Emprestimo emprestimo) {
        try {   
            // Variáveis
            Long usuarioId = emprestimo.getUsuario().getId();
            Long livroId = emprestimo.getLivro().getId();
            
            
            if (this.usuarioTemLivroNaoDevolvido(usuarioId, livroId)) {
                
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setMessage("Usuário já possui este livro emprestado e não devolvido.");
                return ResponseEntity.badRequest().body(errorMessage); // Retorna a mensagem de erro

            } else if (this.usuarioPossuiTresLivrosEmprestados(usuarioId)) {

                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setMessage("Usuário já possui três livros emprestados.");
                return ResponseEntity.badRequest().body(errorMessage); // Retorna a mensagem de erro

            } else {
                // Se nenhuma das condições acima for atendida, o empréstimo pode ser cadastrado com sucesso
                Emprestimo emprestimoSalvo = this.emprestimoRepository.save(emprestimo);
                return ResponseEntity.ok(emprestimoSalvo);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Isso irá imprimir o stack trace da exceção no console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro interno no servidor.");
        }
    }
    

    // Método para buscar empréstimos pelo ID;
    public Optional<Emprestimo> buscarEmprestimoPorId(Long id) {
        try {
            return emprestimoRepository.findById(id); 
        } catch (Exception e) {
            System.out.println("Erro ao tentar buscar empréstimo do livro por Id " + e.getMessage());
            throw e;
        }
    }


    // Método para listar todos os empréstimos
    public List<Emprestimo> listarEmprestimo() {
        try {
            return this.emprestimoRepository.findAll(); 
        } catch (Exception e) {
            System.out.println("Erro ao tentar listar empréstimo dos livros " + e.getMessage());
            return null;
        }
    }
    

    // Método para atulizar os empréstimos
    public Emprestimo atualizarEmprestimo(Long idEmprestimo) {
        try {
            Optional<Emprestimo> emprestimoExistente = emprestimoRepository.findById(idEmprestimo);
            if (emprestimoExistente.isPresent()) {
                // Atualiza o campo entregaRealizada do empréstimo existente para true
                Emprestimo emprestimo = emprestimoExistente.get();
                emprestimo.setEntregaRealizada(true);
                
                // Salva e retorna o empréstimo atualizado
                return emprestimoRepository.save(emprestimo);
            } else {
                // Se o empréstimo não existir, lança uma exceção personalizada
                throw new EmprestimoNotFoundException("Empréstimo com ID " + idEmprestimo + " não encontrado.");
            }
        } catch (Exception e) {
            // Log da exceção (use uma biblioteca de logging no lugar)
            System.err.println("Erro ao tentar atualizar empréstimo: " + e.getMessage());
            throw e; // Repropaga a exceção
        }
    }

    public class EmprestimoNotFoundException extends RuntimeException {
        public EmprestimoNotFoundException(String message) {
            super(message);
        }
    }

    // Método para verificar se o usuário já possui três livros emprestados
    public boolean usuarioPossuiTresLivrosEmprestados(Long usuarioId) {
        // Conta quantos empréstimos ativos o usuário possui
        Long countEmprestimos = this.emprestimoRepository.countByUsuarioIdAndEntregaRealizada(usuarioId, false);
        // Se o número de empréstimos ativos for igual ou superior a três, retorna true
        return countEmprestimos >= 3;
    }

    // Método para verificar se o usuário está tentando solicitar livro que já possui
    public boolean usuarioTemLivroNaoDevolvido(Long usuarioId, Long livroId) {
        // Verificar se o usuário possui um empréstimo ativo do livro especificado
        //boolean possuiLivroNaoDevolvido = emprestimoRepository.existsByUsuarioIdAndLivroId(usuarioId, livroId, false);
        Emprestimo livroNaoDevolvido = this.emprestimoRepository.findOneByUsuarioIdAndLivroIdAndEntregaRealizada(usuarioId, livroId, false);
        return (livroNaoDevolvido!= null && livroNaoDevolvido.getId() != null);
        
        
        
    }
}


