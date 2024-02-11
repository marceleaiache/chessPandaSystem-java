package chess;

public class ChessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    //MÉTODO CONSTRUTOR RECEBENDO UMA STRING
    public ChessException(String msg) {
        super(msg);
    }
}
