package boardgame;

public abstract class Piece {

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

    //OPERAÇÃO ABSTRATA
    public abstract boolean[][] possibleMoves();

    //MÉTODO FUNÇÃO
    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove() {
        boolean[][] mat = possibleMoves();
        for (int i=0; i<mat.length; i++) {
            for (int j=0; j<mat.length; j++) {
                if (mat[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
