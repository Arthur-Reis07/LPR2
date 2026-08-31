public class TestAuthor {
    public static void main(String[] args) {
        Author autor = new Author(
                "Wellington Tuler",
                "tulermoraes@yahoo.com",
                'm'
        );

        System.out.println("Nome inicial: " + autor.getName());
        System.out.println("E-mail inicial: " + autor.getEmail());
        System.out.println("Gênero: " + autor.getGender());
        System.out.println("Objeto: " + autor);

        autor.setEmail("novoemail@exemplo.com");
        System.out.println("E-mail após o setter: " + autor.getEmail());

        System.out.println("Nome final: " + autor.getName());
        System.out.println("Gênero final: " + autor.getGender());
    }
}
