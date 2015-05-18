package com.thoughtworks.util;

import com.thoughtworks.repository.Repository;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryUtilTest {
    private static final String[] TEST_INPUT = {"ten", "fifty", "one", "one"};
    private static final String TEST_OUTPUT = "XLII";

    @Test
    public void shouldLookupAndReturnSymbols() {
        Repository repository = mock(Repository.class);

        when(repository.get("one")).thenReturn("I");
        when(repository.get("ten")).thenReturn("X");
        when(repository.get("fifty")).thenReturn("L");

        assertThat(RepositoryUtil.lookupSymbolsFromVariables(repository, TEST_INPUT), is(equalTo(TEST_OUTPUT)));
    }
}
