package com.todolist.configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Haimov on 24/01/2018.
 * Helper which perform cookies operations
 *
 */
public final class CookieHelper {

    /**
     * Create Cookie
     * @param cookieName
     * @param cookiValue
     * @param cookiePath
     * @param response
     */
    public static void createCookie(String cookieName, String cookiValue, String cookiePath, HttpServletResponse response) {

        Cookie cookie = new Cookie(cookieName, cookiValue);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath(cookiePath);
        response.addCookie(cookie);
    }

    /**
     * Get Value of Cookie
     * @param equalsString name of needed cookie
     * @param request
     * @return cookieValue Value of Cookie
     */
    public static String getValueCookie(String equalsString, HttpServletRequest request) {

        String cookieValue = null;

        Cookie[] requestCookies = request.getCookies();
        if (requestCookies != null) {

            for (Cookie c : requestCookies) {
                if (c.getName().equals(equalsString)) {
                    cookieValue = c.getValue();
                }
            }
        }

        return cookieValue;
    }

    /**
     * Clear cookies and set it to null
     * @param equalsString name of needed cookie
     * @param request
     * @param response
     */
    public static void clearCookies(String equalsString, HttpServletRequest request, HttpServletResponse response) {

        Cookie[] requestCookies = request.getCookies();
        for (Cookie c : requestCookies) {
            if (c.getName().equals(equalsString)) {
                c.setValue(null);
                response.addCookie(c);

            }
        }
    }




}
