package seaBattle;

import javax.swing.*;
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
    private Escucha escucha;

    public GUI(PanelBoard panelBoard) {

        escucha = new Escucha();

        setVisible(true);
        setSize(new Dimension(1200, 800));
        setResizable(false);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridLayout(2, 2));


        setVisible(true);
        setSize(new Dimension(1200, 800));
        setResizable(false);

        //Tablero del computador

        board = panelBoard;
        board.setBorder(BorderFactory.createTitledBorder("Tablero principal:"));

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;

        add(board);

        //Tablero del jugador

        boardIA = new PanelBoard(1);
        boardIA.setBorder(BorderFactory.createTitledBorder("Tablero de posición:"));
        boardIA.setPreferredSize(new Dimension(600, 300));


        c.gridx = 1;
        c.gridy = 3;

        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        add(boardIA, c);

        //Panel de información

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
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        setLocationRelativeTo(null);

    }

    public static void main(String[] args){
        PanelBoard panelBoard = new PanelBoard();
        GUI gui = new GUI(panelBoard);

    }

    public class Escucha implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {



        }
    }

}
