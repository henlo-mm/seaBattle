package seaBattle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

/**
 * Se usa para declarar los métodos y las acciones tanto del jugador como del computador.
 * Author: Robert Fernando Gil robert.gil@correounivalle.edu.co - 2022985 - Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 05/03/2022
 */

public class Control implements  PlayerAction {
    private PlayerPanelBoard playerBoard;
    private PanelBoard computerPanelBoard;
    private boolean[] cellsUsedForShipBuilding;
    private int[] forRandomPick;
    private ArrayList<Integer> forPreferredPick=new ArrayList<>();
    private int i;
    private GUI gameWindow;


    private ArrayDeque<Integer> forSmartPick = new ArrayDeque<>();
    private int goodShotsSoFar = 0;
    private int goodShotSoFarMin = 1000;
    private int goodShotSoFarMax = 0;
    private boolean orientSoFar;
    private int prev;
    private State state;

    /**
     * Constructor de la clase Control
     */
    Control() {
        play();
    }

    private void buildPreferredPick4(){
        int[] temp={1,5,9,12,16,20,23,27,34,38};
        for (int i:temp){
            forPreferredPick.add(i);
            forPreferredPick.add(i+40);
            if (i+80<=GameConstant.DIMENSION) forPreferredPick.add(i+GameConstant.DIMENSION);
        }
    }

    /**
     * Inicia la partida
     */
    private void play() {
        if (gameWindow == null) {
            gameWindow = new GUI();
            gameWindow.setHandler(this);
        }

       //Panel del jugador
        playerBoard = new PlayerPanelBoard();

        //Panel del computador
        computerPanelBoard = new PanelBoard();
        computerPanelBoard.autoPlaceShips();

        cellsUsedForShipBuilding = new boolean[GameConstant.CELLS_COUNT +1];
        i = 1;
        resetSmartFields();
        forRandomPick = new int[GameConstant.CELLS_COUNT +1];
        for (int j = 1; j < forRandomPick.length; j++) {
            forRandomPick[j] = j;
        }
        buildPreferredPick4();
        forRandomPick[0] = 1;
        state = State.DO_NOTHING;
    }


    /**
     *
     * @param x
     * @param y
     * @param s
     */
    public void onMouseClicked(int x, int y, State s) {
        state = s;
        int xx = x / GameConstant.CELL_SIZE + 1;
        int yy = y / GameConstant.CELL_SIZE + 1;
        switch (state) {
            case BUILD_HORIZONTAL:
                buildShip(xx, yy, GameConstant.HORIZONTAL);
                break;
            case BUILD_VERTICAL:
                buildShip(xx, yy, GameConstant.VERTICAL);
                break;
            case MAKE_MOVE:
                processUserMove(xx, yy);
                break;
        }
    }



    /**
     *
     * @param s
     */

    public void onPassState(State s) {
        state = s;

        switch (state) {
            case NEW_GAME:
                this.play();
                break;
            case CHOOSE_ORIENT:
                if (i == GameConstant.SHIPS_SIZE.length) gameWindow.startShooting();

                else {
                    gameWindow.updateMessage("Longitud del barco:  " + GameConstant.SHIPS_SIZE[i], "");

                    if (GameConstant.SHIPS_SIZE[i] == 1) {
                        gameWindow.updateMessage("", " ");
                        gameWindow.updateState(State.BUILD_HORIZONTAL);
                    } else gameWindow.chooseOrientation();
                }
                break;
            case AUTO_BUILD_SHIPS: {
                playerBoard.autoPlaceShips();
                for (Ship ship : playerBoard.getAllShips()) {
                    gameWindow.drawOnLeft(ship);
                }
                state = State.MAKE_MOVE;
            }

            break;

        }

    }


    /**
     *
     * @param x
     * @param y
     */

    private void processUserMove(int x, int y) {
        state = State.DO_NOTHING;
        int coordinate = (y - 1) * GameConstant.DIMENSION + x;
        if (computerPanelBoard.isCellChecked(coordinate)) {
            gameWindow.updateMessage("Por favor, elige otra celda", "");
            return;
        }
        int shot = computerPanelBoard.getShot(coordinate);
        if (shot < 0) {
            gameWindow.drawOnRight(new PanelBoard.Miss(coordinate));
            gameWindow.updateMessage("", "!");
            computerMove();
            if (playerBoard.isAllShot()) {
                gameWindow.updateMessage(":(", "Has perdido");
                gameWindow.updateState(State.END);
                state = State.END;
            }
            return;
        }

        if (shot > 0) {
            gameWindow.drawOnRight(computerPanelBoard.getDestroyedShip(shot));

            gameWindow.updateMessage("", "Este barco está listo");
        }
        gameWindow.drawOnRight(new PanelBoard.Shot(coordinate));
        gameWindow.updateMessage(":)", "Por favor, haz el siguiente disparo");
        gameWindow.updateState(State.MAKE_MOVE);
        if (computerPanelBoard.isAllShot()) {
            gameWindow.updateMessage(":D", "¡Has ganado!");
            gameWindow.updateState(State.END);
            state = State.END;
        }
    }

