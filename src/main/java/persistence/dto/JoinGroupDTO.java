package persistence.dto;

import lombok.*;

@Getter
@Setter
@ToString

public class JoinGroupDTO {
    private int joinGroupIndex;
    private String memberID;
    private String groupNumber;
}
