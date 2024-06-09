package br.com.biblioteca.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.biblioteca.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro,  Long> {
    public List<Livro> findByTitulo(String titulo);
}

