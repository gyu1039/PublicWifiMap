package com.example.publicwifimap.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
public class History {

    private Integer id;
    private String latitude;
    private String longitude;
    private String viewed;
    private Integer isRemoved;
}
