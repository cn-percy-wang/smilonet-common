///////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2012 smilonet.
// All rights reserved
///////////////////////////////////////////////////////////////////////////////////////////////////
package com.smilonet.common.utils;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 字符串处理公共类
 * 
 * @author wanglong(a)smilonet.com
 * @version 1.0
 * @since 1.0
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private StringUtils() {
    }

    public static void main(String[] args) throws Exception {
        // Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        // String encoded = encoder.encodePassword("33", "2008bjAY");
        // System.out.println(encoded);
        // byte[] aesEncoded = AESEncoder.encode("abcdefghijklmnopqrstuvw",
        // "1234567890abcdef");
        // String base64Encoded = Base64Encoder.encode(aesEncoded);
        // System.out.println(base64Encoded);
        //
        // byte[] base64 =
        // Base64Encoder.decode("LuD5WoRRcHq1tuEWZQHLH/AGSjn0YDXCS98PGTdwRDA=");
        // //String base64Decodeds = AESEncoder.parseByte2HexStr(base64);
        // // System.out.println(be.encode(base64));
        //
        // byte[] aesDecodedb = AESEncoder.decode(base64, "1234567890abcdef");
        // String aesDecodeds = new String(aesDecodedb);
        // System.out.println(aesDecodeds);
        //
        // System.out.println(MD5EncodeString("33", Operator.PASSWORD_SALT));
        // System.out.println(new
        // org.springframework.security.authentication.encoding.Md5PasswordEncoder().encodePassword("1",
        // "2008bjAY"));
        String hex = new Md5Hash("1", "00000000000000000000000000000001").toString();
        System.out.println(hex);
    }

    /**
     * 生成指定长度随机密码
     * 
     * @param length
     * @return
     */
    public static String randomPassword(int length) {
        String radStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuffer generateRandStr = new StringBuffer();
        Random rand = new Random(new Date().getTime());
        for (int i = 0; i < length; i++) {
            int randNum = rand.nextInt(radStr.length());
            generateRandStr.append(radStr.substring(randNum, randNum + 1));
        }
        return generateRandStr + "";
    }

    /**
     * Escape SQL tags, ' to ''; \ to \\.
     * 
     * @param input
     *            string to replace
     * @return string
     */
    public static String escapeSQLTags(String input) {
        if (input == null || input.length() == 0)
            return input;
        StringBuffer buf = new StringBuffer();
        char ch = ' ';
        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            if (ch == '\\')
                buf.append("\\\\");
            else if (ch == '\'')
                buf.append("\'\'");
            else
                buf.append(ch);
        }
        return buf.toString();
    }

    public static boolean isNumeric(String string) {

        return string.length() != 0 && org.apache.commons.lang3.StringUtils.isNumeric(string);

    }

    /**
     * 转换字符串为固定的格式,格式如果为空则转换为#,###
     * 
     * @param preValue
     *            被转换字符串
     * @param pattern
     *            转换格式
     * @return
     */
    public static String formatNumber(String preValue, String pattern) {

        String aftValue = "";
        if (isEmpty(pattern)) {
            pattern = "#,###";
        }
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        if (!isEmpty(preValue) && isNumeric(preValue)) {
            aftValue = decimalFormat.format(Long.parseLong(preValue));
        }
        return aftValue;
    }

    /**
     * 将字符串用制定字符在前面添加为指定长度
     * 
     * @param source
     * @param fillStr
     * @param needLength
     * @return
     */
    public static String formatStringLength(String source, String fillStr, int needLength) {
        if (isEmpty(source)) {
            return source;
        }

        int absNeedLength = Math.abs(needLength);

        String result = source;
        if (source.length() < absNeedLength) {
            for (int i = 0; i < absNeedLength - source.length(); i++) {
                if (needLength > 0) {
                    result = result + fillStr;
                } else {
                    result = fillStr + result;
                }
            }
        }
        if (source.length() > absNeedLength) {
            result = result.substring(0, absNeedLength);
        }
        return result;
    }

    /**
     * 将字符串用制定字符在前面添加为指定长度
     * 
     * @param source
     * @param fillChar
     * @param needLength
     * @return
     */
    public static String formatStringLength(String source, char fillChar, int needLength) {
        if (isEmpty(source)) {
            return source;
        }

        int absNeedLength = Math.abs(needLength);

        String result = source;
        if (source.length() < absNeedLength) {
            for (int i = 0; i < absNeedLength - source.length(); i++) {
                if (needLength > 0) {
                    result = result + fillChar;
                } else {
                    result = fillChar + result;
                }
            }
        }
        if (source.length() > absNeedLength) {
            result = result.substring(0, absNeedLength);
        }
        return result;
    }

    /**
     * 判断一个字符串是否能通过一个正则表达式
     * 
     * @param targetString
     * @param patternString
     * @return
     */
    public static boolean matchReg(String targetString, String patternString) {
        if (StringUtils.isEmpty(targetString) || StringUtils.isEmpty(patternString)) {
            return true;
        }
        Pattern pattern = null;
        try {
            pattern = Pattern.compile(patternString);
        } catch (PatternSyntaxException e) {
            System.out.println("pattern is wrong!");
        }
        Matcher matcher = pattern.matcher(targetString);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 转换字符串为boolean
     * 
     * @param param
     *            被转换字符串
     * @return boolean value, if param begin with(1,y,Y,t,T) return true, on
     *         exception return false.
     */
    public static boolean toBoolean(String param) {
        if (isEmpty(param))
            return false;
        switch (param.charAt(0)) {
        case '1':
        case 'y':
        case 'Y':
        case 't':
        case 'T':
            return true;
        }
        return false;
    }

    /**
     * 将字符串转换为double数字，如果出错则返回0
     * 
     * @param param
     *            string to parse
     * @return double value, on exception return 0.
     */
    public static Double parseDouble(String param) {
        Double d = null;
        try {
            d = Double.parseDouble(param);
        } catch (Exception e) {
            throw new RuntimeException("参数错误");
        }
        return d;
    }

    /**
     * 将字符串转换为int，如果出错则返回0
     * 
     * @param param
     *            string to parse
     * @return int value, on exception return 0.
     */
    public static Integer parseInt(String param) {
        Integer i = null;
        try {
            i = Integer.parseInt(param);
        } catch (Exception e) {
            throw new RuntimeException("参数错误");
        }
        return i;
    }

    /**
     * 将字符串分割为一个list
     * 
     * @param str
     *            被分割的字符串
     * @param delim
     *            分隔符
     * @return
     */
    // public static List<String> split(String str, String delim) {
    // List<String> splitList = null;
    // StringTokenizer st = null;
    //
    // if (str == null)
    // return splitList;
    //
    // if (delim != null)
    // st = new StringTokenizer(str, delim);
    // else
    // st = new StringTokenizer(str);
    //
    // if (st != null && st.hasMoreTokens()) {
    // splitList = new ArrayList<String>();
    //
    // while (st.hasMoreTokens())
    // splitList.add(st.nextToken());
    // }
    // return splitList;
    // }

    /**
     * 删除字符串中的重复字符
     * 
     * @param str
     *            被转换的字符串
     * @return
     */
    public static String strDistinct(String str) {
        String[] strArr = str.split(",");
        String strAim = ",";
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(""))
                continue;
            if (strAim.indexOf("," + strArr[i] + ",") == -1) {
                strAim = strAim + strArr[i] + ",";
            }
        }
        if (!strAim.equals(","))
            strAim = strAim.substring(1, strAim.length() - 1);
        else
            strAim = "";
        return strAim;
    }

    /**
     * 检查一个字符串是否为null或者“null”，如果是则范围空串，否则返回原字符串去除空格
     * 
     * @param str
     * @return
     */
    public static String toString(String str) {
        if (str == null)
            str = "";
        if (str.equals("null"))
            str = "";
        str = str.trim();
        return str;
    }

    /**
     * 检查是否具有SQL嵌入式攻击
     * 
     * @param sortColumns
     */
    public static boolean checkHasSqlInjection(String sortColumns) {
        if (sortColumns == null)
            return false;
        if (sortColumns.indexOf("'") >= 0 || sortColumns.indexOf("\\") >= 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为合法的排序字符串
     * 
     * @param sortColumnsString
     * @return
     */
    public static boolean isSortCoulmns(String sortColumnsString) {
        if (isBlank(sortColumnsString)) {
            return false;
        }
        return true;
    }

    /**
     * 将下划线方式的字符串替换为首位小写驼峰式
     * 
     * @param src
     * @return
     */
    public static String underlineToLowerCamel(String src) {
        if (isBlank(src) || !src.contains("_")) {
            return src;
        }
        StringBuffer sb = new StringBuffer();
        boolean isBehindUnderline = false;
        for (int i = 0; i < src.length(); i++) {
            char charItem = src.charAt(i);
            if (charItem == '_') {
                isBehindUnderline = true;
            } else {
                if (isBehindUnderline) {
                    sb.append(Character.toUpperCase(charItem));
                    isBehindUnderline = false;
                } else {
                    sb.append(charItem);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 将首位小写驼峰式字符串替换为下划线方式
     * 
     * @param src
     * @return
     */
    public static String lowerCamelToUnderline(String src) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < src.length(); i++) {
            char charItem = src.charAt(i);
            if (Character.isLetter(charItem) && Character.isUpperCase(charItem)) {
                sb.append('_');
                sb.append(Character.toLowerCase(charItem));
            } else {
                sb.append(charItem);
            }
        }
        return sb.toString();
    }

    public static String pwdEncrypt(String src) {

        String KEY = "!k@U3p~1%*";
        KEY = pad2Length(KEY, 16);
        SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(src.getBytes());
            return new String(new Base64().encodeToString(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String pad2Length(final String text, int padLen) {
        int len = text.length() % padLen;

        StringBuffer buf = new StringBuffer(text);
        for (int i = 0, n = padLen - len; i < n; ++i) {
            buf.append((char) n);
        }
        return buf.toString();
    }

    public static String mix(List<String> targets, String spliter) {
        if (targets == null || targets.size() == 0) {
            return null;
        }
        StringBuffer result = new StringBuffer();
        for (String target : targets) {
            if (result.length() > 0) {
                result.append(spliter);
            }
            result.append(target);
        }
        return result.toString();
    }

    public static String mix(List<String> targets, char spliter) {
        return mix(targets, String.valueOf(spliter));
    }
}