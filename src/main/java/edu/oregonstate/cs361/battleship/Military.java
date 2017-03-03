package edu.oregonstate.cs361.battleship;

/**
 * Created by Keana on 3/2/2017.
 */


public class Military extends Ship {

    public Military(String n, int l, Coordinate s, Coordinate e) {
        super();
        setName(n);
        setLength(l);
        setStart(s);
        setEnd(e);
        setSunk(false);
    }
}
