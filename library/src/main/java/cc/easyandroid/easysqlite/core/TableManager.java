package cc.easyandroid.easysqlite.core;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.ArrayMap;

import java.util.Locale;
import java.util.Set;

import cc.easyandroid.easysqlite.EasySqliteLog;
import cc.easyandroid.easysqlite.SQLiteDelegate;


/**
 * Created by cgpllx on 2016/6/12.
 */
public class TableManager {
    static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS %1$s ( " + //
            SQLiteDelegate.ID + " PRIMARY KEY," +  //
            SQLiteDelegate.GSONSTRING + " NOT NULL," +//
            SQLiteDelegate.CREATEDTIME + " NOT NULL)";

    static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS %1$s";

    public <T extends EasyDbObject> void createTable(String tableName, Class<T> clazz) throws SQLException {
        tableInfoMap.put(tableName, clazz);
        System.out.println("easyandroid createTable=" + tableInfoMap);
    }

    //对象和表名的映射关系在这里
    private static final ArrayMap<String, Class> tableInfoMap = new ArrayMap<>();// 所有表放在这里 key是对象的name

    public <T extends EasyDbObject> void dropTable(SQLiteDatabase database, String tableName, Class<T> clazz) throws SQLException {
        String sql = String.format(Locale.CHINA, DROP_TABLE_SQL, tableName);
        database.execSQL(sql);
    }

    public void execute(SQLiteDatabase database) {
        Set<String> tabnames = tableInfoMap.keySet();
        EasySqliteLog.d("easyandroid tab map: %1$s", tableInfoMap);
        for (String tableName : tabnames) {
            String sql = String.format(Locale.CHINA, CREATE_TABLE_SQL, tableName);
            database.execSQL(sql);
            EasySqliteLog.d("easyandroid execute sql : %1$s", sql);
        }
    }

    public Class getTableMappingClass(String tableName) {
        if (tableName == null) {
            throw new IllegalArgumentException("table info get error,because the tablename is null");
        }
        Class tableMappingClass = tableInfoMap.get(tableName);
        if (tableMappingClass == null) {// 没有就创建表
            throw new IllegalArgumentException("tabName is not EXISTS");
        }
        return tableMappingClass;
    }
}
