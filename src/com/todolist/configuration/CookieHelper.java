package com.todolist.configuration;

/**
 * Created by Haimov on 19/02/2018.
 */

import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie Helper for creating and editing user cookies
 */
public final class CookieHelper {

    private final static Logger logger = Logger.getLogger(CookieHelper.class);
    private final static int MAX_AGE_SECONDS = 24 * 60 * 60;

    private CookieHelper() {
    }

    /**
     * Create Cookie
     *
     * @param cookieName
     * @param cookieValue
     * @param cookiePath
     * @param response
     */
    public static void createCookie(String cookieName, String cookieValue, String cookiePath, HttpServletResponse response) {
        Cookie cookie;
        cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(MAX_AGE_SECONDS);
        cookie.setPath(cookiePath);
        response.addCookie(cookie);
        logger.debug("added new cookie: " + cookieName + " with val " + cookieValue);
    }

    /**
     * Get cookie val by name
     *
     * @param cookieName name of needed cookie
     * @param request
     * @return cookieValue Value of Cookie
     */
    public static String getCookieValueByName(String cookieName, HttpServletRequest request) {
        String cookieValue;
        Cookie[] requestCookies = request.getCookies();
        if (requestCookies != null) {
            for (Cookie c : requestCookies) {
                if (c.getName().equals(cookieName)) {
                    cookieValue = c.getValue();
                    logger.debug("got cookie: " + cookieName + " with val " + cookieValue);
                    return cookieValue;
                }
            }
        }
        return "";
    }

    /**
     * clear cookie by name
     *
     * @param cookieName name of needed cookie
     * @param request
     * @param response
     */
    public static void clearCookieByName(String cookieName, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] requestCookies = request.getCookies();
        for (Cookie c : requestCookies) {
            if (c.getName().equals(cookieName)) {
                c.setValue("");
                c.setMaxAge(0);
                c.setPath("/");
                response.addCookie(c);
                logger.debug("cleared cookie: " + cookieName);
            }
        }
    }

    /**
     * clear all user cookies
     *
     * @param request
     * @param response
     */
    public static void clearAllUserCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] requestCookies = request.getCookies();
        for (Cookie c : requestCookies) {
            c.setValue("");
            c.setMaxAge(0);
            c.setPath("/");
            response.addCookie(c);
            logger.debug("cleared user cookies");
        }
    }
}
