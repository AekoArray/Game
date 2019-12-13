package Test;

import Model.GameObjects.Line;
import Model.Shapes.LineShape;
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
