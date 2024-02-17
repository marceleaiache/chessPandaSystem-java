package chess.pieces;

import boardgame.Board;
import boardgame.Position;
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

    //MÉTODO AUXILIAR
    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }
    //IMPLEMENTANDO MÉTODO FUNÇÃO
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        //MÉTODO FUNÇÃO MOVIMENTOS POSSIVEIS DO REI ACIMA
        Position p = new Position(0, 0);
        p.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //MÉTODO FUNÇÃO MOVIMENTOS POSSIVEIS DO REI ESQUERDA
        p.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //MÉTODO FUNÇÃO MOVIMENTOS POSSIVEIS DO REI DIAGONAL ESQUERDA
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //MÉTODO FUNÇÃO MOVIMENTOS POSSIVEIS DO REI DIREITA
        p.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //MÉTODO FUNÇÃO MOVIMENTOS POSSIVEIS DO REI DIAGONAL DIREITA
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //MÉTODO FUNÇÃO MOVIMENTOS POSSIVEIS DO REI ABAIXO
        p.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
