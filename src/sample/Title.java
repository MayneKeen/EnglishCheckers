package sample;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

class Title extends Rectangle {

    private Piece piece;

    Piece getPiece() {
        return piece;
    }

    boolean hasPiece() {
        return piece!=null;
    }

    void setPiece(Piece piece) {
        this.piece = piece;
    }

    Title(boolean light, int x, int y){
        setWidth(Main.titleSize);
        setHeight(Main.titleSize);

        relocate(x * Main.titleSize, y * Main.titleSize);

        setFill(light ? Color.valueOf("#feb") : Color.valueOf("#582"));

    }
}
