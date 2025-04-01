package org.example.service;

import org.example.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserClient {
    @PostMapping("users/batch")
    List<UserDto> getUsersByIds(@RequestBody List<Long> ids);
}
