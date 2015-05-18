package com.thoughtworks.writer;

import com.thoughtworks.repository.CreditRepository;
import com.thoughtworks.repository.SymbolRepository;

public interface Writer {
    void process(String input);

    void setSymbolRepository(SymbolRepository symbolRepository);

    void setCreditRepository(CreditRepository creditRepository);
}