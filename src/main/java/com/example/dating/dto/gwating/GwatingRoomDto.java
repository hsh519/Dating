package com.example.dating.dto.gwating;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@AllArgsConstructor
public class GwatingRoomDto {

    private String roomCategory;

    @NotEmpty(message = "방 제목을 입력해주세요.")
    private String roomName;

    @NotEmpty(message = "방 설명을 입력해주세요.")
    private String roomDescription;

    @NotEmpty(message = "만날 위치를 입력해주세요.")
    private String location;

    private String image;

    private Integer manCount;

    private Integer womanCount;

    private List<Long> inviteIdList;
}
