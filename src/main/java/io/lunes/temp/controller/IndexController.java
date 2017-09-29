package io.lunes.temp.controller;

import io.lunes.temp.model.FormView;
import io.lunes.temp.util.MailchimpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author wandyersilva
 */

@Controller
public class IndexController {

    @Autowired
    private MailchimpUtil mailchimpUtil;

    private Boolean isEnglish = false;

    @GetMapping("/")
    public ModelAndView index(FormView formView, String errorMessage, String successMessage) {
        return pt(formView, errorMessage, successMessage);
    }

    @GetMapping("/pt")
    public ModelAndView pt(FormView formView, String errorMessage, String successMessage) {
        isEnglish = false;
        ModelAndView mv = new ModelAndView("index-pt");
        mv.addObject("formView", formView);

        if (errorMessage != null) {
            mv.addObject("errorMessage", errorMessage);
        }

        if (successMessage != null) {
            mv.addObject("successMessage", successMessage);
        }
        return mv;
    }

    @GetMapping("/en")
    public ModelAndView en(FormView formView, String errorMessage, String successMessage) {
        isEnglish = true;
        ModelAndView mv = new ModelAndView("index-en");
        mv.addObject("formView", formView);

        if (errorMessage != null) {
            mv.addObject("errorMessage", errorMessage);
        }

        if (successMessage != null) {
            mv.addObject("successMessage", successMessage);
        }

        return mv;
    }


    @PostMapping("/submitEmail")
    public ModelAndView subscribeSubmit(@Valid FormView formView, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            if (isEnglish) {
                return en(formView, null, null);
            } else {
                pt(formView, null,null);
            }
        }

        String response;

        if (isEnglish) {
            response = mailchimpUtil.subscribe(formView.getEmail(), "en");
        } else {
            response = mailchimpUtil.subscribe(formView.getEmail(), "pt");
        }


        if (!response.equalsIgnoreCase("ok")) {
            if (isEnglish) {
                return en(formView, response, null);
            } else {
                return pt(formView, response,null);
            }
        }

        if(isEnglish) {
            return en(formView, null, "Subscription Confirmed.");
        } else {
            return pt(formView, null, "Inscrição Confirmada.");
        }

    }
}
