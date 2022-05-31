package persistence.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString

public class SportsFacilitiesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int sportsFacilitiesIndex;
    private String sportsFacilitiesName;
    private String longitude;
    private String latitude;
    private String sportsFacilitiesTel;
    private String sportsFacilitiesType;
    private String largeCategoryLocal;
    private String middleCategoryLocal;
    private String smallCategoryLocal;
}
