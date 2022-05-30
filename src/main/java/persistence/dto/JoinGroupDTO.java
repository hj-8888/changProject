package persistence.dto;

import lombok.*;

@Getter
@Setter
@ToString

public class JoinGroupDTO {
    private static final long serialVersionUID = 1L;
    private int joinGroupIndex;
    private String memberID;
    private String groupNumber;
}
