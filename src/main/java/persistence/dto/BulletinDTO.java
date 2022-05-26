package persistence.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class BulletinDTO {
<<<<<<< Updated upstream
=======
<<<<<<< Updated upstream
    private int bulletinIndex;
=======
    private int bulletinIndex
>>>>>>> Stashed changes
>>>>>>> Stashed changes
    private String bulletinTitle;
    private String bulletinContent;
    private String attachedPicture;
    private Timestamp uploadDate;
    private int memberIndex;
    private int groupIndex;
    private int chatRoomIndex
}

