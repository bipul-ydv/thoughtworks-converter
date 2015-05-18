package com.thoughtworks.factory;

import com.thoughtworks.repository.CreditRepository;
import com.thoughtworks.repository.Repository;
import com.thoughtworks.repository.SymbolRepository;

public class RepositoryFactory {
    private static final String REGEX_SYMBOL = "^[a-zA-Z]+( is )(I|V|X|L|C|D|M)$";
    private static final String REGEX_CREDIT = "^[a-zA-Z ]*[a-zA-Z]+( is )[0-9]+( Credits)$";
    private static final String NAME_SYMBOL = "symbolRepository";
    private static final String NAME_CREDIT = "creditRepository";
    private static SymbolRepository symbolRepository;
    private static CreditRepository creditRepository;

    static {
        symbolRepository = new SymbolRepository();
        creditRepository = new CreditRepository();
        creditRepository.setSymbolRepository(symbolRepository);
    }

    public static Repository getRepository(String input) {
        if (NAME_SYMBOL.equals(input) || input.matches(REGEX_SYMBOL)) {
            return symbolRepository;
        }
        else if (NAME_CREDIT.equals(input) || input.matches(REGEX_CREDIT)) {
            return creditRepository;
        }

        return null;
    }
}
