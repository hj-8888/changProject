package persistence.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class LocalInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int localInfoIndex;
    private String largeCategoryLocal;
    private String middleCategoryLocal;
    private String smallCategoryLocal;
}
