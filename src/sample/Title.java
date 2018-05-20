package sample;

import java.awt.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Title extends Rectangle {

    private Piece piece;

    public Piece getPiece() {
        return piece;
    }

    public boolean hasPiece() {
        return piece!=null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Title(boolean light, int x, int y){
        setWidth(Main.titleSize);
        setHeight(Main.titleSize);

        relocate(x * Main.titleSize, y * Main.titleSize);

        setFill(light ? Color.valueOf("#feb") : Color.valueOf("#582"));

    }
}
