package persistence.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString

public class BulletinDTO {
    private int bulletinIndex;
    private String bulletinTitle;
    private String bulletinContent;
    private String attachedPicture;
    private String bulletinPW;
    private Date uploadDate;
}

//김주호 테스트
//김주호 테스트 5시 30분