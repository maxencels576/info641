public class JeuDeTests {
    public static void main(String[] args) {
        Bavard marie = new Bavard("Marie", "Positive");
        Bavard maxence = new Bavard("Maxence", "Négative");
        Bavard sterenn = new Bavard("Sterenn", "Positive");
        marie.addAmi(maxence);
        marie.addAmi(sterenn);
        maxence.addAmi(marie);
        maxence.addAmi(sterenn);
        sterenn.addAmi(marie);
        // maxence.afficheBavard();
        // System.out.println("Les amis de Marie sont :");
        // marie.afficheAmis();
        // System.out.println("Les amis de Maxence sont :");
        // maxence.afficheAmis();
        // System.out.println("Les amis de Sterenn sont :");
        // sterenn.afficheAmis();
        MessageEvent msg1 = marie.envoyerMessage("Coucouuu !", 8);
        MessageEvent msg2 = maxence.retransmettreMessage(msg1);
        marie.retransmettreMessage(msg2);
        sterenn.retransmettreMessage(msg2);
        maxence.retransmettreMessage(msg2);

        System.out.println("");

        new InterfaceReseau();
    }

}