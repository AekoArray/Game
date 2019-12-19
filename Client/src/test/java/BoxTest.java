package test.java;

import main.java.Model.GameObjects.Box;
import main.java.Model.GameObjects.Line;
import org.junit.Assert;
import org.junit.Test;

public class BoxTest {
    @Test
    public void shouldNotBeFilledBox(){
        Line line1 = new Line();
        Line line2 = new Line();
        Line line3 = new Line();
        Line line4 = new Line();
        Box box = new Box();
        box.setLinesRelationship(line1, line2, line3, line4);
        Assert.assertFalse(box.shouldBeFilled());
    }
}
