package com.example.teste.dtos;

import com.example.teste.domain.Terminal;

public class TerminalDTO {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Integer logic;
    private String serial;
    private String model;

    public TerminalDTO(){

    }

    public TerminalDTO(Terminal obj) {
       id = obj.getId();
        logic = obj.getLogic();
        serial = obj.getSerial();
        model = obj.getModel();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLogic() {
        return logic;
    }

    public void setLogic(Integer logic) {
        this.logic = logic;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
