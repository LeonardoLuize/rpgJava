package rpg.items;

import rpg.system.Roll;

import java.util.Random;

public class Weapon {
    public static final Weapon SOQUINHO =
            new Weapon("soquinho", new Roll(0, 0, 1));

    private String name;
    private Roll damage;

    public Weapon(String name, Roll damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public Roll getDamage() {
        return damage;
    }

    public int roll() {
        return damage.roll();
    }

    @Override
    public String toString() {
        return name + " (" + damage + ")";
    }
}
