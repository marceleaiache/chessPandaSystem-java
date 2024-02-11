package chess;

import boardgame.Board;
import boardgame.Piece;
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

    //MÉTODO FUNÇÃO QUE RETIRA A PEÇA NA POSIÇÃO DE ORIGEM PARA A POSIÇÃO DESTINO
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece)capturedPiece;

    }

    //MÉTODO FUNÇÃO QUE MOVIMENTA A PEÇA NO TABULEIRO
    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);                    //retirada da peça na posição de origem
        Piece caputuredPiece = board.removePiece(target);       //remove a POSSIVEL peça que está na posição de destino
        board.placePiece(p, target);
        return caputuredPiece;
    }

    //MÉTODO FUNÇÃO DE VALIDAÇÃO SE HÁ PEÇA NA POSIÇÃO DE ORIGEM
    public void validateSourcePosition(Position position) {
        if(!board.thereIsAPiece(position)){
            throw new ChessException("There is no piece on source position");
        }
    }

    //MÉTODO FUNÇÃO QUE RECEBE AS COORDENADAS DO XADREZ
    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    //MÉTODO RESPONSÁVEL POR INICIAR A PARTIDA DE XADREZ, COLOCANDO AS PEÇAS NO TABULEIRO
    private void initialSetup() {
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
}
