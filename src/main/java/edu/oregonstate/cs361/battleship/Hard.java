package edu.oregonstate.cs361.battleship;

import java.util.Random;

/**
 * Created by Keana on 3/15/2017.
 */
public class Hard extends Mode{


        public Hard(){

        }
        public void placeShips(Military computer_aircraftCarrier,  Military computer_battleship,  Military computer_submarine, Civilian computer_Dinghy,  Civilian computer_Clipper){


                int max = 10;
                int min = 1;
                Random random = new Random();
                boolean validLocation = false;

                //Aircraft Carrier

                while(validLocation == false) {


                        int randDir = random.nextInt(2);

                        int randRow = random.nextInt(max - min + 1) + min;
                        int randCol = random.nextInt(max - min + 1) + min;

                        Coordinate coor = new Coordinate(randRow, randCol);

                        computer_aircraftCarrier.setStart(coor);
                        if (randDir == 0) {
                                int endRow = randRow + 4;
                                Coordinate endCoor = new Coordinate(endRow, randCol);
                                computer_aircraftCarrier.setEnd(endCoor);

                                if(endRow <= 10){
                                        validLocation = true;
                                }
                        }else{
                                int endCol = randCol + 4;
                                Coordinate endCoor = new Coordinate(randRow, endCol);
                                computer_aircraftCarrier.setEnd(endCoor);

                                if(endCol <= 10){
                                        validLocation = true;
                                }
                        }



                }


                //Battleship

                 validLocation = false;

                 while(validLocation == false) {

                         boolean crosses = false;
                         int randDir = random.nextInt(2);

                         int randRow = random.nextInt(max - min + 1) + min;
                         int randCol = random.nextInt(max - min + 1) + min;

                         Coordinate coor = new Coordinate(randRow, randCol);

                         computer_battleship.setStart(coor);
                         if (randDir == 0) {
                                 int endRow = randRow + 3;
                                 Coordinate endCoor = new Coordinate(endRow, randCol);
                                 computer_battleship.setEnd(endCoor);
                                 Coordinate curCoor = coor;

                                 for(int i = 0; i<4; i++) {
                                         if (computer_aircraftCarrier.covers(curCoor)) {
                                                 crosses = true;
                                                 break;
                                         }

                                                Coordinate nextCoor = new Coordinate(curCoor.getAcross()+1, curCoor.getDown());
                                                curCoor = nextCoor;

                                 }
                                 if(endRow <= 10 && !crosses){
                                         validLocation = true;
                                 }
                         }else{
                                 int endCol = randCol + 3;
                                 Coordinate endCoor = new Coordinate(randRow, endCol);
                                 computer_battleship.setEnd(endCoor);

                                 Coordinate curCoor = coor;

                                 for(int i = 0; i<4; i++) {
                                         if (computer_aircraftCarrier.covers(curCoor)) {
                                                 crosses = true;
                                                 break;
                                         }

                                                 Coordinate nextCoor = new Coordinate(curCoor.getAcross(), curCoor.getDown()+1);
                                                 curCoor = nextCoor;

                                 }

                                 if(endCol <= 10){
                                         validLocation = true;
                                 }
                         }



                 }

                 //Submarine

                validLocation = false;

                while(validLocation == false) {

                        boolean crosses = false;
                        int randDir = random.nextInt(2);

                        int randRow = random.nextInt(max - min + 1) + min;
                        int randCol = random.nextInt(max - min + 1) + min;

                        Coordinate coor = new Coordinate(randRow, randCol);

                        computer_submarine.setStart(coor);
                        if (randDir == 0) {
                                int endRow = randRow + 2;
                                Coordinate endCoor = new Coordinate(endRow, randCol);
                                computer_submarine.setEnd(endCoor);
                                Coordinate curCoor = coor;

                                for(int i = 0; i<3; i++) {
                                        if (computer_aircraftCarrier.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }
                                        else if (computer_battleship.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }

                                        Coordinate nextCoor = new Coordinate(curCoor.getAcross()+1, curCoor.getDown());
                                        curCoor = nextCoor;

                                }
                                if(endRow <= 10 && !crosses){
                                        validLocation = true;
                                }
                        }else{
                                int endCol = randCol + 2;
                                Coordinate endCoor = new Coordinate(randRow, endCol);
                                computer_submarine.setEnd(endCoor);

                                Coordinate curCoor = coor;

                                for(int i = 0; i<3; i++) {
                                        if (computer_aircraftCarrier.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }
                                        else if (computer_battleship.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }

                                        Coordinate nextCoor = new Coordinate(curCoor.getAcross(), curCoor.getDown()+1);
                                        curCoor = nextCoor;

                                }

                                if(endCol <= 10){
                                        validLocation = true;
                                }
                        }



                }

                //Clipper

                validLocation = false;

                while(validLocation == false) {

                        boolean crosses = false;
                        int randDir = random.nextInt(2);

                        int randRow = random.nextInt(max - min + 1) + min;
                        int randCol = random.nextInt(max - min + 1) + min;

                        Coordinate coor = new Coordinate(randRow, randCol);

                        computer_Clipper.setStart(coor);
                        if (randDir == 0) {
                                int endRow = randRow + 2;
                                Coordinate endCoor = new Coordinate(endRow, randCol);
                                computer_Clipper.setEnd(endCoor);
                                Coordinate curCoor = coor;

                                for(int i = 0; i<3; i++) {
                                        if (computer_aircraftCarrier.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }
                                        else if (computer_battleship.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }
                                        else if (computer_submarine.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }

                                        Coordinate nextCoor = new Coordinate(curCoor.getAcross()+1, curCoor.getDown());
                                        curCoor = nextCoor;

                                }
                                if(endRow <= 10 && !crosses){
                                        validLocation = true;
                                }
                        }else{
                                int endCol = randCol + 2;
                                Coordinate endCoor = new Coordinate(randRow, endCol);
                                computer_Clipper.setEnd(endCoor);

                                Coordinate curCoor = coor;

                                for(int i = 0; i<3; i++) {
                                        if (computer_aircraftCarrier.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }
                                        else if (computer_battleship.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }
                                        else if (computer_submarine.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }

                                        Coordinate nextCoor = new Coordinate(curCoor.getAcross(), curCoor.getDown()+1);
                                        curCoor = nextCoor;

                                }

                                if(endCol <= 10){
                                        validLocation = true;
                                }
                        }



                }



                //Dinghy

                validLocation = false;

                while(validLocation == false) {

                        boolean crosses = false;
                        int randDir = random.nextInt(2);

                        int randRow = random.nextInt(max - min + 1) + min;
                        int randCol = random.nextInt(max - min + 1) + min;

                        Coordinate coor = new Coordinate(randRow, randCol);

                        computer_Dinghy.setStart(coor);
                        if (randDir == 0) {
                                int endRow = randRow + 2;
                                Coordinate endCoor = new Coordinate(endRow, randCol);
                                computer_Dinghy.setEnd(endCoor);
                                Coordinate curCoor = coor;

                                for(int i = 0; i<1; i++) {
                                        if (computer_aircraftCarrier.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }
                                        else if (computer_battleship.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }
                                        else if (computer_submarine.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }
                                        else if (computer_Clipper.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }

                                        Coordinate nextCoor = new Coordinate(curCoor.getAcross()+1, curCoor.getDown());
                                        curCoor = nextCoor;

                                }
                                if(endRow <= 10 && !crosses){
                                        validLocation = true;
                                }
                        }else{
                                int endCol = randCol + 2;
                                Coordinate endCoor = new Coordinate(randRow, endCol);
                                computer_Dinghy.setEnd(endCoor);

                                Coordinate curCoor = coor;

                                for(int i = 0; i<1; i++) {
                                        if (computer_aircraftCarrier.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }
                                        else if (computer_battleship.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }
                                        else if (computer_submarine.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }
                                        else if (computer_Clipper.covers(curCoor)) {
                                                crosses = true;
                                                break;
                                        }

                                        Coordinate nextCoor = new Coordinate(curCoor.getAcross(), curCoor.getDown()+1);
                                        curCoor = nextCoor;

                                }

                                if(endCol <= 10){
                                        validLocation = true;
                                }
                        }



                }

        }

}
