package com.petx.api.controller;

import com.petx.api.dto.Usuario.*;
import com.petx.facade.UsuarioFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioFacade facade;

    @Tag(name = "Public API")
    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        UsuarioLogadoDTO usuarioLogado = facade.cadastrar(usuarioDTO);
        return ResponseEntity.ok(usuarioLogado);
    }

    @Tag(name = "Public API")
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

    @Tag(name = "Public API")
    @PostMapping("/autenticar")
    public ResponseEntity<Object> autenticar(@RequestBody @Valid LoginUsuarioDTO loginUsuarioDTO) {
        UsuarioLogadoDTO usuarioLogado = facade.autenticar(loginUsuarioDTO);
        return ResponseEntity.ok(usuarioLogado);
    }

    @Tag(name = "Public API")
    @PostMapping("/autenticar/gmail")
    public ResponseEntity<Object> autenticarGmail(@RequestBody String token){
        UsuarioLogadoDTO usuarioLogado = facade.autenticarGoogle(token);
        return ResponseEntity.ok(usuarioLogado);
    }

    @Tag(name = "Public API")
    @PostMapping("/validar/email")
    public ResponseEntity<Object> validarEmail(@RequestBody @Valid EmailDTO emailDTO) {
        facade.validarEmail(emailDTO);
        return ResponseEntity.ok("email enviado");
    }

    @Tag(name = "Public API")
    @PostMapping("/validar/esquecer-senha")
    public ResponseEntity<Object> esqueceuSenha(@RequestBody @Valid EmailDTO emailDTO) {
        facade.esqueceuSenha(emailDTO);
        return ResponseEntity.ok("email enviado");
    }

    @Tag(name = "Public API")
    @PostMapping("/validar/link/{codigoValidacao}")
    public ResponseEntity<Object> validarLink(@PathVariable UUID codigoValidacao ) {
        facade.validarLink(codigoValidacao);
        return ResponseEntity.ok("link valido");
    }

    @Tag(name = "Public API")
    @PutMapping("/validar/trocar-senha/{codigoValidacao}")
    public ResponseEntity<Object> trocarSenha(@RequestBody @Valid TrocarSenhaDTO trocarSenhaDTO, @PathVariable UUID codigoValidacao ) {
        facade.trocarSenha(trocarSenhaDTO, codigoValidacao);
        return ResponseEntity.ok("senha trocada");
    }
}