package com.stormnet.serverservice.dao;

import com.stormnet.ja.Person;

public interface PersonDao<T extends Person> extends BaseDao<T> {


    T loadPersonByLoginAndPassword(String login, String password);
}
