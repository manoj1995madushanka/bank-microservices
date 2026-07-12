package com.example.accounts.controller;

import com.example.accounts.constants.AccountConstants;
import com.example.accounts.dto.*;
import com.example.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for accounts ",
        description = "This API provides CRUD operations for accounts"
)
@RestController // combination of @ResponseBody and @Controller
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class AccountsController {

    private final IAccountService accountService;

    private final AccountsContactInfo accountsContactInfo;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;


    @Operation(summary = "Create a new account", description = "This API creates a new account")
    @ApiResponse(responseCode = "201", description = "Account created successfully")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch account details by mobile number", description = "This API fetches account details by mobile number")
    @ApiResponse(responseCode = "200", description = "Account details fetched successfully")
    @GetMapping("fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "^[0-9]{10}$", message = "Invalid account number format") String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccountsByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(summary = "Update account details", description = "This API updates account details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "Updated"));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }

    @Operation(summary = "Delete account", description = "This API deletes an account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account deleted successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam @Pattern(regexp = "^[0-9]{10}$", message = "Invalid account number format") String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "Deleted"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }

    @Operation(summary = "Get build information", description = "Get build information")
    @ApiResponse(responseCode = "200", description = "Account MS build version fetched successfully")
    @GetMapping("/version")
    public ResponseEntity<String> getVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Operation(summary = "Get java home information", description = "Get build information")
    @ApiResponse(responseCode = "200", description = "Account MS build version fetched successfully")
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(summary = "Get contact information", description = "Get contact information")
    @ApiResponse(responseCode = "200", description = "Account MS contact information fetched successfully")
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfo> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfo);
    }
}
