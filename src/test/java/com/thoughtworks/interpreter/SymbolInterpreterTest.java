package com.thoughtworks.interpreter;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.IllegalFormatException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SymbolInterpreterTest {
    public final static Pair[] VALID_SYMBOLS = {
            new Pair<String, BigDecimal>("I", new BigDecimal(1)),
            new Pair<String, BigDecimal>("V", new BigDecimal(5)),
            new Pair<String, BigDecimal>("X", new BigDecimal(10)),
            new Pair<String, BigDecimal>("L", new BigDecimal(50)),
            new Pair<String, BigDecimal>("C", new BigDecimal(100)),
            new Pair<String, BigDecimal>("D", new BigDecimal(500)),
            new Pair<String, BigDecimal>("M", new BigDecimal(1000)),
            new Pair<String, BigDecimal>("IV", new BigDecimal(4)),
            new Pair<String, BigDecimal>("IX", new BigDecimal(9)),
            new Pair<String, BigDecimal>("XL", new BigDecimal(40)),
            new Pair<String, BigDecimal>("XC", new BigDecimal(90)),
            new Pair<String, BigDecimal>("CD", new BigDecimal(400)),
            new Pair<String, BigDecimal>("CM", new BigDecimal(900)),
            new Pair<String, BigDecimal>("III", new BigDecimal(3)),
            new Pair<String, BigDecimal>("XXX", new BigDecimal(30)),
            new Pair<String, BigDecimal>("CCC", new BigDecimal(300)),
            new Pair<String, BigDecimal>("MMM", new BigDecimal(3000)),
            new Pair<String, BigDecimal>("XXXIX", new BigDecimal(39)),
    };
    public final static String[] INVALID_SYMBOLS = {
            "IL", "IC", "ID", "IM", "XD", "XM", "VX", "VL", "VC", "VD", "VM", "LC", "LD", "LM",
            "IIII", "VVVV", "XXXX", "LLLL", "LLLL", "CCCC", "DDDD", "MMMM", "IIV", "XXC", "CCM",
            "VVV"
    };
    private SymbolInterpreter symbolInterpreter;

    @Before
    public void setUp() {
        symbolInterpreter = new SymbolInterpreter();
    }

    @Test
    public void shouldIntepretValidSymbol() {
        for (Pair<String, BigDecimal> pair : VALID_SYMBOLS) {
            assertThat(pair.getValue(), is(equalTo(symbolInterpreter.interpret(pair.getKey()))));
        }
    }

    @Test
    public void shouldThrowExceptionForIllegalSymbol() {
        int count = 0;

        for (String symbol : INVALID_SYMBOLS) {
            try {
                symbolInterpreter.interpret(symbol);
            }
            catch (IllegalArgumentException e) {
                count++;
            }
        }

        assertThat(count, is(equalTo(INVALID_SYMBOLS.length)));
    }
}
