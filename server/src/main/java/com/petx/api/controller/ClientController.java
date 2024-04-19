package com.petx.api.controller;

import com.petx.api.dto.GetQRCodeResponseDTO;
import com.petx.api.dto.LoginUsuarioDTO;
import com.petx.api.dto.PetDTO;
import com.petx.api.dto.UsuarioDTO;
import com.petx.api.exceptions.PetNotCadastro;
import com.petx.facade.PetFacade;
import com.petx.facade.UsuarioFacade;
import com.petx.service.PetService;
import com.petx.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private PetService petService;

    @Autowired
    private PetFacade petFacade;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioFacade usuarioFacade;

    @PostMapping("/usuario")
    public ResponseEntity<Object> postUsuarioController(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Map<String, Object> UsuarioIDNome = usuarioFacade.cadastrar(usuarioDTO);
        return ResponseEntity.ok(UsuarioIDNome);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Object> getUsuarioControler(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioFacade.buscar(id);
        return ResponseEntity.ok(usuarioDTO);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity putUsuarioController(@RequestBody @Valid UsuarioDTO usuarioDTO, @PathVariable("id") Long id) {
        usuarioFacade.atualizar(usuarioDTO, id);
        return ResponseEntity.ok("Usuario salvo");
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<String> deleteUsuarioController(@PathVariable Long id) {
        usuarioFacade.deletar(id);
        return ResponseEntity.ok("usuario deletado com sucesso");
    }

    @GetMapping("/autenticar-usuario")
    public ResponseEntity<Object> autenticarUsuarioController(@RequestBody @Valid LoginUsuarioDTO loginUsuarioDTO) {
        Map<String, Object> usuarioIDNome = usuarioFacade.autenticar(loginUsuarioDTO);
        return ResponseEntity.ok(usuarioIDNome);
    }

    @PostMapping("/pet")
    public ResponseEntity postPetController(@RequestBody @Valid PetDTO petDTO) {
        petFacade.cadastrar(petDTO);
        return ResponseEntity.ok("Pet salvo");
    }

    @GetMapping("/pet/{uuid}")
    public ResponseEntity<Object> getPetController(@PathVariable @Valid UUID uuid) {
        PetDTO petDTO = petFacade.buscarUUID(uuid);
        return ResponseEntity.ok(petDTO);
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Object> getPetsController(@PathVariable Long id) {
        List<PetDTO> petDTO = petFacade.buscarTodos(id);
        return ResponseEntity.ok(petDTO);
    }

    @PutMapping("/pet/{uuid}")
    public ResponseEntity putPetController(@RequestBody @Valid PetDTO petDTO, @PathVariable("uuid") UUID uuid) {
        petFacade.atualizar(petDTO, uuid);
        return ResponseEntity.ok("usuario atualizado");
    }

    @DeleteMapping("/pet/{uuid}")
    public ResponseEntity<String> deletePetController(@PathVariable UUID uuid) {
        petFacade.deletar(uuid);
        return ResponseEntity.ok("pet deletado com sucesso");
    }

    @GetMapping("/qrcode/{uuid}")
    public ResponseEntity<Object> getPetQRCodeController(@PathVariable UUID uuid) throws PetNotCadastro {
        GetQRCodeResponseDTO getQRCodeResponseDTO = petFacade.buscarQRCode(uuid);
        return ResponseEntity.ok(getQRCodeResponseDTO);
    }
}