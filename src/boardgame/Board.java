package boardgame;

public class Board {

    //INSTANCIAÇÃO DOS ARGUMENTOS DA CLASSE
    private int rows;
    private int columns;

    //MATRIZ DE PEÇAS
    private Piece[][] pieces;

    //MÉTODO CONSTRUTOR
    public Board(int rows, int columns) {
        //PROGRAMAÇÃO DEFENSIVA
        if(rows < 1 || columns < 1) {
            throw new BoardException("Error creating board: there must be at least 1 row an 1 column");
        }

        this.rows = rows;
        this.columns = columns;
        //INSTANCIAÇÃO DE UMA MATRIZ
        pieces = new Piece[rows][columns];
    }

    //MÉTODOS GETTERS AND SETTERS
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece piece(int row, int column) {
        //PROGRAMAÇÃO DEFENSIVA
        if(!positionExists(row, column)) {
            throw new BoardException("Position not on the board");
        }

        return pieces[row][column];
    }

    public Piece piece(Position position) {
        //PROGRAMAÇÃO DEFENSIVA
        if(!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }

        return pieces[position.getRow()][position.getColumn()];
    }

    //MÉTODO FUNÇÃO PLACEPIECE RESPONSÁVEL POR PEGAR UMA PEÇA NUMA POSIÇÃO E COLOCA-LA NO TABULEIRO
    public void placePiece(Piece piece, Position position) {
        //PROGRAMAÇÃO DEFENSIVA
        if (thereIsAPiece(position)) {
            throw new BoardException("There is already a piece on position " + position);
        }

        //PEGAR A MTRIZ NA POSIÇÃO DADA E ATRIBUIR A PELA QUE EU INFORMI
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    //MÉTODO AUXILIAR DO METODO POSITIONEXISTS
    private boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    //MÉTODO FUNÇÃO QUE VERIFICA SE UMA POSIÇÃO EXISTE OU NÃO
    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    //MÉTODO FUNÇÃ QUE VERIFICA SE NA POSIÇÃO REFERIDA EXISTE OU NAO UMA PEÇA
    public boolean thereIsAPiece(Position position) {
        //PROGRAMAÇÃO DEFENSIVA
        if(!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }

        return piece(position) != null;
    }

}
