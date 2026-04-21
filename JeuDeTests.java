public class JeuDeTests {

    public static void main(String[] args) {
        Bavard marie = new Bavard("Marie", "Positive");
        Bavard maxence = new Bavard("Maxence", "Positive");
        Bavard sterenn = new Bavard("Sterenn", "Positive");
        marie.addAmi(maxence);
        marie.addAmi(sterenn);
        maxence.afficheBavard();
        System.out.println("Les amis de Marie sont :");
        marie.afficheAmis();
    }

}