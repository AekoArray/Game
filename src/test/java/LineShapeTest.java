package test.java;

import main.java.Model.GameObjects.Line;
import main.java.Model.Shapes.LineShape;
import org.junit.Assert;
import org.junit.Test;

public class LineShapeTest {
    @Test
    public void LineObject_NOT_NULL(){
        Line line = new Line();
        LineShape lineShape = new LineShape(line);
        Assert.assertNotNull(lineShape.getLineObject());
    }
}
