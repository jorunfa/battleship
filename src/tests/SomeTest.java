package tests;

import junit.framework.Assert;
import android.test.AndroidTestCase;


public class SomeTest extends AndroidTestCase {

    public void testSomething() throws Throwable {
       Assert.assertEquals(1 + 1, 2);
    }

    public void testSomethingElse() throws Throwable {
       Assert.assertNotSame(1 + 1, 3);
    }
}
