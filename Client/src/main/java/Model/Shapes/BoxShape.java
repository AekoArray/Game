package main.java.Model.Shapes;

import main.java.Model.GameObjects.Box;
import javafx.scene.shape.Rectangle;

public class BoxShape extends Rectangle {
    private Box boxObject;

    public BoxShape(Box boxObject) {
        this.boxObject = boxObject;
        boxObject.setBoxShapeRelation(this);
    }
}
