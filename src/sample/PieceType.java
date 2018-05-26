package sample;

public enum PieceType {
    RED(1), WHITE(-1);

    final int moveDir;

    PieceType(int moveDir) {
        this.moveDir = moveDir;
    }

    public static PieceType other(PieceType a) {
        if(a==PieceType.RED)
            return PieceType.WHITE;
        else return PieceType.RED;
    }
}
