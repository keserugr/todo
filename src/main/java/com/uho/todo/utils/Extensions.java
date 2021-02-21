package com.uho.todo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Extensions {

    public static Date getDate(){
        //SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        //Date date = new Date(System.currentTimeMillis());
        //String format = formatter.format(date);
        return new Date(System.currentTimeMillis());

    }
}
