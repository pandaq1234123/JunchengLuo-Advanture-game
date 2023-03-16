package adventure_game.items;
import adventure_game.Character;
import adventure_game.Player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ManaPotiontest {
    private Character c;
    @BeforeEach
    void setup(){
        // TO-DO 
        c = new Player("Hero", 100, 9, 7);
        // create a new player c

    }

    @Test
    void testManaPotion(){
        // TO-DO
        assertTrue(c.getMana() == 9); //check for original mana
        c.modifyMana(-3); // decrease mana by 3
        assertTrue(c.getMana() == 6);
        new ManaPotion().consume(c); // consume manapotion
        assertTrue(c.getMana() == 9);//mana recover back to 9
        
    }
}