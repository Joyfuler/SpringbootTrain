package com.springboot3.blogMaking.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

public class CookieUtil {
    // 요청값 ( 이름 / 값 / 만료기간) 을 바탕으로 http 응답에 쿠키를 추가한다.
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge){
        Cookie cookie = new Cookie(name, value);
        // 쿠키는 이름 / 값 형태이므로...
        cookie.setPath("/");
        cookie.setMaxAge(maxAge); // 만료시간
        response.addCookie(cookie); // 쿠키를 넣어 보낸다.
    }

    // 쿠키 값을 이용하여 삭제한다. 다만 실제로 삭제하는 메소드가 없으므로, 만료 시간을 0으로 설정하여 생성 되지마자 만료 처리한다.
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name){
        Cookie[] cookies = request.getCookies(); // 모든 쿠키들을 가져와 그 이름을 가지고 삭제한다.
        if (cookies == null) {
            return;
        }

        for (Cookie c : cookies){
           if (name.equals(c.getName())){
               c.setValue("");
               c.setPath("/");
               c.setMaxAge(0); // 만료 시간을 0으로 설정.
               response.addCookie(c);
              }
          }
        }

    // 객체를 쿠키에 들어갈 값으로 변환한다.
    public static String serialize(Object obj) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }


    // 역직렬화?? 객체로 변환??
    public static <T> T deserialize(Cookie cookie, Class<T> cls){
        return cls.cast(
                SerializationUtils.deserialize(Base64.getUrlDecoder().decode(cookie.getValue()))
        );
    }
    // 제네릭이므로 단순한 값이 아닌 다양한 타입으로 변환이 가능.
}
