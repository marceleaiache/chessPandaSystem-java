package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

    //ARGUMENTO DA CLASSE
    private Color color;

    //MÉTODO CONSTRUTOR
    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    //MÉTODO GET
    public Color getColor() {
        return color;
    }

    //OPERAÇÃO
    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p != null && p.getColor() != color;
    }

}
