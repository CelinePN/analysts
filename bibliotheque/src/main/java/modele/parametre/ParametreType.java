package modele.parametre;

public enum ParametreType {
    LANGUE("Langue"),
    AUTEUR("Auteur Nom"),
    TYPE_DE_DOC("Type de document"),
    GENRE("Catégorie statistique 1");

    public String getString() {
        return string;
    }

    private String string;


    ParametreType(String a) {
        this.string=a;
    }

}
