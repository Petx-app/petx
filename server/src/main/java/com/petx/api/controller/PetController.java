package com.petx.api.controller;

import com.petx.api.dto.pet.ImagemPetDTO;
import com.petx.api.dto.pet.ListPetsDTO;
import com.petx.api.dto.pet.PetDTO;
import com.petx.facade.PetFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetFacade facade;

    @PostMapping
    public ResponseEntity cadastrar(@RequestHeader("Authorization") String token, @RequestBody @Valid PetDTO petDTO) throws IOException {
        facade.cadastrar(petDTO, token);
        return ResponseEntity.ok("Pet cadastrado!");
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> buscarUUID(@RequestHeader("Authorization") String token, @PathVariable @Valid UUID uuid) {
        PetDTO petDTO = facade.buscarUUID(uuid, token);
        return ResponseEntity.ok(petDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> buscarTodos(@RequestHeader("Authorization") String token) {
        List<ListPetsDTO> listPetsDTO = facade.buscarTodos(token);
        return ResponseEntity.ok(listPetsDTO);
    }

    @PutMapping
    public ResponseEntity<Object> atualizar(@RequestHeader("Authorization") String token, @RequestBody @Valid PetDTO petDTO) throws IOException {
        facade.atualizar(petDTO, token);
        return ResponseEntity.ok("Pet atualizado");
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deletar(@RequestHeader("Authorization") String token, @PathVariable UUID uuid) throws IOException {
        facade.deletar(uuid, token);
        return ResponseEntity.ok("Pet deletado com sucesso, a TAG QRCode pode ser reutilizado!");
    }

    @PostMapping(value = "/cadastrarImagem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> cadastrarImagem(ImagemPetDTO imagemDTO) throws IOException {
        facade.cadastrarImagemPet(imagemDTO);
        return ResponseEntity.ok("pet cadastrado!");
    }

    @PutMapping(value = "/atualizarImagem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> atualizarImagem(ImagemPetDTO imagemDTO) throws IOException {
        facade.atualizarImagemPet(imagemDTO);
        return ResponseEntity.ok("Pet atualizado!");
    }
}