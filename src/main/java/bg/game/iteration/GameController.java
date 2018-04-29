package bg.game.iteration;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.tuple.Pair;

import bg.game.model.Board;

@RequestScoped
public class GameController {
	private char[][] nextBoard;

	@Inject
	private Board board;

	public void iterate(int numberIterations) {
		int size = board.getBoard().length;
		Pair<Integer, Integer> location;
		char nextState;
		for (int it = 0; it < numberIterations; it++) {
			nextBoard = new char[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					location = Pair.of(i, j);
					nextState = calculateNextState(location);
					nextBoard[i][j] = nextState;
				}
			}
			board.setBoard(nextBoard);
		}
	}

	public void newGame(int size, String content) {
		char[][] newBoard = new char[size][size];
		int index = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				newBoard[i][j] = content.charAt(index);
				index += 1;
			}
			index += 2;
			
		}
		board.setBoard(newBoard);
	}

	public String generateOutput() {
		String result = "";
		char[][] board = this.board.getBoard();
		int size = board.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				result = result + board[i][j] + "&nbsp;&nbsp;&nbsp;";
			}
			result = result + "<br>";
		}
		return result;
	}

	private char calculateNextState(Pair<Integer, Integer> location) {
		int neighbours = calculateAliveNeighbours(location);

		if (neighbours < 2 && board.isAlive(location)) {
			return Board.DEATH_VALUE;
		} else if (neighbours >= 2 && neighbours <= 3
				&& board.isDeath(location)) {
			return Board.ALIVE_VALUE;
		} else if (neighbours > 3 && board.isAlive(location)) {
			return Board.DEATH_VALUE;
		} else if (neighbours == 3 && board.isDeath(location)) {
			return Board.ALIVE_VALUE;
		}

		return board.getLifeState(location);
	}

	private int calculateAliveNeighbours(Pair<Integer, Integer> location) {
		int size = board.getBoard().length;
		Pair<Integer, Integer> neighbourLocation;
		int neighbours = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				if ((location.getLeft() + i >= 0
						&& location.getLeft() + i < size)
						&& (location.getRight() + j >= 0
								&& location.getRight() + j < size)) {

					neighbourLocation = Pair.of(location.getLeft() + i,
							location.getRight() + j);
					if (board.isAlive(neighbourLocation)) {
						neighbours++;
					}
				}
			}
		}
		return neighbours;
	}
}
