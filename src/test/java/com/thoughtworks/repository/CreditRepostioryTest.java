package com.thoughtworks.repository;

import com.thoughtworks.interpreter.SymbolInterpreter;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreditRepostioryTest {
    private static final String TEST_VALUE_1 = "1";
    private static final String TEST_VALUE_9 = "9";
    private static final String TEST_VALUE_81 = "81";
    private static final String TEST_KEY = "Gold";
    private static final String TEST_INPUT_SINGLE = "foo " + TEST_KEY + " is " + TEST_VALUE_81 + " Credits";
    private static final String TEST_INPUT_DOUBLE = "foo bar " + TEST_KEY + " is " + TEST_VALUE_81 + " Credits";
    private CreditRepository creditRepository;
    private SymbolRepository symbolRepository;
    private SymbolInterpreter symbolInterpreter;

    @Before
    public void setUp() {
        creditRepository = new CreditRepository();

        symbolRepository = mock(SymbolRepository.class);
        symbolInterpreter = mock(SymbolInterpreter.class);

        when(symbolRepository.get("foo")).thenReturn("I");
        when(symbolRepository.get("bar")).thenReturn("X");
        when(symbolInterpreter.interpret("I")).thenReturn(new BigDecimal(TEST_VALUE_1));
        when(symbolInterpreter.interpret("IX")).thenReturn(new BigDecimal(TEST_VALUE_9));

        creditRepository.setSymbolRepository(symbolRepository);
        creditRepository.setSymbolInterpreter(symbolInterpreter);
    }

    @Test
    public void shouldSetAndGetSingleVariableCredit() {
        creditRepository.put(TEST_INPUT_SINGLE);
        assertThat(creditRepository.get(TEST_KEY), is(equalTo(TEST_VALUE_81)));
    }

    @Test
    public void shouldSetAndGetDoubleVariableCredit() {
        creditRepository.put(TEST_INPUT_DOUBLE);
        assertThat(creditRepository.get(TEST_KEY), is(equalTo(TEST_VALUE_9)));
    }

    @Test
    public void shouldGetInvalidKeyReturnsNull() {
        assertThat(creditRepository.get(TEST_KEY), is(nullValue()));
    }
}
