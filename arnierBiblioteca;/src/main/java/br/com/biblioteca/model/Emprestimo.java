package br.com.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "livro_id", nullable = false, referencedColumnName = "id")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, referencedColumnName = "id")
    private Usuario usuario;


    @Column(name = "data_de_entrega", nullable = false)
    private LocalDate dataDeEntrega;
    
    //A data de entraga não pode ser menor que a data atual na hora da criação
    @Column(name = "entrega_realizada")
    private boolean entregaRealizada;


    public Emprestimo() {
        
    }
    

    public Long getId() {
        return id;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Livro getLivro() {
        return livro;
    }


    public LocalDate getDataDeEntrega() {
        return dataDeEntrega;
    }

    public void setDataDeEntrega(LocalDate dataDeEntrega) {
        this.dataDeEntrega = dataDeEntrega;
    }

    public boolean isEntregaRealizada() {
        return entregaRealizada;
    }

    public void setEntregaRealizada(boolean entregaRealizada) {
        this.entregaRealizada = entregaRealizada;
    }

    

    public Usuario getUsuario() {
        return usuario;
    }
}
