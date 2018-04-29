package bg.game.model;

import javax.enterprise.context.ApplicationScoped;
import org.apache.commons.lang3.tuple.Pair;

@ApplicationScoped
public class Board {
	public static final char DEATH_VALUE = 'x';
	public static final char ALIVE_VALUE = 'o';
	private char board[][];

	public char[][] getBoard() {
		return board;
	}
	
	public void setBoard(char[][] board) {
		this.board = board;
	}
	
	public char getLifeState(Pair<Integer, Integer> location) {
		return board[location.getLeft()][location.getRight()];
	}
	
	public boolean isAlive(Pair<Integer, Integer> location) {
		if(ALIVE_VALUE == board[location.getLeft()][location.getRight()]) {
			return true;
		}
		return false;
	}
	
	public boolean isDeath(Pair<Integer, Integer> location) {
		if(DEATH_VALUE == board[location.getLeft()][location.getRight()]) {
			return true;
		}
		return false;
	}
}
