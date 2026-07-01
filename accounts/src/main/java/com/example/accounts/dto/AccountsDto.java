package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(name = "Accounts", description = "Accounts DTO")
@Data
public class AccountsDto {
    @Schema(description = "Account Number", example = "1234567890")
    @NotEmpty(message = "Account Number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid account number format")
    private Long accountNumber;

    @Schema(description = "Account Type", example = "Savings")
    @NotEmpty(message = "Account Type cannot be empty")
    private String accountType;

    @Schema(description = "Branch Address", example = "New York")
    @NotEmpty(message = "Branch Address cannot be empty")
    private String branchAddress;
}
