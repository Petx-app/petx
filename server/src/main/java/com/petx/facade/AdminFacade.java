package com.petx.facade;

import com.petx.api.dto.AdminDTO;
import com.petx.api.dto.UUIDRetornoDTO;
import com.petx.domain.Admin;
import com.petx.domain.Pet;
import com.petx.mapper.AdminMapper;
import com.petx.service.AdminService;
import com.petx.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminFacade {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PetService petService;

    @Autowired
    private AdminMapper mapper;

    public AdminDTO autenticar(AdminDTO loginAdminDTO) {
        Admin admin = mapper.toEntity(loginAdminDTO);
        Admin adminLogado = adminService.autenticar(admin);
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setUsuario(adminLogado.getUsuario());
        return adminDTO;
    }

    public void criarQRCode(int qtd) {
        adminService.criarQRCode(qtd);
    }

    public List<UUIDRetornoDTO> buscarPetsNaoCadastrados() {
        List<Pet> pets = adminService.buscarPetsNaoCadastrados();
        List<UUIDRetornoDTO> retornoUUIDLista = new ArrayList<>();

        for (Pet pet : pets) {
            UUIDRetornoDTO retornoUUID = new UUIDRetornoDTO();
            retornoUUID.setUuid(pet.getUuid());
            retornoUUIDLista.add(retornoUUID);
        }
        return retornoUUIDLista;
    }
}
