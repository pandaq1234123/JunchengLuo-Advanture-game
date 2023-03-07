package adventure_game;
import java.util.ArrayList;

import adventure_game.items.Consumable;

abstract public class Character{
    private int maxHealth;
    private int health;

    private int maxMana;
    private int mana;

    private int baseDamage;

    private String name;

    private ArrayList<Consumable> items;

    // Character Conditions:
    private int turnsVulnerable; // number of turns Character is vulnerable
    private int turnsInvincible; // number of turns Character takes no damage
    private int turnsStunned; // number of turns Character gets no actions
    // buffer factor for next attack
    // E.g, if 2.0, the next attack will do double damage
    private double tempDamageBuff;

    public Character(String name, int health, int mana, int damage){
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.maxMana = mana;
        this.mana = mana;
        this.baseDamage = damage;
        this.tempDamageBuff = 1.0;
        items = new ArrayList<Consumable>();
    }

    @Override
    public String toString(){
        String output;
        output = "";
        output += "Name " + getName() + "\n";
        output += "hp " + getHealth() + "\n";
        output += "mana " + getMana() + "\n";
        output += "damage " + getBaseDamage() + "\n";
        return output;
    }

    /**
     * Get the name of this Character
     * @return the name of this Character
     */
    public String getName(){
        return this.name;
    }
    /**
     * get the number of the health
     * 
     * @return the health of this Character
     */
    public int getHealth(){
        return this.health;
    }
    /**
     * get the number of the maxhealth
     * 
     * @return the maxhealth of this Character
     */
    public int getMaxHealth(){
        return this.maxHealth;
    }
    /**
     * get the number of the maxmana
     * 
     * @return the maxmana of this Character
     */
    public int getMaxMana(){
        return this.maxMana;
    }
    /**
     * get the number of the mana
     * 
     * @return the mana of this Character
     */
    public int getMana(){
        return this.mana;
    }
    /**
     * get the number of the basedamage
     * 
     * @return the basedamage of this Character
     */
    public int getBaseDamage(){
        return this.baseDamage;
    }
    /**
     * character is alive is true
     * 
     * @return health of the character greater than 0
     */
    public boolean isAlive(){
        return this.health > 0;
    }

    abstract void takeTurn(Character other);
    /**
     * when the character choose to attack another character he can do 0.8-1.2 times his base damage to the chosen character
     * 
     * if the chosen character is currently invincible the attack will decrease the turn of the invincible
     * 
     * if the character defend successfully or using other duff methods to increase his damage current damage should times the buff amount
     * 
     * and if the chosen character is currently vulnerable the character can also do more damage to the chosen one 
     * 
     */
    public void attack(Character other){
        if(other.isInvincible()){
            System.out.printf("%S is unable to attack %S!\n", 
                                this.getName(), 
                                other.getName());
            other.decreaseTurnsInvincible();
            return;
        }
        double modifier = Game.rand.nextDouble();
        modifier = (modifier*0.4) + 0.8;
        int damage = (int)(this.baseDamage * modifier);
        // apply temporary damage buff, then reset it back to 1.0
        damage *= this.tempDamageBuff;
        this.tempDamageBuff = 1.0;

        if(other.isVulnerable()){
            damage *= 1.5;
            other.decreaseTurnsVulnerable();
        }

        System.out.printf("%s dealt %d damage to %s\n", 
                            this.getName(), 
                            damage, 
                            other.getName());
        other.modifyHealth(-damage);
    }
    /**
     * when the character choose to defend himself he has 75% chance to become 
     * invulnerable for 1 turn and charge his next attack and do more damage
     * 
     * if the defend is not working (25%) the charater will take more damage for the next turn
     * 
     * 
     */
    public void defend(Character other){
        double chance = Game.rand.nextDouble();
        if(chance <=0.75){
            System.out.printf("%s enters a defensive posture and charges up their next attack!\n", this.getName());
            this.setAsInvincible(1);
            this.setTempDamageBuff(2.0);
        } else {
            System.out.printf("%s stumbles. They are vulnerable for the next turn!\n", this.getName());
            this.setAsVulnerable(1);
        }
    }
    /**
     * this function modify the character's health and 
     * the current health of the character cannot be lower than 0 or greater than maxhealth
     * 
     * @param modifier the amount of health is changed
     */
    public void modifyHealth(int modifier) {
        this.health += modifier;
        if(this.health < 0){
            this.health = 0;
        }
        if(this.health > this.getMaxHealth()){
            this.health = this.getMaxHealth();
        }
    }

    /* CONDITIONS */
    public void setAsVulnerable(int numTurns){
        this.turnsVulnerable = numTurns;
    }
    /**
     * when isVulnerable is true the turnstuned should be greater than 1
     * 
     * 
     */
    public boolean isVulnerable(){
        return this.turnsVulnerable > 0;
    }
    /**
     * decrease the vulnerable turn.
     * 
     * This factor will decrease the vulnerable turn of the character by 1 
     */
    public void decreaseTurnsVulnerable(){
        this.turnsVulnerable--;
    }
     /**
     * set the Invincible turn 
     * 
     * this factor will set the turn which the charater will not take any damage
     * @param numTurns numbers of turn the player cannot being attacked
     */
    public void setAsInvincible(int numTurns){
        this.turnsInvincible = numTurns;
    }
     /**
     * when isInvincible is true the turnstuned should be greater than 1
     * 
     * 
     */
    public boolean isInvincible(){
        return this.turnsInvincible > 0;
    }
     /**
     * decrease the invincible turn.
     * 
     * This factor will decrease the invincible turn of the character by 1 
     */
    public void decreaseTurnsInvincible(){
        this.turnsInvincible--;
    }
    /**
     * set the stunned turn 
     * 
     * this factor will set the turn which the charater cannot make action
     * 
     * @param numTurns numbers of turn the player cannot make action
     */
    public void setAsStunned(int numTurns){
        this.turnsStunned = numTurns;
    }
     /**
     * when isstunned is true the turnstuned should be greater than 1
     * 
     * 
     */
    public boolean isStunned(){
        return this.turnsStunned > 0;
    }
    /**
     * decrease the stunned turn. 
     * 
     * This factor will decrease the stunned turn of the character by 1 
     * 
     */
    public void decreaseTurnsStunned(){
        this.turnsStunned--;
    }

    /**
     * Set the temporary damage buff. 
     * 
     * This is a multiplicative factor which will modify the damage 
     * for the next attack made by this Character. After the next 
     * attack, it will get reset back to 1.0
     * 
     * @param buff the multiplicative factor for the next attack's
     * damage.
     */
    public void setTempDamageBuff(double buff){
        this.tempDamageBuff = buff;
    }
    
    public void obtain(Consumable item){
        items.add(item);
    }

    public void useItem(Character owner, Character other){
        int i = 1;
        System.out.printf("  Do you want to use:\n");
        for(Consumable item : items){
            System.out.printf("    %d: %S\n", i, item.getClass().getName());
            i++;
        }
        System.out.print("  Enter your choice: ");
        int choice = Game.in.nextInt();
        items.get(choice-1).consume(owner);
        items.remove(choice-1);
    }

    public boolean hasItems(){
        return !items.isEmpty();
    }
}
