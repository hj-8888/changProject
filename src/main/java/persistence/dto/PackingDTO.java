package persistence.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PackingDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private BulletinDTO bulletinDTO;
    private ChatLogDTO chatLogDTO;
    private ChatRoomDTO chatRoomDTO;
    private CommentsDTO commentsDTO;
    private CreatingBulletinDTO creatingBulletinDTO;
    private FriendDTO friendDTO;
    private GroupDTO groupDTO;
    private GroupScheduleDTO groupScheduleDTO;
    private InterestingSportsDTO interestingSportsDTO;
    private JoinChatRoomDTO joinChatRoomDTO;
    private LocalInfoDTO localInfoDTO;
    private MemberDTO memberDTO;
    private SportsFacilitiesDTO sportsFacilitiesDTO;
}
