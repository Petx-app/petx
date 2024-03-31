package com.petx.controller;

import com.petx.domain.GetQRCodeResponse;
import com.petx.domain.Pet;
import com.petx.domain.Usuario;
import com.petx.service.ServicePet;
import com.petx.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ServicePet servicePet;
    ServiceUsuario serviceUsuario = new ServiceUsuario();

//    @PostMapping("/registrar/usuario")
//    public void postUsuarioController(@RequestBody Usuario usuario) {
//        serviceUsuario.postUsuario(usuario);
//    }
//
//    @PostMapping("/registrar/pet")
//    public void postPetController(@RequestBody Pet pet){
//        servicePet.postPet(pet);
//    }

    @GetMapping("/consulta/pet/{id}")
    public GetQRCodeResponse getPetController(@PathVariable UUID id){
         GetQRCodeResponse getQRCodeResponse  = servicePet.getQRCode(id);

         return getQRCodeResponse;
    }
}
