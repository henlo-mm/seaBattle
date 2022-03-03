package batallaNaval;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameBoard {
    private JPanel board;
    private JButton[][] cSquares = new JButton[10][10];
    private JPanel cBoard, cBoard2;
    JToolBar toolBar = new JToolBar();
    Insets margin = new Insets(0,0,0,0);
    int squares = 10;
    int space = 10;
    ImageIcon icon = new ImageIcon(new BufferedImage(space, space, BufferedImage.TYPE_INT_ARGB));

    public GameBoard(){
        initGUI();

    }

    public void initGUI(){
        board = new JPanel(new BorderLayout(3, 3));
        board.setBorder(new EmptyBorder(5,5,5,5));
        toolBar.setFloatable(false);
        board.add(toolBar, BorderLayout.PAGE_START);
        cBoard = new JPanel(new GridLayout(0, 10));
        cBoard.setBorder(new LineBorder(Color.BLACK));
        board.add(cBoard);

        for (int i=0; i < cSquares.length; i++){
            for (int j = 0; j<cSquares[i].length; j++){
                JButton button = new JButton();
                button.setMargin(margin);
                button.setIcon(icon);
                if((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)){
                    button.setBackground(Color.WHITE);
                }else {
                    button.setBackground(Color.BLACK);
                }
                cSquares[j][i] = button;
            }
        }
        for (int i = 0; i < squares; i++){
            for (int j = 0; j < squares; j++){
                cBoard.add(cSquares[j][i]);
            }
        }


    }
    public JComponent getGui(){
        return board;
    }
    public  JComponent getGui2(){
        return board;
    }
}
