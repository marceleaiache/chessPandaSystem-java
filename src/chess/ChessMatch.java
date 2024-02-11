package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

    //DECLARAÇÃO DE UM OBJETO BOARD;
    private Board board;

    public ChessMatch(){
        //INSTANCIANDO UM OBJETO BOARD
        board = new Board(8, 8);
        //INSTANCIANDO UM INICIO DE PARTIDA
        initialSetup();
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

    //MÉTODO RESPONSÁVEL POR INICIAR A PARTIDA DE XADREZ, COLOCANDO AS PEÇAS NO TABULEIRO
    private void initialSetup() {
        board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
        board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
        board.placePiece(new King(board, Color.WHITE), new Position(7, 4));

    }
}
