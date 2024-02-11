package application;

import chess.ChessMatch;

public class Program {
    public static void main(String[] args) {

        ChessMatch chessMatch = new ChessMatch();
        //FUNÇÃO PARA IMPRIMIR AS PEÇAS DE UMA PARTIDA
        UI.printBoard(chessMatch.getPieces());
    }
}
