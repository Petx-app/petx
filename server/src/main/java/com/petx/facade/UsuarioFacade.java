package com.petx.facade;

import com.petx.api.dto.LoginUsuarioDTO;
import com.petx.api.dto.UsuarioDTO;
import com.petx.domain.Usuario;
import com.petx.mapper.UsuarioMapper;
import com.petx.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UsuarioFacade {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioMapper mapper;

    public Map<String, Object> cadastrar(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.toEntity(usuarioDTO);
        Usuario usuarioSalvo = service.cadastrar(usuario);

        Map<String, Object> usuarioMap = new HashMap<>();
        usuarioMap.put("id", usuarioSalvo.getId());
        usuarioMap.put("nome", usuarioSalvo.getNome());

        return usuarioMap;
    }

    public UsuarioDTO buscar(Long id) {
        Usuario usuario = service.buscar(id);
        UsuarioDTO usuarioDto = mapper.toDTO(usuario);
        return usuarioDto;
    }

    public void atualizar(UsuarioDTO usuarioDTO, Long id) {
        Usuario usuario = mapper.toEntity(usuarioDTO);
        service.atualizar(usuario, id);
    }

    public void deletar(Long id) {
        service.deletar(id);
    }

    public Map<String, Object> autenticar(LoginUsuarioDTO loginUsuarioDTO) {
        Usuario usuario = mapper.toEntityLogin(loginUsuarioDTO);
        Usuario usuarioLogado = service.autenticar(usuario);

        Map<String, Object> usuarioMap = new HashMap<>();
        usuarioMap.put("id", usuarioLogado.getId());
        usuarioMap.put("nome", usuarioLogado.getNome());

        return usuarioMap;
    }
}