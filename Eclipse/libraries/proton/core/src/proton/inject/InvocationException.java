package proton.inject;

public class InvocationException extends ProtonRuntimeException {
	private static final long serialVersionUID = -3718940628174272461L;

	public InvocationException() {
		super();
	}

	public InvocationException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public InvocationException(String detailMessage) {
		super(detailMessage);
	}

	public InvocationException(Throwable throwable) {
		super(throwable);
	}
}
