## P1 - Sistema de Gestão de Biblioteca (Sisbiblioteca)

Projeto desenvolvido para a **Atividade Prática - P1**, com o objetivo de desenvolver um sistema de gestão de biblioteca utilizando Java, Spring Boot, Spring Data JPA e banco de dados H2.

---

## Integrantes

**Nome:** BEATRIZ GONÇALVES SILVA COSTA


---

##  Tecnologias utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* H2 Database
* Maven
* Lombok

---

##  Objetivo do projeto

O Sisbiblioteca é um sistema desenvolvido para realizar o cadastro e consulta de **autores e livros**, utilizando persistência de dados através do Spring Data JPA.

O projeto utiliza relacionamento entre as entidades **Autor** e **Livro**, sendo que um autor pode possuir vários livros.

---

##  Estrutura do projeto

```text
src/main/java/com/lab/jpa/sisbiblioteca/

├── config/
│   └── DataInitializer.java
│
├── model/
│   ├── Autor.java
│   └── Livro.java
│
├── repository/
│   ├── AutorRepository.java
│   └── LivroRepository.java
│
└── SisbibliotecaApplication.java
```

---

##  Funcionalidades

O sistema possui um menu interativo executado através do console.

### Funcionalidades implementadas

* Cadastro de autores
* Listagem de autores
* Cadastro de livros
* Listagem de livros
* Busca de livros por título
* Busca de livros por autor
* Busca de livros por ano
* Exclusão de autores
* Validação antes da exclusão de autores
* Encerramento da aplicação

---

#  Modificações realizadas

Além das funcionalidades apresentadas no roteiro inicial, foram realizadas modificações no sistema.

## 1. Exclusão de autores

Foi adicionada uma opção para excluir autores cadastrados no sistema.

Para realizar a exclusão, o usuário informa o ID do autor que deseja remover.

Antes da exclusão, o sistema verifica se o autor informado existe.

---

## 2. Validação para exclusão de autores

Foi adicionada uma validação para verificar se o autor possui livros cadastrados.

Caso existam livros relacionados ao autor, a exclusão não é realizada.

Essa validação evita problemas com o relacionamento entre **Autor** e **Livro**.

---

## 3. Busca de livros por título

Foi adicionada uma opção para realizar pesquisas de livros utilizando o título.

A pesquisa permite encontrar livros utilizando parte do título informado e não diferencia letras maiúsculas e minúsculas.

---

## 4. Busca de livros por autor

Foi adicionada uma opção para consultar os livros relacionados a um determinado autor.

O usuário informa o ID do autor e o sistema apresenta os livros associados a ele.

---

## 5. Busca de livros por ano

Foi adicionada uma opção para pesquisar livros utilizando o ano de publicação como critério.

O usuário informa o ano desejado e o sistema apresenta os livros correspondentes.

---


**Atividade Prática - P1: Sistema de Gestão de Biblioteca (Sisbiblioteca)**

Projeto desenvolvido utilizando Java 17+, Spring Boot, Spring Data JPA, H2 Database e CommandLineRunner.

As modificações realizadas no projeto estão descritas neste README.

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

Tentativa 1 

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



