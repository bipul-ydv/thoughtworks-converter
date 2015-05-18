package com.thoughtworks.factory;

import com.thoughtworks.repository.CreditRepository;
import com.thoughtworks.repository.SymbolRepository;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class RepositoryFactoryTest {
    private static final String SYMBOL_MATCH = "prok is V";
    private static final String SYMBOL_NAME = "symbolRepository";
    private static final String CREDIT_MATCH = "glob prok Gold is 57800 Credits";
    private static final String CREDIT_NAME = "creditRepository";

    @Test
    public void shouldGetSymbolRepositoryByMatcher() {
        assertThat(RepositoryFactory.getRepository(SYMBOL_MATCH), is(instanceOf(SymbolRepository.class)));
    }

    @Test
    public void shouldGetSymbolRepositoryByName() {
        assertThat(RepositoryFactory.getRepository(SYMBOL_NAME), is(instanceOf(SymbolRepository.class)));
    }

    @Test
    public void shouldGetCreditRepositoryByMatcher() {
        assertThat(RepositoryFactory.getRepository(CREDIT_MATCH), is(instanceOf(CreditRepository.class)));
    }

    @Test
    public void shouldGetCreditRepositoryByName() {
        assertThat(RepositoryFactory.getRepository(CREDIT_NAME), is(instanceOf(CreditRepository.class)));
    }
}
