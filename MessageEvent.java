import java.util.ArrayList;
import java.util.EventObject;

class MessageEvent extends EventObject {

    private String contenu;
    private int bienveillance; // entre -10 et +10
    private ArrayList<String> auteur;

    public MessageEvent(Object source, String contenu, int bienveillance, ArrayList<String> auteur) {
		super(source);
        this.contenu = contenu;
		this.bienveillance = bienveillance;
        this.auteur = new ArrayList<>(auteur);
	}
	
    public String getContenu() {
		return contenu;
	}
    public int getBienveillance() {
		return bienveillance;
	}
    public ArrayList<String> getAuteurs() {
		return auteur;
	}
    public String getPremierAuteur() {
		return auteur.get(0);
	}
    public String getDernierAuteur() {
        int n = auteur.size();
		return auteur.get(n - 1);
	}
}