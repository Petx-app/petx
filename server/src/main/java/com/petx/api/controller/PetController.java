package com.petx.api.controller;

import com.petx.api.dto.PetDTO;
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
    public ResponseEntity cadastrar(@RequestBody @Valid PetDTO petDTO) {
        facade.cadastrar(petDTO);
        return ResponseEntity.ok("Pet salvo");
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> buscarUUID(@PathVariable @Valid UUID uuid) {
        PetDTO petDTO = facade.buscarUUID(uuid);
        return ResponseEntity.ok(petDTO);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Object> buscarTodos(@PathVariable Long id) {
        List<PetDTO> petDTO = facade.buscarTodos(id);
        return ResponseEntity.ok(petDTO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity atualizar(@RequestBody @Valid PetDTO petDTO, @PathVariable("uuid") UUID uuid) {
        facade.atualizar(petDTO, uuid);
        return ResponseEntity.ok("Usuario atualizado");
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deletar(@PathVariable UUID uuid) {
        facade.deletar(uuid);
        return ResponseEntity.ok("Pet deletado com sucesso");
    }
}