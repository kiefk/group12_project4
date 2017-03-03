package edu.oregonstate.cs361.battleship;

/**
 * Created by michaelhilton on 1/5/17.
 */
public class Ship {
    private String name;
    private int length;
    private Coordinate start;
    private Coordinate end;
    private boolean sunk;

    public Ship() {

    }


    public void setName(String n){
        name = n;
    }

    public String getName(){
        return name;
    }

    public void setLength(int l){
        length = l;
    }

    public int getLength(){
        return length;
    }

    public void setStart(Coordinate s){
        start = s;
    }

    public Coordinate getStart(){
        return start;
    }

    public void setEnd(Coordinate e){
        end = e;
    }

    public Coordinate getEnd(){
        return end;
    }

    public void setSunk(boolean s){
        sunk = s;
    }

    public boolean getSunk(){
        return sunk;
    }


    public void setLocation(Coordinate s, Coordinate e) {
        start = s;
        end = e;

    }

    public boolean covers(Coordinate test) {
        //horizontal
        if(start.getAcross() == end.getAcross()){
            if(test.getAcross() == start.getAcross()){
                if((test.getDown() >= start.getDown()) &&
                (test.getDown() <= end.getDown()))
                return true;
            } else {
                return false;
            }
        }
        //vertical
        else{
            if(test.getDown() == start.getDown()){
                if((test.getAcross() >= start.getAcross()) &&
                        (test.getAcross() <= end.getAcross()))
                    return true;
            } else {
                return false;
            }

        }
        return false;
    }


    public boolean scan(Coordinate coor) {
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
