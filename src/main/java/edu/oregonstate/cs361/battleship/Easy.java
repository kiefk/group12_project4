package edu.oregonstate.cs361.battleship;


/**
 * Created by Keana on 3/15/2017.
 */
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

        //playerShot(coor);
    }

}
