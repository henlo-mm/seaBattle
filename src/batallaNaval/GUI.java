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
public class GUI extends JFrame {
    private PanelBoard board, boardIA;
    private  InfoGame infoGame;
    private int player;
    private String cols = "ABCDEFGHIJ";
    private Square[][] squares = new Square[10][10];
    private JLabel bor, num;
    private Escucha escucha;

    public GUI(PanelBoard panelBoard) {

        escucha = new Escucha();

        setVisible(true);
        setSize(new Dimension(1200, 800));
        setResizable(false);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridLayout(2, 3));



        setVisible(true);
        setSize(new Dimension(1200, 800));
        setResizable(false);


      /*  board = panelBoard;
        board.setBorder(BorderFactory.createTitledBorder("Tablero principal:"));
        board.setPreferredSize(new Dimension(600, 300));


        // column 1
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        add(board, c);
        */

        //Panel Jugador

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Tablero principal:"));
        panel.setLayout(new GridLayout(0, 11));


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

                    buttons.setBackground(new Color(0,191,255));

                squares[j][i] = buttons;
                squares[j][i].addActionListener(escucha);
            }
        }

        this.add(new JLabel(""));

        for (int i = 0; i<10; i++){
            bor = new JLabel(cols.substring(i, i + 1), SwingConstants.CENTER);
            Border border = BorderFactory.createLineBorder(new Color(90,90,90), 1);
            bor.setBorder(border);
            panel.add(bor);
        }

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                switch (j) {

                    case 0:
                        num = new JLabel("" + (i + 1), SwingConstants.CENTER);
                        Border border = BorderFactory.createLineBorder(new Color(90,90,90), 1);
                        num.setBorder(border);
                        panel.add(num);
                    default:

                        squares[j][i].addActionListener(escucha);

                        panel.add(squares[j][i]);
                }
            }
        }



        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;


        add(panel);


        boardIA = new PanelBoard(1);
        boardIA.setBorder(BorderFactory.createTitledBorder("Tablero de posición:"));
        boardIA.setPreferredSize(new Dimension(600, 300));

        c.gridx = 1;
        c.gridy = 3;

        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        add(boardIA, c);


       // add(panelBoats, c);

        infoGame = new InfoGame();
        infoGame.setBorder(BorderFactory.createTitledBorder("Información:"));
        infoGame.setPreferredSize(new Dimension(250, 100));


        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;

        add(infoGame);


        setTitle("Batalla Naval");

        //this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        //add(board.getGui());
        //setLocationByPlatform(true);

        //setLocation(50,50);
       // pack();
        setLocationRelativeTo(null);


    }

    public static void main(String[] args){
        PanelBoard panelBoard = new PanelBoard(1);
        GUI gui = new GUI(panelBoard);

    }

    public class Escucha implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {



                Square squar = (Square)e.getSource();


                for (int i = 0; i<squares.length; i++){
                    for (int j = 0; j<squares[0].length; j++){
                        if(squar.equals(squares[i][j])){
                            System.out.print(" Columna " + i +  ", " + " Fila " + j );
                        }
                    }
                }





        }
    }

}
