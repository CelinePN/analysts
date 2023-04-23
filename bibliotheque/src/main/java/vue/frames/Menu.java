package vue.frames;
import javax.swing.*;
import net.java.dev.designgridlayout.DesignGridLayout;

public class Menu extends JFrame {

    public Menu() {
        initComponents();
    }
    private void initComponents() {

        JPanel buttonPanel = new JPanel();
        JPanel emptyPanel = new JPanel();

        // Ajouter les boutons au premier JPanel
        JButton demandeButton = new JButton("Demande");
        JButton offreButton = new JButton("Offre");
        JButton comparaisonButton = new JButton("Comparaison Offre/demande");

        buttonPanel.setLayout(new DesignGridLayout());

        CellConstraints cc = new CellConstraints();

        buttonPanel.add(demandeButton, cc.xy(2, 2));
        buttonPanel.add(offreButton, cc.xy(4, 2));
        buttonPanel.add(comparaisonButton, cc.xy(6, 2));

        // Ajouter les deux JPanels au JFrame
        getContentPane().setLayout(new FormLayout("fill:pref:grow",
                "fill:pref:grow, fill:pref:grow"));

        getContentPane().add(buttonPanel, cc.xy(1, 1));
        getContentPane().add(emptyPanel, cc.xy(1, 2));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Exemple JFrame avec deux JPanel");

        pack();
    }

    public static void main(String[] args) {
        Menu frame = new Menu();
        frame.setVisible(true);
    }

}


