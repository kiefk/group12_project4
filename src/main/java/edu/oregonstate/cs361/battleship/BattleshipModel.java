package edu.oregonstate.cs361.battleship;

import java.util.Random;
import java.util.ArrayList;

/**
 * Created by michaelhilton on 1/4/17.
 */
public class BattleshipModel {
    /*
    These next ten lines create and construct the ships for the player and the computer.
     */
    private Military aircraftCarrier = new Military("AircraftCarrier",5, new Coordinate(0,0),new Coordinate(0,0));
    private Military battleship = new Military("Battleship",4, new Coordinate(0,0),new Coordinate(0,0));
    private Military submarine = new Military("Submarine",3, new Coordinate(0,0),new Coordinate(0,0));
    private Civilian Dinghy = new Civilian("Dinghy",1, new Coordinate(0,0),new Coordinate(0,0));
    private Civilian Clipper = new Civilian("Clipper",3, new Coordinate(0,0),new Coordinate(0,0));

    private Military computer_aircraftCarrier = new Military("Computer_AircraftCarrier",5, new Coordinate(0,0),new Coordinate(0,0));
    private Military computer_battleship = new Military("Computer_Battleship",4, new Coordinate(0,0),new Coordinate(0,0));
    private Military computer_submarine = new Military("Computer_Submarine",3, new Coordinate(0,0),new Coordinate(0,0));
    private Civilian computer_Dinghy = new Civilian("Computer_Dinghy",1, new Coordinate(0,0),new Coordinate(0,0));
    private Civilian computer_Clipper = new Civilian("Computer_Clipper",3, new Coordinate(0,0),new Coordinate(0,0));

    /*
    Other variables the battleship model uses.
     */
    static ArrayList<Coordinate> playerHits;
    static ArrayList<Coordinate> playerMisses;
    ArrayList<Coordinate> computerHits;
    ArrayList<Coordinate> computerMisses;
    ArrayList<Coordinate> playerSunk;
    ArrayList<Coordinate> computerSunk;


    boolean scanResult = false;
    boolean hardMode = false;
    public class Mode {
        /*
            This is a parent class for easy mode and hard mode. These empty functions will be overridden
            by a function with the same name in either the easy or hard mode class.
         */
        public void placeShips(Ship computer_aircraftCarrier, Ship computer_battleship, Ship computer_submarine,Ship computer_Dinghy,Ship computer_Clipper){


        }
        public void fire(){

        }

    }
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

    public class Easy extends Mode {
        private int Row; //row to fire at
        private int Col; //column to fire at

        public Easy(){ //easy mode constructor
            Row = 1; //The first place easy mode will fire at is (1,1)
            Col = 1; //The top left corner of the battleship grid.
        }
        /*
            Easy mode hard codes the placement of the computer's ships.
         */
        public void placeShips(Military computer_aircraftCarrier,  Military computer_battleship,  Military computer_submarine, Civilian computer_Dinghy,  Civilian computer_Clipper){

            computer_aircraftCarrier.setLocation(new Coordinate(1, 0),new Coordinate(1,4));
            computer_battleship.setLocation(new Coordinate(2,0), new Coordinate(2, 3));
            computer_Clipper.setLocation(new Coordinate(5,3),new Coordinate(5,3));
            computer_Dinghy.setLocation(new Coordinate(3,7), new Coordinate(5, 7));
            computer_submarine.setLocation(new Coordinate(7, 9), new Coordinate(9, 9));

        }

        public void fire(){


            Coordinate coor = new Coordinate(Row,Col); //set Coordinate to row and column held by the Easy class
            Row++; //increment row to fire at the next row down.
            if(Row > 10){ //if the end of the column has been reached
                Row = 1; //reset row to one to go to the top of the next column
                Col++; //Col should never exceed 10 because it should reach a win condition before the program
                //tries to fire outside of the grid.
            }

            playerShot(coor);
        }


    }

    private Mode gameMode;



    public BattleshipModel(boolean hardMode) { //battleship constructor creates dynamic arrays for it's variables
        playerHits = new ArrayList<>();
        playerMisses= new ArrayList<>();
        computerHits = new ArrayList<>();
        computerMisses= new ArrayList<>();
        playerSunk= new ArrayList<>();
        computerSunk= new ArrayList<>();

        if(hardMode == false){ //if we are on easy construct an easy class game mode
            gameMode = new Easy();
        }

        else{ //if we are on hard construct hard class game mode
            gameMode = new Hard();
        }

        gameMode.placeShips(computer_aircraftCarrier, computer_battleship, computer_submarine, computer_Dinghy, computer_Clipper);
        //place the computer's ships

    }

