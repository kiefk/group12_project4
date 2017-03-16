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
    public class Hard extends Mode {

        boolean hit;
        int count;//tracks directions checked around a hit
        Coordinate lastHit;
        Coordinate hitPrime;


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




        private void _fire(Coordinate shot){//helper function for fire
            int result;
            result = playerShot(shot);//shoots at the player
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
        public void fire() {
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
                } while (playerMisses.contains(shot) || (playerHits.contains(shot)));//don't fire on the same spot more than once
                _fire(shot);
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
                    if((playerMisses.contains(shot)) || (playerHits.contains(shot)))//inc count if shot previously taken
                        count = count + 1;
                } while ((playerMisses.contains(shot)) || (playerHits.contains(shot)));
                _fire(shot);//FIRE
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