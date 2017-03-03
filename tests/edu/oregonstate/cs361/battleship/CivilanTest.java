package edu.oregonstate.cs361.battleship;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by michaelhilton on 2/7/17.
 */
class CivilianTest {

    @Test
    public void testPlaceShip() {
        Civilian s = new Civilian("AircraftCarrier",5, new Coordinate(5,2),new Coordinate(5,7));
        assertEquals(true,s.covers(new Coordinate(5,2)));
    }

    @Test
    public void testPlaceShipHorizontal() {
        Civilian s = new Civilian("AircraftCarrier",5, new Coordinate(5,2),new Coordinate(10,2));
        assertEquals(true,s.covers(new Coordinate(6,2)));
    }

    @Test
    public void testPlaceShipHorizontalFalse() {
        Civilian s = new Civilian("AircraftCarrier",5, new Coordinate(5,2),new Coordinate(10,2));
        assertEquals(false,s.covers(new Coordinate(1,1)));
    }

    @Test
    public void testPlaceShipVerticalFalse() {
        Civilian s = new Civilian("AircraftCarrier",5,new Coordinate(5,2),new Coordinate(5,7));
        assertEquals(false,s.covers(new Coordinate(1,1)));
    }

    //BAD INPUT
    @Test
    public void testPlaceShipVerticalNoLocation() {
        Civilian s = new Civilian("AircraftCarrier",5,new Coordinate(0,0),new Coordinate(9,9));
        assertEquals(false,s.covers(new Coordinate(1,1)));
    }

    @Test
    public void testScan() {
        Civilian s = new Civilian("AircraftCarrier",5, new Coordinate(5,2),new Coordinate(5,7));
        assertEquals(false,s.scan(new Coordinate(1,1)));
        assertEquals(true,s.scan(new Coordinate(4,2)));
        assertEquals(true,s.scan(new Coordinate(5,2)));
        assertEquals(true,s.scan(new Coordinate(6,2)));
        assertEquals(true,s.scan(new Coordinate(5,1)));
        assertEquals(true,s.scan(new Coordinate(5,3)));
    }

    @Test
    public void testSunk(){
        Civilian s = new Civilian("AircraftCarrier",5, new Coordinate(5,2),new Coordinate(5,7));
        s.setSunk(true);
        assertEquals(true,s.getSunk());
        s.setSunk(false);
        ArrayList<Coordinate> hits;
        hits = new ArrayList<>();
        hits.add(new Coordinate(5,2));
        s.isSunk(hits);
        assertEquals(true,s.getSunk());
    }


}