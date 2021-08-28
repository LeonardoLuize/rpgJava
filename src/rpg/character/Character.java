package rpg.character;

import rpg.items.Potion;
import rpg.items.Weapon;
import rpg.system.Roll;

import java.util.Random;

public class Character{


    //Constante
    private static final Random RANDOM = new Random();

    //Atributos
    private String name;
    private int skill;
    private int defense;
    private int life;
    private Weapon weapon; //TEM UMA ARMA
    private Potion lifePotion;

    //Construtores
    public Character(String name, int skill, int defense, int life, Weapon weapon, Potion lifePotion) {
        this.setName(name);
        this.skill = skill;
        this.defense = defense;
        this.life = life < 0 ? 0 : life;
        this.setWeapon(weapon);
        this.setLifePotion(lifePotion);
    }

    //Na associação simples, a arma é opcional
    public Character(String name, int skill, int defense, int life) {
        this(name, skill, defense, life, null, null);
    }

    //Métodos fábrica (exemplo de static)
    public static Character createGoblin() {
        String names[] = {"Tobias", "Jeremias", "Timotio", "Jesivaldo"};
        String surnames[] = {"Carrasco", "o grande", "abençoado", "raivoso"};

        String name = names[RANDOM.nextInt(names.length)];
        String surname = surnames[RANDOM.nextInt(surnames.length)];

        int skill = 5 + RANDOM.nextInt(11);
        int defense = RANDOM.nextInt(5) + 1;
        int life = (RANDOM.nextInt(10) + RANDOM.nextInt(10) + 1) * 3;
        Roll daggerDamage = new Roll(1, 4, -1);
        Weapon dagger = new Weapon("adaga enferrujada", daggerDamage);

        return new Character(name + " " + surname, skill, defense, life, dagger, new Potion());
    }

    public static Character[] createGoblins(int number) {
        Character[] goblins = new Character[number];
        for (int i = 0; i < number; i++) {
            goblins[i] = createGoblin();
        }
        return goblins;
    }

    //Métodos de acesso
    //Propriedades
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    //Propriedade calculada
    public boolean isAlive() {
        return life > 0;
    }

    //Métodos (ações)
    public void takeDamage(int damage) {
        life = life - damage;
        if (life < 0) {
            life = 0;
        }
        System.out.printf("%s levou %d de dano. Life: %d%n", name, damage, life);
    }

    public void drinkPotion(){
        int heal = lifePotion.drinkPotion();
        life += heal;

        System.out.printf("%s estava muito fraco e curou %d com a poção de vida%n", name, heal);
    }

    /**
     * Ataca o inimigo. O ataque terá sucesso se skill - enemy.defense <= 3D6
     * @param enemy inimigo a ser atacado
     */
    public void attack(Character enemy) {
        System.out.printf("%s atacou %s com %s!%n", name, enemy.name, weapon);
        int target = skill - enemy.defense;
        int roll = new Roll(3, 6).roll();
        if (roll <= target) {
            enemy.takeDamage(weapon.roll());
        } else {
            System.out.println("ERROU!!");
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon == null ? Weapon.SOQUINHO : weapon;
    }

    public Potion getLifePotion(){ return lifePotion;}
    public void setLifePotion(Potion lifePotion){this.lifePotion = lifePotion == null ? Potion.emptyPotion : lifePotion;}
}
