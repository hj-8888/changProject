package Network;

public class NetworkLog {

    private static int logNum = 0;
    private String line = "===================================================";

    private String wrapMessage(String msg) {
        return line + "\n" + msg + "\n" + line;
    }

    private String getLogNum() {
        return logNum++ + "\n";
    }

    public String streamWaitLog() {
        return wrapMessage(getLogNum() + "Request 대기 중");
    }

    public String getInfoLog(Protocol protocol) {
        return wrapMessage(getLogNum() + protocol.getInfo());
    }

    public String makeMsg(String msg) {
        return wrapMessage(getLogNum() + msg);
    }

    public String makeMsgWithInfoLog(String msg, Protocol protocol) {
        return wrapMessage(getLogNum() + msg + "\n\n" + protocol.getInfo());
    }
}
