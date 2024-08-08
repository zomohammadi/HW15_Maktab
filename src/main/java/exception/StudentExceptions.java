package exception;

public class StudentExceptions {

    public static class StudentInsertSqlException extends RuntimeException{
        public StudentInsertSqlException() {
            super();
        }

        public StudentInsertSqlException(String message) {
            super(message);
        }

        public StudentInsertSqlException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
