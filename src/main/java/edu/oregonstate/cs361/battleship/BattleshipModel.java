package edu.oregonstate.cs361.battleship;

import java.util.ArrayList;
import java.util.Random;

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
    ArrayList<Coordinate> playerHits;
    private ArrayList<Coordinate> playerMisses;
    ArrayList<Coordinate> computerHits;
    private ArrayList<Coordinate> computerMisses;
    ArrayList<Coordinate> playerSunk;
    ArrayList<Coordinate> computerSunk;


    boolean scanResult = false;



    public BattleshipModel() { //battleship constructor creates dynamic arrays for it's variables
        playerHits = new ArrayList<>();
        playerMisses= new ArrayList<>();
        computerHits = new ArrayList<>();
        computerMisses= new ArrayList<>();
        playerSunk= new ArrayList<>();
        computerSunk= new ArrayList<>();
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
        int max = 10;
        int min = 1;
        Random random = new Random();
        int randRow = random.nextInt(max - min + 1) + min;
        int randCol = random.nextInt(max - min + 1) + min;

        Coordinate coor = new Coordinate(randRow,randCol);
        playerShot(coor);
    }

    void playerShot(Coordinate coor) {
        if(playerMisses.contains(coor)){
            System.out.println("Dupe");
        }

        if(aircraftCarrier.covers(coor)){
            playerHits.add(coor);
            checkSunk(aircraftCarrier, playerHits, playerSunk);
        }else if (battleship.covers(coor)){
            playerHits.add(coor);
            checkSunk(battleship, playerHits, playerSunk);
        }else if (Dinghy.covers(coor)){
            playerHits.add(coor);
            checkSunk(Dinghy, playerHits, playerSunk);
        }else if (Clipper.covers(coor)){
            playerHits.add(coor);
            checkSunk(Clipper, playerHits,playerSunk);
        }else if (submarine.covers(coor)){
            playerHits.add(coor);
            checkSunk(submarine, playerHits, playerSunk);
        } else {
            playerMisses.add(coor);
        }
    }

    public void checkSunk(Ship s, ArrayList<Coordinate> hits, ArrayList<Coordinate> sunk){

       s.isSunk(hits);

        if(s.getSunk()==true){
            for(int i=0; i<hits.size(); i++){
                if(s.covers(hits.get(i))){
                    sunk.add(hits.get(i));
                }
            }
        }
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