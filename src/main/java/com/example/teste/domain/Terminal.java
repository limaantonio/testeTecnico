package com.example.teste.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "info")
public class Terminal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(nullable = false)
	private Integer logic;

	@Column(nullable = false)
	private String serial;

	@Column(nullable = false)
	private String model;

	private Integer sam;

	private String ptid;

	private Integer plat;

	@Column(nullable = false)
	private String version;

	private Integer mxr;

	private Integer mxf;

	private String verfm;

	public Terminal() {

	}

	public Terminal(Long id, Integer logic, String serial, String model) {
		this.id = id;
		this.logic = logic;
		this.serial = serial;
		this.model = model;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Terminal terminal = (Terminal) o;

		return logic != null ? logic.equals(terminal.logic) : terminal.logic == null;
	}

	@Override
	public int hashCode() {
		return logic != null ? logic.hashCode() : 0;
	}
}