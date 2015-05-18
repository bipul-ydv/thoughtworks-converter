package com.thoughtworks.factory;

import com.thoughtworks.repository.CreditRepository;
import com.thoughtworks.repository.SymbolRepository;
import com.thoughtworks.writer.CreditWriter;
import com.thoughtworks.writer.SymbolWriter;
import com.thoughtworks.writer.UnknownWriter;
import com.thoughtworks.writer.Writer;

public class WriterFactory {
    private static final String REGEX_SYMBOL = "^(how much is )[a-zA-Z ]+(\\?)$";
    private static final String REGEX_CREDIT = "^(how many Credits is )[a-zA-Z ]+(\\?)$";
    private static final String NAME_SYMBOL = "symbolWriter";
    private static final String NAME_CREDIT = "creditWriter";
    private static SymbolWriter symbolWriter;
    private static CreditWriter creditWriter;
    private static UnknownWriter unknownWriter;

    static {
        symbolWriter = new SymbolWriter();
        creditWriter = new CreditWriter();
        unknownWriter = new UnknownWriter();

        SymbolRepository symbolRepository = (SymbolRepository) RepositoryFactory.getRepository("symbolRepository");
        CreditRepository creditRepository = (CreditRepository) RepositoryFactory.getRepository("creditRepository");

        symbolWriter.setSymbolRepository(symbolRepository);
        creditWriter.setSymbolRepository(symbolRepository);
        creditWriter.setCreditRepository(creditRepository);
    }

    public static Writer getWriter(String input) {
        if (NAME_SYMBOL.equals(input) || input.matches(REGEX_SYMBOL)) {
            return symbolWriter;
        }
        else if (NAME_CREDIT.equals(input) || input.matches(REGEX_CREDIT)) {
            return creditWriter;
        }
        else {
            return unknownWriter;
        }
    }
}
