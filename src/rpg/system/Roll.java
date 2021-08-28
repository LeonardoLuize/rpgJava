package rpg.system;

import java.util.Random;

public class Roll {
    private static final Random RANDOM = new Random();

    private int number;
    private int faces;
    private int bonus;

    public Roll(int number, int faces, int bonus) {
        this.number = number;
        this.faces = faces;
        this.bonus = bonus;
    }

    public Roll(int number, int faces) {
        this(number, faces, 0);
    }

    public int getNumber() {
        return number;
    }

    public int getFaces() {
        return faces;
    }

    public int getBonus() {
        return bonus;
    }

    public int roll() {
        int sum = 0;
        for (int i = 0; i < number; i++) {
            sum += 1 + RANDOM.nextInt(faces);
        }
        sum += bonus;
        return sum < 0 ? 0 : sum;
    }

    @Override
    public String toString() {
        String texto = number + "D" + faces;
        if (bonus > 0) {
            texto += "+" + bonus;
        } else if (bonus < 0){
            texto += bonus;
        }
        return texto;
    }
}
