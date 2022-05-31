package test;

import java.io.Serializable;

public class Protocol implements Serializable {
    private static final long serialVersionUID = 1L;
    //프로토콜 타입에 관한 변수
    //프로토콜 타입에 관한 변수
    public static final int PT_UNDEFINED = -1;	// 프로토콜이 지정되어 있지 않은 경우
    public static final int PT_EXIT = 0;		// 프로그램 종료
    public static final
    int PT_REQ_LOGIN = 1;	// 로그인 요청
    public static final int PT_RES_LOGIN = 2;	// 로그인 응답
    public static final int PT_LOGIN_RESULT=3;	// 인증 결과
    public static final int LEN_LOGIN_ID=20;	// ID 길이
    public static final int LEN_LOGIN_PASSWORD=20;	// PWD 길이
    public static final int LEN_LOGIN_RESULT=2;	// 로그인 인증 값 길이
    public static final int LEN_PROTOCOL_TYPE=1;	// 프로토콜 타입 길이
    public static final int LEN_MAX = 1000;		//최대 데이터 길이
    protected int protocolType;

    // 전송하는 객체 데이터
    private Object obj = null;

    public Protocol() { // 생성자
        this(PT_UNDEFINED);
    }

    public Protocol(int protocolType) { // 생성자
        this.protocolType = protocolType;
    }

    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
    }

    public int getProtocolType() {
        return protocolType;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }
}