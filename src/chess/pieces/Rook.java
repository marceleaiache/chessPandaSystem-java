package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
    //MÉTODO CONSTRUTOR (repassando os dados da classe para a super classe)
    public Rook(Board board, Color color) {
        super(board, color);
    }

    //MÉTODO TOSTRING
    @Override
    public String toString(){
        return "R";
    }

}
