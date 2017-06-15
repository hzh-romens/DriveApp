package com.example.ausu.erpapp.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;


import com.example.ausu.erpapp.model.Test;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by HZH on 2016/4/11.
 */
public class TestDao extends AbstractDao<Test, Long> {
    public static final String TABLENAME = "TEST";
    private DaoSession daoSession;

    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", true, "_id");
        public final static Property Sex = new Property(2, String.class, "sex", true, "_id");
    }

    public TestDao(DaoConfig config) {
        super(config);
    }

    public TestDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "'TEST' (" +
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'NAME' TEXT NOT NULL" +
                " 'SEX' TEXT NOT NULL);"); // 1: name

    }

    public static void dropTable(SQLiteDatabase db, boolean ifNotExists) {
        String sql = "DROP TABLE " + (ifNotExists ? "IF EXISTS " : "") + "'TEST'";
        db.execSQL(sql);
    }

    public static void updateTable(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTable(db, true);
        createTable(db, false);
    }

    @Override

    protected Test readEntity(Cursor cursor, int offset) {
        Test test = new Test();
        test.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        test.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        test.setSex(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        return test;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, Test entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSex(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Test entity) {
        stmt.clearBindings();
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(3, sex);
        }
    }

    @Override
    protected Long updateKeyAfterInsert(Test entity, long rowId) {
        return entity.getId();
    }

    @Override
    protected Long getKey(Test entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }
}
