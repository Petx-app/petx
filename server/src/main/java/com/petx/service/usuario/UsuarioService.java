package com.petx.service.usuario;

import com.petx.domain.pet.Pet;
import com.petx.domain.usuario.Usuario;
import com.petx.domain.usuario.ValidacaoEmail;
import com.petx.repository.PetRepository;
import com.petx.repository.UsuarioRepository;
import com.petx.repository.ValidacaoUsuarioRepository;
import com.petx.utils.Criptografia;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    Criptografia criptografia;

    @Autowired
    private ValidacaoUsuarioRepository validacaoUsuarioRepository;

    public Usuario cadastrar(Usuario usuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (optionalUsuario.isPresent()) {
            throw new RuntimeException("Usuario já existe");
        }
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        Optional<ValidacaoEmail> optionalValidacaoEmail = validacaoUsuarioRepository.findByEmail(usuario.getEmail());
        if(optionalValidacaoEmail.isPresent()) {
            validacaoUsuarioRepository.deleteById(optionalValidacaoEmail.get().getId());
        }
        return usuarioSalvo;
    }

    public Usuario cadastrarGoogle(Usuario usuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(usuario.getEmail().toLowerCase());
        if(optionalUsuario.isPresent()){
            throw new RuntimeException("Usuario já existe");
        }
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioSalvo;
    }

    public Usuario buscar(UUID uuid) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(uuid);
        if (optionalUsuario.isPresent()) {
            return optionalUsuario.get();
        }
        throw new EntityNotFoundException("Usuário com ID " + uuid + " não encontrado.");
    }

    public void atualizar(Usuario usuarioAtualizar, UUID uuid) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(uuid);
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

    public void deletar(UUID uuid) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(uuid);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            List<Pet> pets = petRepository.findByDonoUuid(usuario.getUuid());
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
            throw new EntityNotFoundException("erro: Usuário com ID " + uuid + " não encontrado. Não é possível deletar.");
        }
    }

    public Usuario autenticar(Usuario usuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (optionalUsuario.isPresent()) {
            Usuario usuarioBanco = optionalUsuario.get();
            usuario.setSenha(criptografia.getSenhaSalto(usuario.getSenha(), usuarioBanco.getUuid()));

            if (passwordEncoder.matches(usuario.getSenha(), usuarioBanco.getSenha())) {
                return usuarioBanco;
            }
            throw new RuntimeException("Senha Incorreta");
        }
        throw new EntityNotFoundException("Usuario nao encontrado");
    }

    public Usuario autenticarGoogle(Usuario usuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (optionalUsuario.isPresent()) {
            Usuario usuarioBanco = optionalUsuario.get();
            if (usuario.getEmail().equals(usuarioBanco.getEmail())) {
                return usuarioBanco;
            }
            throw new RuntimeException("Email Incorreto");
        }
        throw new EntityNotFoundException("Usuario nao encontrado");
    }
}