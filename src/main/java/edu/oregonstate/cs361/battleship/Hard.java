package edu.oregonstate.cs361.battleship;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Keana on 3/15/2017.
 */

public class Hard extends Mode{


        public Hard(){

        }
        public void placeShips(Military computer_aircraftCarrier,  Military computer_battleship,  Military computer_submarine, Civilian computer_Dinghy,  Civilian computer_Clipper) {

            Ship[] ships;
            ships = new Ship[5];
            ships[0] = computer_aircraftCarrier;
            ships[1] = computer_battleship;
            ships[2] = computer_submarine;
            ships[3] = computer_Clipper;
            ships[4] = computer_Dinghy;

            for(int i=0; i<5; i++){
                validatShip(ships[i], ships, i);
            }

            computer_aircraftCarrier.setStart(ships[0].getStart());
            computer_aircraftCarrier.setEnd(ships[0].getEnd());
            computer_battleship.setStart(ships[1].getStart());
            computer_battleship.setEnd(ships[1].getEnd());
            computer_submarine.setStart(ships[2].getStart());
            computer_submarine.setEnd(ships[2].getEnd());
            computer_Clipper.setStart(ships[3].getStart());
            computer_Clipper.setEnd(ships[3].getEnd());
            computer_Dinghy.setStart(ships[4].getStart());
            computer_Dinghy.setEnd(ships[4].getEnd());
        }


        public void validatShip(Ship s, Ship[] ships, int num) {
            int max = 10;
            int min = 1;
            Random random = new Random();
            boolean validLocation = false;

            while (validLocation == false) {

                boolean crosses = false;
                int randDir = random.nextInt(2);

                int randRow = random.nextInt(max - min + 1) + min;
                int randCol = random.nextInt(max - min + 1) + min;

                Coordinate coor = new Coordinate(randRow, randCol);

                s.setStart(coor);
                if (randDir == 0) {
                    int endRow = randRow + s.getLength();
                    Coordinate endCoor = new Coordinate(endRow, randCol);
                    s.setEnd(endCoor);
                    Coordinate curCoor = coor;

                    for (int i = 0; i < 1; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (j != num && ships[j].covers(curCoor)) {
                                crosses = true;
                                break;
                            }
                        }


                        Coordinate nextCoor = new Coordinate(curCoor.getAcross() + 1, curCoor.getDown());
                        curCoor = nextCoor;

                    }
                    if (endRow <= 10 && !crosses) {
                        validLocation = true;
                    }
                } else{
                    int endCol = randCol + s.getLength();
                    Coordinate endCoor = new Coordinate(randRow, endCol);
                    s.setEnd(endCoor);

                   Coordinate curCoor = coor;

                   for (int i = 0; i < s.getLength(); i++) {

                        for (int j = 0; j < 5; j++) {
                         if (j != num && ships[j].covers(curCoor)) {
                                crosses = true;
                                break;
                            }
                        }

                        Coordinate nextCoor = new Coordinate(curCoor.getAcross() + 1, curCoor.getDown());
                        curCoor = nextCoor;

                    }

                    Coordinate nextCoor = new Coordinate(curCoor.getAcross(), curCoor.getDown() + 1);
                    curCoor = nextCoor;

                    if (endCol <= 10 && !crosses) {
                        validLocation = true;
                    }

                }



            }



        }
}


