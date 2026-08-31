public class TestBook {
    public static void main(String[] args) {
        Author[] authors = new Author[2];
        authors[0] = new Author(
                "Autor 01",
                "autor01@exemplo.com.br",
                'm'
        );
        authors[1] = new Author(
                "Autor 02",
                "autor02@exemplo.com.br",
                'm'
        );

        Book testeBook = new Book(
                "Java para Iniciantes",
                authors,
                19.99,
                99
        );

        System.out.println("Livro: " + testeBook);
        System.out.println("Nome: " + testeBook.getName());
        System.out.println("Preço: " + testeBook.getPrice());
        System.out.println("Quantidade: " + testeBook.getQty());

        testeBook.setPrice(24.99);
        testeBook.setQty(120);

        System.out.println("Preço atualizado: " + testeBook.getPrice());
        System.out.println("Quantidade atualizada: " + testeBook.getQty());

        System.out.println("Autores:");
        for (Author author : testeBook.getAuthors()) {
            System.out.println(author);
        }
    }
}
