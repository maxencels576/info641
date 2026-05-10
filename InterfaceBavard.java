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

    public InterfaceBavard(Bavard bavard){
        super();
        this.bavard = bavard;

        this.setTitle("Interface de " + bavard.getNom());
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout()); // organiser les composants

        JLabel titre = new JLabel("Messages reçus :");
        titre.setHorizontalAlignment(JLabel.CENTER);
        this.add(titre, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(listeMessages); // barre de défilement
        this.add(scroll, BorderLayout.CENTER);

        JPanel panelBas = new JPanel(new GridLayout(1, 3));
        panelBas.add(champMessage);
        panelBas.add(champBienveillance);
        panelBas.add(boutonEnvoyer);

        boutonEnvoyer.addActionListener(this);

        this.add(panelBas, BorderLayout.SOUTH);

        rafraichirMessages();

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
                // on affiche une nouvelle interface avec le nouveau message reçu
                for (Bavard b : bavard.getListeAmis()) {
                    // il faudrait fermer la 1ère interface ouverte pour le bavard b
                    new InterfaceBavard(b);
                }
            }
        }
    }
}