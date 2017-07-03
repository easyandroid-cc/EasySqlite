package cc.easyandroid.easysqlitedemo;


import cc.easyandroid.easysqlite.core.EasyDbObject;

/**
 * Created by cgp on 2016/6/12.
 */
public class Tab implements EasyDbObject {
    @Override
    public String buildKeyColumn() {
        return xxx;
    }

    String xxx="d";

    public String getXxx() {
        return xxx;
    }

    public void setXxx(String xxx) {
        this.xxx = xxx;
    }
}
