package br.alura.com.literalura.service;

import br.alura.com.literalura.dto.GutendexDTO;
import br.alura.com.literalura.model.Autor;
import br.alura.com.literalura.dto.AutorDTO;
import br.alura.com.literalura.model.Livro;
import br.alura.com.literalura.dto.LivroDTO;
import br.alura.com.literalura.repository.AutorRepository;
import br.alura.com.literalura.repository.LivroRepository;
import br.alura.com.literalura.service.conversor.IConversorDados;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GutendexService {
    private final String API_URL = "https://gutendex.com/books/?search=";
    private HttpClient client = HttpClient.newHttpClient();

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private IConversorDados conversorDados;

    @Transactional
    public Livro fetchLivros(String title) {
        String urlRequest = API_URL + title.replaceAll(" ", "+");
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlRequest)).build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        String responseBody = response.body();
        GutendexDTO responseDTO = conversorDados.obterDados(responseBody, GutendexDTO.class);

        if (!responseDTO.livros().isEmpty()) {
            LivroDTO livroDTO = responseDTO.livros().get(0);
            List<AutorDTO> autoresDTO = livroDTO.autores();

            List<Autor> autores = autoresDTO.stream()
                    .map(dto -> {
                        Autor autor = autorRepository.findByNome(dto.nome()).orElse(null);
                        if (autor == null) {
                            autor = new Autor(dto);
                            autorRepository.save(autor);
                        }
                        return autor;
                    })
                    .collect(Collectors.toList());

            Livro livro = new Livro(livroDTO, autores.get(0));
            livroRepository.save(livro);
            return livro;
        }

        return null;
    }
}

