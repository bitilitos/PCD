package environment;

import Struture.BlockingQueue;
import game.Game;
import game.Player;

import java.util.List;
import java.util.Queue;

public class Cell {
	private Coordinate position;
	private Game game;
	private Player player=null;
	private BlockingQueue<Player> waitingLine = new BlockingQueue<>();

	
	public Cell(Coordinate position,Game g) {
		super();
		this.position = position;
		this.game=g;
	}

	public Coordinate getPosition() {
		return position;
	}

	public boolean isOcupied() {
		return player!=null;
	}


	public Player getPlayer() {
		return player;
	}

	// Should not be used like this in the initial state: cell might be occupied, must coordinate this operation
	public synchronized void setPlayer(Player player) {
		while (isOcupied()) {
			try{
				System.out.println(player + " waiting to be placed on cell " + this.toString() +
						" occupied by " + this.player);
				waitingLine.put(player);
				wait();
			}catch (InterruptedException e) {
				System.out.println(Thread.currentThread() + " was interrupted while waiting to be placed on " +
						this.toString());
			}

		}
		this.player = player;
		player.setPosition(this);
	}

	@Override
	public String toString() { return "Cell[" + position.x +"][" + position.y + "]";

	}

}
