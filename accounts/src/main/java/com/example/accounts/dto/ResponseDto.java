package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Response", description = "Response DTO")
@Data
@AllArgsConstructor
public class ResponseDto {
    @Schema(description = "Status Code", example = "200")
    private String statusCode;
    @Schema(description = "Status Message", example = "Success")
    private String statusMsg;
}
