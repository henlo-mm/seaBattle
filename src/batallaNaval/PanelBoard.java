package batallaNaval;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @autor:
 * Robert Fernando Gil robert.gil@correounivalle.edu.co -
 * Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 05/03/2022
 */

public class PanelBoard extends JPanel {
    private int player;
    private String cols = "ABCDEFGHIJ";
    private Square[][] squares = new Square[10][10];
    private  JLabel bor, num;
    private Escucha escucha;

    public PanelBoard(int player){
        this.player = player;
        initGui();
    }
    public PanelBoard(){
        initGui();
    }

    public void initGui(){
        this.setLayout(new GridLayout(0, 11));
        this.setBorder(new LineBorder(Color.BLACK));

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < squares.length; i++){
            for (int j = 0; j < squares[i].length; j++){
                Square buttons = new Square();
                buttons.setMargin(buttonMargin);

           /**     if (player == 0){
                    buttons.setActionCommand(String.valueOf(j) + "" + String.valueOf(i));
                }else {
                    buttons.setActionCommand("ia." + String.valueOf(j) + "" + String.valueOf(i));
                }

            */
                if (player == 0){
                    buttons.setBackground(Color.darkGray);
                }else{
                    buttons.setBackground(new Color(0,191,255));
                }
                squares[j][i] = buttons;
                squares[j][i].addActionListener(escucha);
            }
        }

        this.add(new JLabel(""));

        for (int i = 0; i<10; i++){
            bor = new JLabel(cols.substring(i, i + 1), SwingConstants.CENTER);
            Border border = BorderFactory.createLineBorder(new Color(90,90,90), 1);
            bor.setBorder(border);
            this.add(bor);
        }

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                switch (j) {

                    case 0:
                        num = new JLabel("" + (i + 1), SwingConstants.CENTER);
                        Border border = BorderFactory.createLineBorder(new Color(90,90,90), 1);
                        num.setBorder(border);
                        this.add(num);
                    default:


                        this.add(squares[j][i]);
                }
            }
        }



    }
   /** public void addActionListenerToSquares(ActionListener actionListener){
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
        for (Square[] lvC : this.squares){
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

    public Square[][] getSquares(){
        return squares;
    }

    */


    public class Escucha implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
