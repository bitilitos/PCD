package game;

import environment.Cell;
import environment.Coordinate;
import environment.Direction;

public class NPC extends Player{


    public NPC(int id, Game game) {
        super(id, game);
    }

    @Override
    public void run(){
        game.addPlayerToGame(this);
        // so para testar se são colocados depois
        while(true){
            movePlayer();
            try {
                sleep(Game.REFRESH_INTERVAL);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean isHumanPlayer() {
        return false;
    }

    @Override
    public void movePlayer(){
        //get a random direction
        Coordinate dir = Direction.values()[(int)(Math.random() * Direction.values().length)].getVector();
        Cell currentCell = getCurrentCell();
        Coordinate currentCellCoords = currentCell.getPosition();
        Coordinate finalCoordinate = dir.positionSum(currentCellCoords);
        while(finalCoordinate.x >= Game.DIMX  || finalCoordinate.y >= Game.DIMY  || finalCoordinate.x < 0 || finalCoordinate.y < 0){
            dir = Direction.values()[(int)(Math.random() * Direction.values().length)].getVector();
            finalCoordinate = dir.positionSum(currentCellCoords);
        }
        Cell cellToMove = game.getCell(finalCoordinate);
        try{
            currentCell.resetCell();
            cellToMove.setPlayer(this);
            System.out.println(this.toString() + " Current position : " + currentCell.toString() + " moving to : " +
                    cellToMove.toString());
            game.notifyChange();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
