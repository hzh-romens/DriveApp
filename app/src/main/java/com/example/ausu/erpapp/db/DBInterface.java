package com.example.ausu.erpapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by HZH on 2016/4/12.
 */
public class DBInterface {
    public static DBInterface dbInterface = null;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context = null;

    public static synchronized DBInterface instance() {
        if (dbInterface == null) {
            synchronized (DBInterface.class) {
                if (dbInterface == null) {
                    dbInterface = new DBInterface();
                }
            }
        }
        return dbInterface;
    }

    private DBInterface() {

    }

    //上下文环境的更新
    //环境变量的clear
    public void close() {
        if (openHelper != null) {
            openHelper.close();
            openHelper = null;
            context = null;
        }
    }

    public void initDbHelp(Context ctx) {
        context = ctx;
        close();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(ctx, "test.db", null);
        this.openHelper = helper;
    }

    public DaoSession openReadableDb() {
        isInitOk();
        SQLiteDatabase db = openHelper.getReadableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        return daoSession;
    }

    public DaoSession openWriteableDb() {
        isInitOk();
        SQLiteDatabase db = openHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        return daoSession;
    }

    private void isInitOk() {
        if (openHelper == null) {
            //FileLog.e("DBInterface", "DBInterface#isInit not success or start,cause by openHelper is null");
            // 抛出异常 todo
            throw new RuntimeException("DBInterface#isInit not success or start,cause by openHelper is null");
        }
    }

    //接下来是数据库的相关操作
}
