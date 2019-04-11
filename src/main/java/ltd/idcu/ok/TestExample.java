package ltd.idcu.ok;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestExample {

    @Test
    public void get() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.v2ex.com/api/members/show.json?id=1")
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "437cca81-98b1-4627-aa7a-65d8713700cb")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }

    @Test
    public void post() throws IOException {
        String username = "admin";
        String password = "admin";

        //client 相当于浏览器
        OkHttpClient client = new OkHttpClient();
        //媒体类型
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        //body
        RequestBody body = RequestBody.create(mediaType, "username="+username+"&password="+password);
        //请求
        Request request = new Request.Builder()
                //地址
                .url("http://192.168.66.17:8088/api/admin/login?_allow_anonymous=true")
                //请求方式 + body
                .post(body)
                //headers
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Accept-Language", "zh-CN")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Pragma", "no-cache")
                .addHeader("Connection", "keep-alive")
                .addHeader("Origin", "http://192.168.66.18:10002")
                .addHeader("Referer", "http://192.168.66.18:10002/")
                .addHeader("Authorization", "Basic d2ViOg==")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
                .addHeader("Host", "192.168.66.17:8088")
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "f055117d-c940-4450-9317-6259db9a5850")
                .build();

        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
//        System.out.println(response.headers().toString());

        Res res = JSON.parseObject(response.body().string(), Res.class);

        Assert.assertEquals(true, res.success);

    }
}
