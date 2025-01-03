package com.bndg.smack.utils;

import android.provider.Settings;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bndg.smack.SmartCommHelper;
import com.bndg.smack.enums.SmartContentType;

/**
 * @author r
 * @date 2024/9/29
 * @description Brief description of the file content.
 */
public class OtherUtil {
    public static String getFileExtension(String filename) {
        if (!TextUtils.isEmpty(filename)) {
            int dot = filename.lastIndexOf('.');
            if (dot > -1 && dot < filename.length() - 1) {
                return filename.substring(dot + 1);
            }
        }
        return "";
    }

    public static String IMAGE_REGEX = "(?i)\\.(jpg|jpeg|png|gif|bmp|webp)$";
    public static String VIDEO_REGEX = "(?i)\\.(mp4|mkv|avi|mov|flv|wmv|webm|3gp|mpeg|mpg|ts|rmvb)$";

    public static boolean matcherUrl(String urlString, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(urlString);
        return matcher.find();
    }

    public static String getMessageTypeByUrl(String url) {
        if (matcherUrl(url, IMAGE_REGEX)) {
            return SmartContentType.IMAGE;
        } else if (matcherUrl(url, VIDEO_REGEX)) {
            return SmartContentType.TEXT;
        }
        return SmartContentType.TEXT;
    }

    public static String getAndroidId(){
        return Settings.System.getString(
                SmartCommHelper.getInstance().getApplication().getContentResolver(),
                Settings.Secure.ANDROID_ID
        );
    }
}
