package com.stormnet.serverservice;

import com.stormnet.ja.Record;

import java.util.List;

public interface RecordService {

    void save (Record record);

    List<Record> loadAll();

    Record loadById (Integer id);

    void delete (Integer id);

    void update (Record record);
}
