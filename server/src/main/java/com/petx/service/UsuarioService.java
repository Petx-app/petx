package com.petx.service;

import com.petx.domain.Usuario;
import com.petx.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void postUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

}