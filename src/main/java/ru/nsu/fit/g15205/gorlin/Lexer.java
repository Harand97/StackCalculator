package ru.nsu.fit.g15205.gorlin;

import java.io.IOException;
import java.io.Reader;

public class Lexer
{
    private Reader reader;
    private int current;

    private boolean alreadyHaveCurrent = false;

    public Lexer(Reader reader) {
        this.reader = reader;
    }

    public Lexeme getLexeme() throws IOException {
        if (!alreadyHaveCurrent) {
            current = reader.read();
        } else {
            alreadyHaveCurrent = false;
        }

        switch (current) {
            case '+':
                return new Lexeme(LexemeType.PLUS, "+");
            case '-':
                return new Lexeme(LexemeType.MINUS, "-");
            case '*':
                return new Lexeme(LexemeType.MULT, "*");
            case '/':
                return new Lexeme(LexemeType.DIV, "/");
            case '(':
                return new Lexeme(LexemeType.OPEN, "(");
            case ')':
                return new Lexeme(LexemeType.CLOSE, ")");
            case '^':
                return new Lexeme(LexemeType.POWER, "^");
            case '\n':
            case -1:
                return new Lexeme(LexemeType.EOF, current + "");
            default:
                if (Character.isDigit(current)) {
                    int number = getNumber();
                    return new Lexeme(LexemeType.NUMBER, number + "");
                }

                throw new LexerUnknownSymbolException();
        }
    }

    private int getNumber() throws IOException {
        int number = current - '0';

        while (true) {
            current = reader.read();
            if (!Character.isDigit(current)) {
                alreadyHaveCurrent = true;
                break;
            }

            number = (number * 10) + current - '0';
        }

        return number;
    }
}
