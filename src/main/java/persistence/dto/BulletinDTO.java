package persistence.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class BulletinDTO {
    private String bulletinTitle;
    private String bulletinContent;
    private String attachedPicture;
    private String bulletinPW;
    private Timestamp uploadDate;
    private int memberIndex;
}

