package ru.sinitsynme.newtonsystems.service;

import org.springframework.stereotype.Service;
import ru.sinitsynme.newtonsystems.dto.EquationSystemRequestDto;
import ru.sinitsynme.newtonsystems.dto.EquationSystemResponseDto;

public interface CalculationService {

    EquationSystemResponseDto solveSystem(EquationSystemRequestDto system);

}
