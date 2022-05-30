package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class CreatingBulletinDTO {
    private static final long serialVersionUID = 1L;
    private int creatingBulletinIndex;
    private int memberIndex;
    private int bulletinIndex;
}
