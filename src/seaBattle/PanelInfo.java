package seaBattle;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
/**
 * Se usa para mostrar un panel informativo del juego
 * Author: Robert Fernando Gil robert.gil@correounivalle.edu.co - 2022985 - Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 15/03/2022
 */


public class PanelInfo extends JPanel {
    private  JLabel symbol, symbol2, symbol3;

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

        setLayout(new GridLayout(1, 4));

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

        add(icons);
        add(symbol);
        add(symbol2);
        add(symbol3);

    }
}
