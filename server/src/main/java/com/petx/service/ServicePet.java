package com.petx.service;

import com.petx.domain.GetQRCodeResponse;
import com.petx.domain.Pet;
import com.petx.domain.Usuario;
import com.petx.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.UUID;

@Service
public class ServicePet{
    @Autowired
    private PetRepository petRepository;

//    EntityManagerFactory factory = Persistence
//            .createEntityManagerFactory("petx");
//
//    public void postPet(Pet pet){
//        EntityManager em = factory.createEntityManager();
//        try{
//            em.getTransaction().begin();
//            em.persist(pet);
//            em.getTransaction().commit();
//        }catch(Exception e){
//            e.printStackTrace();
//            throw new RuntimeException("falha ao salvar o pet:" + e.getMessage());
//        }finally{
//            em.close();
//        }
//    }

//    public String getQRCode(UUID id){
//        EntityManager em = factory.createEntityManager();
//        try{
//            Pet pet = em.find(Pet.class, id);
//            if(pet == null){
//                throw new RuntimeException("pet nao encontrado com o id");
//            }
//
//            if(!pet.getCadastrado()){
//                return "";
//            }else{
//                return pet.getNome();
//            }
//
//        }catch(Exception e){
//            e.printStackTrace();
//            throw new RuntimeException("falha ao salvar o pet:" + e.getMessage());
//        }finally {
//            em.close();
//
//        }
//    }

    public GetQRCodeResponse getQRCode(UUID id){
        Pet pet = petRepository.findByUUID(id);
        Usuario usuario = pet.getDono();

        GetQRCodeResponse getQRCodeResponse = new GetQRCodeResponse();
        getQRCodeResponse.setNomePet(pet.getNome());
        getQRCodeResponse.setNomeDono(usuario.getNome());
        getQRCodeResponse.setTelefoneDono(usuario.getTelefone());

        return getQRCodeResponse;
    }


}
