package pack.youcode.library;

public class Livre {
    private int id;
    private String titre;
    private int quantite;
    private int disponible;
    private String isbn;

    private Auteur auteur;

    public Livre(int id, String titre, int quantite, String isbn, Auteur auteur) {
        this.id = id;
        this.titre = titre;
        this.quantite = quantite;
        this.disponible = quantite;
        this.isbn = isbn;
        this.auteur = auteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
}
