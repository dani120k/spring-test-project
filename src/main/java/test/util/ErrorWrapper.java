package test.util;

public class ErrorWrapper {
    private Long error_code;
    private String message;

    public Long getError_code() {
        return error_code;
    }

    public void setError_code(Long error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Builder{
        private Long error_code;
        private String message;


        public Builder setError_code(Long error_code) {
            this.error_code = error_code;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Long getError_code() {
            return error_code;
        }

        public String getMessage() {
            return message;
        }

        public Builder(){}

        public ErrorWrapper build(){
            return new ErrorWrapper(this);
        }
    }

    public ErrorWrapper(Builder builder){
        this.error_code = builder.getError_code();
        this.message = builder.getMessage();
    }
}
