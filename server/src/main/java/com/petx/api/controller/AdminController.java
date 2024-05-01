package com.petx.api.controller;

import com.petx.api.dto.AdminDTO;
import com.petx.api.dto.UUIDRetornoDTO;
import com.petx.facade.AdminFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminFacade adminFacade;

    @PostMapping("/pet/{qtd}")
    public ResponseEntity criarQRCode(@PathVariable int qtd) {
        adminFacade.criarQRCode(qtd);
        return ResponseEntity.ok("Total de " + qtd + " registros de pet criado(s)");
    }

    @GetMapping("/pets-nao-cadastrados")
    public ResponseEntity buscarPetsNaoCadastrados() {
        List<UUIDRetornoDTO> listUUIDs = adminFacade.buscarPetsNaoCadastrados();
        return ResponseEntity.ok(listUUIDs);
    }

    @GetMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody AdminDTO adminDTO) {
        Map<String, Object> adminUsuario = adminFacade.autenticar(adminDTO);
        return ResponseEntity.ok(adminUsuario);
    }
}