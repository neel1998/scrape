package com.example.game_new.scrape;

/**
 * Created by Admin on 17-05-2018.
 */

public class data {
    private String mtitle;
    private String mvalue;
    public data(String title,String value)
    {
        mtitle=title;
        mvalue=value;
    }
    public String getTitle(){return mtitle;}
    public String getValue(){return mvalue;}
}
