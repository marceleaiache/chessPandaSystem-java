package boardgame;

public class BoardException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    //MÉTODO CONSTRUTOR RECEBENDO UMA STRING
    public BoardException(String msg) {
        super(msg);
    }
}
