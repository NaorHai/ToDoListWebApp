package com.todolist.configuration;

/**
 * Created by Haimov on 19/02/2018.
 */

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * Cookie Helper for creating and editing user cookies
 * */
public final class CookieHelper {

    private final static Logger logger = Logger.getLogger(CookieHelper.class);
    private static Gson gson = new Gson();
    private final static int MAX_AGE_SECONDS = 24 * 60 * 60;

    /**
     * Create Cookie
     * @param cookieName
     * @param cookieValue
     * @param cookiePath
     * @param response
     */
    public static void createCookie(String cookieName, String cookieValue, String cookiePath, HttpServletResponse response) {
        Cookie cookie = null;
        try {
            cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, "UTF-8"));
            cookie.setMaxAge(MAX_AGE_SECONDS);
            cookie.setPath(cookiePath);
            response.addCookie(cookie);
            logger.debug("added new cookie: " + cookieName + " with val " + cookieValue);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("error with saving user items in cookie");
            logger.error(e.getMessage());
        }
    }

    /**
     * Get cookie val by name
     * @param cookieName name of needed cookie
     * @param request
     * @return cookieValue Value of Cookie
     */
    public static String getCookieValueByName(String cookieName, HttpServletRequest request) {
        String cookieValue = null;
        Cookie[] requestCookies = request.getCookies();
        if (requestCookies != null) {
            for (Cookie c : requestCookies) {
                if (c.getName().equals(cookieName)) {
                    try {
                        cookieValue = URLDecoder.decode(c.getValue(), "UTF-8");
                        logger.debug("got cookie: " + cookieName + " with val " + cookieValue);
                        return cookieValue;

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "";
    }

    /**
     * clear cookie by name
     * @param cookieName name of needed cookie
     * @param request
     * @param response
     */
    public static void clearCookieByName(String cookieName, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] requestCookies = request.getCookies();
        for (Cookie c : requestCookies) {
            if (c.getName().equals(cookieName)) {
                c.setValue(null);
                response.addCookie(c);
                logger.debug("cleared cookie: " + cookieName);
            }
        }
    }

    /**
     * clear cookie by name
     * @param list of elements
     */
    public static String Jsonfy(List<?> list) {
        return gson.toJson(list);
    }
}
