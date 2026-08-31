public class Book {
    private String name;
    private Author[] authors;
    private double price;
    private int qty;

    public Book(String name, Author[] authors, double price, int qty) {
        this.name = name;
        this.authors = authors;
        this.price = price;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        StringBuilder autoresTexto = new StringBuilder();
        autoresTexto.append("{");

        for (int i = 0; i < authors.length; i++) {
            autoresTexto.append(authors[i]);
            if (i < authors.length - 1) {
                autoresTexto.append(",");
            }
        }

        autoresTexto.append("}");

        return "Book[name=" + name
                + ",authors=" + autoresTexto
                + ",price=" + price
                + ",qty=" + qty + "]";
    }
}
