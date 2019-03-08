package ro.utcluj.server.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ro.utcluj.server.model.Doctor;
import ro.utcluj.server.model.Patient;
import ro.utcluj.server.model.Staff;

import java.util.Date;

import static ro.utcluj.server.hibernate.HibernateUtil.getSessionFactory;

public class DataInitializer {

	public static void insertInitialData() {
		Doctor doctor = buildDoctor();
		Staff admin = buildAdmin();
		Staff staff = buildSecretary();
		Patient patient = buildPatient();
		Transaction transaction = null;
		try (Session session = getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			session.save(admin);
			session.save(doctor);
			session.save(staff);
			session.save(patient);

			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	private static Patient buildPatient() {
		Patient patient = new Patient();
		patient.setPnc(4);
		patient.setAddress("Cluj");
		patient.setName("Vlad");
		patient.setBirthday(new Date());
		return patient;
	}

	private static Doctor buildDoctor() {
		Doctor doctor = new Doctor();
		doctor.setIdDoctor(1);
		doctor.setName("Valentin Zaharia");
		doctor.setUsername("vali");
		doctor.setPassword("zaharia");
		return doctor;
	}

	private static Staff buildSecretary() {
		Staff staff = new Staff();
		staff.setId(2);
		staff.setRole(Staff.Type.SECRETARY);
		staff.setUsername("secretary");
		staff.setPassword("secretary");
		return staff;
	}

	private static Staff buildAdmin() {
		Staff admin = new Staff();
		admin.setId(3);
		admin.setRole(Staff.Type.ADMIN);
		admin.setUsername("admin");
		admin.setPassword("admin");
		return admin;
	}
}
