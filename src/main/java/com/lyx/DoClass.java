package com.lyx;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class DoClass
{
    private RestTemplate restTemplate;

    public DoClass()
    {
        restTemplate = new RestTemplate();
    }

    public CommonResult getCodeAndPicstr()
    {
        String code;
        String picStr;

        // 获得code
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(null, headers);
        JsonNode jsonNode = restTemplate.getForObject("https://unisrvs.17ebank.com:9117/mid-user-core/auth/dspcode", JsonNode.class);
        code = jsonNode.at("/data").asText(StrUtil.EMPTY);
        if (StrUtil.isBlank(code))
            return CommonResult.errorMsg("没有获得code");

        // 获得图片验证码
        HttpHeaders mHeaders = new HttpHeaders();
        mHeaders.setContentType(MediaType.APPLICATION_JSON);
        MultiValueMap<String,String> jsonData = new LinkedMultiValueMap<>();
        jsonData.add("imitate", "1");
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(jsonData, mHeaders);
        JsonNode picBase64 = restTemplate.postForObject("https://unisrvs.17ebank.com:9117/mid-user-core/kaptcha/image?code={code}", entity, JsonNode.class, code);
        String base64Suffix = picBase64.at("/data/img").asText(StrUtil.EMPTY);
        if (StrUtil.isBlank(base64Suffix))
            return CommonResult.errorMsg("难码的base64编码");
        String base64 = "data:image/png;base64," + base64Suffix;


        return CommonResult.successData(base64Suffix);
    }
}