package helpers;

public enum FolderType {
    Ren_tekst, Nummer, Dato, Valgliste, Medlemsliste, Autonummer, Rik_tekst;

    public String toStr(){
        return this.toString().replace('_', ' ');
    }
}
