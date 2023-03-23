package adventure_game;
import java.util.Scanner;
public class Player extends Character{
    public Player(String name, int health, int mana, int baseDamage, int level, int exp){
        super(name, health, mana, baseDamage, level, exp);
    }

    @Override
    public void takeTurn(Character other){
        if(this.canlevelup()){
            this.levelUp();
            Scanner scanner = new Scanner(System.in);
            int stats = 1;
            System.out.println("You have 1 stat points to spend on your character.");
            while (stats > 0) {
                System.out.printf(" You have %d stat points remaining.\n", stats);
                System.out.println("which stat would you like to increase?");
                System.out.println("1. Health (+10 health points per point)");
                System.out.println("2. Damage (+1 base damage per point)");
                System.out.println("3. Mana (+3 mana points per point)");
                System.out.print("Enter the number of your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        this.modifyMaxHealth(10);
                        stats--;
        
                        break;
                    case 2:
                        this.modifyBaseDamage(1);
                        stats--;
                        
                        break;
                    case 3:
                        this.modifyMaxMana(3);
                        stats--;
            
                        break;
            }
        }
            
         
        }
        if(this.isStunned()){
            this.decreaseTurnsStunned();
            System.out.printf("%S is unable to take any actions this turn!", this.getName());
            return;
        }
        System.out.println();
        System.out.printf("%s has %d of %d health.\n", this.getName(), this.getHealth(), this.getMaxHealth());
        System.out.printf("%s has %d health.\n", other.getName(), other.getHealth());
        System.out.printf("Do you want to...\n");
        System.out.printf("  1: Attack?\n");
        System.out.printf("  2: Defend?\n");
        
        if(this.hasItems()){
            System.out.printf("  3: Use an item?\n"); 
        }
        if(this.getMana()>3){
            System.out.printf("  4: Cast a spell?\n");
        }
        System.out.printf("  5: Charge up mana?\n");          
        
       
        System.out.printf("Enter your choice: ");

        int choice = Game.in.nextInt();
        switch(choice){
            case 1:
                this.attack(other);
                break;
            case 2:
                this.defend(other);
                break;
            case 3:
                if(hasItems()){
                    this.useItem(this, other);
                } else {
                    System.out.println("You dig through your bag but find no items. You lose a turn!!");
                }
                break;
            case 4:
                this.castSpell(other);
                break;
            case 5:
                this.chargeMana();
                break;

                
        }
    }
}