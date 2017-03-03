package edu.oregonstate.cs361.battleship;

import java.util.ArrayList;

/**
 * Created by Keana on 3/2/2017.
 */



public class Civilian extends Ship {

    public Civilian(String n, int l, Coordinate s, Coordinate e) {
        super();
                setName(n);
                setLength(l);
                setStart(s);
                setEnd(e);
                setSunk(false);
       }

             public void isSunk(ArrayList<Coordinate> hits) {        for(int i=0; i<hits.size(); i++){
                        if(covers(hits.get(i))){
                                setSunk(true);
                                return;
                            }
                    }


           }
}
