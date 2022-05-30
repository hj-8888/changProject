package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InterestingSportsDTO {
    private static final long serialVersionUID = 1L;
    private int sportIndex;
    private String sportName;
}
