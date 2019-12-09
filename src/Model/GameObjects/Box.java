package Model.GameObjects;

import Model.Shapes.BoxShape; /**
 * Created by Paul van der Bles on 8-8-2017.
 */
public class Box implements BoardItem {
    public boolean fillStatus;

    private Line northernLine;
    private Line easternLine;
    private Line southernLine;
    private Line westernLine;
    private BoxShape boxShape;

    public void setLinesRelationship(Line northernLine, Line easternLine, Line southernLine, Line westernLine) {
        setAssociatedLines(northernLine, easternLine, southernLine, westernLine);

        northernLine.addAttachedBox(this);
        easternLine.addAttachedBox(this);
        southernLine.addAttachedBox(this);
        westernLine.addAttachedBox(this);
    }

    private void setAssociatedLines(Line northernLine, Line easternLine, Line southernLine, Line westernLine) {
        this.northernLine = northernLine;
        this.easternLine = easternLine;
        this.southernLine = southernLine;
        this.westernLine = westernLine;
    }

    boolean isAlreadyFilled() {
        return fillStatus;
    }

    public boolean shouldBeFilled() {
        fillStatus = northernLine.getFillStatus() && easternLine.getFillStatus() && southernLine.getFillStatus() && westernLine.getFillStatus();
        return fillStatus;
    }

    public void setBoxShapeRelation(BoxShape boxShape) {
        this.boxShape = boxShape;
    }

    public BoxShape getBoxShape() {
        return boxShape;
    }
}
