package ru.hh.school.mappers;

import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.SalaryDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Salary;

public class SalaryMapper {

    public static Salary salaryDtoToSalary (SalaryDto salaryDto){
        return new Salary(salaryDto.getTo(), salaryDto.getFrom(), salaryDto.getCurrency(), salaryDto.getGross());
    }
}
