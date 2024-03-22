package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

    //DECLARAÇÃO DE ARGUMENTOS DA CLASSE
    private int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;
    private boolean checkMate;

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

    //MÉTODOS GETTS
    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
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

        if (testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        check = (testCheck(opponent(currentPlayer))) ? true : false;

        if (testCheckMate(opponent(currentPlayer))) {
            checkMate = true;
        }
        else {
            nextTurn();
        }

        return (ChessPiece)capturedPiece;

    }

    //MÉTODO FUNÇÃO QUE MOVIMENTA A PEÇA NO TABULEIRO
    private Piece makeMove(Position source, Position target) {
        ChessPiece p = (ChessPiece)board.removePiece(source);                    //retirada da peça na posição de origem
        p.increaseMoveCount();
        Piece caputuredPiece = board.removePiece(target);       //remove a POSSIVEL peça que está na posição de destino
        board.placePiece(p, target);

        if (caputuredPiece != null) {
            piecesOnTheBoard.remove(caputuredPiece);
            capturedPieces.add(caputuredPiece);
        }

        return caputuredPiece;
    }

    //MÉTODO FUNÇÃO PARA DESFAZER O MOVIMENTO
    private void undoMove(Position source, Position target, Piece capturedPiece) {
        ChessPiece p = (ChessPiece)board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p, source);

        if(capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);

        }
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

    //MÉTODO FUNÇÃO DA COR DO OPONENTE
    private Color opponent(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    //MÉTODO FUNÇÃO QUE LOCALIZA O REI DE UMA COR
    private ChessPiece king(Color color) {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());

        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There is no " + color + "king on the board");
    }

    //MÉTODO FUNÇÃO QUE CHECA SE O REI DE UMA DETERMINADA COR ESTÁ EM CHECK
    private boolean testCheck(Color color) {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());

        for (Piece p : opponentPieces) {
            boolean[][] mat = p.possibleMoves();
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
                return true;
            }
        }
        return false;
    }

    //MÉTODO FUNÇÃO QUE TESTA SE HOUVE UM CHECK MATE
    private boolean testCheckMate(Color color) {
        if (!testCheck(color)) {
            return false;
        }

        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());

        for (Piece p : list) {
            boolean[][] mat = p.possibleMoves();
            for (int i=0; i< board.getRows(); i++) {
                for (int j=0; j< board.getColumns(); j++) {
                    if (mat[i][j]) {
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    //MÉTODO FUNÇÃO QUE RECEBE AS COORDENADAS DO XADREZ
    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    //MÉTODO RESPONSÁVEL POR INICIAR A PARTIDA DE XADREZ, COLOCANDO AS PEÇAS NO TABULEIRO
    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
    }
}
