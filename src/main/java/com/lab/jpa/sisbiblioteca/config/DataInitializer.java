package com.lab.jpa.sisbiblioteca.config;

import com.lab.jpa.sisbiblioteca.model.Autor;
import com.lab.jpa.sisbiblioteca.model.Livro;
import com.lab.jpa.sisbiblioteca.repository.AutorRepository;
import com.lab.jpa.sisbiblioteca.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    public DataInitializer(AutorRepository autorRepository,
                           LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        var scanner = new Scanner(System.in);
        var continuar = true;

        System.out.println("==========================================");
        System.out.println(" SISTEMA DE GESTÃO DE BIBLIOTECA JPA ");
        System.out.println("==========================================");

        while (continuar) {
            System.out.println("\nMENU DE OPÇÕES:");
            System.out.println("1 - Cadastrar Autor");
            System.out.println("2 - Listar Autores");
            System.out.println("3 - Cadastrar Livro");
            System.out.println("4 - Listar Livros");
            System.out.println("5 - Excluir Autor");
            System.out.println("6 - Buscar Livro por Título");
            System.out.println("7 - Listar Livros por Autor");
            System.out.println("8 - Listar Livros por Ano de Publicação");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            var opcao = scanner.nextLine();

            continuar = switch (opcao) {
                case "1" -> {
                    cadastrarAutor(scanner);
                    yield true;
                }

                case "2" -> {
                    listarAutores();
                    yield true;
                }

                case "3" -> {
                    cadastrarLivro(scanner);
                    yield true;
                }

                case "4" -> {
                    listarLivros();
                    yield true;
                }

                case "5" -> {
                    excluirAutores(scanner);
                    yield true;
                }
                  case "6" -> {
                    buscarLivroPorTitulo(scanner);
                    yield true;
                }
                case "7" -> {
                    listarLivrosPorAutor(scanner);
                    yield true;
                }
                case "8" -> {
                    listarLivrosPorAno(scanner);
                    yield true;
                }
                case "0" -> {
                    System.out.println("Encerrando aplicação...");
                    yield false;
                }

                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                    yield true;
                }
            };
        }

        System.out.println("Aplicação finalizada.");
    }

    private void cadastrarAutor(Scanner scanner) {
        System.out.print("Digite o nome do autor: ");

        var nome = scanner.nextLine();

        if (nome.isBlank()) {
            System.out.println("Nome inválido!");
            return;
        }

        var autor = new Autor(nome);

        autorRepository.save(autor);

        System.out.println(">>> Autor '" + autor.getNome()
                + "' cadastrado com ID: " + autor.getId());
    }
    
    private void excluirAutores(Scanner scanner) {
    System.out.print("Digite o ID do autor: ");
    var idEX = scanner.nextLine();
    if (idEX.isBlank()) {
        System.out.println("ID inválido!");
        return;
    }
    try {
        Long idEXlong = Long.valueOf(idEX);
        Optional<Autor> autoridEX = autorRepository.findById(idEXlong);
        if (autoridEX.isEmpty()) {
            System.out.println("Autor não encontrado com o ID informado!");
            return;
        }
        var livrosDoAutor = livroRepository.findByAutorId(idEXlong);
        if (!livrosDoAutor.isEmpty()) {
            System.out.println(
                    "Não é possível excluir este autor porque ele possui livros cadastrados!"
            );
            return;
        }
        autorRepository.deleteById(idEXlong);
        System.out.println(
                "O autor com o id: " + idEX + " foi excluído com sucesso!"
        );
    } catch (NumberFormatException e) {
        System.out.println("ID inválido!");
    }
}
    
    private void listarAutores() {
        var autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
            return;
        }

        System.out.println("\n--- LISTA DE AUTORES ---");

        autores.forEach(a ->
            System.out.printf("ID: %d | Nome: %s%n",
                    a.getId(),
                    a.getNome())
        );

        System.out.println("------------------------");
    }

    private void cadastrarLivro(Scanner scanner) {
        listarAutores();

        System.out.print("Informe o ID do autor do livro: ");

        var idStr = scanner.nextLine();

        try {
            var autorId = Long.parseLong(idStr);

            Optional<Autor> autorOpt =
                    autorRepository.findById(autorId);

            if (autorOpt.isEmpty()) {
                System.out.println(
                        "Autor não encontrado com o ID informado!"
                );
                return;
            }

            System.out.print("Digite o título do livro: ");

            var titulo = scanner.nextLine();

            System.out.print("Digite o ano de publicação: ");

            var ano = Integer.parseInt(scanner.nextLine());

            var livro = new Livro(
                    titulo,
                    ano,
                    autorOpt.get()
            );

            livroRepository.save(livro);

            System.out.println(">>> Livro '" + livro.getTitulo()
                    + "' cadastrado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println(
                    "Valor numérico inválido informado."
            );
        }
    }

    private void listarLivros() {

        var livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        System.out.println("\n--- LISTA DE LIVROS ---");

        livros.forEach(l ->
            System.out.printf(
                    "ID: %d | Título: %s | Ano: %d | Autor: %s%n",
                    l.getId(),
                    l.getTitulo(),
                    l.getAnoPublicacao(),
                    l.getAutor().getNome()
            )
        );

        System.out.println("-----------------------");
    }

    private void buscarLivroPorTitulo(Scanner scanner) {
    System.out.print("Digite o título ou parte do título: ");
    var titulo = scanner.nextLine();
    if (titulo.isBlank()) {
        System.out.println("Título inválido!");
        return;
    }
    var livros = livroRepository.findByTituloContainingIgnoreCase(titulo);
    if (livros.isEmpty()) {
        System.out.println("Nenhum livro encontrado.");
        return;
    }
    System.out.println("\n--- LIVROS ENCONTRADOS ---");
    livros.forEach(l ->
            System.out.printf(
                    "ID: %d | Título: %s | Ano: %d | Autor: %s%n",
                    l.getId(),
                    l.getTitulo(),
                    l.getAnoPublicacao(),
                    l.getAutor().getNome()
            )
    );
    System.out.println("--------------------------");
}

    private void listarLivrosPorAutor(Scanner scanner) {
    System.out.print("Digite o ID do autor: ");
    var idAutorStr = scanner.nextLine();
    if (idAutorStr.isBlank()) {
        System.out.println("ID inválido!");
        return;
    }
    try {
        var idAutor = Long.valueOf(idAutorStr);
        var autor = autorRepository.findById(idAutor);
        if (autor.isEmpty()) {
            System.out.println("Autor não encontrado!");
            return;
        }
        var livros = livroRepository.findByAutorId(idAutor);
        if (livros.isEmpty()) {
            System.out.println("Este autor não possui livros cadastrados.");
            return;
        }
        System.out.println("\n--- LIVROS DO AUTOR ---");
        livros.forEach(l ->
                System.out.printf(
                        "ID: %d | Título: %s | Ano: %d%n",
                        l.getId(),
                        l.getTitulo(),
                        l.getAnoPublicacao()
                )
        );
        System.out.println("-----------------------");
    } catch (NumberFormatException e) {
        System.out.println("ID inválido!");
    }
}

private void listarLivrosPorAno(Scanner scanner) {
    System.out.print("Digite o ano de lançamento: ");
    var ano = scanner.nextInt();
    scanner.nextLine();
    var livros = livroRepository.findByAnoPublicacao(ano);
    if (livros.isEmpty()) {
        System.out.println("Nenhum livro encontrado para o ano " + ano + ".");
        return;
    }
    System.out.println("\n--- LIVROS LANÇADOS EM " + ano + " ---");
    livros.forEach(l ->
            System.out.printf(
                    "ID: %d | Título: %s | Autor: %s%n",
                    l.getId(),
                    l.getTitulo(),
                    l.getAutor().getNome()
            )
    );
    System.out.println("--------------------------------");
}






}
