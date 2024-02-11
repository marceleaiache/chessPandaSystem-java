package chess;

import boardgame.Board;

public class ChessMatch {

    //DECLARAÇÃO DE UM OBJETO BOARD;
    private Board board;

    public ChessMatch(){
        //INSTANCIANDO UM OBJETO BOARD
        board = new Board(8, 8);
    }

    //MÉTODO FUNÇÃO QUE RETORNA UMA MATRIZ DE PEÇAS DE XADREZ CORRESPONDENTE A UMA PARTIDA
    public ChessPiece[][] getPieces() {
        //INSTANCIANDO UMA MATRIZ CHESSPIECE
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i=0; i<board.getRows(); i++) {
            //PERCORRER A MATRIZ DE PEÇAS DO TABULEIRO (BOARD) E PRA CADA PEÇA DO TABULEIRO FAZER UM DOWNCASTING PRA CHEESPIECE
            for (int j=0; j<board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }
}
