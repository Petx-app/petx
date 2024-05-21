package com.petx.mapper.usuario;

import com.petx.api.dto.Usuario.EmailDTO;
import com.petx.api.dto.Usuario.LoginUsuarioDTO;
import com.petx.api.dto.Usuario.TrocarSenhaDTO;
import com.petx.api.dto.Usuario.UsuarioDTO;
import com.petx.domain.usuario.EmailValidar;
import com.petx.domain.usuario.TrocarSenha;
import com.petx.domain.usuario.Usuario;
import com.petx.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class UsuarioMapper {

    @Autowired
    private Criptografia criptografia;

    public Usuario toEntityLogin(LoginUsuarioDTO dto) {
        Usuario entity = new Usuario();
        entity.setEmail(dto.getEmail());
        entity.setSenha(criptografia.getSenhaSalto(dto.getSenha()));

        return entity;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail().toLowerCase());
        entity.setSenha(criptografia.criptogafarSenha(dto.getSenha()));
        entity.setNome(dto.getNome());
        entity.setTelefone(dto.getTelefone());

        return entity;
    }

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(null);
        dto.setNome(usuario.getNome());
        dto.setTelefone(usuario.getTelefone());

        return dto;
    }

    public EmailValidar toEntityEmail(EmailDTO dto){
        EmailValidar entity = new EmailValidar();
        entity.setEmail(dto.getEmail().toLowerCase());

        return entity;
    }

    public TrocarSenha toEntityTrocarSenha(TrocarSenhaDTO dto, UUID codigoValidacao){
        TrocarSenha entity = new TrocarSenha();
        entity.setCodigoValidacao(codigoValidacao);
        entity.setSenha(criptografia.criptogafarSenha(dto.getSenha()));

        return entity;
    }
}