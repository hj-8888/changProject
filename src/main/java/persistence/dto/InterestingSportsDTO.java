package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class InterestingSportsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int sportIndex;
    private String sportName;
}
