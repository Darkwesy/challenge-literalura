package br.alura.com.literalura.service;

import br.alura.com.literalura.model.Livro;
import br.alura.com.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;

    public void buscarLivroPorTitulo(String title) {
        Livro livro = repository.findByTitulo(title);
        if (livro != null) {
            repository.save(livro);
            System.out.println("Livro salvo: " + livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public void getLivros() {
        List<Livro> books = repository.findAll();
        books.forEach(System.out::println);
    }

    public void getPorIdioma(String idioma) {
        List<Livro> livros = repository.findByIdiomaContaining(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para este idioma");
        } else {
            livros.forEach(System.out::println);
        }
    }
}
