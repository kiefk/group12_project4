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
    public boolean scan(Coordinate coor) {
        if(getName()=="Battleship"){
            return false;
        }
        else if(getName()=="Computer_Battleship"){
            return false;
        }
        else if(getName()=="Submarine"){
            return false;
        }
        else if(getName()=="Computer_Submarine"){
            return false;
        }
        if(covers(coor)){
            return true;
        }
        if(covers(new Coordinate(coor.getAcross()-1,coor.getDown()))){
            return true;
        }
        if(covers(new Coordinate(coor.getAcross()+1,coor.getDown()))){
            return true;
        }
        if(covers(new Coordinate(coor.getAcross(),coor.getDown()-1))){
            return true;
        }
        if(covers(new Coordinate(coor.getAcross(),coor.getDown()+1))){
            return true;
        }
        return false;
    }
}

