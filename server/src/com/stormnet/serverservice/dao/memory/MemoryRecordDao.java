package com.stormnet.serverservice.dao.memory;


import com.stormnet.ja.Record;
import com.stormnet.serverservice.dao.RecordDao;
import com.stormnet.serverservice.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class MemoryRecordDao implements RecordDao {

    private static List<Record> allRecordDb = initRecordDb();

    public MemoryRecordDao() {
    }

    @Override
    public List<Record> loadAll() {
        return allRecordDb;
    }

    @Override
    public Record loadById(Integer recordID) {
        for (Record record: allRecordDb) {
            if (record.getId().equals(recordID)) {
                return record;
            }
        }

        return null;
    }

    @Override
    public void save(Record record) {
        Integer recordId = IdGenerator.getGenerator().nextId();
        record.setId(recordId);

        allRecordDb.add(record);

    }

    @Override
    public void update(Record record) {
        Integer recordID = record.getId();
        if (record == null) {
            return;
        }

        Record dbRecord = loadById(recordID);
        dbRecord.setTime(record.getTime());
        dbRecord.setMasterLastName(record.getMasterLastName());

    }

    @Override
    public void delete(Integer recordId) {
        for (Record record: allRecordDb) {
            if (record.getId().equals(recordId)) {
                allRecordDb.remove(record);
                return;
            }
        }
    }

    private static List<Record> initRecordDb() {
        List<Record> recordsDb = new ArrayList<>();

        Record r1 = new Record();
        r1.setId(3);
        r1.setClientId(1);
        r1.setMasterId(2);
        r1.setClientFirstName("Galina");
        r1.setClientLastName("Kolesnikova");
        r1.setMasterFirstName("Alina");
        r1.setMasterLastName("Yarusova");

        r1.setFinished(false);

        Record r2 = new Record();
        r2.setId(4);
        r2.setClientId(2);
        r2.setMasterId(2);
        r2.setClientFirstName("Galina");
        r2.setClientLastName("Kolesnikova");
        r2.setMasterFirstName("Alina");
        r2.setMasterLastName("Yarusova");

        r2.setFinished(true);

        recordsDb.add(r1);
        recordsDb.add(r2);

        return recordsDb;
    }
}
