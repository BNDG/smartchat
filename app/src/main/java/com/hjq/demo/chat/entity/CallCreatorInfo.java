package com.hjq.demo.chat.entity;

import com.hjq.demo.utils.JsonParser;

/**
 * @author r
 * @date 2024/7/17
 * @description Brief description of the file content.
 */
public class CallCreatorInfo {
    public String creatorJid;
    public String creatorName;
    public String callServiceUrl;

    public static String create(String userId, String userNickName, String callServiceUrl) {
        CallCreatorInfo creatorInfo = new CallCreatorInfo();
        creatorInfo.creatorJid = userId;
        creatorInfo.creatorName = userNickName;
        creatorInfo.callServiceUrl = callServiceUrl;
        return JsonParser.serializeToJson(creatorInfo);
    }
}
