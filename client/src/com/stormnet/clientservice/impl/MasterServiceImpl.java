package com.stormnet.clientservice.impl;

import com.stormnet.ja.Master;
import com.stormnet.clientservice.PersonService;
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
import java.util.ArrayList;
import java.util.List;

public class MasterServiceImpl implements PersonService<Master> {


    public MasterServiceImpl() {

    }

    @Override
    public void save(Master person) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, 8848);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("save-master");
        jsonWriter.endObject();

        jsonWriter.key("parameters");
        jsonWriter.object();

        jsonWriter.key("lastName").value(person.getLastName());
        jsonWriter.key("firstName").value(person.getFirstName());
        jsonWriter.key("login").value(person.getLogin());
        jsonWriter.key("password").value(person.getPassword());
        jsonWriter.key("cabinet").value(person.getCabinet());

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
    public List<Master> loadAll() throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, 8848);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("get-all-masters");
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

        List<Master> allPersons = new ArrayList<>();
        if (code == 200) {

            JSONArray responseData = response.getJSONArray("response-ja");
            int length = responseData.length();

            for (int i = 0; i < length; i++) {
                JSONObject object = (JSONObject) responseData.get(i);

                String IdStr = (String) object.get("id");
                Integer id = Integer.valueOf(IdStr);

                String lastNameStr = (String) object.get("lastName");

                String firstNameStr = (String) object.get("firstName");

                String loginStr = (String) object.get("login");

                String passwordStr = (String) object.get("password");

                String cabinetStr = (String) object.get("cabinet");

                Master person = new Master();
                person.setId(id);
                person.setFirstName(firstNameStr);
                person.setLastName(lastNameStr);
                person.setLogin(loginStr);
                person.setPassword(passwordStr);
                person.setCabinet(cabinetStr);

                allPersons.add(person);
            }
        }
        writer.close();
        is.close();
        socket.close();
        return allPersons;
    }

    @Override
    public Master loadById(Integer id) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, 8848);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("get-master");
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

        Master person = new Master();

        if (code == 200) {

            JSONArray responseData = response.getJSONArray("response-ja");
            JSONObject object = (JSONObject) responseData.get(0);

            String lastNameStr = (String) object.get("lastName");

            String firstNameStr = (String) object.get("firstName");

            String loginStr = (String) object.get("login");

            String passwordStr = (String) object.get("password");

            String cabinetStr = (String) object.get("cabinet");

            person.setId(id);
            person.setFirstName(firstNameStr);
            person.setLastName(lastNameStr);
            person.setLogin(loginStr);
            person.setPassword(passwordStr);
            person.setCabinet(cabinetStr);

        }
        writer.close();
        is.close();
        socket.close();
        return person;
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
        jsonWriter.key("command-name").value("delete-master");
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
    public void update(Master person) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, 8848);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("update-master");
        jsonWriter.endObject();

        jsonWriter.key("parameters");
        jsonWriter.object();

        jsonWriter.key("id").value(person.getId().toString());
        jsonWriter.key("lastName").value(person.getLastName());
        jsonWriter.key("firstName").value(person.getFirstName());
        jsonWriter.key("login").value(person.getLogin());
        jsonWriter.key("password").value(person.getPassword());
        jsonWriter.key("cabinet").value(person.getCabinet());

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
    public Master loadPersonByLoginAndPassword(String login, String password) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, 8848);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("get-master-by-login-and-password");
        jsonWriter.endObject();

        jsonWriter.key("parameters");
        jsonWriter.object();

        jsonWriter.key("login").value(login);
        jsonWriter.key("password").value(password);

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

        Master person = new Master();

        if (code == 200) {

            JSONArray responseData = response.getJSONArray("response-ja");
            JSONObject object = (JSONObject) responseData.get(0);

            String idStr = (String) object.get("id");
            Integer id = Integer.valueOf(idStr);

            String lastNameStr = (String) object.get("lastName");

            String firstNameStr = (String) object.get("firstName");

            String loginStr = (String) object.get("login");

            String passwordStr = (String) object.get("password");

            String cabinetStr = (String) object.get("cabinet");

            person.setId(id);
            person.setFirstName(firstNameStr);
            person.setLastName(lastNameStr);
            person.setLogin(loginStr);
            person.setPassword(passwordStr);
            person.setCabinet(cabinetStr);

        }
        writer.close();
        is.close();
        socket.close();
        return person;
    }
}