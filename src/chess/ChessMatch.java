package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {

    //DECLARAÇÃO DE ARGUMENTOS DA CLASSE
    private int turn;
    private Color currentPlayer;
    private Board board;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch(){
        //INSTANCIANDO UM OBJETO BOARD
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        //INSTANCIANDO UM INICIO DE PARTIDA
        initialSetup();
    }

    //MÉTODO GET
    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
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

    //MÉTODO QUE IMPRIME AS POSIÇÕES POSSIVEIS NA POSIÇÃO DE ORIGEM (SOURCE)
    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }
    //MÉTODO FUNÇÃO QUE RETIRA A PEÇA NA POSIÇÃO DE ORIGEM PARA A POSIÇÃO DESTINO
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        nextTurn();
        return (ChessPiece)capturedPiece;

    }

    //MÉTODO FUNÇÃO QUE MOVIMENTA A PEÇA NO TABULEIRO
    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);                    //retirada da peça na posição de origem
        Piece caputuredPiece = board.removePiece(target);       //remove a POSSIVEL peça que está na posição de destino
        board.placePiece(p, target);

        if (caputuredPiece != null) {
            piecesOnTheBoard.remove(caputuredPiece);
            capturedPieces.add(caputuredPiece);
        }

        return caputuredPiece;
    }

    //MÉTODO FUNÇÃO DE VALIDAÇÃO DE MOVIMENTAÇÃO DA PEÇA
    public void validateSourcePosition(Position position) {
        //SE NÃO HÁ PEÇA NO LUGAR DE ORIGEM ESCOLHIDO HÁ UMA EXCEÇÃO
        if (!board.thereIsAPiece(position)){
            throw new ChessException("There is no piece on source position");
        }

        //SE A PEÇA ESCOLHIDA NÃO É A PEÇA DO JOGADOR HÁ UMA EXCEÇÃO
        if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
            throw new ChessException("The chosen piece is not yours");
        }

        //SE NAO TIVER NENHUM MOVIMENTO POSSIVEL HÁ UMA EXCEÇÃO
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    //MÉTODO FUNÇÃO DE VALIDAÇÃO SE HÁ PEÇA NA POSIÇÃO DE DESTINO
    public void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    //MÉTODO FUNÇÃO QUE TROCA O TURNO
    private void nextTurn() {
        turn ++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    //MÉTODO FUNÇÃO QUE RECEBE AS COORDENADAS DO XADREZ
    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
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
