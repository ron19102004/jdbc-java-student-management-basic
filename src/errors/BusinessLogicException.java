package errors;

public class BusinessLogicException extends RuntimeException {
    private String message;
    private Class<?> _class;

    public BusinessLogicException(Class<?> _class, String message) {
        this.message = message;
        this._class = _class;
    }

    @Override
    public String toString() {
        return "Error at: " + this._class.getName() + " |Message: " + this.message;
    }
}
