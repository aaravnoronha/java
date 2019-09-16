package com.epi_java;

public class Main
{
    public static char abra(char ch)
    {
        // System.out.printf("ascii ch = %d\n", (int)ch);
        char cadabra = '?';
        cadabra = (char)((int)ch % 65 + 97);
        return cadabra;
    }
    public static void main(String[] args)
    {
        String str = "cat";
        System.out.println(str);
        str =  "" + abra('A');
        // System.out.println(str);
        str = str + abra('H');
        System.out.println(str);
    }
}
