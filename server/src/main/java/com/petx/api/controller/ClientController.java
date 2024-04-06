package com.petx.api.controller;

import com.petx.api.dto.GetQRCodeResponseDTO;
import com.petx.api.exceptions.PetNotCadastro;
import com.petx.api.exceptions.PetNotFound;
import com.petx.domain.GetQRCodeResponse;
import com.petx.domain.Pet;
import com.petx.domain.Usuario;
import com.petx.facade.PetFacade;
import com.petx.service.PetService;
import com.petx.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private PetService petService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PetFacade petFacade;

    @PostMapping("/cadastrar/usuario")
    public ResponseEntity postUsuarioController(@RequestBody Usuario usuario) {
        usuarioService.postUsuario(usuario);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/cadastrar/pet")
    public ResponseEntity postPetController(@RequestBody Pet pet) {
        petService.postPet(pet);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/consulta/pet/{uuid}")
    public ResponseEntity<Object> getPetController(@PathVariable UUID uuid) {
        try{
            GetQRCodeResponseDTO getQRCodeResponseDTO = petFacade.getQRCode(uuid);
            return ResponseEntity.status(HttpStatus.OK).body(getQRCodeResponseDTO);
        }catch(PetNotFound | PetNotCadastro e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}