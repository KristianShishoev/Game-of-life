package bg.game.rest;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import bg.game.iteration.GameController;

@Path("/")
public class OperationsResource {

	@Inject
	private GameController gameController;

	@POST
	@Path("/generateBoard")
	public String newGame(@FormParam("boardSize") int boardSize,
			@FormParam("numberIterations") int numberIterations,
			@FormParam("content") String content) {
		
		gameController.newGame(boardSize, content);
		gameController.iterate(numberIterations);
		return gameController.generateOutput();
	}
}
