package br.alura.com.literalura.model;

import br.alura.com.literalura.dto.AutorDTO;
import br.alura.com.literalura.dto.LivroDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "livro_idioma", joinColumns = @JoinColumn(name = "livro_id"))
    @Column(name = "idioma")
    private List<String> idioma;

    private int contadorDownloads;

    public Livro() {}

    public Livro(LivroDTO livro, Autor autor) {
        this.titulo = livro.titulo();
        this.contadorDownloads = livro.contadorDownloads();
        setIdioma(livro.idioma());
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public int getContadorDownloads() {
        return contadorDownloads;
    }

    public void setContadorDownloads(int contadorDownloads) {
        this.contadorDownloads = contadorDownloads;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor=" + (autor != null ? autor.getNome() : "N/A") +
                ", idioma=" + idioma +
                ", contadorDownloads=" + contadorDownloads +
                '}';
    }
}
