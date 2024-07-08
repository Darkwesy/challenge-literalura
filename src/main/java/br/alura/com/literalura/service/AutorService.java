package br.alura.com.literalura.service;

import br.alura.com.literalura.model.Autor;
import br.alura.com.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(autor -> System.out.println("Nome: " + autor.getNome() + " | Data de nascimento: " + autor.getAnoNascimento() + " | Data Falecimento: " + autor.getAnoFalecimento()));
    }

    public void listarAutoresVivos(int ano) {
        List<Autor> autores = autorRepository.autoresVivosNaqueleAno(ano);
        autores.forEach(a -> System.out.println("Nome: " + a.getNome() + " | Data de falecimento: " + a.getAnoFalecimento()));
    }
}
