package com.example.Api.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVo {
    private String username;
    private String rootname;
    private String roomname;
}
