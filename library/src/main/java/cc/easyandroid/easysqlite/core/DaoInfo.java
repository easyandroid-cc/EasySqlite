package cc.easyandroid.easysqlite.core;

import android.support.v4.util.ArrayMap;

import cc.easyandroid.easysqlite.EasySqliteLog;
import cc.easyandroid.easysqlite.abs.DataAccesObject;

/**
 * Created by Administrator on 2016/6/12.
 */
public class DaoInfo {
    private static final ArrayMap<String, DaoInfo> daoMap = new ArrayMap<>();// 所有表放在这里 key是tabname
    private String tableName;// 表名，
    private DataAccesObject<?> dao;// 表名，

    /**
     * key是数据库表名
     */

    public static DaoInfo addDaoInfo(String tabName, DataAccesObject<?> dao) {
        if (dao == null)
            throw new IllegalArgumentException("table info add error,because the DataAccesObject is null");
        if (tabName == null) {
            throw new IllegalArgumentException("table info add error,because the tabName is null");
        }
        DaoInfo daoInfo = daoMap.get(tabName);
        if (daoInfo == null) {// 没有就创建表
            daoInfo = new DaoInfo();
            daoInfo.setTableName(tabName);
            daoInfo.setDao(dao);
            daoMap.put(tabName, daoInfo);
        } else {
            EasySqliteLog.d("EasyAndroid", "DaoInfo is  exists");
        }
        return daoInfo;
    }

    public static DaoInfo getDaoInfo(String tableName) {
        if (tableName == null) {
            throw new IllegalArgumentException("table info get error,because the tablename is null");
        }
        DaoInfo daoInfo = daoMap.get(tableName);

        return daoInfo;
    }

    public static DaoInfo removeTableInfo(String tabName, DataAccesObject<?> dao) {
        if (tabName == null) {
            throw new IllegalArgumentException("table info remove error,because the tablename is null");
        }
        DaoInfo tableInfo = daoMap.remove(tabName);

        return tableInfo;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public DataAccesObject<?> getDao() {
        return dao;
    }

    public void setDao(DataAccesObject<?> dao) {
        this.dao = dao;
    }
}
