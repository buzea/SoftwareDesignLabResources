package ro.utcluj.server.mappers;

public class DataSourceException extends Exception {

	private static final long serialVersionUID = -1423681582021610855L;

	public DataSourceException() {
	}

	public DataSourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataSourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataSourceException(String message) {
		super(message);
	}

	public DataSourceException(Throwable cause) {
		super(cause);
	}
}
