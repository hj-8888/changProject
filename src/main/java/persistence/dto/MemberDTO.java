package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString

public class MemberDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int memberIndex;
    private String memberID;
    private String memberPW;
    private String memberName;
    private int age;
    private String gender;
    private String job;
    private String profileURL;
    private String nickname;
    private int sportsIndex;
    private int localInfoIndex;
}
