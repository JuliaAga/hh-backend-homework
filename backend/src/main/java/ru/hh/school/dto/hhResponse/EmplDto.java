package ru.hh.school.dto.hhResponse;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmplDto {
    private Integer id;
    private String name;

    public EmplDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public EmplDto() {
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