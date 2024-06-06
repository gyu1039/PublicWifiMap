package com.example.publicwifimap.data;

import lombok.*;

@Builder @NoArgsConstructor @AllArgsConstructor @ToString @Getter
public class WifiInfoWithDistance {

    private String distance;

    private String managerNo;

    private String wardOffice;

    private String mainName;

    private String address1;

    private String address2;

    private String installFloor;

    private String installType;

    private String installBy;

    private String serviceSe;

    private String cmcwr;

    private String constructionYear;

    private String inoutDoor;

    private String remars3;

    private String latitude;

    private String longitude;

    private String workDateTime;
}
