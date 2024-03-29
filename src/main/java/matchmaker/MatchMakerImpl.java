package matchmaker;

import model.gameInfo.*;
import model.gameInfo.utils.RandomPlayerPlacer;
import model.gameInfo.utils.RandomVirusGenerator;
import model.gameObjects.GameField;
import model.gameInfo.utils.UniformFoodGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates {@link GameSession} for single player
 *
 * @author Alpi
 */
public class MatchMakerImpl implements MatchMaker {
    @NotNull
    private final Logger log = LogManager.getLogger(MatchMakerImpl.class);
    @NotNull
    private final List<GameSession> activeGameSessions = new ArrayList<>();

  /**
   * Creates new GameSession for single player
   *
   * @param player single player
   */
    @Override
    public void joinGame(@NotNull Player player) {
        GameSession newGameSession = createNewGame();
        activeGameSessions.add(newGameSession);
        newGameSession.join(player);
        if (log.isInfoEnabled()) {
        log.info(player + " joined " + newGameSession);
        }
    }

    @NotNull
    public List<GameSession> getActiveGameSessions() {
    return new ArrayList<>(activeGameSessions);
  }

  /**
   * @return new GameSession
   */
    private
    @NotNull
    GameSession createNewGame() {
        GameField field = new GameField();
        //TODO
        //Ticker ticker = ApplicationContext.instance().get(Ticker.class);
        UniformFoodGenerator foodGenerator = new UniformFoodGenerator(field, GameConstants.FOOD_PER_SECOND_GENERATION, GameConstants.MAX_FOOD_ON_FIELD);
        //ticker.registerTickable(foodGenerator);
        return new GameSessionImpl(foodGenerator, new RandomPlayerPlacer(field), new RandomVirusGenerator(field, GameConstants.NUMBER_OF_VIRUSES));
    }
}
