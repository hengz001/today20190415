package sino.gmn.model;

public class Cmd {
    private Integer cId;

    private String cCmd;

    private String cRequest;

    private String cResponse;

    private String cDescrible;

    private Integer cFlag;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcCmd() {
        return cCmd;
    }

    public void setcCmd(String cCmd) {
        this.cCmd = cCmd == null ? null : cCmd.trim();
    }

    public String getcRequest() {
        return cRequest;
    }

    public void setcRequest(String cRequest) {
        this.cRequest = cRequest == null ? null : cRequest.trim();
    }

    public String getcResponse() {
        return cResponse;
    }

    public void setcResponse(String cResponse) {
        this.cResponse = cResponse == null ? null : cResponse.trim();
    }

    public String getcDescrible() {
        return cDescrible;
    }

    public void setcDescrible(String cDescrible) {
        this.cDescrible = cDescrible == null ? null : cDescrible.trim();
    }

    public Integer getcFlag() {
        return cFlag;
    }

    public void setcFlag(Integer cFlag) {
        this.cFlag = cFlag;
    }
}