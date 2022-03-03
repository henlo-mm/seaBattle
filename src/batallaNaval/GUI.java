package batallaNaval;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GUI extends JFrame {
    private  GUIBoard board, boardIA;
    private  InfoGame infoGame;

    public GUI(GUIBoard guiBoard) {
       // GameBoard board = new GameBoard();
       // setLayout(new GridLayout(1, 3));
        setVisible(true);
        setSize(new Dimension(900, 350));
        setResizable(false);
        board = guiBoard;
        board.setPreferredSize(new Dimension(350,50));
        boardIA = new GUIBoard(1);
        boardIA.setPreferredSize(new Dimension(350,50));

        infoGame = new InfoGame();

        add(board, BorderLayout.EAST);
        add(infoGame, BorderLayout.CENTER);
        add(boardIA, BorderLayout.WEST);

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
        GUIBoard guiBoard = new GUIBoard(1);
        GUI gui = new GUI(guiBoard);

    }

}
