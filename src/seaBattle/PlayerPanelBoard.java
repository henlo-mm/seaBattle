package seaBattle;

/** Esta clase se utiliza para ubicar los barcos en el tablero del jugador y manejar los métodos.
 * @autor:
 * Robert Fernando Gil robert.gil@correounivalle.edu.co -
 * Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 10/03/2022
 */

public class PlayerPanelBoard extends PanelBoard {
    public void playerGetPseudoShot(int n){
        field[n]=-1;
    }

    public void placeShip(Ship ship, int id, boolean[] usedCells) {
        for (int n : ship.getSurrounded()) {
            usedCells[n] = true;
        }
        ships[id] = ship;
        for (int n : ships[id].position()) field[n] = id;
    }

}
