package com.example.ausu.erpapp.db;

import android.database.sqlite.SQLiteDatabase;



import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by HZH on 2016/4/11.
 */
public class DaoSession extends AbstractDaoSession {
    //需要使用的数据库
    private final TestDao testDao;
    private final DaoConfig testDaoConfig;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> daoConfigMap) {
        super(db);
        testDaoConfig = daoConfigMap.get(TestDao.class).clone();
        testDaoConfig.initIdentityScope(type);
        testDao = new TestDao(testDaoConfig, this);
    //    registerDao(Test.class, testDao);
    }

    public void clear() {
        testDaoConfig.getIdentityScope().clear();
    }

    public TestDao getTestDao() {
        return testDao;
    }
}
