package com.ecommerce.userservice.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Builder
public class UserResponse {

    private Long id;
    private String email;
    private String mobileNumber;
    private String address;
    private String role;
    private LocalDateTime createdAt;

}
