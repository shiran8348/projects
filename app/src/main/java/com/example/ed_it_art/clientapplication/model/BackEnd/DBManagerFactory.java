package com.example.ed_it_art.clientapplication.model.BackEnd;

import com.example.ed_it_art.clientapplication.model.dataSource.MySQL_DB;

/**
 * Created by ed-it-art on 21/01/2018.
 */

public class DBManagerFactory {

    static DBmanager manager = null;

    public static DBmanager GetDB() {
        if (manager == null)
            manager = new MySQL_DB();
        //   manager = new MySQL_DBManager();
        return manager;
    }
}
