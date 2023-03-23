package adventure_game.items;
import adventure_game.Character;
import adventure_game.Player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class HealingPotionTests {
    private Character c;
    @BeforeEach
    void setup(){
        // TO-DO 
        c = new Player("Hero", 100, 9, 7, 0, 0);
        // create a new player c

    }

    @Test
    void testHealingPotion(){
        // TO-DO
        assertTrue(c.getHealth() == 100);
        c.modifyHealth(-20);
        assertTrue(c.getHealth() == 80);
        new HealingPotion().consume(c);
        assertTrue(c.getHealth() >= 88);
        assertTrue(c.getHealth() <= 100);
        
        // Assume player c getting attack and lose 20 health after c consume healingpotion 
        // his health will be greater than 88 and lower than 100 casue the potion can recover
        // 8 to 20 health
    }
}
