package com.thoughtworks.factory;

import com.thoughtworks.writer.CreditWriter;
import com.thoughtworks.writer.SymbolWriter;
import com.thoughtworks.writer.UnknownWriter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class WriterFactoryTest {
    private static final String SYMBOL_MATCH = "how much is pish tegj glob glob ?";
    private static final String SYMBOL_NAME = "symbolWriter";
    private static final String CREDIT_MATCH = "how many Credits is glob prok Gold ?";
    private static final String CREDIT_NAME = "creditWriter";
    private static final String UNKNOWN_MATCH = "how much wood could a woodchuck chuck ?";

    @Test
    public void shouldGetSymbolWriterByMatcher() {
        assertThat(WriterFactory.getWriter(SYMBOL_MATCH), is(instanceOf(SymbolWriter.class)));
    }

    @Test
    public void shouldGetSymbolWriterByName() {
        assertThat(WriterFactory.getWriter(SYMBOL_NAME), is(instanceOf(SymbolWriter.class)));
    }

    @Test
    public void shouldGetCreditWriterByMatcher() {
        assertThat(WriterFactory.getWriter(CREDIT_MATCH), is(instanceOf(CreditWriter.class)));
    }

    @Test
    public void shouldGetCreditWriterByName() {
        assertThat(WriterFactory.getWriter(CREDIT_NAME), is(instanceOf(CreditWriter.class)));
    }

    @Test
    public void shouldGetUnknownWriterByMatcher() {
        assertThat(WriterFactory.getWriter(UNKNOWN_MATCH), is(instanceOf(UnknownWriter.class)));
    }
}
