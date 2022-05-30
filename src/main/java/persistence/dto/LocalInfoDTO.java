package persistence.dto;

import lombok.*;

@Getter
@Setter
@ToString

public class LocalInfoDTO {
    private static final long serialVersionUID = 1L;
    private int localInfoIndex;
    private String largeCategoryLocal;
    private String middleCategoryLocal;
    private String smallCategoryLocal;
}
