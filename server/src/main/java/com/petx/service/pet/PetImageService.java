package com.petx.service.pet;

import com.petx.domain.pet.PetImagem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class PetImageService {

    @Value("${pasta.imagem.pets}")
    private String caminhoPetImagens;

    public void salvarImagem(PetImagem imagem) throws IOException {

        Path caminhoArquivo = Paths.get(caminhoPetImagens, imagem.getNomeImagem());

        Files.createDirectories(caminhoArquivo.getParent());

        Files.copy(imagem.getArquivo().getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);
    }

    public void atualizarImagem(PetImagem atualizarImagem, PetImagem imagemAntiga) throws IOException {
        Path caminhoImagemAntiga = Paths.get(caminhoPetImagens + File.separator + imagemAntiga.getNomeImagem());

        if (Files.exists(caminhoImagemAntiga)) {
            Files.delete(caminhoImagemAntiga);
        }

        Path caminhoNovaImagem = Paths.get(caminhoPetImagens + File.separator + atualizarImagem.getNomeImagem());

        Files.createDirectories(caminhoNovaImagem.getParent());

        Files.copy(atualizarImagem.getArquivo().getInputStream(), caminhoNovaImagem, StandardCopyOption.REPLACE_EXISTING);
    }

    public void deletarImagem(String nomeimagem) throws IOException {
        Path caminhoImagemAntiga = Paths.get(caminhoPetImagens + File.separator + nomeimagem);

        if (Files.exists(caminhoImagemAntiga)) {
            Files.delete(caminhoImagemAntiga);
        }
    }
}
