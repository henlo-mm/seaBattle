package seaBattle;

/**
 * Se usa para declarar los estados del juego
 * @autor:
 * Robert Fernando Gil robert.gil@correounivalle.edu.co -
 * Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 10/03/2022
 */

public enum State {
    DO_NOTHING,
    BUILD_HORIZONTAL,
    BUILD_VERTICAL,
    AUTO_BUILD_SHIPS,
    MAKE_MOVE,
    CHOOSE_ORIENT,
    NEW_GAME,
    END
}
