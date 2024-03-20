package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

    //ARGUMENTO DA CLASSE
    private Color color;
    private int moveCount;

    //MÉTODO CONSTRUTOR
    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    //MÉTODO GET
    public Color getColor() {
        return color;
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }



    //MÉTODO FUNÇÃO DE INCREMENTO
    public void increaseMoveCount() {
        moveCount++;
    }

    //MÉTODO FUNÇÃO DE DECREMENTO
    public void decreaseMoveCount() {
        moveCount--;
    }

    //OPERAÇÃO
    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p != null && p.getColor() != color;
    }

}
