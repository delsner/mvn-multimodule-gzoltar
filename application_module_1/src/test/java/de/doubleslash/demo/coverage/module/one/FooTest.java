package de.doubleslash.demo.coverage.module.one;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FooTest {

    private String getCurrentPid() {
        return java.lang.management.ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
    }

    @Test
    public void testFooMethod() {
        System.out.println("STARTING SUITE FooTest PID: " + getCurrentPid());
        Foo testee = new Foo();
        assertThat(testee.fooMethod(), is(7));
    }

}
