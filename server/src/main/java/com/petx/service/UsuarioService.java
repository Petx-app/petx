package com.petx.service;

import com.petx.domain.Pet;
import com.petx.domain.Usuario;
import com.petx.repository.PetRepository;
import com.petx.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario cadastrar(Usuario usuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(usuario.getEmail().toLowerCase());
        if (optionalUsuario.isPresent()) {
            throw new RuntimeException("Usuario já existe");
        }
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioSalvo;
    }

    public Usuario buscar(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            return optionalUsuario.get();
        }
        throw new EntityNotFoundException("Usuário com ID " + id + " não encontrado.");
    }

    public void atualizar(Usuario usuarioAtualizar, Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNome(usuarioAtualizar.getNome());
            usuario.setSenha(usuarioAtualizar.getSenha());
            usuario.setTelefone(usuarioAtualizar.getTelefone());
            usuarioRepository.save(usuario);
        } else {
            throw new EntityNotFoundException("Usuario nao encontrado");
        }
    }

    public void deletar(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            List<Pet> pets = petRepository.findByDonoId(usuario.getId());
            for (Pet pet : pets) {
                pet.setDono(null);
                pet.setNome(null);
                pet.setEspecie(null);
                pet.setRaca(null);
                pet.setCor(null);
                pet.setPorte(null);
                pet.setPeso(null);
                pet.setGenero(null);
                pet.setCadastrado(false);
                pet.setDataNascimento(null);
                petRepository.saveAll(pets);
            }
            usuarioRepository.delete(usuario);
        } else {
            throw new EntityNotFoundException("erro: Usuário com ID " + id + " não encontrado. Não é possível deletar.");
        }
    }

    public Usuario autenticar(Usuario usuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (optionalUsuario.isPresent()) {
            Usuario usuarioBanco = optionalUsuario.get();
            if (passwordEncoder.matches(usuario.getSenha(), usuarioBanco.getSenha())) {
                return usuarioBanco;
            }
            throw new RuntimeException("Senha Incorreta");
        }
        throw new EntityNotFoundException("Usuario nao encontrado");
    }
}