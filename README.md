EasySqlite
==========

![License MIT](https://img.shields.io/badge/Apache-2.0-brightgreen)


## 说明
EasySqlite是非常轻量级的安卓数据库，只有400多行代码，在安卓中对数据存储量不太大时使用非常方便，比如 搜索关键字的历史记录，用户信息 等等

## Installation

Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
         jcenter()
    }
}
```


Add the dependency
```
dependencies {
    implementation 'cc.easyandroid:EasySqlite:1.3.0'
}
```

## How To Use
To create database
```java
public class SimpleSqlite extends EasySqliteHelper {
    static final int VERSION = 24;//版本
    static final String DBNAME = "EasyAndroidDB";

    public SimpleSqlite(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db,TableManager tableManager) {
        try {
            tableManager.createTable("a1", Tab.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion,TableManager tableManager) {
        try {
            tableManager.dropTable(db, "a1", Tab.class);
            onCreate(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

```java
//实现数据库的增删改查
 

public class DbRepository implements DbDataSource {
    public final SimpleSqlite simpleSqlite;

    public DbRepository(Context context) {
        simpleSqlite = new SimpleSqlite(context);
    }


    @Override
    public <T extends EasyDbObject> void getAll(String tabName, Type type, LoadDatasCallback<T> callback) {
        DataAccesObject<T> dataAccesObject = simpleSqlite.getDao(tabName);
        try {
            if(type==null){
                callback.onDatasLoaded(dataAccesObject.findAllFromTabName("DESC"));
            }else{
                callback.onDatasLoaded(dataAccesObject.findAllFromTabName("DESC",type));
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        callback.onDataNotAvailable();
    }

    @Override
    public <T extends EasyDbObject> void getSingle(String tabName, Type type, String key, LoadDataCallback<T> callback) {
        DataAccesObject<T> dataAccesObject = simpleSqlite.getDao(tabName);
        try {
            if(type==null){
                callback.onDatasLoaded(dataAccesObject.findById(key));
            }else{
                callback.onDatasLoaded(dataAccesObject.findById(key,type));
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        callback.onDataNotAvailable();
    }

    @Override
    public <T extends EasyDbObject> boolean deleteAll(String tabName) {
        DataAccesObject<T> dataAccesObject = simpleSqlite.getDao(tabName);
        try {
            return dataAccesObject.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public <T extends EasyDbObject> boolean deleteById(String tabName, String id) {
        DataAccesObject<T> dataAccesObject = simpleSqlite.getDao(tabName);
        try {
            return dataAccesObject.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public <T extends EasyDbObject> boolean insert(String tabName, T dto) {
        DataAccesObject<T> dataAccesObject = simpleSqlite.getDao(tabName);
        try {
            dataAccesObject.insert(dto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public <T extends EasyDbObject> boolean insertAll(String tabName, ArrayList<T> arrayList) {
        DataAccesObject<T> dataAccesObject = simpleSqlite.getDao(tabName);
        try {
            dataAccesObject.insertAll(arrayList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
```

License
-------

```
Copyright 2019 Square, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


