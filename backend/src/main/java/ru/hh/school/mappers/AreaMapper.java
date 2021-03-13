package ru.hh.school.mappers;

import ru.hh.school.dto.AreaDto;
import ru.hh.school.entity.Area;

public class AreaMapper {
    public static Area areaDtoToArea (AreaDto areaDto) {
        return new Area(areaDto.getId(), areaDto.getName());
    }
}
