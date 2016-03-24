package com.nataniel.api;
import org.junit.Test;

/**
 * Created by nataniel.neto on 21/03/2016.
 */
public class MailBoxLayerIntegrationTest {

    @Test
    public void testIntegration() throws Exception {
        MailBoxLayerIntegration test = new MailBoxLayerIntegration("nataniel.neto@s4bdigital.net");
        System.out.println(test.validate());
    }
}
