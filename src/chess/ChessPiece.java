package chess;

import boardgame.Board;
import boardgame.Piece;

public class ChessPiece extends Piece {

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

}
