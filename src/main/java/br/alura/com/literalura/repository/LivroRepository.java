package br.alura.com.literalura.repository;

import br.alura.com.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdiomaContaining(String idioma);
    Livro findByTitulo(String titulo);
}
