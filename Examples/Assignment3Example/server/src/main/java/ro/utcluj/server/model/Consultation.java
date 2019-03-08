package ro.utcluj.server.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The persistent class for the consultation database table.
 */
@Entity(name = "Consultation")
@NamedQuery(name = "Consultation.findAll", query = "SELECT c FROM Consultation c")
public class Consultation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int idConsultation;

	private String diagnosis;

	private String observations;

	private String prescription;

	private Timestamp time;

	//bi-directional many-to-one association to Doctor
	@ManyToOne
	@JoinColumn(name = "idDoctor")
	private Doctor doctor;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name = "pnc")
	private Patient patient;

	public int getIdConsultation() {
		return this.idConsultation;
	}

	public void setIdConsultation(int idConsultation) {
		this.idConsultation = idConsultation;
	}

	public String getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getObservations() {
		return this.observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getPrescription() {
		return this.prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		Consultation other = (Consultation) obj;
		if (doctor == null) {
			if (other.doctor != null) {
				return false;
			}
		} else if (!doctor.equals(other.doctor)) {
			return false;
		}
		if (patient == null) {
			if (other.patient != null) {
				return false;
			}
		} else if (!patient.equals(other.patient)) {
			return false;
		}
		if (time == null) {
			return other.time == null;
		} else {
			return time.equals(other.time);
		}
	}

	@Override
	public String toString() {
		return "Consultation [idConsultation=" + idConsultation + ", diagnosis=" + diagnosis + ", observations=" + observations + ", prescription=" + prescription + ", time=" + time + ", doctor=" + doctor.getName() + ", patient=" + patient.getName() + "]";
	}
}