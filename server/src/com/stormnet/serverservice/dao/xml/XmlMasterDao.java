package com.stormnet.serverservice.dao.xml;

import com.stormnet.ja.Master;
import com.stormnet.serverservice.dao.PersonDao;
import com.stormnet.serverservice.dao.exception.InvalidIdException;
import com.stormnet.serverservice.dao.exception.ObjectAlreadyStoredException;
import com.stormnet.serverservice.dao.exception.ObjectNotFoundException;
import com.stormnet.serverservice.xml.XmlDb;
import com.stormnet.serverservice.xml.XmlDbTable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class XmlMasterDao implements PersonDao<Master> {

    @Override
    public Master loadPersonByLoginAndPassword(String login, String password) {
        List<Master> all = loadAll();

        for (Master master : all) {
            if (master.getLogin().equals(login) && master.getPassword().equals(password)) {
                return master;
            }
        }

        return null;
    }

    @Override
    public List<Master> loadAll() {
        XmlDb xmlDb = XmlDb.getDb();
        Document document = xmlDb.getXmlDocumentForTable(XmlDbTable.Masters);

        List<Master> all = new ArrayList<>();
        NodeList tagList = document.getElementsByTagName("person");
        for (int i = 0; i < tagList.getLength(); i++) {
            Element tag = (Element) tagList.item(i);

            String idStr = tag.getAttribute("id");
            Integer id = Integer.valueOf(idStr);

            String firstNameStr = tag.getAttribute("firstName");

            String passwordStr = tag.getAttribute("password");

            String loginStr = tag.getAttribute("login");

            String lastNameStr = tag.getAttribute("lastName");

            String cabinetStr = tag.getAttribute("cabinet");

            Master person = new Master();
            person.setId(id);
            person.setFirstName(firstNameStr);
            person.setLastName(lastNameStr);
            person.setPassword(passwordStr);
            person.setLogin(loginStr);
            person.setCabinet(cabinetStr);

            all.add(person);
        }

        return all;
    }

    @Override
    public Master loadById(Integer id) {
        if (id == null) {
            throw new InvalidIdException();
        }

        List<Master> all = loadAll();
        for (Master master : all) {
            if (id.equals(master.getId())) {
                return master;
            }
        }

        throw new ObjectNotFoundException();
    }

    @Override
    public void save(Master object) {
        Integer id = object.getId();
        if (id != null) {
            throw new ObjectAlreadyStoredException();
        }

        XmlDb xmlDb = XmlDb.getDb();

        id = XmlDb.getDb().getNextIdForTable();
        object.setId(id);

        Document document = xmlDb.getXmlDocumentForTable(XmlDbTable.Masters);

        Element tag = document.createElement("person");

        tag.setAttribute("id", Integer.toString(id));
        tag.setAttribute("firstName", object.getFirstName());
        tag.setAttribute("lastName", object.getLastName());
        tag.setAttribute("password", object.getPassword());
        tag.setAttribute("login", object.getLogin());
        tag.setAttribute("cabinet", object.getCabinet());

        document.getDocumentElement().appendChild(tag);

        xmlDb.saveDocumentForTable(XmlDbTable.Masters);

    }

    @Override
    public void update(Master object) {

        Integer id = object.getId();
        if (id == null) {
            return;
        }
        Master stored = loadById(id);
        if (stored == null) {
            return;
        }

        XmlDb xmlDb = XmlDb.getDb();
        Document document = xmlDb.getXmlDocumentForTable(XmlDbTable.Masters);

        Element tag = document.createElement("person");

        tag.setAttribute("id", Integer.toString(id));
        tag.setAttribute("firstName", object.getFirstName());
        tag.setAttribute("lastName", object.getLastName());
        tag.setAttribute("password", object.getPassword());
        tag.setAttribute("login", object.getLogin());
        tag.setAttribute("cabinet", object.getCabinet());

        document.getDocumentElement().appendChild(tag);

        xmlDb.updateDocumentForTable(id, "person", tag, XmlDbTable.Masters);

    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            return;
        }
        XmlDb xmlDb = XmlDb.getDb();
        xmlDb.deleteFromDocumentForTable(id, "person", XmlDbTable.Masters);

    }
}


