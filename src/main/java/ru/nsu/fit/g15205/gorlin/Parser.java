package ru.nsu.fit.g15205.gorlin;

import java.io.IOException;
import java.io.Reader;

public class Parser
{
    private Lexer lexer;

    private Lexeme current;

    public Parser(Reader reader) throws IOException {
        lexer = new Lexer(reader);
        current = lexer.getLexeme();
    }

    public int calculate() throws IOException {
        int temp = parseExpr();
        if (current.getType() != LexemeType.EOF) {
            throw new ParserException();
        }

        return temp;
    }

    private int parseExpr() throws IOException {
        int result = parseTerm();

        while (true) {
            if (current.getType() == LexemeType.PLUS) {
                current = lexer.getLexeme();
                int tmp = parseTerm();
                result += tmp;
            } else if (current.getType() == LexemeType.MINUS) {
                current = lexer.getLexeme();
                int tmp = parseTerm();
                result -= tmp;
            } else {
                break;
            }
        }

        return result;
    }

    private int parseTerm() throws IOException {
        int result = parseFactor();
        while (true) {
            if (current.getType() == LexemeType.DIV) {
                current = lexer.getLexeme();
                try {
                    result /= parseFactor();
                } catch (ArithmeticException ae) {
                    throw new ParserException();
                }

            } else if (current.getType() == LexemeType.MULT) {
                current = lexer.getLexeme();
                result *= parseFactor();
            } else {
                break;
            }
        }
        return result;
    }

    private int parseFactor() throws IOException {
        if (current.getType() == LexemeType.MINUS) {
            current = lexer.getLexeme();
            return -parsePower();
        }
        return parsePower();
    }

    private int parsePower() throws IOException {
        int result = parseAtom();

        if (current.getType() == LexemeType.POWER) {
            current = lexer.getLexeme();
            result = (int)Math.pow(result, parseFactor());
        }
        return result;
    }

    private int parseAtom() throws IOException {
        int result;

        if (current.getType() == LexemeType.NUMBER) {
            result = Integer.parseInt(current.getText());
            current = lexer.getLexeme();
        } else if (current.getType() == LexemeType.OPEN) {
            current = lexer.getLexeme();
            result = parseExpr();
            if (current.getType() != LexemeType.CLOSE) {
                throw new ParserException();
            }
            current = lexer.getLexeme();
        } else {
            throw new ParserException();
        }

        return result;
    }
}
