package boardgame;

public class Piece {

    //PROTECTED-> SOMENTE CLASSES DENTRO DO MESMO PACOTE E SUBCLASSES QUE VAO PODER ACESSAR
    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    //MÉTODO GETT
    protected Board getBoard() {
        return board;
    }

}
