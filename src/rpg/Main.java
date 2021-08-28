package rpg;

import rpg.items.Potion;
import rpg.items.Weapon;
import rpg.system.Roll;
import rpg.character.Character;

public class Main {
    public static void main(String[] args) {

        Character hero = new Character(
                "Sir Gallahad",
                15,
                5,
                50,
                new Weapon("Broadsword", new Roll(1, 8)),
                new Potion()
        );

        var monster = Character.createGoblin();


        int turn = 1;

        //A BATALHA ATE A MORTE!!!!
        while (hero.isAlive() && monster.isAlive()) {

            System.out.printf("Turno: %d %n", turn);

            if(hero.getLife() < 6){
                hero.drinkPotion();
            }else{
                hero.attack(monster);
            }

            if (monster.isAlive()) {
                if(monster.getLife() < 6){
                    monster.drinkPotion();
                }else{
                    monster.attack(hero);
                }
            }

            turn++;

            System.out.println();
        }
    }
}
