package com.softserveinc.ita.service;


import java.util.LinkedList;
import java.util.List;

public final class MokDataProvider<T> {

    private final LinkedList<T> dataList;

    private MokDataProvider(){
        dataList = new LinkedList<>();
    }

   private static MokDataProvider dataProvider;


    public static MokDataProvider getDataProvider() {
        if (null == dataProvider){
            dataProvider = new MokDataProvider();
            return dataProvider;

        }else {
            return dataProvider;
        }
    }

   public List<T> getDataList(){
       return dataList;
   }



}
