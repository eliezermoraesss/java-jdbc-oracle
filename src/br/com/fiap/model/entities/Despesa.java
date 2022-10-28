package br.com.fiap.model.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Despesa {
	
	Integer id;
	Integer idUsuario;
	String descricaoDespesa;
	Date dataSaida;
	String tipoDespesa;
	BigDecimal valorDespesa;
	
	public Despesa() {
	}
		
	public Despesa(Integer id, Integer idUsuario, String descricaoDespesa, Date dataSaida, String tipoDespesa,
			BigDecimal valorDespesa) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.descricaoDespesa = descricaoDespesa;
		this.dataSaida = dataSaida;
		this.tipoDespesa = tipoDespesa;
		this.valorDespesa = valorDespesa;
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

	public String getDescricaoDespesa() {
		return descricaoDespesa;
	}

	public void setDescricaoDespesa(String descricaoDespesa) {
		this.descricaoDespesa = descricaoDespesa;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(String tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public BigDecimal getValorDespesa() {
		return valorDespesa;
	}

	public void setValorDespesa(BigDecimal valorDespesa) {
		this.valorDespesa = valorDespesa;
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
		Despesa other = (Despesa) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Despesa [idUsuario=" + idUsuario + ", descricaoDespesa=" + descricaoDespesa + ", dataSaida=" + dataSaida
				+ ", tipoDespesa=" + tipoDespesa + ", valorDespesa=" + valorDespesa + "]";
	}
}
