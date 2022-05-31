package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ChatRoomDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int chatRoomIndex;
    private String chatRoomName;
    private int chatRoomHeadCount;

}
