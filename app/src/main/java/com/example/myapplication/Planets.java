package com.example.myapplication;

public class Planets {

    private String name;
    private String company;
    private String location;
    private int size;
    private String auxdata;



public Planets(String n, String c, String l, int s, String a){
    name = n;
    location = l;
    company = c;
    size = s;
    auxdata = a;

    }

public Planets (String n){
    name = n;
    location="";
    size=-1;
    }

public String toString(){return name;}

 public String info(){
        String str=name;
        str+=" is located at ";
        str+=location+" ";
        str+=Integer.toString(size);
        str+=" km.";
        return str;
    }


public void setHeight(int newSize){
    size = newSize;
}
}