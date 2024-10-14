package org.jeecg.modules.api;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.ObjectUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CryptoUtil {

    public static String decode(Object obj) {
        if (ObjectUtils.isEmpty(obj)) {
            return null;
        }
        try {



            String dd = decode16(null, obj.toString());
            System.out.println("decode:" + dd);
            return decode16(null, obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static String decode16(String key, String str) {
        try {
            return new String(obtainDecode(key, Hex.decodeHex(str.toCharArray())));
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] obtainDecode(String key, byte[] str) {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            Key key1 = obtainKey(key);
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key1);
            byteFina = cipher.doFinal(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    public static Key obtainKey(String key) {
        if (key == null) {
            return  obtainKey("#^&*(@!o9o*8IIwhp@itnw");
        }
        KeyGenerator generator = null;
        try {
            generator = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());

            generator.init(128, random);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Key key1 = generator.generateKey();
        generator = null;

        return key1;
    }

    public static String decryptCFB(String content) {
        if (ObjectUtils.isEmpty(content)) {
            return null;
        }
        try {
            Cipher aesECB = Cipher.getInstance("AES/CFB/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec("gjyxyjyldzjzkxyh".getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec("gjyxyjyldzjzkxyh".getBytes());
            aesECB.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] result = aesECB.doFinal(Base64.decodeBase64(content));
            String AES_decode = new String(result, "utf-8");
            return AES_decode;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("卡号解析出错,请重新刷卡");
        }
    }
}
