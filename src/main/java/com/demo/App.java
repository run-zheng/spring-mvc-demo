package com.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        String define = "萨德里克风机房份额$hello$来看个垃圾无法额$world$";
        //"萨德里克风机房份额(\\{.*\\})来看个垃圾无法额(\\{.*\\})";

        String response = "萨德里克风机房份额sldfke  fwlkge kasdfs来看个垃圾无法额收代理费咖啡个";
        extractKV(define, response, "$", "$");

        define = "{\"message\":\"$message$\",\"data\":{\"resultInfo\":{\"message\":\"$message1$\",\"suc\":true,\"fail\":false,\"code\":$code1$}},\"code\":$code$}";
        response = "{\"message\":\"操作成功\",\"data\":{\"resultInfo\":{\"message\":\"短信验证成功\",\"suc\":true,\"fail\":false,\"code\":1}},\"code\":1}";
        extractKV(define, response, "$", "$");


        define = "{\"message\":\"&message&\",\"data\":{\"resultInfo\":{\"message\":\"&message1&\",\"suc\":true,\"fail\":false,\"code\":&code1&}},\"code\":&code&}";
        response = "{\"message\":\"操作成功\",\"data\":{\"resultInfo\":{\"message\":\"短信验证成功\",\"suc\":true,\"fail\":false,\"code\":1}},\"code\":1}";
        extractKV(define, response, "&", "&");

        define = "{\"message\":\"{message}\",\"data\":{\"resultInfo\":{\"message\":\"{message1}\",\"suc\":true,\"fail\":false,\"code\":{code1}}},\"code\":{code}}";
        response = "{\"message\":\"操作成功\",\"data\":{\"resultInfo\":{\"message\":\"短信验证成功\",\"suc\":true,\"fail\":false,\"code\":1}},\"code\":1}";
        extractKV(define, response, "{", "}");

        define = "{\"message\":\"|message|\",\"data\":{\"resultInfo\":{\"message\":\"|message1|\",\"suc\":true,\"fail\":false,\"code\":|code1|}},\"code\":|code|}";
        response = "{\"message\":\"操作成功\",\"data\":{\"resultInfo\":{\"message\":\"短信验证成功\",\"suc\":true,\"fail\":false,\"code\":1}},\"code\":1}";
        extractKV(define, response, "|", "|");


        define = "{\"message\":\"${message}\",\"data\":{\"resultInfo\":{\"message\":\"${message1}\",\"suc\":true,\"fail\":false,\"code\":${code1}}},\"code\":${code}}";
        response = "{\"message\":\"操作成功\",\"data\":{\"resultInfo\":{\"message\":\"短信验证成功\",\"suc\":true,\"fail\":false,\"code\":1}},\"code\":1}";
        extractKV(define, response, "${", "}");

    }
    private static String identityReg(String identity) {
        List<Character> chars = Lists.newArrayList('{', '}', '(', ')', '$', '!', '?', '[', ']', '|');
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ;i < identity.length(); i++) {
            sb.append(chars.contains(identity.charAt(i)) ? "\\" : "").append(identity.charAt(i));
        }
        return sb.toString();
    }
    private static void extractKV(String define, String response, String lfId, String rtId) {
        System.out.println("模板："+define);
        String leftIdentity = identityReg(lfId);
        String rightIdentity = identityReg(rtId);

        //String pattern = define.replaceAll("("+leftIdentity+".*?"+rightIdentity+")", "(.*)");
        String pattern = define.replaceAll("("+leftIdentity+"(((?!" + leftIdentity + ").)+?)"+rightIdentity+")", "(.*)");
        pattern = pattern.replaceAll("\\{", "\\\\{");
        pattern = pattern.replaceAll("\\}", "\\\\}");

        System.out.println("解析成正则表达式："+pattern);
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(define);
        System.out.println("匹配组数："+m.groupCount());

        List<String> groupList = Lists.newArrayList();
        Map<String, String> kv = Maps.newHashMap();
        while(m.find()){
            for(int i = 1 ;  i <= m.groupCount(); i++){
                System.out.println("组"+i+": "+m.group(i).replaceAll(leftIdentity+"|"+rightIdentity, ""));
                groupList.add(m.group(i).replaceAll(leftIdentity+"|"+rightIdentity, ""));
            }
        }

        Pattern p2 = Pattern.compile(pattern);
        Matcher m2 = p2.matcher(response);
        while(m2.find()){
            for (int i = 1; i <= m2.groupCount() ; i++){
                String key = groupList.get(i-1);
                kv.put(key, m2.group(i));
            }
        }
        System.out.println("组list: "+JSON.toJSONString(groupList));
        System.out.println("结果map: "+JSON.toJSONString(kv));
        System.out.println();
    }

}
