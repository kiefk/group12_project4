package edu.oregonstate.cs361.battleship;

import java.util.Random;

/**
 * Created by Keana on 3/15/2017.
 */
public class Hard extends Mode {
    boolean hit;
    int count;//tracks directions checked around a hit
    Coordinate lastHit;
    Coordinate hitPrime;


    private void _fire(BattleshipModel bs, Coordinate shot){//helper function for fire
        int result;
        result = bs.playerShot(shot);//shoots at the player
        if (result == 1) {//ship was hit but not sunk
            if(!hit) {//ship not found already
                hitPrime.setAcross(shot.getAcross());//record location of ship
                hitPrime.setDown(shot.getDown());
            }
            hit = true;//note that a ship has been found
            count = 0;//initialize counter
            lastHit.setAcross(shot.getAcross());//record location of hit
            lastHit.setDown(shot.getDown());
        } else if (result == 2) {//ship was sunk
            hit = false;//note that we have no information about a ship's location
            count = 0;//reinitialize count
        } else {//miss
            if (hit)
                count = count + 1;//note failed direction if ship found
            else
                count = 0;//do nothing
        }
    }
    public void fire(BattleshipModel bs) {
        int max = 10;//this section mostly copied from model's fire function
        int min = 1;
        Coordinate shot = new Coordinate(1, 1);
        if (!hit) {//ship not found
            do {
                Random random = new Random();
                int randRow = random.nextInt(max - min + 1) + min;
                int randCol = random.nextInt(max - min + 1) + min;
                shot.setAcross(randRow);
                shot.setDown(randCol);
            } while ((bs.playerMisses.contains(shot)) || (bs.playerHits.contains(shot)));//don't fire on the same spot more than once
            _fire(bs,shot);
        }
        else {//ship found
            int row = 0,col = 0;
            do {
                switch (count) {//choose direction to move by count
                    case 0:
                        col = lastHit.getDown() + 1;
                        row = lastHit.getAcross();
                        break;
                    case 1:
                        col = lastHit.getDown();
                        row = lastHit.getAcross() + 1;
                        break;
                    case 2:
                        col = lastHit.getDown() - 1;
                        row = lastHit.getAcross();
                        break;
                    case 3:
                        col = lastHit.getDown();
                        row = lastHit.getAcross() - 1;
                        break;
                    case 4://all four directions missed or already hit
                        lastHit.setAcross(hitPrime.getAcross());//revert to original location
                        lastHit.setDown(hitPrime.getDown());
                        col = lastHit.getDown();//set shot to previous hit
                        row = lastHit.getAcross();
                        count = -1;//count will be incremented to zero by if
                        break;
                }
                shot.setDown(col);
                shot.setAcross(row);
                if((bs.playerMisses.contains(shot)) || (bs.playerHits.contains(shot)))//inc count if shot previously taken
                    count = count + 1;
            } while ((bs.playerMisses.contains(shot)) || (bs.playerHits.contains(shot)));
            _fire(bs,shot);//FIRE
        }
    }

}
