package boardgame;

public class Position {

    //INSTANCIANDO OS ARGUMENTOS DA CLASSE
    private int row;
    private int column;

    //MÉTODO CONSTRUTOR COM ARGUMENTO
    public Position(int row, int columns) {
        this.row = row;
        this.column = columns;
    }

    //MÉTODOS GETTERS AND SETTERS
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumns(int column) {
        this.column = column;
    }

    //MÉTODO FUNÇÃO
    public void setValues(int row, int column) {

    }

    @Override
    public String toString() {
        return row + ", "+ column;
    }
}
