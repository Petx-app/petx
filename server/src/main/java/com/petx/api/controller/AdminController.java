package com.petx.api.controller;

import com.petx.api.dto.AdminDTO;
import com.petx.api.dto.UUIDRetornoDTO;
import com.petx.facade.AdminFacade;
import com.petx.facade.PetFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PetFacade petFacade;
    @Autowired
    private AdminFacade adminFacade;

    @PostMapping("/pet/{qtd}")
    public ResponseEntity postPetAdmin(@PathVariable int qtd) {
        adminFacade.criarQRCode(qtd);
        return ResponseEntity.ok("Total de " + qtd + " registros de pet criado(s)");
    }

    @GetMapping("/pets-nao-cadastrados")
    public ResponseEntity getPetsVazios() {
        List<UUIDRetornoDTO> listUUIDs = adminFacade.buscarPetsNaoCadastrados();
        return ResponseEntity.ok(listUUIDs);
    }

    @GetMapping("/autenticar")
    public ResponseEntity autenticarAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO dto = adminFacade.autenticar(adminDTO);
        return ResponseEntity.ok(dto);
    }
}