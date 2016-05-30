package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by vlady on 30.05.16.
 */

public class PointTests {

    @Test

    public void testDistance(){
        Point p1 = new Point(2,1);
        Point p2 = new Point(2,6);
        Assert.assertEquals(p1.distance(p2),5.0);
        Assert.assertTrue(p2.distance(p1) == p1.distance(p2));
        Assert.assertNotEquals(p1.distance(p2), 5);
        Assert.assertNotNull(p1.distance(p2));
    }
}
