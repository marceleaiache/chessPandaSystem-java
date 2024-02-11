package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    //MÉTODO CONSTRUTOR (repassando os dados da classe para a super classe)
    public King(Board board, Color color) {
        super(board, color);
    }

    //MÉTODO TOSTRING
    @Override
    public String toString() {
        return "K";
    }
}
