package com.jiading.modules.xcx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.jiading.common.util.WebTool;
import com.jiading.modules.xcx.service.XMainService;
//import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.Map;

@SuppressWarnings("all")
@Controller
@RequestMapping("/xcx/main/")
public class XMainController {


    @Autowired
    private XMainService xMainService;


    /*获取微信信息*/

    @RequestMapping("wxinfo")
    @ResponseBody
    public JSONObject wxinfo(@RequestBody Map<String, Object> paramsMap) {
        String AppletUrl = "https://api.weixin.qq.com/sns/jscode2session";

        String code = String.valueOf(paramsMap.get("code"));
        System.out.println("小程序传的code----" + code);
        String appid = "wx6852efb29605435e";
        String secret = "e21b18915ff166755fc5e33acb99c0b4";

        String cs = "?appid=" + appid + "&secret=" + secret + "&js_code=" + code
                + "&grant_type=authorization_code&time=" + System.currentTimeMillis();
        JSONObject json = JSONObject.parseObject(WebTool.callHtmlGet2(AppletUrl + cs));
        return json;
        //JSONObject json = new JSONObject();
        //String openid = "oVqQd5CyN5VoyqEuXDAZfyjwRo_Y";
        //json.put("openid", openid);
        //return json;
    }


    // 客户新增
    @RequestMapping("ifexistuser")
    @ResponseBody
    public Map ifExistClient(HttpServletRequest request, HttpServletResponse response, @RequestBody Map paramsMap,
                             HttpSession session) {
        Map outmap = xMainService.ifExistUser(paramsMap);
        return outmap;
    }


    /**
     * 解密小程序用户手机号码
     *
     * @param map
     * @return
     */
    @RequestMapping("getphone")
    @ResponseBody
    public JSONObject getPhoneNumber(@RequestParam Map map) {
        System.out.println("解密数据--" + map);
        try {
            byte[] data = com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode((String) map.get("encryptedData")); // 被加密的数据
            byte[] aseKey = com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode((String) map.get("session_key")); // 加密秘钥
            byte[] ivData = com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode((String) map.get("iv")); // 偏移量
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            Key sKeySpec = new SecretKeySpec(aseKey, "AES");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIv(ivData));// 初始化

            byte[] result = cipher.doFinal(data);

            return JSON.parseObject(new String(result, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject object = new JSONObject();
            object.put("err", "授权失败，请重新进入");
            System.out.println(object);
            return object;
        }

    }


    public String decryptJsUserInfo(String encryptedData, String iv, String sessionKey) {
        try {
            byte[] data = Base64.decode(encryptedData);
            byte[] aseKey = Base64.decode(sessionKey);
            byte[] ivData = Base64.decode(iv);
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            Key sKeySpec = new SecretKeySpec(aseKey, "AES");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIv(ivData));// 初始化

            byte[] result = cipher.doFinal(data);
            return new String(result, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public AlgorithmParameters generateIv(byte[] iv) throws Exception {
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }


}
