package com.petx.api.controller;

import com.petx.api.dto.GetQRCodeResponseDTO;
import com.petx.api.exceptions.PetNotCadastro;
import com.petx.facade.QRCodeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    @Autowired
    private QRCodeFacade facade;

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> buscarQRCode(@PathVariable UUID uuid) throws PetNotCadastro {
        GetQRCodeResponseDTO getQRCodeResponseDTO = facade.buscarQRCode(uuid);
        return ResponseEntity.ok(getQRCodeResponseDTO);
    }
}