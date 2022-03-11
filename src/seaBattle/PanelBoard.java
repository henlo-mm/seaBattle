package seaBattle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @autor:
 * Robert Fernando Gil robert.gil@correounivalle.edu.co -
 * Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 10/03/2022
 */

public class PanelBoard implements  Drawable {
    int[] field = new int[GameConstant.CELLS_COUNT+1];
    private final int TOTAL_SHIP_LENGTH = IntStream.of(GameConstant.SHIPS_SIZE).sum();
    private int damagedCellsCount = 0;
    Ship[] ships = new Ship[GameConstant.SHIPS_COUNT+1];


    public PanelBoard() {    }

    /**
     *  Método que dibuja el tablero
     */

    public void draw(Graphics g) {

        //Color del borde las celdas
        g.setColor(Color.BLACK);
        ((Graphics2D)g).setStroke(new BasicStroke(0.0f));
        for (int i = GameConstant.CELL_SIZE; i < GameConstant.CELL_SIZE *GameConstant.DIMENSION; i+=GameConstant.CELL_SIZE) {
            g.drawLine(0, i, GameConstant.CELL_SIZE *GameConstant.DIMENSION, i);
        }
        for (int i = GameConstant.CELL_SIZE; i < GameConstant.CELL_SIZE *GameConstant.DIMENSION; i+=GameConstant.CELL_SIZE) {
            g.drawLine(i, 0, i, GameConstant.CELL_SIZE *GameConstant.DIMENSION);
        }
    }

    /**
     * Obtiene los tiros realizados.
     */

    public int getShot(int n) {
        int id = field[n];
        field[n] = -1;
        if (id == 0) {
            return -1;
        }
        ships[id].getShot();
        if (ships[id].isDestroyed()) {
            damagedCellsCount++;
            return id;
        }
        damagedCellsCount++;
        return 0;
    }

    /**
     * Determina si se han realizado todos los tiros necesarios.
     * @return
     */
    public boolean isAllShot() {
        return damagedCellsCount == TOTAL_SHIP_LENGTH;
    }

    /**
     * Dibuja una "x" si el tiro da en el agua
     *
     */
    static class Miss implements Drawable{
        private BufferedImage icone;
        int n;
        public Miss(int n){
            this.n=n;
        }

        public void draw(Graphics g){

            // g.setColor(Color.BLACK);
            // int diameter=(int)(GameConstant.CELL_SIZE *0.3);
            ((Graphics2D)g).setStroke(new BasicStroke(4.0f));

            try {


                icone = ImageIO.read(getClass().getResource("/resources/fail.png"));

            } catch (IOException e) {
                e.printStackTrace();
            }
            // g.drawOval(getX(n)+(GameConstant.CELL_SIZE -diameter)/2,getY(n)+(GameConstant.CELL_SIZE -diameter)/2,diameter,diameter);
            g.drawImage(icone, getX(n),getY(n), null);
        }
    }
    /**
     *
     *
     * //Intento fallido
     static  class Destroy implements Drawable{

     private BufferedImage icon;


     int n;
     public Destroy(int n){
     this.n = n;

     }

     @Override
     public void draw(Graphics g) {

     ((Graphics2D)g).setStroke(new BasicStroke(4.0f));

     //  int diameter=(int)(GameConstant.CELL_SIZE *0.5);

     try {

     icon = ImageIO.read(getClass().getResource("/resources/fire.png"));

     } catch (IOException e) {
     e.printStackTrace();
     }


     //0.5 - size of object/size of board cell
     //  g.fillOval(getX(n)+(GameConstant.CELL_SIZE -diameter)/2,getY(n)+(GameConstant.CELL_SIZE -diameter)/2,diameter,diameter);
     g.drawImage(icon, getX(n),getY(n), null);


     }
     }

     */

    /**
     * Determina si un barco fue destruido.
     * @param id
     * @return
     */

    public Ship getDestroyedShip(int id){
        return ships[id];
    }

    /**
     * Esta clase permite dibujar una bomba donde el barco fue tocado.
     */


    static class Shot implements Drawable{
        int n;
        private BufferedImage iconeNave;

        public Shot(int n){
            this.n=n;
        }
        @Override
        public void draw(Graphics g) {

            ((Graphics2D)g).setStroke(new BasicStroke(4.0f));
            try {

                iconeNave = ImageIO.read(getClass().getResource("/resources/bomb.png"));

            } catch (IOException e) {
                e.printStackTrace();
            }

            g.drawImage(iconeNave, getX(n),getY(n), null);
        }
    }

    /**
     * Obtiene la posición "" del tablero
     */

    public static int getX(int n) {
        if (n % GameConstant.DIMENSION == 0) return (GameConstant.DIMENSION -1)*GameConstant.CELL_SIZE;
        else return (n % GameConstant.DIMENSION -1)*GameConstant.CELL_SIZE;
    }

