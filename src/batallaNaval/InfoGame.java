package batallaNaval;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class InfoGame extends JPanel {
    private  JLabel symbol, symbol2, symbol3;


    public InfoGame(){

        initGUI();


    }
    public void initGUI(){
        setBorder(new LineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        setSize(new Dimension(50, 50));

      //  setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
     //   setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(3, 3));


        symbol = new JLabel("Tiro al agua ");
        symbol.setPreferredSize(new Dimension(100, 25));
        symbol.setIcon(new ImageIcon(getClass().getResource("/resources/fail.png")));
        symbol.setHorizontalAlignment(JLabel.CENTER);

        symbol2 = new JLabel("Tocado");
        symbol2.setPreferredSize(new Dimension(50, 25));
        symbol2.setIcon(new ImageIcon(getClass().getResource("/resources/bomb.png")));
        symbol2.setHorizontalAlignment(JLabel.CENTER);

        symbol3 = new JLabel("Hundido");
        symbol3.setPreferredSize(new Dimension(50, 25));
        symbol3.setIcon(new ImageIcon(getClass().getResource("/resources/fire.png")));
        symbol3.setHorizontalAlignment(JLabel.CENTER);


        add(symbol);
        add(symbol2);
        add(symbol3);

    }
}
