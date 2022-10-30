package game;

public class NPC extends Player implements Runnable{


    public NPC(int id, Game game) {
        super(id, game);
    }

    @Override
    public boolean isHumanPlayer() {
        return false;
    }

    @Override
    public void run() {

    }
}
