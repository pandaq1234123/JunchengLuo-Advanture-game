package adventure_game;


import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;



import org.junit.jupiter.api.BeforeEach;

public class CharacterTests{

    private Character c;
    private Character b;
    @BeforeEach
    void setup(){
        c = new Player("Hero", 100, 9, 7);
        b = new Player("Peter", 50, 9, 12);
    }

    @Test
    void testModifyHealth(){
        assertTrue(c.getHealth() == 100);
        c.modifyHealth(-10);
        assertTrue(c.getHealth() == 90);
    }
    @Test
    void testAttack(){
        c.setAsInvincible(1); //set hero invincible 
        b.attack(c); // peter attack hero 
        assertFalse(c.isInvincible()); // hero should be no more invincible
        c.attack(b); // hero attack peter
        assertTrue(b.getHealth() >= 42);// without any buff peter will take 5 to 8 damage which is hero's damage
        assertTrue(b.getHealth() <= 45);
        c.setAsVulnerable(1);
        b.attack(c);
        assertTrue(c.getHealth() <= 87);//when c is vulnerable c will take 1.5 times more damage and the range should be around 
        assertTrue(c.getHealth() >= 79);// 13 to 21
        assertFalse(c.isVulnerable());
    }
    @Test
    void testDefend(){  //test defend
        c.defend(c);
        if(c.isInvincible() == true){  // if c defend successfully c will do more damage to b
            c.attack(b);
            assertTrue(b.getHealth() < 43);

        }else{
            assertTrue(c.isVulnerable()); // else c will be vulnerable 
        }
    }

    @Test
    void testCastSpell(){
        assertTrue(c.getHealth() == 100);
        b.castSpell(c); // cast spell to make opponent by half
        assertTrue(c.getHealth() == 50);
    }

    @Test
    void testChargeMana(){
        assertTrue(c.getMana() == 9);
        c.modifyMana(-2); // decrease mana by 2 
        assertTrue(c.getMana() == 7);
        c.chargeMana(); // c choose charge up mana
        assertTrue(c.getMana() == 8); // mana increase by 1
}
}