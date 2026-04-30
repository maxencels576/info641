import java.util.EventObject;

class MessageEvent extends EventObject {
    private String contenu;
    private int bienveillance; // entre -10 et +10
    private String auteur;

    public MessageEvent(Object source, String contenu, int bienveillance, String auteur) {
		super(source);
        this.contenu = contenu;
		this.bienveillance = bienveillance;
        this.auteur = auteur;
	}
    public String getcontenu() {
		return contenu;
	}
    public int getbienveillance() {
		return bienveillance;
	}
    public String getauteur() {
		return auteur;
	}
}