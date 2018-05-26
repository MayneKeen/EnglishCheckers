package sample;


import javafx.scene.control.Alert;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

    public static final int titleSize = 100;
    public static final int width = 8;
    public static final int height = 8;

    private Title[][] board = new Title[width][height];


    private Group titleGroup = new Group();
    private Group pieceGroup = new Group();

    private PieceType previousTurn = PieceType.RED;

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
        if(hasRedPieces && hasWhitePieces)
            return false;
        return true;
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
        if (hasRedPieces == true)
            return "Red";
        else return "White";
    }


    public void winner() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Winner");
            alert.setHeaderText(null);
            alert.setContentText(winnerColor() + " player's victory!");
            alert.showAndWait();
            System.exit(1);

    }





    public boolean kingCondition(Piece piece) {
        if(piece.getType() == PieceType.RED && piece.getLayoutY()==7*titleSize) {
            return true;
        }
        if(piece.getType() == PieceType.WHITE && piece.getLayoutY()==0*titleSize) {     //
            return true;
        }
        return false;
    }



    private MoveResult tryMove(Piece piece, int newX, int newY, PieceType turn, MoveType previousMove) {




            if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
                return new MoveResult(MoveType.NONE);
            }
            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir ||
                    (Math.abs(newX - x0) == 1 && piece.isKing())) {
                if(piece.getType() == turn)
                    return new MoveResult(MoveType.NORMAL);
            } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2 ||
                    (Math.abs(newX - x0) == 2 && piece.isKing())) {
                int x1 = x0 + (newX - x0) / 2;
                int y1 = y0 + (newY - y0) / 2;

                if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                    if (piece.getType() == turn || previousMove == MoveType.KILL)
                        return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
                }
            }

        return new MoveResult(MoveType.NONE);
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

            MoveResult result = tryMove(piece, newX, newY, previousTurn, piece.getPrevMove());

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());


            switch (result.getType()) {
                case NONE:
                    piece.abortMove();
                    piece.setPrevMove(MoveType.NONE);
                    previousTurn = PieceType.other(piece.getType());
                    break;

                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    if(kingCondition(piece)) {
                        piece.setKing();
                    }
                    piece.setPrevMove(MoveType.NORMAL);
                    previousTurn = PieceType.other(piece.getType());
                    break;

                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    Piece otherPiece = result.getPiece();
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);

                    if(kingCondition(piece)) {
                        piece.setKing();
                    }

                    if(winCondition()) {
                        winner();
                    }
                    piece.setPrevMove(MoveType.KILL);
                    previousTurn = PieceType.other(piece.getType());

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
