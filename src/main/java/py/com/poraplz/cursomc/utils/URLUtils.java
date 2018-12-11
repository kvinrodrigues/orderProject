package py.com.poraplz.cursomc.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URLUtils {

    public static String decodeParam(String text){
        try {
            return URLDecoder.decode(text.trim(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Word decode error: " + URLUtils.class.getCanonicalName());
            return "";
        }
    }
}