    /*
    Function: getShip
        If a ship name is called by the view this function returns the ship name
        with the variable naming system used in this code.
     */
    public Ship getShip(String shipName) {
        if (shipName.equalsIgnoreCase("aircraftcarrier")) {
            return aircraftCarrier;
        } if(shipName.equalsIgnoreCase("battleship")) {
            return battleship;
        } if(shipName.equalsIgnoreCase("dinghy")) {
            return Dinghy;
        } if(shipName.equalsIgnoreCase("clipper")) {
            return Clipper;
        }if(shipName.equalsIgnoreCase("submarine")) {
            return submarine;
        } else {
            return null;
        }
    }

    public BattleshipModel placeShip(String shipName, String row, String col, String orientation) {
        int rowint = Integer.parseInt(row);
        int colInt = Integer.parseInt(col);
        if(orientation.equals("horizontal")){
            if (shipName.equalsIgnoreCase("aircraftcarrier")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint,colInt+4));
            }
            if(shipName.equalsIgnoreCase("battleship")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint,colInt + 3));
            }
            if(shipName.equalsIgnoreCase("dinghy")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint,colInt));
            }
            if(shipName.equalsIgnoreCase("clipper")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint,colInt + 2));
            }
            if(shipName.equalsIgnoreCase("submarine")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint, colInt), new Coordinate(rowint, colInt + 2));
            }
        }else{
            //vertical
            if (shipName.equalsIgnoreCase("aircraftcarrier")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint+4,colInt));
            }
            if(shipName.equalsIgnoreCase("battleship")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint+3,colInt));
            }
            if(shipName.equalsIgnoreCase("dinghy")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint,colInt));
            }
            if(shipName.equalsIgnoreCase("clipper")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint,colInt),new Coordinate(rowint+2,colInt));
            }
            if(shipName.equalsIgnoreCase("submarine")) {
                this.getShip(shipName).setLocation(new Coordinate(rowint, colInt), new Coordinate(rowint+2, colInt));
            }
        }
        return this;
    }

    public void shootAtComputer(int row, int col) {
        Coordinate coor = new Coordinate(row,col);
        if(computer_aircraftCarrier.covers(coor)){
            computerHits.add(coor);
            checkSunk(computer_aircraftCarrier,computerHits, computerSunk);
        }else if (computer_battleship.covers(coor)){
            computerHits.add(coor);
            checkSunk(computer_battleship,computerHits, computerSunk);
        }else if (computer_Dinghy.covers(coor)){
            computerHits.add(coor);
            checkSunk(computer_Dinghy,computerHits, computerSunk);
        }else if (computer_Clipper.covers(coor)){
            computerHits.add(coor);
            checkSunk(computer_Clipper,computerHits,computerSunk);
        }else if (computer_submarine.covers(coor)){
            computerHits.add(coor);
            checkSunk(computer_submarine,computerHits, computerSunk);
        } else {
            computerMisses.add(coor);
        }
    }

    public void shootAtPlayer() {

        gameMode.fire(); //calls the firing method specific to easy mode or hard mode

    }

    int playerShot(Coordinate coor) {
        if(playerMisses.contains(coor)){
            System.out.println("Dupe");
        }

        if(aircraftCarrier.covers(coor)){
            playerHits.add(coor);
            if(checkSunk(aircraftCarrier, playerHits, playerSunk))
                return 2;
            else
                return 1;
        }
        else if (battleship.covers(coor)){
            playerHits.add(coor);
            if(checkSunk(battleship, playerHits, playerSunk))
                return 2;
            else
                return 1;
        }
        else if (Dinghy.covers(coor)){
            playerHits.add(coor);
            if(checkSunk(Dinghy, playerHits, playerSunk))
                return 2;
            else
                return 1;
        }
        else if (Clipper.covers(coor)){
            playerHits.add(coor);
            if(checkSunk(Clipper, playerHits,playerSunk))
                return 2;
            else
                return 1;
        }
        else if (submarine.covers(coor)){
            playerHits.add(coor);
            if(checkSunk(submarine, playerHits, playerSunk))
                return 2;
            else
                return 1;
        } else {
            playerMisses.add(coor);
            return 0;
        }
    }

    public boolean checkSunk(Ship s, ArrayList<Coordinate> hits, ArrayList<Coordinate> sunk){

        s.isSunk(hits);

        if(s.getSunk()==true){
            for(int i=0; i<hits.size(); i++){
                if(s.covers(hits.get(i))){
                    sunk.add(hits.get(i));
                }
            }
            return true;
        }
        else
            return false;
    }

    public void scan(int rowInt, int colInt) {
        Coordinate coor = new Coordinate(rowInt,colInt);
        scanResult = false;
        if(computer_aircraftCarrier.scan(coor)){
            scanResult = true;
        }
        else if (computer_battleship.scan(coor)){
            scanResult = true;
        }else if (computer_Dinghy.scan(coor)){
            scanResult = true;
        }else if (computer_Clipper.scan(coor)){
            scanResult = true;
        }else if (computer_submarine.scan(coor)){
            scanResult = true;
        } else {
            scanResult = false;
        }
    }

    public boolean getScanResult() {
        return scanResult;
    }
}