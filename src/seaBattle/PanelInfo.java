package seaBattle;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
/**
 * Se usa para mostrar un panel informativo del juego
 * Author: Robert Fernando Gil robert.gil@correounivalle.edu.co - 2022985 - Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 05/03/2022
 */


public class PanelInfo extends JPanel {
    private  JLabel symbol, symbol2, symbol3;
    private JButton frag, destr, sub, carrier;
    private ButtonGroup groupBoats;
    private JLabel amount, numFrag, numDestr, numSub, numCarrier;

    /**
     * Constructor de la clase PanelInfo
     */
    public PanelInfo(){
        initGUI();
    }

    /**
     * Método para inicializar los atributos del panel
     */
    public void initGUI(){
        setBorder(new LineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        setSize(new Dimension(300, 150));

      //  setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
     //   setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(11, 11));

        /**
         * Símbolos de los tiros
         */

        JLabel icons = new JLabel("Significado de los iconos: ");

        symbol = new JLabel("Tiro al agua ");
        symbol.setPreferredSize(new Dimension(100, 30));
        symbol.setIcon(new ImageIcon(getClass().getResource("/resources/fail.png")));
        symbol.setHorizontalAlignment(JLabel.CENTER);

        symbol2 = new JLabel("Tocado");
        symbol2.setPreferredSize(new Dimension(50, 40));
        symbol2.setIcon(new ImageIcon(getClass().getResource("/resources/bomb.png")));
        symbol2.setHorizontalAlignment(JLabel.CENTER);

        symbol3 = new JLabel("Hundido");
        symbol3.setPreferredSize(new Dimension(50, 30));
        symbol3.setIcon(new ImageIcon(getClass().getResource("/resources/fire.png")));
        symbol3.setHorizontalAlignment(JLabel.CENTER);

        /**
         * Tipo y número de barcos disponibles.
         */

        JLabel barcos = new JLabel("Barcos disponibles: ");

        frag = new JButton("Fragata");
        frag.setActionCommand("fragata");
        frag.setIcon(new ImageIcon(getClass().getResource("/resources/fragata_ico.png")));
        frag.setOpaque(true);
        frag.setFocusPainted(false);
        frag.setBorder(null);
        frag.setContentAreaFilled(false);
        frag.setFocusable(true);
        add(frag);


        destr = new JButton("Destructor");
        destr.setActionCommand("destructor");
        destr.setIcon(new ImageIcon(getClass().getResource("/resources/destructor_ico.png")));
        destr.setOpaque(true);
        destr.setFocusPainted(false);
        destr.setBorder(null);
        destr.setContentAreaFilled(false);
        destr.setFocusable(true);


        sub = new JButton("Submarino");
        sub.setActionCommand("submarino");
        sub.setIcon(new ImageIcon(getClass().getResource("/resources/submarino_ico.png")));
        sub.setOpaque(true);
        sub.setFocusPainted(false);
        sub.setBorder(null);
        sub.setContentAreaFilled(false);
        sub.setFocusable(true);

        carrier = new JButton("Portaavion");
        carrier.setActionCommand("portavion");
        carrier.setIcon(new ImageIcon(getClass().getResource("/resources/portaaviones_ico.png")));
        carrier.setOpaque(true);
        carrier.setFocusPainted(false);
        carrier.setBorder(null);
        carrier.setContentAreaFilled(false);
        carrier.setFocusable(true);

        groupBoats = new ButtonGroup();
        groupBoats.add(frag);
        groupBoats.add(destr);
        groupBoats.add(sub);
        groupBoats.add(carrier);
        groupBoats.setSelected(frag.getModel(), true);

        amount = new JLabel("Número de barcos: ");
        numFrag = new JLabel("4");
        numDestr = new JLabel("3");
        numSub = new JLabel("2");
        numCarrier = new JLabel("1");

        add(barcos);
        add(new JLabel(""));
        add(amount);
        add(frag);
        add(new JLabel(""));
        add(numFrag);
        add(destr);
        add(new JLabel(""));
        add(numDestr);
        add(sub);
        add(new JLabel(""));
        add(numSub);
        add(carrier);
        add(new JLabel(""));
        add(numCarrier);
        add(new JSeparator(0));
        add(new JSeparator(0));
        add(new JSeparator(0));

        add(icons);
        add(new JLabel(""));
        add(symbol);
        add(new JLabel(""));
        add(symbol2);
        add(new JLabel(""));
        add(symbol3);

    }
}
