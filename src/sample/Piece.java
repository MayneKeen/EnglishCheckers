package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import static sample.Main.titleSize;


public class Piece extends StackPane {

    private PieceType type;

    private double mouseX, mouseY;
    private double oldX, oldY;

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public PieceType getType() {
        return type;
    }

    public Piece(PieceType type,int x, int y) {
        this.type = type;

        move(x, y);

        Ellipse bg = new Ellipse(titleSize * 0.3125, titleSize * 0.26);

        bg.setFill(Color.BLACK);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(titleSize * 0.03);

        bg.setTranslateX((titleSize - titleSize * 0.3125 * 2) / 2);
        bg.setTranslateY((titleSize - titleSize* 0.26 * 2 ) / 2 + titleSize * 0.07);

        Ellipse ellipse = new Ellipse(titleSize * 0.3125, titleSize * 0.26);

        ellipse.setFill(type == PieceType.RED ?
                Color.valueOf("#c40003") : Color.valueOf("#fff9f4"));

        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(titleSize * 0.03);

        ellipse.setTranslateX((titleSize - titleSize * 0.3125 * 2) / 2);
        ellipse.setTranslateY((titleSize - titleSize * 0.26 * 2) / 2);

        getChildren().addAll(bg, ellipse);


        setOnMousePressed( e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX,
                    e.getSceneY() - mouseY + oldY);
        });
    }

    public void move( int x, int y) {
        oldX = x * titleSize;
        oldY = y * titleSize;
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }

}