package Network;

import java.io.Serializable;

public class Protocol implements Serializable {
    private static final long serialVersionUID = 1L;
    //type
    public static final int PT_EXIT = 999;	// 타입이 지정되어 있지 않은 경우
    public static final int PT_UNDEFINED = 0;	// 타입이 지정되어 있지 않은 경우
    public static final int PT_LOGIN = 1;	// 로그인
    public static final int PT_SIGNUP = 2;	// 회원가입


    // code
    public static final int PC_UNDEFINED = 3;   // 코드가 지정되어 있지 않은 경우

    //로그인
    public static final int CD_LOGIN_REQ = 100;	// 로그인 요청
    public static final int CD_RES_LOGIN_SUCCESS = 101;
    public static final int CD_RES_LOGIN_FAIL = 199;

    // 회원가입
    // 요청
    public static final int CD_SIGNUP_ID_DUPLICATION_REQ = 200; // 아이디 중복 검사 요청
    public static final int CD_SIGNUP_MIDDLE_LOCATION_REQ = 201; // 중분류 요청
    public static final int CD_SIGNUP_SMALL_LOCATION_REQ = 202; // 소분류 요청
    public static final int CD_SIGNUP_NICK_DUPLICATION_REQ = 203; // 닉네임 중복 검사 요청
    public static final int CD_SIGNUP_REQ = 204; // 회원가입 결과 요청

    // 응답
    public static final int CD_SIGNUP_ID_DUPLICATION_RES = 205; // 아이디 중복 검사 응답
    public static final int CD_SIGNUP_MIDDLE_LOCATION_RES = 206; // 중분류 응답
    public static final int CD_SIGNUP_SMALL_LOCATION_RES = 207; // 소분류 응답
    public static final int CD_SIGNUP_NICK_DUPLICATION_RES = 208; // 닉네임 중복 검사 응답
    public static final int CD_SIGNUP_RES = 209; // 회원가입 결과 응답
    public static final int CD_SIGNUP_FAIL = 299; // 회원 가입 실패 응답


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
