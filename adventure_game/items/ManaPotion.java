package adventure_game.items;

import adventure_game.Character;


public class ManaPotion implements Consumable {
    public void consume(Character owner){
        int reMana = 3;
        int PointsFromMax = owner.getMaxMana() - owner.getMana();

        if(reMana > PointsFromMax){
            reMana = PointsFromMax;
        }
        System.out.printf("Your Mana recover for %d points, back up to %d/%d.\n", reMana, owner.getMana(), owner.getMaxMana());
        owner.modifyMana(reMana);
    }
}
