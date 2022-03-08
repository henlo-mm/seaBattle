package seaBattle;

import javax.swing.*;
import java.awt.*;
/**
 * @autor:
 * Robert Fernando Gil robert.gil@correounivalle.edu.co -
 * Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 05/03/2022
 */


public class Square extends JButton {
    public Square(){
        this.setPreferredSize(new Dimension(25, 25));
        this.setBackground(Color.WHITE);
        this.setForeground(Color.darkGray);
    //    this.setContentAreaFilled(false);
    }
}
