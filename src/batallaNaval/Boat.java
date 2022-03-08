package batallaNaval;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @autor:
 * Robert Fernando Gil robert.gil@correounivalle.edu.co -
 * Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 05/03/2022
 */
public class Boat extends JLabel {
    public Boat(int ship){

        this.setPreferredSize(new Dimension(25, 25));

        switch (ship){
            case 1:
                setIcon(new ImageIcon(getClass().getResource("/resources/fragata.png")));
            case 2:
                setIcon(new ImageIcon(getClass().getResource("/resources/destructor.png")));
            case 3:
                setIcon(new ImageIcon(getClass().getResource("/resources/submarino.png")));
            case 4:
                setIcon(new ImageIcon(getClass().getResource("/resources/portaaviones.png")));


        }
      //  setVerticalTextPosition(SwingConstants.BOTTOM);
        //setHorizontalTextPosition(SwingConstants.CENTER);

    }
}
