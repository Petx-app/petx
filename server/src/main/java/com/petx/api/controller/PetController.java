package com.petx.api.controller;

import com.petx.api.dto.pet.PetDTO;
import com.petx.facade.PetFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetFacade facade;

    @PostMapping
    public ResponseEntity cadastrar(@RequestHeader("Authorization") String token, @RequestBody @Valid PetDTO petDTO) {
        facade.cadastrar(petDTO, token);
        return ResponseEntity.ok("Pet salvo");
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> buscarUUID(@RequestHeader("Authorization") String token, @PathVariable @Valid UUID uuid) {
        PetDTO petDTO = facade.buscarUUID(uuid, token);
        return ResponseEntity.ok(petDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> buscarTodos(@RequestHeader("Authorization") String token) {
        List<PetDTO> petDTO = facade.buscarTodos(token);
        return ResponseEntity.ok(petDTO);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestHeader("Authorization") String token, @RequestBody @Valid PetDTO petDTO) {
        facade.atualizar(petDTO, token);
        return ResponseEntity.ok("Pet atualizado");
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deletar(@RequestHeader("Authorization") String token, @PathVariable UUID uuid) {
        facade.deletar(uuid, token);
        return ResponseEntity.ok("Pet deletado com sucesso");
    }
}