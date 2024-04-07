package io.security.corespringsecurity.domain.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDto {

    private String name;
    private String keyword;
    private String topic;
    private int headcount;
    private String filepath;
    private String gender;
    private int minYear;
    private int maxYear;

}