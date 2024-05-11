package com.petx.api.controller;

import com.petx.api.dto.LoginUsuarioDTO;
import com.petx.api.dto.UsuarioDTO;
import com.petx.api.dto.UsuarioLogadoDTO;
import com.petx.facade.UsuarioFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioFacade facade;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        UsuarioLogadoDTO usuarioLogado = facade.cadastrar(usuarioDTO);
        return ResponseEntity.ok(usuarioLogado);
    }

    @PostMapping("/cadastrar/gmail")
    public ResponseEntity<Object> cadastrarGoogle(@RequestBody String tokenGoogle){
        UsuarioLogadoDTO usuarioLogado = facade.cadastrarGoogle(tokenGoogle);
        return ResponseEntity.ok(usuarioLogado);
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
        UsuarioLogadoDTO usuarioLogado = facade.autenticar(loginUsuarioDTO);
        return ResponseEntity.ok(usuarioLogado);
    }

    @PostMapping("/autenticar/gmail")
    public ResponseEntity<Object> autenticarGmail(@RequestBody String token){
        UsuarioLogadoDTO usuarioLogado = facade.autenticarGoogle(token);
        return ResponseEntity.ok(usuarioLogado);
    }
}