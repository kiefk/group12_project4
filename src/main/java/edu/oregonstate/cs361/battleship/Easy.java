package edu.oregonstate.cs361.battleship;

/**
 * Created by Keana on 3/15/2017.
 */
public class Easy extends Mode {

    public Easy(){

    }
       //nothing to construct

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

    }


}
