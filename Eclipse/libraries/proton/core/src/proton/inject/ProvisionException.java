package proton.inject;

public class ProvisionException extends ProtonRuntimeException {
	private static final long serialVersionUID = 8834973331199731825L;

	public ProvisionException() {
		super();
	}

	public ProvisionException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public ProvisionException(String detailMessage) {
		super(detailMessage);
	}

	public ProvisionException(Throwable throwable) {
		super(throwable);
	}
}
