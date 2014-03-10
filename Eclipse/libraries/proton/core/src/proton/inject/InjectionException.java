package proton.inject;

public class InjectionException extends ProtonRuntimeException {
    private static final long serialVersionUID = -3532473089280220332L;

    public InjectionException() {
        super();
    }

    public InjectionException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public InjectionException(String detailMessage) {
        super(detailMessage);
    }

    public InjectionException(Throwable throwable) {
        super(throwable);
    }
}
