package persistence.dto;

import lombok.*;

@Getter
@Setter
@ToString

public class SportsFacilitiesDTO {
    private static final long serialVersionUID = 1L;
    private int sportsFacilitiesIndex;
    private String sportsFacilitiesName;
    private String longitude;
    private String latitude;
    private String sportsFacilitiesTel;
    private int sportsFacilitiesTypeCode;
    private int sportsFacilitiesType;
    private String largeCategoryLocal;
    private String middleCategoryLocal;
    private String smallCategoryLocal;
}
