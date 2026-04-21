import java.util.ArrayList;

public class Bavard {

    private String nom;
    private String perso;
    private ArrayList<Bavard> listeAmis;

    public Bavard(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

}