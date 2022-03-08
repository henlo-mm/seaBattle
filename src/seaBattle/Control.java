package seaBattle;

import javax.swing.*;
import java.awt.*;

/**
 * @autor:
 * Robert Fernando Gil robert.gil@correounivalle.edu.co -
 * Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 05/03/2022
 */

public class Control {
    private Square[][] squares;
    private PanelBoard panelBoard;

    public  Control(){
        panelBoard = new PanelBoard();
        squares = new Square[10][10];
    }

    //Este método nos ayudará a obtener la posición de la casilla que fue clickeada y pintarla.

    public void getPositionClicked(Object button, Square[][] square){
        for (int i = 0; i<square.length; i++){
            for (int j = 0; j<square[0].length; j++){
                if(button.equals(square[i][j])){
                    System.out.print(" Columna " + i +  ", " + " Fila " + j );
                    square[i][j].setBackground(Color.GREEN);
                }
            }
        }
    }
}
