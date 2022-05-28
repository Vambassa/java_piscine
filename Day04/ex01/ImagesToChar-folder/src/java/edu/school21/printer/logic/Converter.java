package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Converter {

    public static int[][] convertImage(char symbW, char symbB) throws IOException {

        BufferedImage   bufferedImage = ImageIO.read(Converter.class.getResource("/resources/image.bmp"));
        int             width = bufferedImage.getWidth();
        int             height = bufferedImage.getHeight();
        int             black = Color.BLACK.getRGB();

        int arrImg[][] = new int[width][height];

        for (int y = 0; y < height; ++y)
        {
            for (int x = 0; x < width; ++x)
            {
                int color = bufferedImage.getRGB(x, y);
                if (color == black) {
                    arrImg[x][y] = symbB;
                } else {
                    arrImg[x][y] = symbW;
                }
            }
        }

        return arrImg;
    }
}