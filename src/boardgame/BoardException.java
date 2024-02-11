package boardgame;

public class BoardException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    //MÉTODO CONSTRUTOR
    public BoardException(String msg) {
        super(msg);
    }
}
