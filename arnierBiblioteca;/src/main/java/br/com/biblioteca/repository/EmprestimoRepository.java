package br.com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.biblioteca.model.Emprestimo;
import java.util.List;



@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    // Método para contar os empréstimos ativos de um usuário específico
    Long countByUsuarioIdAndEntregaRealizada(Long usuarioId, boolean entregaRealizada);

    // Método para buscar empréstimos de um usuário específico com uma determinada entrega realizada
    List<Emprestimo> findAllByUsuarioIdAndEntregaRealizada(Long usuarioid ,boolean entregaRealizada);
    //boolean existsByUsuarioIdAndLivroId(Long usuario, Long livro, boolean entregaRealizada);
    Emprestimo findOneByUsuarioIdAndLivroIdAndEntregaRealizada(Long usuarioid, Long livroid, boolean entregaRealizada);
}
