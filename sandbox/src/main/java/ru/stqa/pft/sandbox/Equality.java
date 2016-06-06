package ru.stqa.pft.sandbox;

/**
 * Created by vlady on 06.06.16.
 */
public class Equality {

    public static void main (String[] args) {
        String s1 = "Firefox";
        String s2 = "Firefox";

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }
}
