package io.dfjx.module.query.controller;

import com.alibaba.fastjson.JSONObject;
import io.dfjx.common.utils.R;
import io.dfjx.core.util.common.WebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/proxy")
public class ProxyController {
    @Value("${remote.request.url}")
    private String req;
    @GetMapping("/get")
    public R get(@RequestParam("url") String url) throws Exception {
        WebClient client = new WebClient();
        client.addHead("Authorization", "Bearer 7bee4ad2-1d1f-36e0-b9ab-625a7e495de2");
        JSONObject json = client.getJSONObject(req+url.replace("&amp;","&"));
        if (json == null) {
            return R.error("server error");
        }
        return R.ok().put("data", json);
    }

    @PostMapping("/post")
    public R post(@RequestBody Map<String, String> params) {
        WebClient client = new WebClient();
        JSONObject json = client.post3("", params);
        return R.ok().put("data", json);
    }
}
