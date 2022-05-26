package network;

import java.io.Serializable;

public class Protocol implements Serializable{

    protected int protocolType;
    protected int protocolCode;

    //프로토콜 타입에 관한 변수
    public static final int UNDEFINED = -1;   //프로토콜이 지정되어 있지 않을 경우에
    public static final int EXIT = 0;    // 종료
    public static final int PT_MEMBBER_CREATE = 1;   // 회원가입

    // 프로토콜 코드
    // 회원가입
    public static final int CD_REQ_MEMBBER_CREATE = 1;   //회원가입요청
    public static final int CD_REQ_largeCategoryLocal = 2;  // 대분류 요청
    public static final int CD_REQ_middleCategoryLocal = 3;  // 중분류요청
    public static final int CD_REQ_smallCategoryLocal = 4;  // 소분류요청
    public static final int CD_RES_largeCategoryLocal = 5;  // 대분류 응답
    public static final int CD_RES_middleCategoryLocal = 6;  // 중분류응답
    public static final int CD_RES_smallCategoryLocal = 7;  // 소분류응답
    public static final int CD_REQ_SPORTS = 8;  // 종목 요청
    public static final int CD_RES_SPORTS = 9;  // 종목응답
    public static final int CD_MEMBBER_CREATE_RESULT = 10;  // 회원가입결과

    // 데이터 길이
    public static final int LEN_LOGIN_ID = 20;   //ID길이
    public static final int LEN_LOGIN_PASSWORD = 20; //PW길이
    public static final int LEN_LOGIN_RESULT = 2;  //로그인인증값 길이
    public static final int LEN_PROTOCOL_TYPE = 1;  //프로토콜타입 길이
    public static final int LEN_PROTOCOL_CODE = 1;  //프로토콜타입 길이
    public static final int LEN_MAX = 1000;    //최대 데이타 길이
    public static final int LEN_MEMBBER_CREATE = 500;   //회원가입 길이
    public static final int LEN_CategoryLocal = 300;  // 대분류 길이
    public static final int LEN_SPORTS = 300;  // 대분류 길이
    public static final int LEN_MEMBBER_CREATE_RESULT = 2;  // 회원가입결과 길이


    private byte[] packet;   //프로토콜과 데이터의 저장공간이 되는 바이트배열

    public Protocol(){
        this(UNDEFINED, UNDEFINED);
    }

    public Protocol(int protocolType, int protocolCode){
        this.protocolType = protocolType;
        this.protocolCode = protocolCode;
        getPacket(protocolType, protocolCode);
    }


    public byte[] getPacket(int protocolType, int protocolCode){

        if(packet == null){
            switch(protocolType){
                case PT_MEMBBER_CREATE :
                    switch (protocolCode){
                        case CD_REQ_MEMBBER_CREATE:
                            packet = new byte[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_MEMBBER_CREATE];
                            break;
                        case CD_REQ_largeCategoryLocal:
                            packet = new byte[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_CategoryLocal ];
                            break;
                        case CD_REQ_middleCategoryLocal:
                            packet = new byte[LEN_PROTOCOL_TYPE +LEN_PROTOCOL_CODE+LEN_CategoryLocal];
                            break;
                        case CD_REQ_smallCategoryLocal:
                            packet = new byte[LEN_PROTOCOL_TYPE +LEN_PROTOCOL_CODE+LEN_CategoryLocal];
                            break;
                        case CD_RES_largeCategoryLocal:
                            packet = new byte[LEN_PROTOCOL_TYPE +LEN_PROTOCOL_CODE+LEN_CategoryLocal];
                            break;
                        case CD_RES_middleCategoryLocal:
                            packet = new byte[LEN_PROTOCOL_TYPE +LEN_PROTOCOL_CODE+LEN_CategoryLocal];
                            break;
                        case CD_RES_smallCategoryLocal:
                            packet = new byte[LEN_PROTOCOL_TYPE +LEN_PROTOCOL_CODE+LEN_CategoryLocal];
                            break;
                        case CD_REQ_SPORTS:
                            packet = new byte[LEN_PROTOCOL_TYPE +LEN_PROTOCOL_CODE+ LEN_SPORTS];
                            break;
                        case CD_RES_SPORTS:
                            packet = new byte[LEN_PROTOCOL_TYPE +LEN_PROTOCOL_CODE+ LEN_SPORTS];
                            break;
                        case CD_MEMBBER_CREATE_RESULT:
                            packet = new byte[LEN_PROTOCOL_TYPE +LEN_PROTOCOL_CODE+LEN_MEMBBER_CREATE_RESULT];
                            break;

                    }
                    break;
                case UNDEFINED : packet = new byte[LEN_MAX]; break;
                case EXIT : packet = new byte[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE]; break;
            }
        }

        packet[0] = (byte)protocolType;
        packet[1] = (byte)protocolCode;
        return packet;
    }

