package com.petx.controller;

import com.petx.domain.GetQRCodeResponse;
import com.petx.domain.Pet;
import com.petx.domain.Usuario;
import com.petx.service.ServicePet;
import com.petx.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ServicePet servicePet;

    @Autowired
    private ServiceUsuario serviceUsuario;

    @PostMapping("/cadastrar/usuario")
    public ResponseEntity postUsuarioController(@RequestBody Usuario usuario) {
        serviceUsuario.postUsuario(usuario);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/cadastrar/pet")
    public ResponseEntity postPetController(@RequestBody Pet pet) {
        servicePet.postPet(pet);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/consulta/pet/{uuid}")
    public GetQRCodeResponse getPetController(@PathVariable UUID uuid) {
        GetQRCodeResponse getQRCodeResponse = servicePet.getQRCode(uuid);
        return getQRCodeResponse;
    }
}