package chess;

import boardgame.Position;

public class ChessPosition {

    //DECLARAÇÃO DOS ARGUMENTOS DA CLASSE
    private char column;
    private int row;

    //MÉTODO CONSTRUTOR
    public ChessPosition(char column, int row) {
        //PROGRAMAÇÃO DEFENSICA
        if (column < 'a' || column > 'h' || row < 1 || row > 8) {
            throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8.");
        }

        this.column = column;
        this.row = row;
    }

    //MÉTODOS GET
    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    //MÉTODO FUNÇÃO QUE CONVERTE A POSIÇÃO DA MATRIZ PARA O VALOR DO TABULEIRO 1-8/A-H
    protected Position toPosition() {
        return new Position(8 - row, column - 'a');
    }

    //MÉTODO FUNÇÃO
    protected static ChessPosition fromPosition(Position position) {
        return new ChessPosition((char)('a' + position.getColumn()), 8 - position.getRow());
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
