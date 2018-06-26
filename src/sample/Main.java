package sample;


import javafx.scene.control.Alert;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main extends Application {

    static final int titleSize = 100;
    private static final int width = 8;
    private static final int height = 8;

    private Title[][] board = new Title[width][height];


    private boolean mustKill = false;
    private List<Piece> killerPieces = new ArrayList<>();

    private Group titleGroup = new Group();
    private Group pieceGroup = new Group();

    private PieceType turn = PieceType.WHITE;

    private void canKillUpdater() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board[i][j].hasPiece()) {
                    Piece piece = board[i][j].getPiece();

                    int x = toBoard(piece.getOldX());
                    int y = toBoard(piece.getOldY());

                    MoveResult first = (tryMove(piece, x - 2, y - 2));
                    MoveResult second = (tryMove(piece, x - 2, y + 2));
                    MoveResult third = (tryMove(piece, x + 2, y - 2));
                    MoveResult fourth = (tryMove(piece, x + 2, y + 2));

                    piece.canKill = (first.getType() == MoveType.KILL) ||
                            (second.getType() == MoveType.KILL) ||
                            (third.getType() == MoveType.KILL) ||
                            (fourth.getType() == MoveType.KILL);





/*
                    if(x == 0 || x == 1 || x == 6 || x == 7 || y == 0 || y == 1 || y == 6 || y == 7) {
                        switch (x) {
                            case 0: {
                                switch (y) {
                                    case 0: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) + 2, toBoard(piece.getOldY())
                                                + 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }
                                    case 1: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) + 2, toBoard(piece.getOldY())
                                                + 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }

                                    case 6: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) + 2, toBoard(piece.getOldY())
                                                - 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }
                                    case 7: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) + 2, toBoard(piece.getOldY())
                                                - 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }
                                }
                                break;
                            }
                            case 1: {
                                switch (y) {
                                    case 0: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) + 2, toBoard(piece.getOldY())
                                                + 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }
                                    case 1: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) + 2, toBoard(piece.getOldY())
                                                + 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }

                                    case 6: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) + 2, toBoard(piece.getOldY())
                                                - 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }
                                    case 7: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) + 2, toBoard(piece.getOldY())
                                                - 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }
                                }
                                break;
                            }

                            case 6: {
                                switch (y) {
                                    case 0: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) - 2, toBoard(piece.getOldY())
                                                + 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }
                                    case 1: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) - 2, toBoard(piece.getOldY())
                                                + 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }

                                    case 6: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) - 2, toBoard(piece.getOldY())
                                                - 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }
                                    case 7: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) - 2, toBoard(piece.getOldY())
                                                - 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }
                                }
                                break;
                            }

                            case 7: {
                                switch (y) {
                                    case 0: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) - 2, toBoard(piece.getOldY())
                                                + 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }
                                    case 1: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) - 2, toBoard(piece.getOldY())
                                                + 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }

                                    case 6: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) - 2, toBoard(piece.getOldY())
                                                - 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }
                                    case 7: {
                                        if (tryMove(piece, toBoard(piece.getOldX()) - 2, toBoard(piece.getOldY())
                                                - 2).getType() == MoveType.KILL)
                                            piece.canKill = true;
                                        else
                                            piece.canKill = false;
                                        break;
                                    }
                                }
                                break;
                            }
                        }

                    }
                    else {
                        if ((tryMove(piece, x - 2, y - 2).getType() == MoveType.KILL) ||
                                (tryMove(piece, x - 2, y + 2).getType() == MoveType.KILL) ||
                                (tryMove(piece, x + 2, y - 2).getType() == MoveType.KILL) ||
                                (tryMove(piece, x + 2, y + 2).getType() == MoveType.KILL))
                            piece.canKill = true;
                        else
                            piece.canKill = false;
                    }











                    /*if(x == 0 || y == 0 || x == 7 || y == 7) {
                        if (x == 0 && y == 0) {
                            if (tryMove(piece, toBoard(piece.getOldX()) + 2, toBoard(piece.getOldY())
                                    + 2).getType() == MoveType.KILL)
                                piece.canKill = true;
                            else
                                piece.canKill = false;
                        }
                        if (x == 7 && y == 7) {
                            if (tryMove(piece, toBoard(piece.getOldX()) - 2, toBoard(piece.getOldY())
                                    - 2).getType() == MoveType.KILL)
                                piece.canKill = true;
                            else
                                piece.canKill = false;
                        }
                        if (x == 7 && y == 0) {
                            if (tryMove(piece, toBoard(piece.getOldX()) - 2, toBoard(piece.getOldY())
                                    + 2).getType() == MoveType.KILL)
                                piece.canKill = true;
                            else
                                piece.canKill = false;
                        }

                        if (x == 0 && y == 7) {
                            if (tryMove(piece, toBoard(piece.getOldX()) + 2, toBoard(piece.getOldY())
                                    - 2).getType() == MoveType.KILL)
                                piece.canKill = true;
                            else
                                piece.canKill = false;
                        }
                    }
                    else {
                        if ((tryMove(piece, x - 2, y - 2).getType() == MoveType.KILL) ||
                                (tryMove(piece, x - 2, y + 2).getType() == MoveType.KILL) ||
                                (tryMove(piece, x + 2, y - 2).getType() == MoveType.KILL) ||
                                (tryMove(piece, x + 2, y + 2).getType() == MoveType.KILL))
                            piece.canKill = true;
                        else
                            piece.canKill = false;
                    }

*/
                }
            }
        }
    }


    private void checkKillStreak() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board[i][j].hasPiece()) {
                    Piece piece = board[i][j].getPiece();
                    if (piece != null) {
                        if (piece.canKill) {
                            mustKill = true;
                            killerPieces.add(piece);
                        }
                        else killerPieces.remove(piece);
                        if(killerPieces.isEmpty())
                            mustKill = false;
                    }
                }
            }
        }
    }


    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(width * titleSize, height * titleSize);
        root.getChildren().addAll(titleGroup, pieceGroup);

        for (int y = 0; y<height; y++) {
            for(int x=0; x<width; x++) {
                Title title = new Title((x + y) % 2 == 0, x, y);

                board[x][y] = title;

                titleGroup.getChildren().add(title);


                Piece piece = null;

                if(y <= 2 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.RED, x, y);
                }

                if(y >= 5 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.WHITE, x, y);
                }

                if(piece!=null) {
                    title.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }

        return root;
    }


    private boolean winCondition() {
        boolean hasWhitePieces = false;
        boolean hasRedPieces = false;
        for(int i=0; i<width; i++) {
            for(int j=0; j<height; j++) {
                if(board[i][j].hasPiece()) {
                    Piece piece = board[i][j].getPiece();
                    if(piece.getType() == PieceType.RED) {
                        hasRedPieces = true;
                    }
                    else {
                        hasWhitePieces = true;
                    }
                }
            }
        }
        return !hasRedPieces || !hasWhitePieces;
    }

    private String winnerColor() {
        boolean hasRedPieces = false;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board[i][j].hasPiece()) {
                    Piece piece = board[i][j].getPiece();
                    if (piece.getType() == PieceType.RED)
                        hasRedPieces = true;
                    break;
                }
            }
        }
        if (hasRedPieces)
            return "Red";
        else return "White";
    }


    private void winner() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Winner");
            alert.setHeaderText(null);
            alert.setContentText(winnerColor() + " player's victory!");
            alert.showAndWait();
            System.exit(1);

    }





    private boolean kingCondition(Piece piece) {
        if(piece.getType() == PieceType.RED && piece.getLayoutY()==7*titleSize) {
            return true;
        }
        return piece.getType() == PieceType.WHITE && piece.getLayoutY() == 0 * titleSize;
    }


    private void changeTurn() {
        turn = PieceType.other(turn);
        canKillUpdater();
        checkKillStreak();
        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Next turn");
        alert.setHeaderText(null);
        alert.setContentText("Now it's the time for " + turn + " player's turn" );
        alert.showAndWait();
        */
    }



    private MoveResult tryMove(Piece piece, int newX, int newY) {

        try {
            if ((board[newX][newY].hasPiece() || (newX + newY) % 2 == 0))
                return new MoveResult(MoveType.NONE);

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir ||
                    (Math.abs(newX - x0) == 1 && piece.isKing())) {
                if (piece.getType() == turn)
                    if(!mustKill && killerPieces.isEmpty())
                        return new MoveResult(MoveType.NORMAL);
                else return new MoveResult(MoveType.NONE);
            } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2 ||
                    (Math.abs(newX - x0) == 2 && piece.isKing())) {
                int x1 = x0 + (newX - x0) / 2;
                int y1 = y0 + (newY - y0) / 2;

                if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                    if (piece.getType() == turn)
                        return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
                }
            }

            return new MoveResult(MoveType.NONE);
        }
        catch (ArrayIndexOutOfBoundsException e){
            return new MoveResult(MoveType.NONE);
        }

        catch (Exception e) {
            System.out.println(e.toString() + Arrays.toString(e.getStackTrace()));
            return new MoveResult(MoveType.NONE);

        }

    }

    private int toBoard(double pixel) {
        return (int)(pixel+titleSize / 2)/titleSize;
    }

    @Override
    public void start(Stage primaryStage){
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);

        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            MoveResult result = tryMove(piece, newX, newY);

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());


            switch (result.getType()) {
                case NONE:
                    piece.abortMove();
                    piece.setPrevMove(MoveType.NONE);

                    canKillUpdater();
                    checkKillStreak();

                    //changeTurn();
                    //turn = PieceType.other(piece.getType());
                    break;

                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    canKillUpdater();
                    checkKillStreak();

                    if (kingCondition(piece)) {
                        piece.setKing();
                    }
                    piece.setPrevMove(MoveType.NORMAL);
                    changeTurn();
                    //turn = PieceType.other(piece.getType());
                    break;

                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    Piece otherPiece = result.getPiece();
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);

                    canKillUpdater();
                    checkKillStreak();

                    if(kingCondition(piece)) {
                        piece.setKing();
                    }

                    if(winCondition()) {
                        winner();
                    }
                    piece.setPrevMove(MoveType.KILL);
                    if(!piece.canKill)
                        changeTurn();

                    break;
            }
        });




        return piece;
    }





    public static void main(String[] args)
    {
        launch(args);
    }
}
