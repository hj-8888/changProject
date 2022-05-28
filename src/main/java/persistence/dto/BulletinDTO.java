package persistence.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class BulletinDTO {
    private int bulletinIndex;
    private String bulletinTitle;
    private String bulletinContent;
    private String attachedPicture;
    private Timestamp uploadDate;
    private int memberIndex;
    private int groupIndex;
    private int chatRoomIndex;
}

