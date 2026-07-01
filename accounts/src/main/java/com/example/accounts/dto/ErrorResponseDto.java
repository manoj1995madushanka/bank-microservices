package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(name = "ErrorResponse", description = "Error Response DTO")
@Data
@AllArgsConstructor
public class ErrorResponseDto {
    @Schema(description = "API Path", example = "/api/create")
    private String apiPath;
    @Schema(description = "HTTP Status Code", example = "400")
    private HttpStatus statusCode;
    @Schema(description = "Error Message", example = "Bad Request")
    private String errorMessage;
    @Schema(description = "Error Time", example = "2023-01-01T00:00:00")
    private LocalDateTime errorTime;
}