    /**
     *
     * @param x
     * @param y
     * @param orientation
     */
    private void buildShip(int x, int y, boolean orientation)  {
        int coordinate = (y - 1) * GameConstant.DIMENSION + x;
        int size = GameConstant.SHIPS_SIZE[i];
        Ship ship = new Ship(orientation, coordinate, size);
        if (!playerBoard.isShipValid(ship, cellsUsedForShipBuilding) || !isValidClickForBuildShip(coordinate, size, orientation)) {
            state=State.DO_NOTHING;
            gameWindow.showException();
            if (size==1) gameWindow.updateState(State.BUILD_HORIZONTAL);
            else gameWindow.chooseOrientation();
            return;
        }
        state = State.DO_NOTHING;
        playerBoard.placeShip(ship, i, cellsUsedForShipBuilding);
        gameWindow.drawOnLeft(ship);
        i++;
    }

    /**
     * Determina si hizo click en una posición válida para construir un barco
     * @param start
     * @param size
     * @param isHorizontal
     * @return
     */
    private boolean isValidClickForBuildShip(int start, int size, boolean isHorizontal) {
        if (size==1) return true;
        int dim=GameConstant.DIMENSION;
        int distToEdge;
        if (isHorizontal) {
            distToEdge = dim - start % dim;
        } else {
            int row;
            if (start % dim == 0) row = start / dim;
            else row = start / dim + 1;
            distToEdge = dim - row;
        }
        return size - 1 <= distToEdge && distToEdge != dim;
    }

    /**
     * Método para generar los movimientos del computador
     */
    private void computerMove() {
        gameWindow.updateState(State.DO_NOTHING);
        int shot;
        if (goodShotsSoFar == 0) shot = computerRandomShot();
        else shot = computerMakesSmartShot();
        while (shot >= 0) {
            if (shot == 0) {
                gameWindow.updateMessage("", "Tocado");
                shot = computerMakesSmartShot();
            } else {
                gameWindow.updateMessage("", "Uno de los barcos ha sido completamente destruidos");


                if (playerBoard.isAllShot()) {

                    return;
                }
                if (shot==1) forPreferredPick.clear();

                Ship destroyed = playerBoard.getDestroyedShip(shot);

                for (int n : destroyed.getSurrounded()) {

                    playerBoard.playerGetPseudoShot(n);

                }
                resetSmartFields();

                shot = computerRandomShot();
            }
        }
        gameWindow.updateState(State.MAKE_MOVE);
    }

    /**
     * Realiza los tiros del computador
     * @return
     */

    private int computerMakesSmartShot() {
        if (goodShotsSoFar == 1 && forSmartPick.isEmpty()) {
            int[] possible = {prev - 1, prev + 1, prev - GameConstant.DIMENSION, prev + GameConstant.DIMENSION};
            for (int m : possible) {
                if (m > 0 && m <=GameConstant.CELLS_COUNT && !playerBoard.isCellChecked(m)) {
                    forSmartPick.push(m);
                }
            }
        } else if (goodShotsSoFar >= 2) {
            forSmartPick.clear();
            int[] possible;
            if (orientSoFar) possible = new int[]{goodShotSoFarMin - 1, goodShotSoFarMax + 1};
            else possible = new int[]{goodShotSoFarMin - GameConstant.DIMENSION, goodShotSoFarMax + GameConstant.DIMENSION};
            for (int m : possible) {
                if (m > 0 && m <=GameConstant.CELLS_COUNT && !playerBoard.isCellChecked(m)) {
                    forSmartPick.push(m);
                }
            }
        }
        int n = forSmartPick.pop();
        int shot = playerBoard.getShot(n);
        if (shot >= 0) handleGoodShot(n);
        else gameWindow.drawOnLeft(new PanelBoard.Miss(n));
        gameWindow.updateMessage("Disparo en la celda " + n, "");
        return shot;
    }

    private void resetSmartFields() {
        goodShotsSoFar = 0;
        goodShotSoFarMin = 1000;
        goodShotSoFarMax = 0;
        forSmartPick.clear();
        prev = -1;
    }

    private int getValidRandom(int[] forRandomPick) {
        int position = computerPanelBoard.getRandom(forRandomPick[0], GameConstant.CELLS_COUNT +1);
        int validRandom = forRandomPick[position];
        computerPanelBoard.update(forRandomPick, position);
        return validRandom;
    }

    private int getPreferred(){
        Random r=new Random();
        int random=r.nextInt(forPreferredPick.size());
        int n=forPreferredPick.get(random);
        forPreferredPick.remove(random);
        return n;
    }

    /**
     * Permite al computador hacer tiros random
     * @return
     */

    private int computerRandomShot() {
        int n;
        if (forPreferredPick.size()>0) n=getPreferred();
        else n = getValidRandom(forRandomPick);

        while (playerBoard.isCellChecked(n)) {
            if (forPreferredPick.size()>0) n=getPreferred();
            else n = getValidRandom(forRandomPick);
        }
        int shot = playerBoard.getShot(n);
        gameWindow.updateMessage("Disparo en " + n, "");
        if (shot >= 0) handleGoodShot(n);
        else gameWindow.drawOnLeft(new PanelBoard.Miss(n));
        return shot;
    }

    private void handleGoodShot(int n) {
        gameWindow.drawOnLeft(new PanelBoard.Shot(n));
        prev = n;
        goodShotsSoFar++;
        goodShotSoFarMin = Math.min(goodShotSoFarMin, n);
        goodShotSoFarMax = Math.max(goodShotSoFarMax, n);
        if (goodShotsSoFar == 2) {
            orientSoFar = goodShotSoFarMax - goodShotSoFarMin == 1;
        }
    }

}
