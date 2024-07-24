package com.petx.mapper.usuario;

import com.petx.api.dto.Usuario.*;
import com.petx.domain.usuario.*;
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
        entity.setSenha(dto.getSenha());

        return entity;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        entity.setUuid(UUID.randomUUID());
        entity.setEmail(dto.getEmail().toLowerCase());
        entity.setSenha(criptografia.criptogafarSenha(dto.getSenha(), entity.getUuid()));
        entity.setNome(dto.getNome());
        entity.setTelefone(dto.getTelefone());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());

        return entity;
    }

    public Usuario toEntityUsuarioAtualizar(UsuarioAtualizarDTO dto) {
        Usuario entity = new Usuario();
        entity.setNome(dto.getNome());
        entity.setTelefone(dto.getTelefone());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());

        return entity;
    }

    public AtualizarSenha toEntityAtualizarSenha(TrocarSenhaDTO dto, UUID uuid) {
        AtualizarSenha entity = new AtualizarSenha();
        entity.setSenha(criptografia.criptogafarSenha(dto.getSenha(), uuid));

        return entity;
    }

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setEmail(usuario.getEmail());
        dto.setSenha(null);
        dto.setNome(usuario.getNome());
        dto.setTelefone(usuario.getTelefone());
        dto.setCidade(usuario.getCidade());
        dto.setEstado(usuario.getEstado());

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
        entity.setSenha(dto.getSenha());

        return entity;
    }

    public CodigoValidacaoEmail toEntityCodigoValidacaoEmail(CodigoValidacaoEmailDTO dto){
        CodigoValidacaoEmail entity = new CodigoValidacaoEmail();
        entity.setCodigoVerificacao(dto.getCodigoVerificacao());
        entity.setEmail(dto.getEmail());

        return entity;
    }
}