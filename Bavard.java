import java.util.ArrayList;

public class Bavard implements MessageListener {

    private String nom;
    private String perso;
    private ArrayList<Bavard> listeAmis;

    public Bavard(String nom, String perso){
        this.nom = nom;
        this.perso = perso;
        this.listeAmis = new ArrayList<>();
    }

    public String getNom(){
        return nom;
    }
    public String getPerso(){
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
            System.out.println("   - " + bavard.getNom());
        }
    }

    public MessageEvent envoyerMessage(String contenu, int bienveillance){
        ArrayList<String> auteur = new ArrayList<>();
        auteur.add(this.getNom());
        MessageEvent message = new MessageEvent(this, contenu, bienveillance, auteur);
        System.out.println("Message envoyé par " + this.getNom() + " (avec une bienveillance de " + bienveillance + ") : " + message.getContenu());
        for (Bavard b : this.getListeAmis()) {
            b.messageRecu(message);
        }
        return message;
    }    
 
    @Override
    public void messageRecu(MessageEvent e){
        System.out.println(this.getNom() + " : " + e.getDernierAuteur() + " vous a envoyé un message");
    }

    public void retransmettreMessage(MessageEvent message){
        if (message.getAuteurs().contains(this.getNom())){
            int new_bienveillance = message.getBienveillance();
            if ((this.perso.equals("Positive") || this.perso.equals("Positif")) && new_bienveillance < 10 ){
                new_bienveillance += 1;
            } else if ((this.perso.equals("Négative") || this.perso.equals("Négatif")) && new_bienveillance > 0){
                new_bienveillance -= 1;
            }
            String new_contenu = message.getContenu();
            if ((this.perso.equals("Positive") || this.perso.equals("Positif"))){
                new_contenu += "<3";
            } else if ((this.perso.equals("Négative") || this.perso.equals("Négatif"))){
                new_contenu += ":-(";
            }
            ArrayList<String> new_auteurs = message.getAuteurs();
            new_auteurs.add(this.getNom());
            MessageEvent new_message = new MessageEvent(this, new_contenu, new_bienveillance, new_auteurs);
            System.out.println("Message de " + message.getPremierAuteur() + " retransmis par " + nom + " (avec une bienveillance de " + new_message.getBienveillance() + ") : " + message.getContenu());
        }
    
    }

}