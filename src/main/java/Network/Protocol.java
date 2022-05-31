package Network;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class Protocol implements Serializable {
    private static final long serialVersionUID = 1L;
    //type
    public static final int PT_EXIT = 999;   // 타입이 지정되어 있지 않은 경우
    public static final int PT_UNDEFINED = 0;   // 타입이 지정되어 있지 않은 경우
    public static final int PT_LOGIN = 1;   // 로그인
    public static final int PT_SIGNUP = 2;   // 회원가입
    public static final int PT_SPORTSFACILITIE_SEARCH = 3;   // 체육시설 검색
    public static final int PT_PROFILE = 4;   // 프로필

    public static final int PT_MEMBER_SEARCH = 5;   // 인물 검색
    public static final int PT_GROUP = 6;   // 인물 검색
    public static final int PT_BULLETIN = 7;   // 게시판


    // code
    public static final int PC_UNDEFINED = 0;   // 코드가 지정되어 있지 않은 경우

    //로그인
    public static final int CD_LOGIN_REQ = 100;   // 로그인 요청
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
    public static final int CD_SIGNUP_ID_NOT_DUPLICATION_RES = 206; // 아이디 중복 검사 응답 (중복아님)
    public static final int CD_SIGNUP_MIDDLE_LOCATION_RES = 207; // 중분류 응답
    public static final int CD_SIGNUP_SMALL_LOCATION_RES = 208; // 소분류 응답
    public static final int CD_SIGNUP_NICK_DUPLICATION_RES = 209; // 닉네임 중복 검사 응답
    public static final int CD_SIGNUP_NICK_NOT_DUPLICATION_RES = 210; // 닉네임 중복 검사 응답 (중복아님)
    public static final int CD_SIGNUP_RES = 211; // 회원가입 결과 응답
    public static final int CD_SIGNUP_FAIL = 299; // 회원 가입 실패 응답

    // 체육시설 검색
    // 요청
    public static final int CD_SPORTSFACILITIE_SEARCH_MIDDLE_LOCATION_REQ = 300; // 중분류 요청
    public static final int CD_SPORTSFACILITIE_SEARCH_SMALL_LOCATION_REQ = 301; // 소분류 요청
    public static final int CD_SPORTSFACILITIE_SEARCH_REQ = 302; // 스포츠센터 검색 결과 요청
    // 응답
    public static final int CD_SPORTSFACILITIE_SEARCH_MIDDLE_LOCATION_RES = 303; // 중분류 응답
    public static final int CD_SPORTSFACILITIE_SEARCH_SMALL_LOCATION_RES = 304; // 소분류 응답
    public static final int CD_SPORTSFACILITIE_SEARCH_RES = 305; // 스포츠센터 결과 응답
    public static final int CD_SPORTSFACILITIE_SEARCH_FAIL = 399; // 스포츠센터 실패 응답

    // 프로필 코드
    //응답
    public static final int CD_PT_PROFILE_REQ = 400; // 프로필 정보 요청
    public static final int CD_PT_PROFILE_UPDATE_REQ = 401; // 수정 요청
    public static final int CD_PROFILE_NICK_DUPLICATION_REQ = 402; // 수정 요청

    // 요청
    public static final int CD_PROFILE_RES = 403; // 프로필 정보 응답
    public static final int CD_PROFILE_UPDATE_RES = 404; // 수정 응답
    public static final int CD_PROFILE_NICK_NOT_DUPLICATION_RES = 405; // 닉네임 중복 없음 응답
    public static final int CD_PROFILE_NICK_DUPLICATION_RES = 406; // 닉네임 중복 있음 응답
    public static final int CD_PROFILE_FAIL = 499; // 프로필 실패 응답

    // 인물 검색
    // 요청
    public static final int CD_MEMBER_SEARCH_MIDDLE_LOCATION_REQ = 500; // 중분류 요청
    public static final int CD_MEMBER_SEARCH_SMALL_LOCATION_REQ = 501; // 소분류 요청
    public static final int CD_MEMBER_SEARCH_REQ = 502; // 인물 검색 결과 요청
    // 응답
    public static final int CD_MEMBER_SEARCH_MIDDLE_LOCATION_RES = 503; // 중분류 응답
    public static final int CD_MEMBER_SEARCH_SMALL_LOCATION_RES = 504; // 소분류 응답
    public static final int CD_MEMBER_SEARCH_RES = 505; // 인물 결과 응답
    public static final int CD_MEMBER_SEARCH_FAIL = 599; // 인물 실패 응답

    // 그룹 코드 GROUP
    // 요청
    public static final int CD_GROUP_NAME_DUPLICATION_REQ = 600; // 그룹 이름 중복 검사 요청
    public static final int CD_GROUP_CREATE_REQ = 602; // 그룹 생성 요청

    // 응답
    public static final int CD_GROUP_NAME_DUPLICATION_RES = 605; // 그룹 이름 검사 응답
    public static final int CD_GROUP_NAME_NOT_DUPLICATION_RES = 601; // 그룹 이름 중복 검사 응답 (중복아님)
    public static final int CD_GROUP_CREATE_RES = 602; // 그룹 생성 응답
    public static final int CD_GROUP_FAIL = 699; // 그룹 생성 응답

    // 게시판 코드 BULLETIN
    // 요청

    // 응답
    protected int protocolType;
    protected int protocolCode;

    // 전송하는 객체 데이터
    private Object obj = null;
    private Object[] arrObj = null;

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

    public void setArrObj(Object[] arrObj) { this.arrObj = arrObj; }

    public Object[] getArrObj() { return arrObj; };

    public String getInfo() {
        return "[Protocol Info]: " + toString() + "\n[Object Info]: " + obj.toString();
    }

}