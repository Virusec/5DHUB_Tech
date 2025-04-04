package org.example.service;

import org.example.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserClient {
    @GetMapping("users/company_id/{id}")
    List<UserDto> getUsersByCompanyId(@PathVariable("id") Long id);
}
