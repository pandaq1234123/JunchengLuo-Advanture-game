package adventure_game;

/*
 * Project-01: Adventure Game
 * Name: Juncheng Luo
 */

import java.util.Scanner;

import adventure_game.items.HealingPotion;
import adventure_game.items.ManaPotion;
import java.util.Random;

public class Game {
    static Scanner in = new Scanner(System.in);
    public static Random rand = new Random();
    private Player player;
    
    public static void main(String[] args){

        Game game = new Game();

        game.createPlayer();
        System.out.println(game.player.toString());

        NPC opponent = new NPC("Geoff", 200, 0, 10);
        System.out.println(opponent.toString());
        game.enterCombat(opponent);

        in.close();
    }

    public Game() {
        
    }

    public void createPlayer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your character's name ");
        String name = scanner.nextLine();

        int healthpoint = 0;
        int damagepoint = 0;
        int manapoint = 0;
        int stats = 20;
        
        System.out.println("You have 20 stat points to spend on your character.");
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
                    healthpoint++;
                    stats--;
                    System.out.printf( "Health increased to %d (+%d)\n", healthpoint*10, healthpoint);
                    break;
                case 2:
                    damagepoint++;
                    stats--;
                    System.out.printf( "Damage increased to %d (+%d)\n", damagepoint, damagepoint);
                    break;
                case 3:
                    manapoint++;
                    stats--;
                    System.out.printf( "Mana increased to %d (+%d)\n", manapoint*3, manapoint);
                    break;
            }
        }

        System.out.println("\n Character created! Here are your stats:");
        System.out.printf("Name: %s", name);
        System.out.printf("Health: %d", healthpoint*10);
        System.out.printf("Damage: %d", damagepoint);
        System.out.printf("Mana: %d",manapoint*3);
        Player player = new Player(name, healthpoint*10, manapoint*3,damagepoint);
        player.obtain(new HealingPotion());
        player.obtain(new ManaPotion());

       
    }

    public void enterCombat(NPC opponent){
        System.out.printf("%s and %s are in a brawl to the bitter end.\n", this.player.getName(), opponent.getName());
        while(true){
            this.player.takeTurn(opponent);
            if(!opponent.isAlive()){
                System.out.printf("%S is SLAIN!!\n",opponent.getName());
                break;
            }

            opponent.takeTurn(this.player);
            if(!this.player.isAlive()){
                System.out.printf("%S is SLAIN!!\n",this.player.getName());
                break;
            }
        }
    }
}
