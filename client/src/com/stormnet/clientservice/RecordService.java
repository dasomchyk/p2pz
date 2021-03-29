package com.stormnet.clientservice;

import com.stormnet.ja.Record;

import java.io.IOException;
import java.util.List;

public interface RecordService {

    void save(Record record) throws IOException;

    List<Record> loadAll() throws IOException;

    Record loadById(Integer id) throws IOException;

    void delete(Integer id) throws IOException;

    void update(Record record) throws IOException;
}
