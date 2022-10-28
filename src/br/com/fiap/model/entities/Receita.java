package br.com.fiap.model.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Receita {
	
	Integer id;
	Integer IdUsuario;
	String descricaoReceita;
	Date dataEntrada;
	String tipoReceita;
	BigDecimal valorReceita;
	
	public Receita() {
	}
	
	public Receita(Integer id, Integer IdUsuario, String descricaoReceita, Date dataEntrada, String tipoReceita,
			BigDecimal valorReceita) {
		super();
		this.id = id;
		this.IdUsuario = IdUsuario;
		this.descricaoReceita = descricaoReceita;
		this.dataEntrada = dataEntrada;
		this.tipoReceita = tipoReceita;
		this.valorReceita = valorReceita;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		IdUsuario = idUsuario;
	}

	public String getDescricaoReceita() {
		return descricaoReceita;
	}

	public void setDescricaoReceita(String descricaoReceita) {
		this.descricaoReceita = descricaoReceita;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(String tipoReceita) {
		this.tipoReceita = tipoReceita;
	}

	public BigDecimal getValorReceita() {
		return valorReceita;
	}

	public void setValorReceita(BigDecimal valorReceita) {
		this.valorReceita = valorReceita;
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
		Receita other = (Receita) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Receita [usuario=" + IdUsuario + ", descricaoReceita=" + descricaoReceita + ", dataEntrada=" + dataEntrada
				+ ", tipoReceita=" + tipoReceita + ", valorReceita=" + valorReceita + "]";
	}
}
