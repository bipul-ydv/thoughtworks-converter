package com.thoughtworks.repository;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SymbolRepostioryTest {
    private static final String TEST_KEY = "foo";
    private static final String TEST_VALUE = "bar";
    private static final String TEST_INPUT_VALID = TEST_KEY + " is " + TEST_VALUE;
    private SymbolRepository symbolRepository;

    @Before
    public void setUp() {
        symbolRepository = new SymbolRepository();
    }

    @Test
    public void shouldSetAndGet() {
        symbolRepository.put(TEST_INPUT_VALID);
        assertThat(symbolRepository.get(TEST_KEY), is(equalTo(TEST_VALUE)));
    }

    @Test
    public void shouldGetInvalidKeyReturnsNull() {
        assertThat(symbolRepository.get(TEST_KEY), is(nullValue()));
    }
}
