package chess;

import boardgame.BoardException;

public class ChessException extends BoardException {
    private static final long serialVersionUID = 1L;

    //MÉTODO CONSTRUTOR RECEBENDO UMA STRING
    public ChessException(String msg) {
        super(msg);
    }
}
