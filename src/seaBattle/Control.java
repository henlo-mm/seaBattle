package seaBattle;

import javax.swing.*;

/**
 * @autor:
 * Robert Fernando Gil robert.gil@correounivalle.edu.co -
 * Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 05/03/2022
 */

public class Control {
    private Square[][] squares;
    private PanelBoard panelBoard;
    private Boat boat;

    //Este método nos ayudará a obtener la posición de la casilla que fue clickeada.

    public  Control(){
        panelBoard = new PanelBoard();
        squares = new Square[10][10];
        boat = new Boat(1);
    }

    public void getPositionClicked(Object button, Square[][] square){
        for (int i = 0; i<square.length; i++){
            for (int j = 0; j<square[0].length; j++){

                if(button.equals(square[i][j])){
                    System.out.print(" Columna " + i +  ", " + " Fila " + j );
                    square[1][2].setIcon(new ImageIcon(getClass().getResource("/resources/destructor.png")));





                }
            }
        }
    }



 /**   public void addActionListenerToSquares(ActionListener actionListener){
        for  (Square[] lvC : this.squares){
            for (Square vC : lvC){
                if (vC.isEnabled()){
                    vC.addActionListener(actionListener);
                }
            }
        }

    }

    public void removeActionListenerToSquares(ActionListener action){
        for (Square[] lvC : this.squares){
            for (Square vC : lvC){
                vC.removeActionListener(action);
            }
        }
    }
    public  void setVisibleSquares(){
        for (Square[] lvC : this.getSquares()){
            for (Square vC : lvC){
                if(vC.getText().equals("b")){
                    vC.setEnabled(true);
                }else{
                    vC.setEnabled(false);
                }
                vC.setText("");
            }
        }
    }

  */



}
