package seaBattle;

/**
 * Esta interfaz permite al jugador realizar las acciones mediante onMouseClicked
 * Author: Robert Fernando Gil robert.gil@correounivalle.edu.co - 2022985 - Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 10/03/2022
 */

public interface PlayerAction {
    void onMouseClicked(int x, int y, State s);
    void onPassState(State state);

}
