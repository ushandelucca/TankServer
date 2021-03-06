package de.oo2.m.server.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class.
 */
public class ResponseErrorTest {

    @Test
    public void testConstructor() throws Exception {
        ResponseError responseError = new ResponseError("", "");

        Assert.assertNotNull(responseError);
    }

    @Test
    public void testGetMessage() throws Exception {
        ResponseError responseError = new ResponseError("Hello %s!", "World");

        Assert.assertEquals("Hello World!", responseError.getMessage());
    }

    @Test
    public void testGetStadandardMessage() throws Exception {
        ResponseError responseError = new ResponseError(null, "World");

        Assert.assertEquals("Error while processing the request!", responseError.getMessage());
    }
}
