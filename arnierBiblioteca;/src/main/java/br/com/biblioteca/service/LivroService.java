package br.com.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.repository.LivroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository; 

    // Método para criar livro;
    public Livro cadastrarLivro(Livro livro) {
        try {
            return livroRepository.save(livro); 
        } catch (Exception e) {
            System.out.println("Erro ao tentar criar livro " + e.getMessage());   
        }
        return null;
    }

    // Método para buscar livro pelo ID;
    public Optional<Livro> buscarLivroPorId(Long id) {
        try {
            return livroRepository.findById(id); 
        } catch (Exception e) {
            System.out.println("Erro ao tentar buscar livro por Id " + e.getMessage());
            throw e;
        }
    }

    // Método para buscar livro por titulo;
    public List<Livro> buscarLivroPorTitulo(String titulo) {
        try {
            return livroRepository.findByTitulo(titulo); 
        } catch (Exception e) {
            System.out.println("Erro ao tentar buscar livro por título " + e.getMessage());
            throw e;
        }
    }

    // Método para listar todos os livros;
    public List<Livro> listarLivros() {
        try {
            return livroRepository.findAll(); 
        } catch (Exception e) {
            System.out.println("Erro ao tentar listar todos os livros " + e.getMessage());
            return null;
        }
    }


     // Método para verificar se os campos obrigatórios de um livro estão preenchidos
     public boolean camposObrigatoriosPreenchidos(Livro livro) {
        // Verifica se os campos obrigatórios estão preenchidos
        if (livro != null &&
            livro.getTitulo() != null && !livro.getTitulo().isEmpty() &&
            livro.getAnoPublicacao() != 0) {
            // Se todos os campos obrigatórios estiverem preenchidos, retorna true
            return true;
        } else {
            // Se algum campo obrigatório estiver vazio ou igual a zero, retorna false
            return false;
        }
    }
}
