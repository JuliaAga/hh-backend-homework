package ru.hh.school.dto.hhDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HhEmplDto {
    private Integer id;
    private String name;

    public HhEmplDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public HhEmplDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
