package persistence.dto;

import lombok.*;

@Getter
@Setter
@ToString

public class SportsFacilitiesDTO {
    private int sportsFacilitiesIndex;
    private String sportsFacilitiesName;
    private String longitude;
    private String latitude;
    private String sportsFacilitiesTel;
    private String sportsFacilitiesTypeCode;
    private int localInfoIndex;

}
