package io.dfjx.module.query.controller;

import com.alibaba.fastjson.JSONObject;
import io.dfjx.common.utils.R;
import io.dfjx.core.util.common.WebClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    @GetMapping("/get")
    public R get(@RequestParam("url") String url) {
        WebClient client = new WebClient();
        JSONObject json = client.getJSONObject(url.replace("&amp;", "&"));
        return R.ok().put("data", json);
    }

    @PostMapping("/post")
    public R post(@RequestBody Map<String, String> params) {
        WebClient client = new WebClient();
        JSONObject json = client.post3("", params);
        return R.ok().put("data", json);
    }
}
