package com.example.myapplication;

import java.io.Serializable;

public class Planets {

    private String name;
    private String type;
    private String location;
    private int diameter;
    private String textInfo;



public Planets(String n, String c, String l, int s, String a){
    name = n;
    location = l;
    type = c;
    diameter = s;
   textInfo = a;

    }

public Planets (String n){
    name = n;
    location="Solar System";
    diameter=-1;
    }

public String toString(){return name;}

 public String info(){
        String str=name;
        str+=" is located at ";
        str+=location+" ";
        str+=Integer.toString(diameter);
        str+=" km.";
        str+=textInfo;
        return str;
    }

    public void setName(String n){    /*} , String l, String c, int s, String a) {*/
        name = n;
       // location = l;
        //type = c;
        //diameter = s;
        //textInfo = a;

    }
    public String getName() {
        return name;
    }

    public void setLocation(String l) {
        location=l;
    }
    public String getLocation() {
        return location;
    }

    public void setType(String c) { type = c; }
    public String getType() {
        return type;
    }

    public void setDiameter(int s) { diameter = s; }
    public int getDiameter() {
        return diameter;
    }

    public void setTextInfo(String a) {textInfo = a;}
    public String getTextInfo() { return textInfo;}
public void setHeight(int newSize){
    diameter = newSize;
}
}