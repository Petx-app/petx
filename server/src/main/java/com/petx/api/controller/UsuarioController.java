package com.petx.api.controller;

import com.petx.api.dto.LoginUsuarioDTO;
import com.petx.api.dto.UsuarioDTO;
import com.petx.facade.UsuarioFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscar(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = facade.buscar(id);
        return ResponseEntity.ok(usuarioDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@RequestBody @Valid UsuarioDTO usuarioDTO, @PathVariable("id") Long id) {
        facade.atualizar(usuarioDTO, id);
        return ResponseEntity.ok("Usuario salvo");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        facade.deletar(id);
        return ResponseEntity.ok("usuario deletado com sucesso");
    }

    @PostMapping("/autenticar")
    public ResponseEntity<Object> autenticar(@RequestBody @Valid LoginUsuarioDTO loginUsuarioDTO) {
        Map<String, Object> usuarioIDNome = facade.autenticar(loginUsuarioDTO);
        return ResponseEntity.ok(usuarioIDNome);
    }


}