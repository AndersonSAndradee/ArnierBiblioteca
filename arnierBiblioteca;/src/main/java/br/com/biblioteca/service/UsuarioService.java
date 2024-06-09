package br.com.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    //CPF and EMAIL unicos
    //CPF precisa ter exatamente 11 números
    @Autowired UsuarioRepository usuarioRepository;

    //Método para criação de conta;
    public Usuario cadastrarUsuario(Usuario usuario) {
        try{
            return usuarioRepository.save(usuario);
        } catch(Exception e) {
            System.out.println("Erro ao tentar criar conta " + e.getMessage());
        }
        return null;

    }

    //Método para buscar usuário por ID;
    public Optional<Usuario> buscarUsuarioPorID(Long id) {
        try {
            return usuarioRepository.findById(id); 
        } catch (Exception e) {
            System.out.println("Erro ao tentar buscar usuario por Id " + e.getMessage());
            throw e;
        }

    }

    //Método para buscar usuário por email;
    public List<Usuario> buscarUsuarioPorEmail(String email) {
        try {
            return usuarioRepository.findByEmail(email); 
        } catch (Exception e) {
            System.out.println("Erro ao tentar buscar usuário por email " + e.getMessage());
            throw e;
        }
    }

    // Método para listar todos os usuários
    public List<Usuario> listarUsuarios() {
        try {
            return usuarioRepository.findAll(); 
        } catch (Exception e) {
            System.out.println("Erro ao tentar listar todos os usuários " + e.getMessage());
            return null;
        }
    }
    
    // Método para verificar se os campos obrigatórios de usuários estão preenchidos
    public boolean camposObrigatoriosPreenchidos(Usuario usuario) {
        // Verifica se os campos obrigatórios estão preenchidos
        if (usuario != null &&
            usuario.getNome() != null && !usuario.getNome().isEmpty() &&
            usuario.getCpf() != null &&
            usuario.getEmail() != null && !usuario.getEmail().isEmpty()) {
            // Se todos os campos obrigatórios estiverem preenchidos, retorna true
            return true;
        } else {
            // Se algum campo obrigatório estiver vazio, retorna false
            return false;
        }
    }
    
}
