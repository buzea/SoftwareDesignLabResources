package ro.utcluj.server.commands;

public class CommandFactory {

	public static Command getCommand(String[] args) {
		try {
			switch (args[0]) {
				case "login":
					return new LoginCommand(args[1], args[2]);
				case "viewConsults":
					return new ViewConsultsCommand(args[1], args[2]);
				case "getCurrentPatient":
					return new GetCurrentPatientCommand(args[1]);
				case "updateConsult":
					return new UpdateConsultCommand(args[1], args[2], args[3], args[4]);
				case "createPatient":
					return new CreatePatientCommand(args[1], args[2], args[3], args[4]);
				case "updatePatient":
					return new UpdatePatientCommand(args[1], args[2], args[3], args[4]);
				case "loadPatient":
					return new LoadPatientCommand(args[1]);
				case "deletePatient":
					return new DeletePatientCommand(args[1]);
				case "createDoctor":
					return new CreateDoctorCommand(args[1], args[2], args[3]);
				case "createStaff":
					return new CreateStaffCommand(args[1], args[2], args[3]);
				case "findAccount":
					return new FindAccountCommand(args[1]);
				case "updateDoctor":
					return new UpdateDoctorCommand(args[1], args[2], args[3], args[4]);
				case "updateStaff":
					return new UpdateStaffCommand(args[1], args[2], args[3], args[4]);
				case "deleteAccount":
					return new DeleteAccount(args[1], args[2]);
				case "deleteConsult":
					return new DeleteConsultCommand(args[1]);
				case "getPatients":
					return new GetPattientsCommand();
				case "getDoctors":
					return new GetDoctorsCommand();
				case "createConsultation":
					return new CreateConsultationCommand(args[1], args[2], args[3]);
				case "notifyDoctor":
					return new NotifyDoctorCommand(args[1]);
				default:
					return null;
			}
		} catch (ArrayIndexOutOfBoundsException arrE) {
			return null;
		}
	}
}
