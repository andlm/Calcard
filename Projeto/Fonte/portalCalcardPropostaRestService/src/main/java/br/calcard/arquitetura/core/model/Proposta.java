package br.calcard.arquitetura.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "proposta")
@Entity(name = "Proposta")
@Table(name = "Proposta")
@NamedQuery(name = "Proposta.pesquisarCPF", query = "SELECT p FROM Proposta p WHERE LOWER(p.cpf) = LOWER(?1)")
public class Proposta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "tbProposta_id_seq", sequenceName = "tbProposta_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbProposta_id_seq")
	@ApiModelProperty(notes = "O ID da proposta gerado pelo banco de dados")
	private Long id;

	@Autowired(required = false)
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "Data da proposta")
	private Date data;

	@Column(nullable = false)
	@NotBlank(message = "Nome é uma informação obrigatória.")
	@ApiModelProperty(notes = "Nome da proposta")
	private String nome;

	@Column(nullable = false,length = 15)
	@ApiModelProperty(notes = "CPF da proposta")
	private String cpf;
	
	
	@Column(nullable = false)
	@NotNull(message = "Idade é uma informação obrigatória.")
	@ApiModelProperty(notes = "Idade da proposta")
	private Long idade;

	@Column(nullable = false)
	@NotBlank(message = "Sexo é uma informação obrigatória.")
	@ApiModelProperty(notes = "Sexo da proposta")
	private String sexo;

	@Column(nullable = false)
	@NotBlank(message = "Estado civil é uma informação obrigatória.")
	@ApiModelProperty(notes = "Estado civil da proposta")
	private String estadocivil;

	@Column(nullable = false)
	@NotBlank(message = "Estado é uma informação obrigatória.")
	@ApiModelProperty(notes = "Estado da proposta")
	private String estado;

	@Column(nullable = false)
	@NotNull(message = "Dependentes é uma informação obrigatória.")
	@ApiModelProperty(notes = "Dependentes da proposta")
	private Long dependentes;
	
	@NotNull(message = "Renda é uma informação obrigatória.")
	@Column(name = "renda",nullable = false, precision = 10, scale = 2)
	@ApiModelProperty(notes = "Renda da proposta")
	private String renda;
	
	@Column(nullable = true)
	@ApiModelProperty(notes = "Resultado da análise da proposta")
	private String resultadoanalise;
	
	@Column(nullable = true)
	@ApiModelProperty(notes = "Resultado da análise da proposta")
	private String limite;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getIdade() {
		return idade;
	}

	public void setIdade(Long idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstadocivil() {
		return estadocivil;
	}

	public void setEstadocivil(String estadocivil) {
		this.estadocivil = estadocivil;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getDependentes() {
		return dependentes;
	}

	public void setDependentes(Long dependentes) {
		this.dependentes = dependentes;
	}

	public String getRenda() {
		return renda;
	}

	public void setRenda(String renda) {
		this.renda = renda;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	public String getResultadoanalise() {
		return resultadoanalise;
	}

	public void setResultadoanalise(String resultadoanalise) {
		this.resultadoanalise = resultadoanalise;
	}

	public String getLimite() {
		return limite;
	}

	public void setLimite(String limite) {
		this.limite = limite;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Proposta))
			return false;

		Proposta other = (Proposta) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "br.calcard.arquitetura.core.model.Proposta[ id=" + id + " ]";
	}
}
