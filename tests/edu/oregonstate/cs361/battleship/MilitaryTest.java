package edu.oregonstate.cs361.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MilitaryTest {

    @Test
    public void testPlaceShip() {
        Military s = new Military("AircraftCarrier",5, new Coordinate(5,2),new Coordinate(5,7));
        assertEquals(true,s.covers(new Coordinate(5,2)));
    }

    @Test
    public void testPlaceShipHorizontal() {
        Military s = new Military("AircraftCarrier",5, new Coordinate(5,2),new Coordinate(10,2));
        assertEquals(true,s.covers(new Coordinate(6,2)));
    }

    @Test
    public void testPlaceShipHorizontalFalse() {
        Military s = new Military("AircraftCarrier",5, new Coordinate(5,2),new Coordinate(10,2));
        assertEquals(false,s.covers(new Coordinate(1,1)));
    }

    @Test
    public void testPlaceShipVerticalFalse() {
        Military s = new Military("AircraftCarrier",5,new Coordinate(5,2),new Coordinate(5,7));
        assertEquals(false,s.covers(new Coordinate(1,1)));
    }

    //BAD INPUT
    @Test
    public void testPlaceShipVerticalNoLocation() {
        Military s = new Military("AircraftCarrier",5,new Coordinate(0,0),new Coordinate(9,9));
        assertEquals(false,s.covers(new Coordinate(1,1)));
    }

    @Test
    public void testScan() {
        Military s = new Military("AircraftCarrier",5, new Coordinate(5,2),new Coordinate(5,7));
        assertEquals(false,s.scan(new Coordinate(1,1)));
        assertEquals(true,s.scan(new Coordinate(4,2)));
        assertEquals(true,s.scan(new Coordinate(5,2)));
        assertEquals(true,s.scan(new Coordinate(6,2)));
        assertEquals(true,s.scan(new Coordinate(5,1)));
        assertEquals(true,s.scan(new Coordinate(5,3)));
        s.setName("Battleship");
        assertEquals(false,s.scan(new Coordinate(5,2)));
        s.setName("Computer_Battleship");
        assertEquals(false,s.scan(new Coordinate(5,2)));
        s.setName("Submarine");
        assertEquals(false,s.scan(new Coordinate(5,2)));
        s.setName("Computer_Submarine");
        assertEquals(false,s.scan(new Coordinate(5,2)));
    }

    @Test
    public void testSunk(){
        Military s = new Military("AircraftCarrier",5, new Coordinate(5,2),new Coordinate(5,7));
        s.setSunk(true);
        assertEquals(true,s.getSunk());
    }


}