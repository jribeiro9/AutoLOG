package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chamados")
public class Chamado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "decricao")
	private String descricao;
		
	@Column(name = "precoDasPcs")
	private int precoDasPcs;

	@Column(name = "maoDeObra")
	private int maoDeObra;	
	
	@Column(name = "estadoDoServico")
	private String estadoDoServico;
	
	@ManyToOne
	@JoinColumn(name="veiculo_id")
	private Veiculo veiculo;
	
	@ManyToOne
	@JoinColumn(name="mecanico_id")
	private Mecanico mecanico;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPrecoDasPcs() {
		return precoDasPcs;
	}

	public void setPrecoDasPcs(int precoDasPcs) {
		this.precoDasPcs = precoDasPcs;
	}

	public int getMaoDeObra() {
		return maoDeObra;
	}

	public void setMaoDeObra(int maoDeObra) {
		this.maoDeObra = maoDeObra;
	}

	public String getEstadoDoServico() {
		return estadoDoServico;
	}

	public void setEstadoDoServico(String estadoDoServico) {
		this.estadoDoServico = estadoDoServico;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	public void setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	

}