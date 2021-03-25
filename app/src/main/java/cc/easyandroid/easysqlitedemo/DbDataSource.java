package cc.easyandroid.easysqlitedemo;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cc.easyandroid.easysqlite.core.EasyDbObject;


/**
 * Created by cgpllx on 2016/8/16.
 */
public interface DbDataSource {


    <T extends EasyDbObject> void getAll(String tabName, Type type,  DbDataSource.LoadDatasCallback<T> callback);

    <T extends EasyDbObject> void getSingle(String tabName, Type type, String key,  DbDataSource.LoadDataCallback<T> callback);

    <T extends EasyDbObject> boolean deleteAll(String tabName);

    <T extends EasyDbObject> boolean deleteById(String tabName, String id);

    <T extends EasyDbObject> boolean insert(String tabName, T dto);

    <T extends EasyDbObject> boolean insertAll(String tabName, ArrayList<T> arrayList);

    interface LoadDatasCallback<T> {

        void onDatasLoaded(ArrayList<T> datas);

        void onDataNotAvailable();
    }
    interface LoadDataCallback<T> {

        void onDatasLoaded(T datas);

        void onDataNotAvailable();
    }


}
