package br.calcard.arquitetura.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

@XmlRootElement(name = "serasaspc")
@Entity(name = "Serasaspc")
@Table(name = "Serasaspc")
public class Serasaspc implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "tbSerasaspc_id_seq", sequenceName = "tbSerasaspc_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbSerasaspc_id_seq")
	private Long id;


	@Column(nullable = false)
	@NotBlank(message = "Nome é uma informação obrigatória.")
	private String nome;

	@Column(nullable = false,length = 15)
	private String cpf;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Serasaspc))
			return false;

		Serasaspc other = (Serasaspc) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "br.calcard.arquitetura.core.model.Serasaspc[ id=" + id + " ]";
	}
}
