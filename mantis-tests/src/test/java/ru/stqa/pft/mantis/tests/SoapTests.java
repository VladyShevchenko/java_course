package ru.stqa.pft.mantis.tests;


import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase{
    @Test (enabled = false)
    public void testGetProjects () throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project: projects) {
            System.out.println(project.getName());
        }
    }

    @Test (enabled = false)
    public void testCreateIssue () throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue")
                .withDescription("Test description").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }

    @Test
    public void testIssueStatus() throws RemoteException, ServiceException, MalformedURLException {
        Issue issue = app.soap().getIssue(1);
        skipIfNotFixed(issue.getId());
    }
}
