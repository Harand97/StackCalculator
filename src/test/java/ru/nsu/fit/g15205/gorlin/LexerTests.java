package ru.nsu.fit.g15205.gorlin;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.StringReader;

public class LexerTests extends TestCase {
    @Test
    public void testLexerPlusLexeme() throws Exception {
        Lexer lexer = new Lexer(new StringReader("+"));
        assertEquals(LexemeType.PLUS, lexer.getLexeme().getType());
    }

    @Test
    public void testLexerMinusLexeme() throws Exception {
        Lexer lexer = new Lexer(new StringReader("-"));
        assertEquals(LexemeType.MINUS, lexer.getLexeme().getType());
    }

    @Test
    public void testLexerMultLexeme() throws Exception {
        Lexer lexer = new Lexer(new StringReader("*"));
        assertEquals(LexemeType.MULT, lexer.getLexeme().getType());
    }

    @Test
    public void testLexerDivLexeme() throws Exception {
        Lexer lexer = new Lexer(new StringReader("/"));
        assertEquals(LexemeType.DIV, lexer.getLexeme().getType());
    }

    @Test
    public void testLexerOpenLexeme() throws Exception {
        Lexer lexer = new Lexer(new StringReader("("));
        assertEquals(LexemeType.OPEN, lexer.getLexeme().getType());
    }

    @Test
    public void testLexerCloseLexeme() throws Exception {
        Lexer lexer = new Lexer(new StringReader(")"));
        assertEquals(LexemeType.CLOSE, lexer.getLexeme().getType());
    }

    @Test
    public void testLexerEofLexeme() throws Exception {
        Lexer lexer = new Lexer(new StringReader(""));
        assertEquals(LexemeType.EOF, lexer.getLexeme().getType());
    }

    @Test
    public void testLexerSimpleNumberLexeme() throws Exception {
        Lexer lexer = new Lexer(new StringReader("3"));
        Lexeme lexeme = lexer.getLexeme();
        assertEquals(LexemeType.NUMBER, lexeme.getType());
        assertEquals("3", lexeme.getText());
    }

    @Test
    public void testLexerComplexNumberLexeme() throws Exception {
        Lexer lexer = new Lexer(new StringReader("34214"));
        Lexeme lexeme = lexer.getLexeme();
        assertEquals(LexemeType.NUMBER, lexeme.getType());
        assertEquals("34214", lexeme.getText());
    }

    @Test
    public void testLexerDoubleLexemeCheck() throws Exception {
        Lexer lexer = new Lexer(new StringReader("3"));
        Lexeme lexeme = lexer.getLexeme();
        assertEquals(LexemeType.NUMBER, lexeme.getType());
        assertEquals("3", lexeme.getText());
        assertEquals(LexemeType.EOF, lexer.getLexeme().getType());
    }

    @Test
    public void testLexerExceptions() throws Exception {
        StringBuilder builder = new StringBuilder();

        for (char i = 0; i < 65535; i++) {
            if (!Character.isDigit(i) && !isLexeme(i)) {
                builder.append(i);
            }
        }

        Lexer lexer = new Lexer(new StringReader(builder.toString()));

        for (int i = 0; i < builder.length(); i++) {
            try {
                lexer.getLexeme();
                assertTrue(false);
            } catch (LexerUnknownSymbolException luse) {
                assertTrue(true);
            }
        }
    }

    private boolean isLexeme(int i) {
        return (i == '+'
                || i == '-'
                || i == '*'
                || i == '/'
                || i == '('
                || i == ')'
                || i == '^'
                || i == '\n');
    }
}
