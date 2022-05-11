package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ChatRoomDTO {
    private int chatRoomIndex;
    private String chatRoomName;
    private int chatRoomHeadCount;

}
