package com.thoughtworks.writer;

import com.thoughtworks.repository.CreditRepository;
import com.thoughtworks.repository.SymbolRepository;

public class UnknownWriter implements Writer {
    private static final String UNKNOWN_MESSAGE = "I have no idea what you are talking about";

    public void process(String input) {
        System.out.println(UNKNOWN_MESSAGE);
    }

    public void setSymbolRepository(SymbolRepository symbolRepository) {
    }

    public void setCreditRepository(CreditRepository creditRepository) {
    }
}
