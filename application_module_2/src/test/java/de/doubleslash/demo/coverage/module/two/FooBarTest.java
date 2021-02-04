package de.doubleslash.demo.coverage.module.two;

import de.doubleslash.demo.coverage.module.one.Foo;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FooBarTest {

    @Test
    public void testFooBar() {
        Foo foo = new Foo();
        assertThat(foo.fooMethod(), is(7));
    }

    @Test
    public void testTrue() {
        assertThat(true, is(true));
    }

}
