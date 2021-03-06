package main.java.Model.GameObjects;

import main.java.Model.Shapes.LineShape;

import java.util.ArrayList;

/**
 * Created by Paul van der Bles on 8-8-2017.
 */
public class Line implements BoardItem {
    private boolean fillStatus;

    public LineShape getLineShape() {
        return lineShape;
    }

    public void setLineShape(LineShape lineShape) {

        this.lineShape = lineShape;
    }

    private LineShape lineShape;
    private ArrayList<Box> attachedBoxes = new ArrayList<>();

    void addAttachedBox(Box box) {
        attachedBoxes.add(box);
    }

    public ArrayList<Box> getAttachedBoxes() {
        return attachedBoxes;
    }

    public boolean getFillStatus() {
        return fillStatus;
    }



    public void setFillStatus(boolean fill) {
        this.fillStatus = fill;
    }

    public int howManyAttachedBoxesAreFilledSinceThisTurn() {
        int sum = 0;
        for (Box box : attachedBoxes) {
            if (!box.isAlreadyFilled()) {
                if (box.shouldBeFilled()) {
                    sum += 1;
                }
            }
        }
        return sum;
    }
}
