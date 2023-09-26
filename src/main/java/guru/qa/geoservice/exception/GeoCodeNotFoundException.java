package guru.qa.geoservice.exception;

public class GeoCodeNotFoundException extends RuntimeException{
    public GeoCodeNotFoundException() {
    }

    public GeoCodeNotFoundException(String message) {
        super(message);
    }

    public GeoCodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeoCodeNotFoundException(Throwable cause) {
        super(cause);
    }
}
