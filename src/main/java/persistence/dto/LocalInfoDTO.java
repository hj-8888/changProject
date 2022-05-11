package persistence.dto;

import lombok.*;

@Getter
@Setter
@ToString

public class LocalInfoDTO {
    private int localInfoIndex;
    private String largeCategoryLocal;
    private String middleCategoryLocal;
    private String smallCategoryLocal;
}
