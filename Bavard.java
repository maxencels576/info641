import java.util.ArrayList;

public class Bavard {

    private String nom;
    private String perso;
    private ArrayList<Bavard> listeAmis;

    public Bavard(String nom, String perso){
        this.nom = nom;
        this.perso = perso;
        this.listeAmis = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }
    public String getPerso() {
        return perso;
    }
    public ArrayList<Bavard> getListeAmis(){
        return listeAmis;
    }

    public void addAmi(Bavard bavard){
        if (listeAmis.contains(bavard) == false){
            this.listeAmis.add(bavard);
        }
    }

    public void afficheBavard(){
        System.out.println(this.getNom() + " (" + this.getPerso() + ")");
    }
    public void afficheAmis(){
        for (Bavard bavard : this.getListeAmis()){
            System.out.println(bavard.getNom());
        }
    }

}