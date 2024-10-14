package org.jeecg.modules.api;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class CardUtil {




    public static String sign(SortedMap<String, String> params) {
        String privateKey = "ea729ae5fb29bcae175b94af4df53473";
        List<String> keys = new ArrayList<String>(params.keySet());
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = String.valueOf(params.get(key));

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        prestr = prestr + privateKey;
        return DigestUtils.md5Hex(prestr);
    }

    public static String parseCardNo(String paramStr) {
        String cardNo = "";
        if (StringUtils.isEmpty(paramStr)) {
            return "电子卡号为空！";
        }
        String cardNoAndTimeStr = null;
        try {
            if (paramStr.length() > 34) {
                if (paramStr.length() > 48) {// 密文96位
                    cardNoAndTimeStr = CryptoUtil.decode(paramStr);
                } else {// 密文48位
                    cardNoAndTimeStr = CryptoUtil.decryptCFB(paramStr);
                }
            } else {
                if (paramStr.indexOf("$") > 0) {// 不加密，输出原码
                    cardNoAndTimeStr = paramStr.replace("$", "&");
                } else {// 【自主研发】混淆算法：解密
                    cardNoAndTimeStr = decryptHX(paramStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("电子卡号有误！");
        }
        if (ObjectUtils.isEmpty(cardNoAndTimeStr) || !cardNoAndTimeStr.contains("&")) {
            throw new RuntimeException("电子卡号有误！");
        }
        String[] cardNoAndTimeArr = cardNoAndTimeStr.split("&");
        if (cardNoAndTimeArr.length < 2) {
            throw new RuntimeException("电子卡号有误！");
        }
        String time = cardNoAndTimeArr[1];
        //从redis获取电子卡过期时间
        String outTimeStr = "86400000";

        Long outTime = Long.valueOf(outTimeStr);
        if (!ObjectUtils.isEmpty(time) && System.currentTimeMillis() - Long.valueOf(time) > outTime) {
            throw new RuntimeException("电子卡号已过期，请刷新后重试！");
        }
        cardNo = cardNoAndTimeArr[0];

        return cardNo;
    }


    public static void main(String[] args) {
        String cardNo = "6cb9c6a1eec85acb7319eb53fa567dda8d8c5f9ec9e831e4a995c478854e1de20955fe3484fdb16c0b1c4039dcbcee22";
        String txtTime = System.currentTimeMillis() + "";
        String merNo = "207";
        SortedMap<String, String> params = new TreeMap<>();
        params.put("txTime", txtTime);
        params.put("merNo", merNo);
        params.put("cardNo", cardNo);
        String sign = sign(params);
        StringBuffer sbf = new StringBuffer();
        sbf.append("<req>");
        sbf.append("<txTime>").append(txtTime).append("</txTime>");
        sbf.append("<merNo>").append(merNo).append("</merNo>");
        sbf.append("<cardNo>").append(cardNo).append("</cardNo>");
        sbf.append("<sign>").append(sign).append("</sign>");
        sbf.append("</req>");
        String param = sbf.toString();
        System.out.println(param);

        String cardNo1 = parseCardNo(cardNo);
        System.out.println(cardNo1);

    }


    public static String decryptHX(String content) {
        try {
            String eCardNo = content.substring(19, 20).toUpperCase() + content.substring(4, 5).toUpperCase()
                    + content.substring(28, 31) + content.substring(13, 16) + content.substring(20, 23) +
                    content.substring(5, 8) + content.substring(11, 13) + content.substring(26, 28) + content.substring(31);
            String timeStr = content.substring(23, 26) + content.substring(16, 19) + content.substring(8, 11) + content.substring(0, 4);
            return eCardNo + "&" + timeStr;
        } catch (Exception ex) {
            throw new RuntimeException("decryptHX error:" + ex);
        }
    }
}
