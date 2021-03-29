package com.stormnet.serverservice.dao.xml;

import com.stormnet.ja.Record;
import com.stormnet.serverservice.dao.RecordDao;
import com.stormnet.serverservice.dao.exception.InvalidIdException;
import com.stormnet.serverservice.dao.exception.ObjectAlreadyStoredException;
import com.stormnet.serverservice.dao.exception.ObjectNotFoundException;
import com.stormnet.serverservice.xml.XmlDb;
import com.stormnet.serverservice.xml.XmlDbTable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class XmlRecordDao implements RecordDao {

    @Override
    public List<Record> loadAll() {

        XmlDb xmlDb = XmlDb.getDb();
        Document recordDocument = xmlDb.getXmlDocumentForTable(XmlDbTable.Records);

        List<Record> allRecords = new ArrayList<>();
        NodeList recordTagList = recordDocument.getElementsByTagName("record");
        for (int i = 0; i < recordTagList.getLength(); i++) {
            Element recordTag = (Element) recordTagList.item(i);

            String idStr = recordTag.getAttribute("id");
            Integer recordId = Integer.valueOf(idStr);

            String clientIdStr = recordTag.getAttribute("clientId");
            Integer clientId = Integer.valueOf(clientIdStr);

            String isFinishedStr = recordTag.getAttribute("isFinished");
            Boolean isFinished = Boolean.valueOf(isFinishedStr);

            String masterLastNameStr = recordTag.getAttribute("masterLastName");

            String masterFirstNameStr = recordTag.getAttribute("masterFirstName");

            String clientLastNameStr = recordTag.getAttribute("clientLastName");

            String clientFirstNameStr = recordTag.getAttribute("clientFirstName");

            String dateStr = recordTag.getAttribute("date");
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            LocalDate date = LocalDate.parse(dateStr, formatter);

            String timeStr = recordTag.getAttribute("time");

            String masterIdStr = recordTag.getAttribute("masterId");
            Integer masterId = Integer.valueOf(masterIdStr);

            String cabinetStr = recordTag.getAttribute("cabinet");

            String ratingStr = recordTag.getAttribute("rating");

            String clientCommentStr = recordTag.getAttribute("clientComment");

            String masterCommentStr = recordTag.getAttribute("masterComment");

            Record record = new Record();
            record.setId(recordId);
            record.setFinished(isFinished);
            record.setClientId(clientId);
            record.setMasterLastName(masterLastNameStr);
            record.setMasterFirstName(masterFirstNameStr);
            record.setClientLastName(clientLastNameStr);
            record.setClientFirstName(clientFirstNameStr);
            record.setDate(date);
            record.setTime(timeStr);
            record.setMasterId(masterId);
            record.setCabinet(cabinetStr);
            record.setRating(ratingStr);

            allRecords.add(record);
        }

        return allRecords;
    }

    @Override
    public Record loadById(Integer recordId) {
        if (recordId == null) {
            throw new InvalidIdException();
        }

        List<Record> allRecords = loadAll();
        for (Record record : allRecords) {
            if (recordId.equals(record.getId())) {
                return record;
            }
        }

        throw new ObjectNotFoundException();
    }

    @Override
    public void save(Record record) {
        Integer recordId = record.getId();
        if (recordId != null) {
            throw new ObjectAlreadyStoredException();
        }

        XmlDb xmlDb = XmlDb.getDb();

        recordId = XmlDb.getDb().getNextIdForTable();
        record.setId(recordId);

        Document recordDocument = xmlDb.getXmlDocumentForTable(XmlDbTable.Records);

        Element recordTag = recordDocument.createElement("record");

        recordTag.setAttribute("id", recordId.toString());
        recordTag.setAttribute("clientId", record.getClientId().toString());
        recordTag.setAttribute("isFinished", "false");
        recordTag.setAttribute("masterLastName", record.getMasterLastName());
        recordTag.setAttribute("masterFirstName", record.getMasterFirstName());
        recordTag.setAttribute("clientLastName", record.getClientLastName());
        recordTag.setAttribute("clientFirstName", record.getClientFirstName());
        recordTag.setAttribute("masterId", record.getMasterId().toString());
        recordTag.setAttribute("date", record.getDate().toString());
        recordTag.setAttribute("time", record.getTime());
        recordTag.setAttribute("cabinet", record.getCabinet());
        recordTag.setAttribute("rating", record.getRating());

        recordDocument.getDocumentElement().appendChild(recordTag);

        xmlDb.saveDocumentForTable(XmlDbTable.Records);

    }

    @Override
    public void update(Record record) {
        Integer recordId = record.getId();
        if (recordId == null) {
            return;
        }
        Record storedRecord = loadById(recordId);
        if (storedRecord == null) {
            return;
        }

        XmlDb xmlDb = XmlDb.getDb();
        Document recordDocument = xmlDb.getXmlDocumentForTable(XmlDbTable.Records);

        Element recordTag = recordDocument.createElement("record");

        recordTag.setAttribute("id", recordId.toString());
        recordTag.setAttribute("clientId", record.getClientId().toString());
        recordTag.setAttribute("isFinished", record.getFinished().toString());
        recordTag.setAttribute("masterLastName", record.getMasterLastName());
        recordTag.setAttribute("masterFirstName", record.getMasterFirstName());
        recordTag.setAttribute("clientLastName", record.getClientLastName());
        recordTag.setAttribute("clientFirstName", record.getClientFirstName());
        recordTag.setAttribute("masterId", record.getMasterId().toString());
        recordTag.setAttribute("date", record.getDate().toString());
        recordTag.setAttribute("time", record.getTime());
        recordTag.setAttribute("cabinet", record.getCabinet());
        recordTag.setAttribute("rating", record.getRating());

        xmlDb.updateDocumentForTable(recordId, "record", recordTag, XmlDbTable.Records);

    }

    @Override
    public void delete(Integer recordId) {
        if (recordId == null) {
            return;
        }
        XmlDb xmlDb = XmlDb.getDb();
        xmlDb.deleteFromDocumentForTable(recordId, "record", XmlDbTable.Records);

    }
}