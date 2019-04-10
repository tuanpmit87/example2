package metro.example2.common;

public class ApiCustomError {

    private String error;
    private String errorDescription;

    public ApiCustomError(String error, String errorDescription){
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
