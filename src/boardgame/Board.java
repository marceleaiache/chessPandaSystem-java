package boardgame;

public class Board {

    //INSTANCIAÇÃO DOS ARGUMENTOS DA CLASSE
    private int rows;
    private int columns;

    //MATRIZ DE PEÇAS
    private Piece[][] pieces;

    //MÉTODO CONSTRUTOR

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        //INSTANCIAÇÃO DA MATRIZ
        pieces = new Piece[rows][columns];
    }

    //MÉTODOS GETTERS AND SETTERS


    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
