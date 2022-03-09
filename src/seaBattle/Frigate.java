package seaBattle;

import javax.swing.*;
import java.awt.*;

public class Frigate extends  Boat {
    private int length;
    public  Frigate(){
       // this.length = 1;
       setIcon(new ImageIcon(getClass().getResource("/resources/fragata.png")));
       setText("F");

    }
}
