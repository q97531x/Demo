package com.example.component.demo;

import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by bo.wei on 2017/3/2.
 */

public class Data implements Serializable{
    private String name;
    private String tel;

    public Data(String name, String tel) {
        this.name = name;
        this.tel = tel;
        Log.d("DataInit","init");
    }

    public String getName() {
        if(name == null){
            name = "";
        }
        return name;
    }

    public void setName(String name) {
        if(name!=null) {
            this.name = name;
        }
    }

    public String getTel() {
        if(tel == null){
            tel = "";
        }
        return tel;
    }

    public void setTel(String tel) {
        if(tel!=null) {
            this.tel = tel;
        }
    }
}