    //로그인후 성공/실패의 결과값을 프로토콜로 부터 추출하여 문자열로 리턴
    public String getLoginResult(){
        //String의 다음 생성자를 사용 : String(byte[] bytes, int offset, int length)
        return new String(packet, LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE, LEN_LOGIN_RESULT).trim();
    }


    //String ok를 byte[] 로 만들어서 packet의 프로토콜 타입 바로 뒤에 추가한다.
    public void setLoginResult(String ok){
        //arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
        System.arraycopy(ok.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE, ok.trim().getBytes().length);
    }


    public void setProtocolType(int protocolType){
        this.protocolType = protocolType;
    }

    public void setProtocolCode(int protocolCode){
        this.protocolCode = protocolCode;
    }

    public int getProtocolType(){
        return protocolType;
    }

    public int getProtocolCode(){
        return protocolCode;
    }
    public byte[] getPacket(){
        return packet;
    }


    //Default 생성자로 생성한 후 Protocol 클래스의 packet 데이타를 바꾸기 위한 메서드
    public void setPacket(int pt, int pc, byte[] buf){
        packet = null;
        packet = getPacket(pt,pc);
        protocolType = pt;
        protocolCode = pc;
        System.arraycopy(buf, 0, packet, 0, packet.length);
    }


    public String getId(){
        //String(byte[] bytes, int offset, int length)
        return new String(packet, LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE, LEN_LOGIN_ID).trim();
    }



    //byte[] packet 에 String ID를 byte[]로 만들어 프로토콜 타입 바로 뒷부분에 추가한다.
    public void setId(String id){
        System.arraycopy(id.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE, id.trim().getBytes().length);
    }
    public void setPassword(String password){
        System.arraycopy(password.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE+LEN_LOGIN_ID, password.trim().getBytes().length);
        packet[LEN_PROTOCOL_TYPE +LEN_PROTOCOL_CODE+ LEN_LOGIN_ID + password.trim().getBytes().length] = '\0';
    }
    public void setName(String name){
        System.arraycopy(name.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE, name.trim().getBytes().length);
    }
    public void setGender(String gender){
        System.arraycopy(gender.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE, gender.trim().getBytes().length);
    }
    public void setJob(String job){
        System.arraycopy(job.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE, job.trim().getBytes().length);
    }
    public void setPickture(String pickture){
        System.arraycopy(pickture.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE, pickture.trim().getBytes().length);
    }

    public void setNick(String nick){
        System.arraycopy(nick.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE, nick.trim().getBytes().length);
    }

    public void setLocation(String location){
        System.arraycopy(location.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE, location.trim().getBytes().length);
    }
    public void setSports(String sports) {
        System.arraycopy(sports.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE+LEN_PROTOCOL_CODE, sports.trim().getBytes().length);
    }

    public String getPassword(){
        return new String(packet, LEN_PROTOCOL_TYPE +LEN_PROTOCOL_CODE+ LEN_LOGIN_ID, LEN_LOGIN_PASSWORD).trim();
    }

}
