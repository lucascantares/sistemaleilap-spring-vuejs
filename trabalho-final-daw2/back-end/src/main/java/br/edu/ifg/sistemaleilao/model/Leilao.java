package br.edu.ifg.sistemaleilao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Leilao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String item;
	@NotBlank
	private String dataAbertura;
	@NotNull
	private double lanceMinimo;
	@NotBlank
	private String dataFechamento;
	
	@Enumerated(EnumType.STRING)
	private StatusSituacao status = StatusSituacao.INATIVO;

	@ManyToOne
	private Lance lanceAtual;
	@OneToMany
	private List<Lance> lances = new ArrayList<>();
	
	public Leilao() {
	
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public double getLanceMinimo() {
		return lanceMinimo;
	}

	public void setLanceMinimo(double lanceMinimo) {
		this.lanceMinimo = lanceMinimo;
	}

	public String getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(String dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	
	public StatusSituacao getStatus() {
		return status;
	}
	
	public void setStatus(StatusSituacao status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((dataAbertura == null) ? 0 : dataAbertura.hashCode());
		result = prime * result + ((dataFechamento == null) ? 0 : dataFechamento.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		long temp;
		temp = Double.doubleToLongBits(lanceAtual.getValor());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lanceMinimo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Leilao other = (Leilao) obj;
		if (id != other.id)
			return false;
		if (dataAbertura == null) {
			if (other.dataAbertura != null)
				return false;
		} else if (!dataAbertura.equals(other.dataAbertura))
			return false;
		if (dataFechamento == null) {
			if (other.dataFechamento != null)
				return false;
		} else if (!dataFechamento.equals(other.dataFechamento))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (Double.doubleToLongBits(lanceAtual.getValor()) != Double.doubleToLongBits(other.lanceAtual.getValor()))
			return false;
		if (Double.doubleToLongBits(lanceMinimo) != Double.doubleToLongBits(other.lanceMinimo))
			return false;
		if (status == null) {
			if (other.status!= null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}