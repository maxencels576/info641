import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class InterfaceBavard extends JFrame implements ActionListener{

    private Bavard bavard;
    private DefaultListModel<String> modelMessages = new DefaultListModel<>();
    private JList<String> listeMessages = new JList<>(modelMessages);
    private JTextField champMessage = new JTextField("Contenu du message");
    private JTextField champBienveillance = new JTextField("Bienveillance (-10 à +10)");
    private JButton boutonEnvoyer = new JButton("Envoyer");
    private JButton boutonRetransmettre = new JButton("Retransmettre");
    private DefaultListModel<String> modelAmis = new DefaultListModel<>();
    private JList<String> listeAmis = new JList<>(modelAmis);

    public InterfaceBavard(Bavard bavard){
        super();
        this.bavard = bavard;

        this.setTitle("Interface de " + bavard.getNom());
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout()); // organisation des éléments

        // Colonne de gauche : messages reçus
        JPanel panelGauche = new JPanel(new BorderLayout());
        JLabel titreMessages = new JLabel("Messages reçus :");
        titreMessages.setHorizontalAlignment(JLabel.CENTER);
        panelGauche.add(titreMessages, BorderLayout.NORTH);
        panelGauche.add(new JScrollPane(listeMessages), BorderLayout.CENTER);

        // Colonne de droite : liste des amis
        JPanel panelDroit = new JPanel(new BorderLayout());
        JLabel titreAmis = new JLabel("Amis :");
        titreAmis.setHorizontalAlignment(JLabel.CENTER);
        panelDroit.add(titreAmis, BorderLayout.NORTH);
        panelDroit.add(new JScrollPane(listeAmis), BorderLayout.CENTER);

        // Panel central qui contient les deux colonnes
        JPanel panelCentre = new JPanel(new GridLayout(1, 2));
        panelCentre.add(panelGauche);
        panelCentre.add(panelDroit);

        this.add(panelCentre, BorderLayout.CENTER);

        JPanel panelBas = new JPanel(new GridLayout(1, 4));
        panelBas.add(champMessage);
        panelBas.add(champBienveillance);
        panelBas.add(boutonEnvoyer);
        panelBas.add(boutonRetransmettre);

        boutonEnvoyer.addActionListener(this);
        boutonRetransmettre.addActionListener(this);

        this.add(panelBas, BorderLayout.SOUTH);

        rafraichirMessages();
        rafraichirAmis();

        this.setVisible(true);
    }

    private void rafraichirMessages() {
        modelMessages.clear();
        ArrayList<MessageEvent> messages = bavard.getMessagesRecus();
        ArrayList<String> auteurs = bavard.getRecusPar();
        for (int i = 0; i < messages.size(); i++) {
            MessageEvent m = messages.get(i);
            String auteur = auteurs.get(i);
            modelMessages.addElement(auteur + " (" + m.getBienveillance() + ") : " + m.getContenu());
        }
    }

    private void rafraichirAmis() {
        modelAmis.clear();
        for (Bavard ami : bavard.getListeAmis()) {
            modelAmis.addElement(ami.getNom());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonEnvoyer) {
            String txt = champMessage.getText();
            int bienveillance = Integer.parseInt(champBienveillance.getText());
            if (!txt.isEmpty()) {
                bavard.envoyerMessage(txt, bienveillance);
                champMessage.setText("");
                champBienveillance.setText("");
                rafraichirMessages();
                for (Bavard b : bavard.getListeAmis()) {
                    new InterfaceBavard(b);
                }
            }
        }
        if (e.getSource() == boutonRetransmettre) {
            int index = listeMessages.getSelectedIndex();
            MessageEvent messageEvent = bavard.getMessagesRecus().get(index);
            bavard.retransmettreMessage(messageEvent);
            rafraichirMessages();
            for (Bavard b : bavard.getListeAmis()) {
                new InterfaceBavard(b);
            }
        }
    }
}