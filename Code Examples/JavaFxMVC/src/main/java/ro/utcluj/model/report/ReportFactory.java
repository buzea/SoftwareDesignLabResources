package ro.utcluj.model.report;

public class ReportFactory {

	public static Report createReport(ReportType type) {

		switch (type) {
			case XML:
				return new XmlReport();
			//break;
			case TEXT:
				return new TextReport();
			default:
				return null;
			//break;
		}
	}
	public enum ReportType {TEXT, XML}
}
