package ro.utcluj.server.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table
@Entity
@NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int    pnc;
	private String address;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date   birthday;

	//bi-directional many-to-one association to Consultation
	@OneToMany(mappedBy = "patient")
	private List<Consultation> consultations;

	public int getPnc() {
		return this.pnc;
	}

	public void setPnc(int pnc) {
		this.pnc = pnc;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Consultation> getConsultations() {
		return this.consultations;
	}

	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

	public Consultation addConsultation(Consultation consultation) {
		getConsultations().add(consultation);
		consultation.setPatient(this);

		return consultation;
	}

	public Consultation removeConsultation(Consultation consultation) {
		getConsultations().remove(consultation);
		consultation.setPatient(null);

		return consultation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pnc;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Patient other = (Patient) obj;
		return pnc == other.pnc;
	}

	@Override
	public String toString() {
		return name + " [" + pnc + "]";
	}

	public String getMedicalHistory() {
		String history = "";
		if (consultations != null) {
			for (Consultation c : consultations) {
				history += "Observations : " + c.getObservations() + "\r\n";
				history += "Diagnosis    : " + c.getDiagnosis() + "\r\n";
				history += "Prescription : " + c.getPrescription() + "\r\n";
				history += "\r\n";
			}
		}
		return history;
	}
}