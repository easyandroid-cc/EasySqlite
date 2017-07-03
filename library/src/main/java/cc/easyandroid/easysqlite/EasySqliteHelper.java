package cc.easyandroid.easysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cc.easyandroid.easysqlite.abs.DataAccesObject;
import cc.easyandroid.easysqlite.core.DaoInfo;
import cc.easyandroid.easysqlite.core.EasyDbObject;
import cc.easyandroid.easysqlite.core.TableManager;


/**
 * Created by cgpllx on 2016/6/12.
 */
public abstract class EasySqliteHelper extends SQLiteOpenHelper {
    private final TableManager tableManager;

    private volatile boolean isCreated = false;

    public EasySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int newVersion) {
        super(context, name, factory, newVersion);//如果是升级onUpgrade方法会先执行
        tableManager = new TableManager();
        getWritableDatabase();
    }

    /**
     * TableManager.createTable(db, "", Tab.class);
     *
     * @param db SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        if (!isCreated) {
            onCreate(db, tableManager);
            isCreated = true;
        }
        EasySqliteLog.d("easyandroid onCreate is : %1$s", isCreated);
        tableManager.execute(db);
    }

    public abstract void onCreate(SQLiteDatabase db, TableManager tableManager);

    /**
     * TableManager.dropTable(db, "", Tab.class);
     *
     * @param db         SQLiteDatabase
     * @param oldVersion oldVersion
     * @param newVersion newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion, tableManager);
    }

    public abstract void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion, TableManager tableManager);

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!isCreated) {
            onCreate(db, tableManager);
            isCreated = true;
        }
        EasySqliteLog.d("easyandroid onCreate is : %1$s", isCreated);
    }

    /**
     * DataAccesObject
     *
     * @param tableName table name
     * @param <T>       class
     * @return DataAccesObject
     */
    public <T extends EasyDbObject> DataAccesObject<T> getDao(String tableName) {
        DaoInfo daoInfo = DaoInfo.getDaoInfo(tableName);
        DataAccesObject<T> sqLiteDelegate = null;
        if (daoInfo == null) {
            sqLiteDelegate = new SQLiteDelegate(this, tableName, tableManager.getTableMappingClass(tableName));
            DaoInfo.addDaoInfo(tableName, sqLiteDelegate);
        } else {
            try {
                sqLiteDelegate = (DataAccesObject<T>) daoInfo.getDao();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sqLiteDelegate;
    }

}
