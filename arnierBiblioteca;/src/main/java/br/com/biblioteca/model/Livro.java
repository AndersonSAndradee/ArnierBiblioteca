package br.com.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "ano_publicacao", nullable = false)
    private Integer anoPublicacao; 

    // Disponibilidade do livro bolean variavel(criar) e sua lógica respectiva

    //@OneToOne(mappedBy = "livro")
    //private Emprestimo emprestimo;
    
  
    public Livro() {
    }
    

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnoPublicacao() { 
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) { 
        this.anoPublicacao = anoPublicacao;
    }

    //public Emprestimo getEmprestimo() {
        //return emprestimo;
    //}
}
