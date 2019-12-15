package test.java;

import main.java.Model.GameObjects.Player;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
    @Test
    public void scoreIs0(){
        Player player = new Player("RED", "Player1");
        int expected = player.getScore();
        Assert.assertEquals(expected, 0);
    }
}
