package axual.npospolita.pokemonapi.exception;

public class ApplicationInitializationException extends RuntimeException {
    public ApplicationInitializationException(String message) {
        super(message);
    }

    public ApplicationInitializationException(String message, Throwable e) {
        super(message, e);
    }
}
