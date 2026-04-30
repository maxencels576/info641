public class JeuDeTests {

    public static void main(String[] args) {
        Bavard marie = new Bavard("Marie", "Positive");
        Bavard maxence = new Bavard("Maxence", "Négative");
        Bavard sterenn = new Bavard("Sterenn", "Neutre");
        marie.addAmi(maxence);
        marie.addAmi(sterenn);
        maxence.addAmi(marie);
        maxence.addAmi(sterenn);
        sterenn.addAmi(marie);
        // maxence.afficheBavard();
        System.out.println("Les amis de Marie sont :");
        marie.afficheAmis();
        System.out.println("Les amis de Maxence sont :");
        maxence.afficheAmis();
        System.out.println("Les amis de Sterenn sont :");
        sterenn.afficheAmis();
        marie.envoyerMessage("Coucouuu !", 8);
        
    }

}