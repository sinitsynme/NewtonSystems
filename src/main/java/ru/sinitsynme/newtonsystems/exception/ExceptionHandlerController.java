package ru.sinitsynme.newtonsystems.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({ArithmeticException.class, IllegalArgumentException.class, ErrorRateException.class})
    public String inconsistentSystemException(Exception ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("error", ex.getMessage());
        return "redirect:/";
    }

}
