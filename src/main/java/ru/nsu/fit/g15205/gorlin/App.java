package ru.nsu.fit.g15205.gorlin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Reader reader = new InputStreamReader(System.in);

        try {
            Parser parser = new Parser(reader);
            System.out.println("Result: " + parser.calculate());
        } catch (IOException ioe) {
            System.out.println("EXCEPTION: " + ioe);
        }
    }
}
