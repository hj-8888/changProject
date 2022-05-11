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
//김동희 테스트2