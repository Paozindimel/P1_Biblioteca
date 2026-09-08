# P1_Biblioteca
Aluno 1: Beatriz Gonçalves Silva Costa

Funcionalidades Novas
Excluir um Autor
A ideia é poder escolher o autor pelo ID e excluir ele.
Execução: 
Coloquei a opção no switch e crie a função
        while (continuar) {
            System.out.println("5 - Excluir Autor");

		Adicionei também:
case "5" -> {
                    excluirAutores(scanner);
                    yield false;
                }
 		
		A função:
Tentativas das função
Primeira tentativa:
private void excluirAutores(Scanner scanner) {
    System.out.print("Digite o nome do autor: ");
    var nome = scanner.nextLine();


    if (nome.isBlank()) {
        System.out.println("Nome inválido!");
        return;
    }


    var autor = new Autor(nome);
    autorRepository.delete(autor);


    System.out.println(">>> Autor '" + autor.getNome()
            + "' excluido com ID: " + autor.getId());

Segunda tentativa:


private void excluirAutores(Scanner scanner) {
        System.out.print("Digite o ID do autor: ");


        var idEX = scanner.nextLine();


        if (idEX.isBlank()) {
            System.out.println("ID inválido!");
            return;
        }
        Long idEXlong = Long.valueOf(idEX);
        autorRepository.deleteById(idEXlong);


        System.out.println(">>> Autor '" + autor.getNome()
                + "' excluido com ID: " + autor.getId());
    }

Terceira tentativa
    private void excluirAutores(Scanner scanner) {
        System.out.print("Digite o ID do autor: ");
        var idEX = scanner.nextLine();
        if (idEX.isBlank()) {
            System.out.println("ID inválido!");
            return;
        }
        autorRepository.deleteById(idEXlong);
                try {
        Long idEXlong = Long.valueOf(idEX);


            Optional<Autor> autoridEX =
                    autorRepository.findById(idEXlong);
            if (autoridEX.isEmpty()) {
                System.out.println(
                        "Autor não encontrado com o ID informado!"
                );
                return;
            }
    System.err.println("O autor com o id: "+ idEX + "Foi excluido com sucesso!");


    }
Quarta tentativa:
    private void excluirAutores(Scanner scanner) {
        System.out.print("Digite o ID do autor: ");
        var idEX = scanner.nextLine();
        if (idEX.isBlank()) {
            System.out.println("ID inválido!");
            return;
        }
        try {


            Optional<Autor> autoridEX =
                    autorRepository.findById(idEXlong);
            if (autoridEX.isEmpty()) {
                System.out.println(
                        "Autor não encontrado com o ID informado!"
                );
                return;
            }
    autorRepository.deleteById(idEXlong);
    System.err.println("O autor com o id: "+ idEX + "Foi excluido com sucesso!");


    }catch (NumberFormatException e);

Quinta tentativa:
    private void excluirAutores(Scanner scanner) {
        System.out.print("Digite o ID do autor: ");
        var idEX = scanner.nextLine();
        if (idEX.isBlank()) {
            System.out.println("ID inválido!");
            return;
        }
        try {
        Long idEXlong = Long.valueOf(idEX);
            Optional<Autor> autoridEX =
                    autorRepository.findById(idEXlong);
            if (autoridEX.isEmpty()) {
                System.out.println(
                        "Autor não encontrado com o ID informado!"
                );
                return;
            }
    autorRepository.deleteById(idEXlong);
    System.out.println("O autor com o id: "+ idEX + " Foi excluido com sucesso!");


    }catch (NumberFormatException e){
        System.out.println("ID inválido!");}
    }

Essa de cima (A 5) está correta porém quando fui testar a aplicação finalizou porque esqueci de retornar para o menu então troquei o case 5 por:
                case "5" -> {
                    excluirAutores(scanner);
                    yield true;
                }

Impedir que o autor seja excluído se houver livro vinculado a ele
Tentativas da função
Tentativa 1
    private void excluirAutores(Scanner scanner) {
        System.out.print("Digite o ID do autor: ");
        var idEX = scanner.nextLine();
        if (idEX.isBlank()) {
            System.out.println("ID inválido!");
            return;
        }
        try {
            Long idEXlong = Long.valueOf(idEX);
            if(  livroRepository.findByAutorId(idEXlong)=false){
 
           
            Optional<Autor> autoridEX =
                    autorRepository.findById(idEXlong);
            if (autoridEX.isEmpty()) {
                System.out.println(
                        "Autor não encontrado com o ID informado!"
                );
                return;
            }
    autorRepository.deleteById(idEXlong);
    System.out.println("O autor com o id: "+ idEX + " Foi excluido com sucesso!");


    }}catch (NumberFormatException e){
        System.out.println("ID inválido!");}
    }

Tentativa 2:
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

Essa deu certo, não foi tão difícil, só tive dificuldade na hora de rodar mas consegui resolver





Buscar livro por título
Tentativa 1 -
private void buscarLivroPorTitulo(Scanner scanner) {
    System.out.print("Digite o título ou parte do título: ");
    var titulo = scanner.nextLine();
    if (titulo.isBlank()) {
        System.out.println("Título inválido!");
        return;
    }
    var livros = livroRepository.findByTituloContaining(titulo);
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

tentativa 2:
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

Eu também mudei no switch :
}
                  case "6" -> {
                    buscarLivroPorTitulo(scanner);
                    yield true;

System.out.println("6 - Buscar Livro por Título");

Listar livro de um autor
Tentativa 1:
private void listarLivrosPorAutor(Scanner scanner) {
    System.out.print("Digite o ID do autor: ");
    var idAutor = scanner.nextLine();
    var livros = livroRepository.findByAutorId(idAutor);
    if (livros.isEmpty()) {
        System.out.println("Nenhum livro encontrado.");
        return;
    }
    livros.forEach(l ->
            System.out.println(l.getTitulo())
    );
}

Tentativa 2:
 
private void listarLivrosPorAutor(Scanner scanner) {
    System.out.print("Digite o ID do autor: ");
    var idAutor = Long.valueOf(scanner.nextLine());
    var livros = livroRepository.findByAutorId(idAutor);
    if (livros.isEmpty()) {
        System.out.println("Nenhum livro encontrado.");
        return;
    }
    System.out.println("Livros encontrados:");
    livros.forEach(l ->
            System.out.println(
                    "ID: " + l.getId()
                    + " | Título: " + l.getTitulo()
                    + " | Ano: " + l.getAnoPublicacao()
            )
    );
    System.out.println("Autor não encontrado!");
}

Tentativa 3:
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

Esse está certo, e também adicionei no switch 
case "7" -> {
                    listarLivrosPorAutor(scanner);
                    yield true;
  

System.out.println("7 - Listar Livros por Autor");


Listar livros por ano
Tentativa 1:
private void listarLivrosPorAno(Scanner scanner) {
    System.out.print("Digite o ano de lançamento: ");
    var ano = scanner.nextInt();
    scanner.nextLine();
    var livros = livroRepository.findByAnoPublicacao(ano + 1);
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






Tentativa 2:
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

Também adicionei no livro.repository:

    List<Livro> findByAnoPublicacao(Integer anoPublicacao);



