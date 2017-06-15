package com.example.ausu.erpapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

/**
 * Created by HZH on 2016/4/11.
 */
public class DaoMaster extends AbstractDaoMaster {
    // private final DaoConfig testDaoConfig;

    //创建所有表
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        TestDao.createTable(db, ifNotExists);
    }

    //删除所有表
    public static void dropAllTables(SQLiteDatabase db, boolean ifNotExists) {
        TestDao.dropTable(db, ifNotExists);
    }

    //更新所有表
    public static void updateAllTables(SQLiteDatabase db, int oldVersion, int newVersion) {
        TestDao.updateTable(db, oldVersion, newVersion);
    }

    //创建所有表
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            createAllTables(db, false);
        }
    }

    public static class DevOpenHelper extends OpenHelper {

        public DevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            updateAllTables(db, oldVersion, newVersion);
        }
    }

    public static final int SCHEMA_VERSION = 72;

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(TestDao.class);
    }

    @Override
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    @Override
    public DaoSession newSession(IdentityScopeType type) {
        return null;
    }

}
