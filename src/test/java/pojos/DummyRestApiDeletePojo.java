package pojos;

public class DummyRestApiDeletePojo {

    private String status;
    private String data;
    private String message;

    public DummyRestApiDeletePojo(String status, String data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public DummyRestApiDeletePojo() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DummyRestApiDeletePojo{" +
                "status='" + status + '\'' +
                ", data='" + data + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
