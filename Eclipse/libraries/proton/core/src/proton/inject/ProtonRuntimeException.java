package proton.inject;

public class ProtonRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -2107546653119828392L;

	public ProtonRuntimeException() {
		super();
	}

	public ProtonRuntimeException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public ProtonRuntimeException(String detailMessage) {
		super(detailMessage);
	}

	public ProtonRuntimeException(Throwable throwable) {
		super(throwable);
	}
}
