package tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.rules.ExternalResource;

public class setSystemOut extends ExternalResource {

    private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Override
    protected void before() throws Throwable {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @Override
    protected void after() {
        System.setOut(sysOut);
    }

    public String asString() {
        return outContent.toString();
    }
}