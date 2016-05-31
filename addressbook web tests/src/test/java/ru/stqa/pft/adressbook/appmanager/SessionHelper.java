package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by vlady on 31.05.16.
 */
public class SessionHelper {
    private FirefoxDriver wd;

    public SessionHelper(FirefoxDriver wd) {
        this.wd = wd;
    }
}
