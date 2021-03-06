package edu.up.cs301.risk;

import java.util.ArrayList;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.config.GamePlayerType;


/**
 * RiskMainActivity
 * 
 * This class is in charge of handling the computer players, human players 
 * and the game along with starting the game.
 * 
 * @authors Shamus Murray, Garrett Becker, Logan Mealy, Lucas Burns, John Will Bryan 
 * 
 * @version April 2015 
 */


public class RiskMainActivity extends GameMainActivity {
	
	// the port number that this game will use when playing over the network
	private static final int PORT_NUMBER = 2234;

	@Override
	public LocalGame createLocalGame() {
		return new RiskLocalGame();
	}
	
	/**
	 * Create the default configuration for this game:
	 * - one human player vs. one computer player
	 * - minimum of 1 player, maximum of 2
	 * - one kind of computer player and one kind of human player available
	 * 
	 * @return
	 * 		the new configuration object, representing the default configuration
	 */
	@Override
	public GameConfig createDefaultConfig() {
		
		// Define the allowed player types
		ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();
		
		// a human player player type (player type 0)
		playerTypes.add(new GamePlayerType("Local Human Player") {
			public GamePlayer createPlayer(String name) {
				return new RiskHumanPlayer(name);
			}});
		
		// a computer player type (player type 1)
		playerTypes.add(new GamePlayerType("Smart AI Player") {
			public GamePlayer createPlayer(String name) {
				return new RiskComputerPlayer2(name);
			}});
		// a computer player type (player type 2)
		playerTypes.add(new GamePlayerType("AI Player") {
			public GamePlayer createPlayer(String name) {
				return new RiskComputerPlayer1(name);
			}});

		// Create a game configuration class for Risk:
		// - player types as given above
		// - from 1 to 2 players
		// - name of game is "Risk Game"
		// - port number as defined above
		GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Risk Game",
				PORT_NUMBER);

		// Add the default players to the configuration
		defaultConfig.addPlayer("Human", 0); // player 1: a human player
		defaultConfig.addPlayer("Smart AI Player", 1); // player 2: a smart computer player
		
		// Set the default remote-player setup:
		// - player name: "Remote Player"
		// - IP code: (empty string)
		// - default player type: human player
		defaultConfig.setRemoteData("Remote Player", "", 0);
		
		// return the configuration
		return defaultConfig;
	}//createDefaultConfig
}
