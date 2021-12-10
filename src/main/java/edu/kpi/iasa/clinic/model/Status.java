package edu.kpi.iasa.clinic.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "status")
@ToString
public class Status {

    @Id
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    @Column(name="closed")
    private Boolean isClosed;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) { isClosed = closed; }

    public Long getId() {
        return this.id;
    }
}

