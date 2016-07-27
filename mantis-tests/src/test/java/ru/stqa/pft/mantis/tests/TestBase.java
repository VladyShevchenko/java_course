package ru.stqa.pft.mantis.tests;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php","config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.back","config_inc.php");
        app.stop();
    }

    public boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException, ServiceException {
        Issue issue = app.soap().getIssue(issueId);
        String status = issue.getStatus();
        return !status.equals("closed");
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue: " + issueId);
        }
    }


}