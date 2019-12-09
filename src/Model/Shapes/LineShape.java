package Model.Shapes;

import Model.GameObjects.Line;
import javafx.scene.shape.Rectangle;

public class LineShape extends Rectangle{
    private Line lineObject;

    public LineShape(Line lineObject) {
        this.lineObject = lineObject;
    }

    public Line getLineObject() {
        return lineObject;
    }
}
