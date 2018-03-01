package ru.nsu.fit.g15205.gorlin;

import java.io.IOException;

public class LexerUnknownSymbolException extends IOException
{
    public LexerUnknownSymbolException() {
        super();
    }

    public LexerUnknownSymbolException(String message) {
        super(message);
    }

    public LexerUnknownSymbolException(String message, Throwable cause) {
        super(message, cause);
    }

    public LexerUnknownSymbolException(Throwable cause) {
        super(cause);
    }
}
