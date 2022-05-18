package persistence.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class FriendDTO {
    private int friendIndex;
    private int memberIndex;
    private int friend_memberIndex;
}