    /**
     * Obtiene la posición "Y" del tablero
     */

    public static int getY(int n) {
        if (n % GameConstant.DIMENSION == 0) return (n / GameConstant.DIMENSION -1)* GameConstant.CELL_SIZE;
        else return n / GameConstant.DIMENSION *GameConstant.CELL_SIZE;
    }

    /**
     * Este método ubica automáticamente los barcos enemigos en su tablero.
     */

    public void autoPlaceShips() {
        boolean[] used = new boolean[GameConstant.CELLS_COUNT+1];
        int[] forRandomPickX = new int[GameConstant.CELLS_COUNT+1];
        int[] forRandomPickY = new int[GameConstant.CELLS_COUNT+1];
        for (int i = 1; i < forRandomPickX.length; i++) {
            forRandomPickX[i] = i;
            forRandomPickY[i] = i;
        }
        forRandomPickX[0] = 1;
        forRandomPickY[0] = 1;
        Random random = new Random();
        for (int i = 1; i < GameConstant.SHIPS_SIZE.length; i++) {
            boolean choice = random.nextBoolean();
            if (choice) autoBuildShip(GameConstant.SHIPS_SIZE[i], i, GameConstant.HORIZONTAL, forRandomPickX, used);
            else autoBuildShip(GameConstant.SHIPS_SIZE[i], i, GameConstant.VERTICAL, forRandomPickY, used);
        }
    }


    /**
     * Genera un número aleatorio entre una posición y otra.
      * @param from
     * @param to
     * @return
     */
    public int getRandom(int from, int to) {
        Random r = new Random();
        return r.nextInt(to - from) + from;
    }

    /**
     * Obtiene una posición válida para empezar a añadir los barcos.
     * @param size
     * @param orient
     * @param forRandomPick
     * @return
     */
    private int getValidShipStart(int size, boolean orient, int[] forRandomPick) {
        int position = getRandom(forRandomPick[0], GameConstant.CELLS_COUNT+1);
        int start = forRandomPick[position];
        update(forRandomPick, position);

        while (!isValidStartForBuildShip(start, size, orient)) {
            position = getRandom(forRandomPick[0], GameConstant.CELLS_COUNT+1);
            start = forRandomPick[position];
            update(forRandomPick, position);
        }
        return start;
    }

    /**
     * Determina si una posición es válida para comenzar a añadir los barcos.
     * @param start
     * @param size
     * @param orient
     * @return
     */
    private boolean isValidStartForBuildShip(int start, int size, boolean orient) {
        if (start < 1 || start > GameConstant.CELLS_COUNT+1) return false;
        int distToEdge;
        if (orient) {
            distToEdge = GameConstant.DIMENSION - start % GameConstant.DIMENSION;
        } else {
            int row;
            if (start % GameConstant.DIMENSION == 0) row = start / GameConstant.DIMENSION;
            else row = start / GameConstant.DIMENSION + 1;
            distToEdge = GameConstant.DIMENSION - row;
        }
        return size - 1 <= distToEdge && distToEdge != GameConstant.DIMENSION;
    }

    /**
     * Genera los barcos del computador.
     * @param size
     * @param id
     * @param orient
     * @param forRandomPick
     * @param used
     */

    private void autoBuildShip(int size, int id, boolean orient, int[] forRandomPick, boolean[] used) {

        int start = getValidShipStart(size, orient, forRandomPick);
        //Construye los barcos
        ships[id] = new Ship(orient, start, size);
        while (!isShipValid(ships[id], used)) {
            start = getValidShipStart(size, orient, forRandomPick);
            ships[id] = new Ship(orient, start, size);
        }
        //Actualizaclas celdas usadas
        for (int n : ships[id].getSurrounded()) {
            used[n] = true;
        }
        for (int n : ships[id].position()) field[n] = id;
    }

    /**
     * Obtiene todos los barcos
     * @return
     */

    public Ship[] getAllShips(){
        Ship[] allShips =new Ship[ships.length-1];
        System.arraycopy(ships,1, allShips,0, ships.length-1);
        return allShips;
    }

    /**
     * Determina si un barco es válid
     * @param ship
     * @param usedCells
     * @return
     */

    public boolean isShipValid(Ship ship, boolean[] usedCells) {
        for (int n : ship.position()) {
            if (usedCells[n]) return false;
        }
        return true;
    }

    /**
     *  Verifica las celdas.
     * @param n
     * @return
     */
    public boolean isCellChecked(int n) { //true if this cell is already checked
        return field[n] == -1;
    }

    /**
     * Actualiza el tablero
     * @param array
     * @param b
     */
    public void update(int[] array, int b) {
        int a=array[0];
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
        array[0]++;
    }
}
