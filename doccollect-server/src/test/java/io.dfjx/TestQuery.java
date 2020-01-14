package io.dfjx;

import io.dfjx.core.util.common.WebClient;
import org.junit.Test;

public class TestQuery {

    @Test
    public void testQ() {
        String url = "https://www.baidu.com/";
        WebClient client = new WebClient();


        System.out.println(client.getString(url));
    }
}
