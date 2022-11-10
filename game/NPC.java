package game;

public class NPC extends Player{


    public NPC(int id, Game game) {
        super(id, game);
    }

    @Override
    public boolean isHumanPlayer() {
        return false;
    }

}
