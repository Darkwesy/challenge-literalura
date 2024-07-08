package br.alura.com.literalura.principal;

import br.alura.com.literalura.model.Livro;
import br.alura.com.literalura.service.AutorService;
import br.alura.com.literalura.service.GutendexService;
import br.alura.com.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class MenuService {

    @Autowired
    private LivroService livroService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private GutendexService gutendexService;

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[1] -> Buscar livro pelo título;");
            System.out.println("[2] -> Listar livros registrados;");
            System.out.println("[3] -> Listar autores registrados;");
            System.out.println("[4] -> Listar autores vivos em determinado ano;");
            System.out.println("[5] -> Listar livros em um determinado idioma;");
            System.out.println("[0] -> Sair");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Digite o título do livro: ");
                    String title = scanner.nextLine();
                    Livro livro = gutendexService.fetchLivros(title);
                    if (livro != null) {
                        System.out.println("--------- Livro Registrado ---------");
                        System.out.println("\tTitulo: " + livro.getTitulo());
                        System.out.println("\tAutor: " + livro.getAutor().getNome());
                        System.out.println("\tIdioma: " + livro.getIdioma());
                        System.out.println("\tNumero de downloads: " + livro.getContadorDownloads());
                        System.out.println("------------------------------------");
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 2:
                    livroService.getLivros();
                    break;
                case 3:
                    autorService.listarAutores();
                    break;
                case 4:
                    System.out.print("Digite o ano: ");
                    var ano = scanner.nextInt();
                    autorService.listarAutoresVivos(ano);
                    break;
                case 5:
                    System.out.println("Selecione um dos idiomas abaixo: ");
                    System.out.println("""
                            es -> Espanhol
                            en -> Inglês
                            fr -> Francês
                            pt -> Português
                            """);
                    String idioma = scanner.nextLine();
                    livroService.getPorIdioma(idioma);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
