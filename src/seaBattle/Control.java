package seaBattle;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @autor:
 * Robert Fernando Gil robert.gil@correounivalle.edu.co -
 * Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 05/03/2022
 */

public class Control {
    private Square[][] squares;
   // private Square square;

    private PanelBoard panelBoard;

    private boolean horizontal;
    private int length;
    private Frigate frigate;
 /**   private Boat frigate2;
    private Boat frigate3;
    private Boat frigate4;
    private Boat destroyer;
    private Boat destroyer2;
    private Boat destroyer3;
    private Boat submarine;
    private Boat submarine2;
    private Boat cruiser;
    private Boat[] boats;
  */
    private Random random;

    public  Control(){
        panelBoard = new PanelBoard(1);
        squares = new Square[10][10];
        frigate = new Frigate();
   /**     frigate2 = new Frigate();
        frigate3 = new Frigate();
        frigate4 = new Frigate();
        destroyer = new Destroyer();
        destroyer2 = new Destroyer();
        destroyer3 = new Destroyer();
        submarine = new Submarine();
        submarine2 = new Submarine();
        cruiser = new Cruiser();
        random = new Random();
        boats = new Boat[10];
        boats[0] = frigate;
        boats[1] = frigate2;
        boats[2] = frigate3;
        boats[3] = frigate4;
        boats[4] = destroyer;
        boats[5] = destroyer2;
        boats[6] = destroyer3;
        boats[7] = submarine;
        boats[8] = submarine2;
        boats[9] = cruiser;

    */
       // square = new Square();
    }

    //Este método nos ayudará a obtener la posición de la casilla que fue clickeada y pintarla.

    public void getPositionClicked(Object button, Square[][] square){
        for (int i = 0; i<square.length; i++){
            for (int j = 0; j<square[0].length; j++){
                if(button.equals(square[i][j])){
                    System.out.print(" X: " + i +  " , " + " Y: " + j );
                   // square[i][j].setBackground(Color.GREEN);

                }
            }
        }
    }
}
