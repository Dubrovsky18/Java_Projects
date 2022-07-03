package battleship;

import place.CheckPositionShips;
import place.PlaceBattleField;
import Fight.Fire;

public class Main {

    public static void main(String[] args) {
        PlaceBattleField.placeBattleField();
        CheckPositionShips.askCellsShip();
        Fire.game();
    }
}
