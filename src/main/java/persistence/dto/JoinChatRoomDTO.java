package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class JoinChatRoomDTO {
    private int joinChatRoomIndex;
    private int memberIndex;
    private int chatRoomIndex;
}
