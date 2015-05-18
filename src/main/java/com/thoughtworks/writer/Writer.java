package com.thoughtworks.writer;

import com.thoughtworks.repository.Repository;

public interface Writer {
    void process(String input);

    void setRepository(Repository repository);
}