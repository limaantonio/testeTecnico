package com.example.teste.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "terminal")
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
		this.sam = sam;
		this.ptid = ptid;
		this.plat = plat;
		this.version = version;
		this.mxr = mxr;
		this.mxf = mxf;
		this.verfm = verfm;
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

	public Integer getSam() {
		return sam;
	}

	public void setSam(Integer sam) {
		this.sam = sam;
	}

	public String getPtid() {
		return ptid;
	}

	public void setPtid(String ptid) {
		this.ptid = ptid;
	}

	public Integer getPlat() {
		return plat;
	}

	public void setPlat(Integer plat) {
		this.plat = plat;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getMxr() {
		return mxr;
	}

	public void setMxr(Integer mxr) {
		this.mxr = mxr;
	}

	public Integer getMxf() {
		return mxf;
	}

	public void setMxf(Integer mxf) {
		this.mxf = mxf;
	}

	public String getVerfm() {
		return verfm;
	}

	public void setVerfm(String verfm) {
		this.verfm = verfm;
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