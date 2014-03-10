package proton.inject;

public class ConfigurationException extends ProtonRuntimeException {
	private static final long serialVersionUID = 1171685360550055388L;

	public ConfigurationException() {
		super();
	}

	public ConfigurationException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public ConfigurationException(String detailMessage) {
		super(detailMessage);
	}

	public ConfigurationException(Throwable throwable) {
		super(throwable);
	}
}
