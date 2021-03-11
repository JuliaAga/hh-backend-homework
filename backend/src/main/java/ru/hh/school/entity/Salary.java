package ru.hh.school.entity;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "sal_to")
    private Integer to;
    @Column(name = "sal_from")
    private Integer from;
    private String currency;
    private Boolean gross;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getGross() {
        return gross;
    }

    public void setGross(Boolean gross) {
        this.gross = gross;
    }
}
