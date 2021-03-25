EasySqlite
==========

![License MIT](https://img.shields.io/badge/Apache-2.0-brightgreen)



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

## Usage
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
            tableManager.createTable("a2", Tab.class);
            tableManager.createTable("a3", Tab.class);
            tableManager.createTable("a4", Tab.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion,TableManager tableManager) {
        try {
            tableManager.dropTable(db, "a1", Tab.class);
            tableManager.dropTable(db, "a2", Tab.class);
            tableManager.dropTable(db, "a3", Tab.class);
            tableManager.dropTable(db, "a4", Tab.class);
            onCreate(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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


