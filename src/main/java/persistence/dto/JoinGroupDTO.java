package persistence.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString

public class JoinGroupDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int joinGroupIndex;
    private String memberID;
    private String groupNumber;
}
