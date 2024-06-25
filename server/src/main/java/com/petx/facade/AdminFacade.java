package com.petx.facade;

import com.petx.api.dto.admin.AdminDTO;
import com.petx.api.dto.admin.RegistroPet;
import com.petx.api.dto.admin.UuidDTO;
import com.petx.domain.admin.Admin;
import com.petx.domain.admin.UuidQRCodeGerado;
import com.petx.domain.pet.Pet;
import com.petx.mapper.admin.AdminMapper;
import com.petx.service.admin.AdminService;
import com.petx.service.security.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AdminFacade {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminMapper mapper;

    @Autowired
    private JwtServiceImpl jwtService;

    public Map<String, Object> autenticar(AdminDTO loginAdminDTO) {
        Admin admin = mapper.toEntity(loginAdminDTO);
        Admin adminLogado = adminService.autenticar(admin);
        String token = jwtService.gerarTokenAdmin(adminLogado);

        Map<String, Object> adminMap = new HashMap<>();
        adminMap.put("usuario", adminLogado.getUsuario());
        adminMap.put("token", token);

        return adminMap;
    }

    public void criarQRCode(int qtd) {
        adminService.criarQRCode(qtd);
    }

    public void QRCodeGerado(UUID QRCodeGeradoDTO){
        UuidQRCodeGerado uuid = mapper.toEntityUuid(QRCodeGeradoDTO);
        adminService.QRCodeGerado(uuid);
    }

    public RegistroPet buscarRegistros(){
        RegistroPet registroPet = adminService.buscarRegistroPet();
        return registroPet;
    }

    public List<UuidDTO> buscarPetsNaoCadastrados() {
        List<Pet> pets = adminService.buscarPetsNaoCadastrados();
        List<UuidDTO> retornoUUIDLista = new ArrayList<>();

        for (Pet pet : pets) {
            UuidDTO retornoLink = new UuidDTO();
            retornoLink.setUUIDQRCode(pet.getUuid());
            retornoUUIDLista.add(retornoLink);
        }
        return retornoUUIDLista;
    }
}
