package Game.model.gameModel;

import Game.model.elements.Element;
import Game.model.elements.Position;
import Game.model.elements.fighter.Fighter;
import Game.model.elements.obstacles.Obstacle;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;
import static java.lang.Math.toIntExact;

public class GameUtils {
    GameModel gameModel;

    public GameUtils(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public boolean elementCanBePlaced(Position position){
        //To make more efficient
        if(position.getX() < gameModel.getArenaStartPoint()
                || position.getX() > gameModel.getWidth() - 1
                || position.getY() < 0
                || position.getY() > gameModel.getHeight() - 1)
            return false;

        if(gameModel.getPlayer().getPosition().equals(position))
            return false;


        for(Element el : gameModel.getEnemyList()){
            if(el.getPosition().equals(position))
                return false;

        }

        for(Element el : gameModel.getAllyList()){
            if(el.getPosition().equals(position))
                return false;

        }

        for (Element el : gameModel.getObstacleList()){
            if(el.getPosition().equals(position))
                return false;
        }

        return true;
    }

    //Calculates the aim penalty from one position to another
    public int aimPenaltyCalculator(Position origin, Position target){
        Obstacle obstacle = checkObstacles(origin, target);

        if(obstacle != null)
            return 2 * toIntExact(round(Position.getDistance(origin, target))) + obstacle.getProtection();

        return 2 * toIntExact(round(Position.getDistance(origin, target)));
    }

    public int aimCalculator(Fighter origin, Position target){
        int aim = origin.getAim() - aimPenaltyCalculator(origin.getPosition(), target);
        return Math.max(aim, 0);

    }

    public int damagePenaltyCalculator(Position origin, Position target){
        Obstacle obstacle = checkObstacles(origin, target);

        if(obstacle != null)
            return obstacle.getDamageReduction();

        return 0;
    }

    public int damageCalculator(Fighter origin, Position target){
        int damage = origin.getDamage() - damagePenaltyCalculator(origin.getPosition(), target);
        return Math.max(damage, 0);

    }

    //Checks for any obstacle adjacent to the target position that may interfere with the Line of Sight (LoS) from the origin
    private Obstacle checkObstacles(Position origin, Position target){
        //In case there is more than one possible obstacle, choose the one with the biggest protection
        List<Obstacle> candidates = new ArrayList<>();
        Obstacle result = null;

        for(Obstacle ob : gameModel.getObstacleList()){
            Position ob_position = ob.getPosition();
            //Check for adjacent obstacles that are between two positions
            if(Position.getDistance(ob_position, target) < 2){
                if((ob_position.getX() >= origin.getX() && ob_position.getY() >= origin.getY() && ob_position.getX() <= target.getX() && ob_position.getY() <= target.getY()) ||
                        (ob_position.getX() <= origin.getX() && ob_position.getY() >= origin.getY() && ob_position.getX() >= target.getX() && ob_position.getY() <= target.getY()) ||
                        (ob_position.getX() <= origin.getX() && ob_position.getY() <= origin.getY() && ob_position.getX() >= target.getX() && ob_position.getY() >= target.getY()) ||
                        (ob_position.getX() >= origin.getX() && ob_position.getY() <= origin.getY() && ob_position.getX() <= target.getX() && ob_position.getY() >= target.getY())){

                    candidates.add(ob);
                }
            }
        }

        for(Obstacle ob : candidates){
            if(result == null){
                result = ob;
            }

            else if(ob.getProtection() > result.getProtection()){
                result = ob;
            }
        }

        return result;
    }

}
