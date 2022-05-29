import java.io.Serializable;

public class Protocol implements Serializable {
    private static final long serialVersionUID = 1L;
    //type
    public static final int PT_UNDEFINED = 0;	// 타입이 지정되어 있지 않은 경우
    public static final int PT_EXIT = 1;        // 프로그램 종료
    public static final int PT_CREATE_REQ = 2; // 생성 요청 응답
    public static final int PT_CREATE_RES = 3;
    public static final int PT_READ_REQ = 4;   // 조회 요청 응답
    public static final int PT_READ_RES = 5;
    public static final int PT_UPDATE_REQ = 6; // 업데이트 요청 응답
    public static final int PT_UPDATE_RES = 7;
    public static final int PT_DELETE_REQ = 8; // 삭제 요청 응답
    public static final int PT_DELETE_RES = 9;
    public static final int PT_LOGIN_REQ = 10;	// 로그인 요청
    public static final int PT_LOGIN_RES = 11;	// 로그인 응답

    // 코드
    public static final int PC_UNDEFINED = 0;   // 코드가 지정되어 있지 않은 경우

    // 회원가입 코드

    // 로그인 code
    public static final int CD_RES_LOGIN_FAIL = 1;
    public static final int CD_RES_LOGIN_SUCCESS = 2;


    //READ_RES code
    //READ_REQ code
    //CREATE_REQ code
    //CREATE_RES code
    //UPDATE_REQ code
    //UPDATE_RES code
    //DELETE_REQ code
    //DELETE_RES code


    // 필요한가?
    public static final int PT_TYPE_NETWORK_SUCCESS = -1;
    public static final int PT_TYPE_NETWORK_FAILED = -2;

    protected int protocolType;
    protected int protocolCode;

    // 전송하는 객체 데이터
    private Object obj = null;

    public Protocol() { // 생성자
        this(PT_UNDEFINED, PC_UNDEFINED);
    }

    public Protocol(int protocolType) { // 생성자
        this.protocolType = protocolType;
        this.protocolCode = PC_UNDEFINED;
    }

    public Protocol(int protocolType, int protocolCode) { // 생성자
        this.protocolType = protocolType;
        this.protocolCode = protocolCode;
    }

    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
    }

    public int getProtocolType() {
        return protocolType;
    }

    public void setProtocolCode(int protocolCode) {
        this.protocolCode = protocolCode;
    }

    public int getProtocolCode() {
        return protocolCode;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }
}
