package br.alura.com.literalura.repository;

import br.alura.com.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);
    @Query("Select a from Autor a where a.anoNascimento <= :ano and a.anoFalecimento >= :ano ")
    List<Autor> autoresVivosNaqueleAno(int ano);
}
