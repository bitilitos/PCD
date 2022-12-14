package game;



import environment.Cell;

/**
 * Represents a player.
 * @author luismota
 *
 */
public abstract class Player extends Thread  {


	protected  Game game;

	private int id;

	private byte currentStrength;
	protected byte originalStrength;
	private boolean isWaitingPlacement;
	private Cell position;

	// TODO: get player position from data in game
	public Cell getCurrentCell() {
		return position;
	}

	public Player(int id, Game game) {
		super();
		this.id = id;
		this.game=game;
		currentStrength=initialStrength();
		originalStrength=currentStrength;
		isWaitingPlacement = false;
	}

	// devolve a Strength com probabilidade discreta uniforme (0.33)
	private Byte initialStrength() {
		Double prob = Math.random();
		if (prob < 0.33) return 1;
		if (prob < 0.66) return 2;
		else return 3;
	}

	@Override
	public void run(){
		game.addPlayerToGame(this);
		// so para testar se são colocados depois

		while(!isInterrupted()){

		}
	}

	public abstract boolean isHumanPlayer();
	
	@Override
	public String toString() {
		return "Player [id=" + id + ", currentStrength=" + currentStrength + ", getCurrentCell()=" + getCurrentCell()
		+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public byte getCurrentStrength() {
		return currentStrength;
	}


	public int getIdentification() {
		return id;
	}

	public void setPosition(Cell cell) { position = cell;};
}
