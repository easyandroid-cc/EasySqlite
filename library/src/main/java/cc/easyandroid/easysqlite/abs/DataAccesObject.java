package cc.easyandroid.easysqlite.abs;

import android.database.Cursor;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cc.easyandroid.easysqlite.core.EasyDbObject;


public interface DataAccesObject<T extends EasyDbObject> {
    void insert(T dto) throws Exception;

    void insertAll(ArrayList<T> arrayList) throws Exception;

    void update(T dto);

    T findById(String id, Type type) throws Exception;

    T findById(String id) throws Exception;

    boolean delete(String id) throws Exception;

    boolean deleteAll() throws Exception;

    ArrayList<T> findAllFromTabName(String orderBy, Type type) throws Exception;

    ArrayList<T> findAllFromTabName(String orderBy) throws Exception;

    Cursor findAllCursor(String orderBy);

    void addOnDataChangedListener(OnDataChangedListener listener);

    void removeOnDataChangedListener(OnDataChangedListener listener);

    void removeAllOnDataChangedListener();

    interface OnDataChangedListener {
        void onChanged();
    }
}
