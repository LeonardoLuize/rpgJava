package rpg.items;

import rpg.system.Roll;
import rpg.character.Character;

public class Potion {

    private int size;
    private Roll glup;

    public static final Potion emptyPotion = new Potion(0, new Roll(0, 0, 0));

    public Potion(int size, Roll glup){
        this.size = size;
        this.glup = glup;
    }

    public Potion(){
        this(10, new Roll(1, 6));
    }

    public int drinkPotion(){

        int heal = glup.roll();

        if(heal > size){
            heal = size;
        }
        size -= heal;

        return heal;
    }


}
