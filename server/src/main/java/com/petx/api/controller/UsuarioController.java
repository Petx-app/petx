package com.petx.api.controller;

import com.petx.api.dto.LoginUsuarioDTO;
import com.petx.api.dto.UsuarioDTO;
import com.petx.facade.UsuarioFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioFacade facade;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Map<String, Object> UsuarioIDNome = facade.cadastrar(usuarioDTO);
        return ResponseEntity.ok(UsuarioIDNome);
    }

    @GetMapping
    public ResponseEntity<Object> buscar(@RequestHeader("Authorization") String token) {
        UsuarioDTO usuarioDTO = facade.buscar(token);
        return ResponseEntity.ok(usuarioDTO);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestHeader("Authorization") String token, @RequestBody @Valid UsuarioDTO usuarioDTO) {
        facade.atualizar(usuarioDTO, token);
        return ResponseEntity.ok("Usuario salvo");
    }

    @DeleteMapping
    public ResponseEntity<String> deletar(@RequestHeader("Authorization") String token) {
        facade.deletar(token);
        return ResponseEntity.ok("usuario deletado com sucesso");
    }

    @PostMapping("/autenticar")
    public ResponseEntity<Object> autenticar(@RequestBody @Valid LoginUsuarioDTO loginUsuarioDTO) {
        Map<String, Object> usuarioIDNome = facade.autenticar(loginUsuarioDTO);
        return ResponseEntity.ok(usuarioIDNome);
    }
}