package com.rizom.excption;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomErrorController {

    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleUsernameNotFoundException(HttpServletRequest request, Exception ex, Model model) {
        model.addAttribute("errorMessage", "Kullanıcı adı bulunamadı.");
        return "error";
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String handleBadCredentialsException(HttpServletRequest request, Exception ex, Model model) {
        model.addAttribute("errorMessage", "Geçersiz kullanıcı adı veya şifre.");
        return "error";
    }

    // Diğer özel istisna işlemleri burada eklenebilir...

    @ExceptionHandler(Exception.class)
    public String handleGenericException(HttpServletRequest request, Exception ex, Model model) {
        model.addAttribute("errorMessage", "Bir hata oluştu.");
        return "error";
    }
}
