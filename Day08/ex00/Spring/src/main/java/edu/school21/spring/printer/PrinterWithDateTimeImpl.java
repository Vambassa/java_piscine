package edu.school21.spring.printer;

import edu.school21.spring.renderer.Renderer;
import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {

    private final Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String msg) {
        renderer.sendToConsole(String.format("%s %s", LocalDateTime.now(), msg));
    }
}
