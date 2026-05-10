import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class InterfaceReseau extends JFrame implements ActionListener {

    private ArrayList<Bavard> reseau = new ArrayList<>();
    private DefaultListModel<String> modelBavards = new DefaultListModel<>();
    private JList<String> listeBavards = new JList<>(modelBavards);
    private JTextField champNom = new JTextField("Nom du bavard");
    private JTextField champPerso = new JTextField("Personnalité");
    private JButton boutonCreer = new JButton("Créer Bavard");
    // private JButton etreAmi = new JButton("veut être ami");
    // private JButton amiAvec = new JButton("avec");

    public InterfaceReseau() {
        super("Création du réseau de bavards");
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLayout(new BorderLayout());

        // Création d'un nouveau bavard
        JPanel panelHaut = new JPanel(new GridLayout(1, 3));
        panelHaut.add(champNom);
        panelHaut.add(champPerso);
        panelHaut.add(boutonCreer);

        boutonCreer.addActionListener(this);

        this.add(panelHaut, BorderLayout.NORTH);

        //Liste des bavards du réseau
        JPanel panelCentre = new JPanel(new BorderLayout());
        JLabel titre = new JLabel("Bavards du réseau :");
        titre.setHorizontalAlignment(JLabel.CENTER);
        panelCentre.add(titre, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(listeBavards);
        panelCentre.add(scroll, BorderLayout.CENTER);

        this.add(panelCentre, BorderLayout.CENTER);

        // Ajouter une amitié entre deux bavards
        // JPanel panelBas = new JPanel(new GridLayout(1, 3));
        // panelBas.add(etreAmi);
        // panelBas.add(amiAvec);

        // etreAmi.addActionListener(this);
        // amiAvec.addActionListener(this);

        // this.add(panelBas, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Création d'un bavard
        if (e.getSource() == boutonCreer) {
            String nom = champNom.getText();
            String perso = champPerso.getText();
            if (!nom.isEmpty() && !perso.isEmpty()) {
                Bavard b = new Bavard(nom, perso);
                reseau.add(b);
                modelBavards.addElement(b.getNom() + " (" + b.getPerso() + ")");
            }
        }
        // Ajout d'une amitié 
        // if (e.getSource() == etreAmi) {
        //     int i = listeBavards.getSelectedIndex();
        //     if (e.getSource() == amiAvec) {
        //         int j = listeBavards.getSelectedIndex();
        //         if (i != j) {
        //             Bavard b1 = reseau.get(i);
        //             Bavard b2 = reseau.get(j);
        //             b1.addAmi(b2);
        //             // b2.addAmi(b1);
        //             System.out.println(b1.getNom() + " est maintenant ami avec " + b2.getNom());
        //         }
        //     }
        // }
        
    }
}