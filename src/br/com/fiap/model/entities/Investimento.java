package br.com.fiap.model.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Investimento {
	
	Integer id;
	Integer idUsuario;
	String descricaoInvestimento;
	Date dataInvestimento;
	String tipoInvestimento;
	BigDecimal valorInvestimento;
	
	public Investimento() {
	}
	
	public Investimento(Integer id, Integer idUsuario, String descricaoInvestimento, Date dataInvestimento,
			String tipoInvestimento, BigDecimal valorInvestimento) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.descricaoInvestimento = descricaoInvestimento;
		this.dataInvestimento = dataInvestimento;
		this.tipoInvestimento = tipoInvestimento;
		this.valorInvestimento = valorInvestimento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDescricaoInvestimento() {
		return descricaoInvestimento;
	}

	public void setDescricaoInvestimento(String descricaoInvestimento) {
		this.descricaoInvestimento = descricaoInvestimento;
	}

	public Date getDataInvestimento() {
		return dataInvestimento;
	}

	public void setDataInvestimento(Date dataInvestimento) {
		this.dataInvestimento = dataInvestimento;
	}

	public String getTipoInvestimento() {
		return tipoInvestimento;
	}

	public void setTipoInvestimento(String tipoInvestimento) {
		this.tipoInvestimento = tipoInvestimento;
	}

	public BigDecimal getValorInvestimento() {
		return valorInvestimento;
	}

	public void setValorInvestimento(BigDecimal valorInvestimento) {
		this.valorInvestimento = valorInvestimento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Investimento other = (Investimento) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Investimento [id=" + id + ", usuario=" + idUsuario + ", descricaoInvestimento=" + descricaoInvestimento
				+ ", dataInvestimento=" + dataInvestimento + ", tipoInvestimento=" + tipoInvestimento + ", valorInvestimento="
				+ valorInvestimento + "]";
	}	
}
