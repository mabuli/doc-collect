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

//        String str = "{\"Pops\":{\"pop\":{\"ct\":{\"cnt\":1},\"p\":{\"name\":\"张德印\",\"age\":63,\"gender_cd\":\"男性\",\"nation_cd\":\"汉族\",\"iden_docu_type_cd\":\"身份证\",\"iden_docu_num\":110224195701214010,\"hous_reg_loc_dtl_addr\":\"南成庄\",\"curr_resi_dtl_addr\":\"南成庄\"}}}}";
//        JSONObject json = JSON.parseObject(str);
//        return R.ok().put("data", json);
    }

    @PostMapping("/post")
    public R post(@RequestBody Map<String, String> params) {
        WebClient client = new WebClient();
        JSONObject json = client.post3("", params);
        return R.ok().put("data", json);
    }
}
