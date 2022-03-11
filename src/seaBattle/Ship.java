package seaBattle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;

/**
 *  Esta clase es usada para manejar y crear los barcos.
 * @autor:
 * Robert Fernando Gil robert.gil@correounivalle.edu.co -
 * Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 05/03/2022
 */


public class Ship implements Drawable{
    private final int size;
    private final int start;
    private final boolean isHorizontal;
    private int damagedCellCount = 0;
    private int i = 0;

    /**
     * Constructor de la clase
     * @param isHorizontal
     * @param start
     * @param size
     */
    Ship(boolean isHorizontal, int start, int size) {
        this.isHorizontal = isHorizontal;
        this.start = start;
        this.size = size;


    }

    /**
     *
     * @return la posición celdas ocupadas
     */
    int[] position() {
        if (size == 1) return new int[]{start};
        int[] location = new int[size];
        int j;
        if (isHorizontal) j = 1;
        else j = GameConstant.DIMENSION;
        for (int i = 0; i < location.length; i++) {
            location[i] = start + j * i;
        }
        return location;
    }


    /**
     * Método para retornar las celdas ocupadas
     * @return
     */
    ArrayDeque<Integer> getSurrounded() {
        ArrayDeque<Integer> s = new ArrayDeque<>();
        int j = 0;
        int k = 1;
        boolean topLeft = true;
        boolean bottomRight = true;


        if (isHorizontal) {
            if (start % GameConstant.DIMENSION == 1) j = 1;
            else if ((start + size - 1) % GameConstant.DIMENSION == 0) k = 0;
            if (start <=GameConstant.DIMENSION) topLeft = false;
            else if (start > GameConstant.CELLS_COUNT-GameConstant.DIMENSION) bottomRight = false;
            for (int i = -1 + j; i < size + k; i++) {
                if (topLeft) s.add(start + i - GameConstant.DIMENSION);
                if (bottomRight) s.add(start + i + GameConstant.DIMENSION);
                s.add(start + i);
            }
        } else {
            if (start <=GameConstant.DIMENSION) j = 1;
            else if (start + (size - 1) * GameConstant.DIMENSION > GameConstant.CELLS_COUNT-GameConstant.DIMENSION) k = 0;
            if (start % GameConstant.DIMENSION == 1) topLeft = false;
            else if (start % GameConstant.DIMENSION == 0) bottomRight = false;
            for (int i = -GameConstant.DIMENSION + j * GameConstant.DIMENSION; i < (size + k) *GameConstant.DIMENSION; i += GameConstant.DIMENSION) {
                if (topLeft) s.add(start + i - 1);
                if (bottomRight) s.add(start + i + 1);
                s.add(start + i);
            }
        }
        return s;
    }


    boolean isDestroyed() {
        return damagedCellCount == size;
    }

    void getShot() {
        damagedCellCount++;
    }

    /**
     * Este método dibuja los barcos
     * @param g
     */
    public void draw(Graphics g) {


        int scale=GameConstant.CELL_SIZE;
        ((Graphics2D)g).setStroke(new BasicStroke(5.0f));

        //Color de los barcos
        g.setColor(Color.LIGHT_GRAY);

        int xLeft;
        if (start % GameConstant.DIMENSION == 0) xLeft = (GameConstant.DIMENSION -1)*scale;
        else xLeft = (start % GameConstant.DIMENSION -1)*scale;
        int yTop;
        if (start % GameConstant.DIMENSION == 0) yTop = (start / GameConstant.DIMENSION -1)*scale;
        else yTop = start / GameConstant.DIMENSION *scale;

        int xRight;
        int yBottom;
        if (isHorizontal){
            xRight = xLeft + size*scale;
            yBottom = yTop - scale;
        }
        else  {
            xRight = xLeft + scale;
            yBottom = yTop - size*scale;
        }

        int width=(xRight-xLeft);
        int height=(yTop-yBottom);
        System.out.print(GameConstant.SHIPS_SIZE[i]);
        g.drawRect(xLeft,yTop,width,height);
        g.fillRect(xLeft,yTop,width,height);

    }

}