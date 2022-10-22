package ru.sinitsynme.newtonsystems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sinitsynme.newtonsystems.dto.EquationSystemRequestDto;
import ru.sinitsynme.newtonsystems.dto.EquationSystemResponseDto;
import ru.sinitsynme.newtonsystems.service.SystemCalculationService;

@Controller
@RequestMapping("/")
public class CalculationController {

    private final SystemCalculationService systemCalculationService;

    @Autowired
    public CalculationController(SystemCalculationService systemCalculationService) {
        this.systemCalculationService = systemCalculationService;
    }

    @GetMapping("/")
    public ModelAndView getMainPage() {
        ModelAndView modelAndView = new ModelAndView("input");
        modelAndView.addObject("requestDto", new EquationSystemRequestDto());

        return modelAndView;
    }

    @GetMapping("/solve")
    public ModelAndView solveSystem(EquationSystemRequestDto system){
        ModelAndView modelAndView = new ModelAndView("solution");

        EquationSystemResponseDto systemResponseDto =  systemCalculationService.solveSystem(system);
        modelAndView.addObject("x", systemResponseDto.getX());
        modelAndView.addObject("y", systemResponseDto.getY());

        return modelAndView;
    }



}
