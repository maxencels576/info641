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
    private JButton creerBavard = new JButton("Créer Bavard");
    private JButton creerAmitie = new JButton("Créer amitié");
    private JButton interfaceBavard = new JButton("Afficher les interfaces des Bavards");
    private JButton propagation = new JButton("Lancer une propagation");

    public InterfaceReseau() {
        super("Création du réseau de bavards");
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        this.setLayout(new BorderLayout());

        // Création d'un nouveau bavard
        JPanel panelHaut = new JPanel(new GridLayout(1, 3));
        panelHaut.add(champNom);
        panelHaut.add(champPerso);
        panelHaut.add(creerBavard);
        panelHaut.add(creerAmitie);

        creerBavard.addActionListener(this);
        creerAmitie.addActionListener(this);

        this.add(panelHaut, BorderLayout.NORTH);

        //Liste des bavards du réseau
        JPanel panelCentre = new JPanel(new BorderLayout());
        JLabel titre = new JLabel("Bavards du réseau :");
        titre.setHorizontalAlignment(JLabel.CENTER);
        panelCentre.add(titre, BorderLayout.NORTH);

        listeBavards.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scroll = new JScrollPane(listeBavards);
        panelCentre.add(scroll, BorderLayout.CENTER);

        this.add(panelCentre, BorderLayout.CENTER);

        JPanel panelBas = new JPanel(new GridLayout(1, 3));
        // Créer une amitié
        panelBas.add(creerAmitie);
        creerAmitie.addActionListener(this);

        // Lancer les interfaces bavards
        panelBas.add(interfaceBavard);
        interfaceBavard.addActionListener(this);

        // Lancer une propagation
        panelBas.add(propagation);
        propagation.addActionListener(this);

        this.add(panelBas, BorderLayout.SOUTH);
        this.revalidate();
		this.repaint();
        this.setVisible(true);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        // Création d'un bavard
        if (e.getSource() == creerBavard) {
            String nom = champNom.getText();
            String perso = champPerso.getText();
            if (!nom.isEmpty() && !perso.isEmpty()) {
                Bavard b = new Bavard(nom, perso);
                reseau.add(b);
                modelBavards.addElement(b.getNom() + " (" + b.getPerso() + ")");
            }
        }
        // Création d'une amitié
        if (e.getSource() == creerAmitie) {
            int[] indices = listeBavards.getSelectedIndices();
            if (indices.length == 2) {
                Bavard b1 = reseau.get(indices[0]);
                Bavard b2 = reseau.get(indices[1]);
                b1.addAmi(b2);
                JOptionPane.showMessageDialog(this, b2.getNom() + " est l'ami de " + b1.getNom());
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez exactement deux bavards !");
            }
        }
        // Afficher les interfaces des bavards
        if (e.getSource() == interfaceBavard) {
            for (Bavard b : reseau) {
                for (java.awt.Window window : java.awt.Window.getWindows()) {
                        if (window instanceof InterfaceBavard) {
                            InterfaceBavard inter = (InterfaceBavard) window;
                            if (inter.getBavard().equals(b)) {
                                inter.dispose();
                            }
                        }
                    }
                    new InterfaceBavard(b);
            }
        }
        // Lancer une propagation
        if (e.getSource() == propagation) {
            for (Bavard b : reseau) {
                ArrayList<MessageEvent> messages = b.getMessagesRecus();
                for (MessageEvent message : messages) {
                    b.retransmettreMessage(message);
                    for (Bavard ami : b.getListeAmis()) {
                        for (java.awt.Window window : java.awt.Window.getWindows()) {
                        if (window instanceof InterfaceBavard) {
                            InterfaceBavard inter = (InterfaceBavard) window;
                            if (inter.getBavard().equals(b)) {
                                inter.dispose();
                            }
                        }
                    }
                    new InterfaceBavard(b);
                    }
                }
            }
        }
    }
}