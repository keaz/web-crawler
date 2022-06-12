package com.kasun.dto;

import com.kasun.validator.URLConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public record Request(@NotNull @NotBlank String searchText,
                      @Size(min = 1, message = "Should contain minimum {min} url")
                      @NotNull(message = "urls cannot be null")
                      @URLConstraint List<String> urls) {

}
