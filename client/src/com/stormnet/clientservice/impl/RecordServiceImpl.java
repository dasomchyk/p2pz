package com.stormnet.clientservice.impl;

import com.stormnet.ja.Record;
import com.stormnet.clientservice.RecordService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RecordServiceImpl implements RecordService {

    public RecordServiceImpl() {

    }

    @Override
    public void save(Record record) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, 8848);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("save-record");
        jsonWriter.endObject();

        jsonWriter.key("parameters");
        jsonWriter.object();

        jsonWriter.key("isFinished").value(String.valueOf(record.getFinished()));
        jsonWriter.key("clientId").value(String.valueOf(record.getClientId()));
        jsonWriter.key("masterLastName").value(record.getMasterLastName());
        jsonWriter.key("masterFirstName").value(record.getMasterFirstName());
        jsonWriter.key("clientLastName").value(record.getClientLastName());
        jsonWriter.key("clientFirstName").value(record.getClientFirstName());
        jsonWriter.key("date").value((record.getDate().toString()));
        jsonWriter.key("time").value(record.getTime());
        jsonWriter.key("masterId").value(String.valueOf(record.getMasterId()));
        jsonWriter.key("cabinet").value(record.getCabinet());
        jsonWriter.key("rating").value(record.getRating());

        jsonWriter.endObject();
        jsonWriter.endObject();

        writer.flush();

        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);

        writer.close();
        is.close();
        socket.close();
    }


    @Override
    public List<Record> loadAll() throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, 8848);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("get-all-records");
        jsonWriter.endObject();

        jsonWriter.key("parameters");
        jsonWriter.object();

        jsonWriter.endObject();
        jsonWriter.endObject();

        writer.flush();

        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);

        List<Record> allRecords = new ArrayList<>();
        if (code == 200) {

            JSONArray responseData = response.getJSONArray("response-ja");
            int length = responseData.length();

            for (int i = 0; i < length; i++) {
                JSONObject object = (JSONObject) responseData.get(i);

                String IdStr = (String) object.get("id");
                Integer id = Integer.valueOf(IdStr);

                String clientIdStr = (String) object.get("clientId");
                Integer clientId = Integer.valueOf(clientIdStr);

                String isFinishedStr = (String) object.get("isFinished");
                Boolean isFinished = Boolean.valueOf(isFinishedStr);

                String masterLastNameStr = (String) object.get("masterLastName");

                String masterFirstNameStr = object.getString("masterFirstName");

                String clientLastNameStr = (String) object.get("clientLastName");

                String clientFirstNameStr = (String) object.get("clientFirstName");

                String dateStr = (String) object.get("date");
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
                LocalDate date = LocalDate.parse(dateStr, formatter);

                String timeStr = (String) object.get("time");

                String masterIdStr = (String) object.get("masterId");
                Integer masterId = Integer.valueOf(masterIdStr);

                String cabinetStr = (String) object.get("cabinet");

                String ratingStr = (String) object.get("rating");

                Record record = new Record();
                record.setId(id);
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
        }
        writer.close();
        is.close();
        socket.close();
        return allRecords;
    }

    @Override
    public Record loadById(Integer id) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, 8848);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("get-record");
        jsonWriter.endObject();

        jsonWriter.key("parameters");
        jsonWriter.object();

        jsonWriter.key("id").value(String.valueOf(id));

        jsonWriter.endObject();

        jsonWriter.endObject();

        writer.flush();

        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);

        Record record = new Record();

        if (code == 200) {

            JSONArray responseData = response.getJSONArray("response-ja");
            JSONObject object = (JSONObject) responseData.get(0);

            String clientIdStr = (String) object.get("clientId");
            Integer clientId = Integer.valueOf(clientIdStr);

            String isFinishedStr = (String) object.get("isFinished");
            Boolean isFinished = Boolean.valueOf(isFinishedStr);

            String masterLastNameStr = (String) object.get("masterLastName");

            String masterFirstNameStr = (String) object.get("masterFirstName");

            String clientLastNameStr = (String) object.get("clientLastName");

            String clientFirstNameStr = (String) object.get("clientFirstName");

            String dateStr = (String) object.get("date");
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            LocalDate date = LocalDate.parse(dateStr, formatter);

            String timeStr = (String) object.get("time");

            String masterIdStr = (String) object.get("masterId");
            Integer masterId = Integer.valueOf(masterIdStr);

            String cabinetStr = (String) object.get("cabinet");

            String ratingStr = (String) object.get("rating");

            record.setId(id);
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

        }
        writer.close();
        is.close();
        socket.close();
        return record;
    }

    @Override
    public void delete(Integer id) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, 8848);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("delete-record");
        jsonWriter.endObject();

        jsonWriter.key("parameters");
        jsonWriter.object();

        jsonWriter.key("id").value(String.valueOf(id));

        jsonWriter.endObject();

        jsonWriter.endObject();

        writer.flush();

        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);

        writer.close();
        is.close();
        socket.close();
    }

    @Override
    public void update(Record record) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, 8848);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("update-record");
        jsonWriter.endObject();

        jsonWriter.key("parameters");
        jsonWriter.object();

        jsonWriter.key("id").value(String.valueOf(record.getId()));
        jsonWriter.key("isFinished").value(String.valueOf(record.getFinished()));
        jsonWriter.key("clientId").value(String.valueOf(record.getClientId()));
        jsonWriter.key("masterLastName").value(record.getMasterLastName());
        jsonWriter.key("masterFirstName").value(record.getMasterFirstName());
        jsonWriter.key("clientLastName").value(record.getClientLastName());
        jsonWriter.key("clientFirstName").value(record.getClientFirstName());
        jsonWriter.key("date").value((record.getDate().toString()));
        jsonWriter.key("time").value(record.getTime());
        jsonWriter.key("masterId").value(String.valueOf(record.getMasterId()));
        jsonWriter.key("cabinet").value(record.getCabinet());
        jsonWriter.key("rating").value(record.getRating());

        jsonWriter.endObject();
        jsonWriter.endObject();

        writer.flush();

        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);

        writer.close();
        is.close();
        socket.close();
    }
}
