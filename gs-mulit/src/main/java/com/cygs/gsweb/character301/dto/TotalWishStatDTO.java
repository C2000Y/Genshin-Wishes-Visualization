package com.cygs.gsweb.character301.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalWishStatDTO {
    private String type;
    private Integer count;
    private Integer left;
}
