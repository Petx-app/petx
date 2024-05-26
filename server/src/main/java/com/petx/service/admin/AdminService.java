package com.petx.service.admin;

import com.petx.domain.admin.Admin;
import com.petx.domain.admin.UuidQRCodeGerado;
import com.petx.domain.pet.Pet;
import com.petx.repository.AdminRepository;
import com.petx.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PetRepository petRepository;

    public void criarQRCode(int qtd) {
        if (qtd > 100) {
            throw new RuntimeException("Maximo 100 pets por vez");
        }
        for (int i = 1; i <= qtd; i++) {
            Pet pet = new Pet();
            pet.setCadastrado(false);
            pet.setQrcodeGerado(false);
            try {
                petRepository.save(pet);
            } catch (RuntimeException e) {
                throw new RuntimeException("erro ao popular banco");
            }
        }
    }

    public List<Pet> buscarPetsNaoCadastrados() {
        List<Pet> pets = petRepository.findByQrcodeGerado(false);
        if (!pets.isEmpty()) {
            return pets;
        }
        throw new RuntimeException("Nao ha pets disponiveis");
    }

    public void QRCodeGerado(UuidQRCodeGerado uuid){
        Optional<Pet> pets = petRepository.findByUuid(uuid.getUuidQRCode());
        if(pets.isPresent()){
            Pet pet = pets.get();
            pet.setQrcodeGerado(true);
            petRepository.save(pet);
        }
        else{
            throw new RuntimeException("erro ao atualizar status do qrcode do pet");
        }
    }

    public Admin autenticar(Admin admin) {
        Optional<Admin> optionalAdmin = adminRepository.findByUsuario(admin.getUsuario());
        if (optionalAdmin.isPresent()) {
            Admin adminBanco = optionalAdmin.get();
            if (adminBanco.getSenha().equals(admin.getSenha())) {
                return admin;
            }
            throw new RuntimeException("Senha incorreta");
        }
        throw new RuntimeException("usuario nao encontrado");
    }
}