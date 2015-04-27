package com.m4thg33k.testing.reference;

/**
 * Created by M4thG33k on 4/18/2015.
 */
public class WhiteListPylon {

    public static  String[] whiteList;
    public static  int sizeOfList;

    public WhiteListPylon()
    {
        sizeOfList = 2;
        whiteList = new String[sizeOfList];
        whiteList[0] = "minecraft.redstone";
        whiteList[1] = "minecraft.coal";
    }
}
