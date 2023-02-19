package tms.console.util;

import tms.util.Writer;

public class ConsoleWriter implements Writer {

    public void write(String message) {
        System.out.println(message);
    }
}