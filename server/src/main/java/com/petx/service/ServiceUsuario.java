package com.petx.service;

import com.petx.domain.Usuario;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServiceUsuario {

//    EntityManagerFactory factory = Persistence
//            .createEntityManagerFactory("petx");
//    public void postUsuario(Usuario usuario){
//
//        EntityManager em = factory.createEntityManager();
//
//        try{
//            em.getTransaction().begin();
//            em.persist(usuario);
//            em.getTransaction().commit();
//        }catch(Exception e){
//            e.printStackTrace();
//            throw new RuntimeException("falha ao salvar o usuario:" + e.getMessage());
//        }finally{
//            em.close();
//        }
//    }
}
