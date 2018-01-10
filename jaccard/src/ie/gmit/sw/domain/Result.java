package ie.gmit.sw.domain;

public class Result {

    private String uploadDocName;
    private String dbDocName;
    private float intersection_rate;

    public Result(String uploadDocName, String dbDocName, float intersection_rate) {
        this.uploadDocName = uploadDocName;
        this.dbDocName = dbDocName;
        this.intersection_rate = intersection_rate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("________ RESULT ________").append("\n");
        sb.append("Uploaded doc: ").append(uploadDocName).append("\n");
        sb.append("Database doc: ").append(dbDocName).append("\n");
        sb.append("Intersection rate: ").append(intersection_rate).append("%");

        return sb.toString();
    }
}
