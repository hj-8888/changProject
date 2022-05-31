package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString

public class JoinChatRoomDTO implements Serializable {
    private int joinChatRoomIndex;
    private int memberIndex;
    private int chatRoomIndex;
}
