package modele.parametre;

public enum ParametreType {
    LANGUE("Langue", 0),
    AUTEUR("Auteur Nom", 1),
    TYPE_DE_DOC("Type de document", 2),
    GENRE("Catégorie statistique 1", 3);

    public String getString() {
        return value;
    }

    private String value;

    public int getIndice() {
        return indice;
    }

    private int indice;


    ParametreType(String v, int i) {

        this.value=v;
        this.indice = i;
    }

}
