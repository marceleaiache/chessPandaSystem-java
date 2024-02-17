package application;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    /*
    Instanciando as cores das peças, e tudo que for necessario pro tabuleiro
    font dos codigos de cores -> https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    /*
        Código para limpar a tela após fazer uma movimentação
        fonte do código: https://stackoverflow.com/questions/2979383/java-clear-the-console

     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //MÉTODO FUNÇÃO QUE LE DO USUÁRIO UMA POSIÇÃO
    public static ChessPosition readChessPosition(Scanner sc) {
        try {
            String s = sc.nextLine();                            //de a-h + 1-8 (ex: a1)
            char column = s.charAt(0);                          //pegando apenas o valor da posição 0 do string s
            int row = Integer.parseInt(s.substring(1));        // recortar o string s a partir da posição 1 e converter o resultado para inteiro
            return new ChessPosition(column, row);
        }
        catch (RuntimeException e) {
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
        }
    }

    //MÉTODO QUE IMPRIME NA TELA O TABULEIRO
    public static void printBoard(ChessPiece[][] pieces) {
        for (int i=0; i<pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j=0; j<pieces.length; j++ ){
               printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    //MÉTODO QUE IMPRIME NA TELA O TABULEIRO COM OS MOVIMENTOS POSSÍVEIS
    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i=0; i<pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j=0; j<pieces.length; j++ ){
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    //MÉTODO FUNÇÃO QUE IMPRIME UMA PEÇA OU COR DE FUNDO
    private static void printPiece(ChessPiece piece, boolean background) {
        if (background) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }

        if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
}
