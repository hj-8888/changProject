package persistence.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@ToString

public class BulletinDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int bulletinIndex;
    private String bulletinTitle;
    private String bulletinContent;
    private Timestamp uploadDate;
    private int memberIndex;
    private int groupIndex;
    private int chatRoomIndex;
}

