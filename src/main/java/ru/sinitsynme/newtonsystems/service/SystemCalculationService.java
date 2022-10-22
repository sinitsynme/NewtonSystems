package ru.sinitsynme.newtonsystems.service;

import ru.sinitsynme.newtonsystems.dto.EquationSystemRequestDto;
import ru.sinitsynme.newtonsystems.dto.EquationSystemResponseDto;

public interface SystemCalculationService {

    EquationSystemResponseDto solveSystem(EquationSystemRequestDto system);

}
