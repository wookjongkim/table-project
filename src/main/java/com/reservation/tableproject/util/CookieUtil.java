package com.reservation.tableproject.util;

import javax.servlet.http.Cookie;

public class CookieUtil {

    public static Cookie generateCookie(String token){
        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }
}
