package edu.school21.printer.app;

import edu.school21.printer.logic.Converter;
import java.io.IOException;

public class Program {

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            printError("Invalid number of arguments");
        }

        if (args[0].length() != 1 || args[1].length() != 1) {
            printError("Invalid arguments");
        }

        char    symbW = args[0].charAt(0);
        char    symbB = args[1].charAt(0);
        int[][] arrImg = Converter.convertImage(symbW, symbB);
        int     height = arrImg.length;

        for (int y = 0; y < height; ++y)
        {
            int length = arrImg[y].length;
            for (int x = 0; x < length; ++x)
            {
                System.out.print((char)arrImg[x][y]);
            }
            System.out.println();
        }
    }

    public static void printError(String str) {
        System.err.println(str);
        System.exit(1);
    }
}