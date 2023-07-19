package com.rinattzak.userservice.controller;

import com.rinattzak.userservice.dto.UserRequestDto;
import com.rinattzak.userservice.dto.UserResponseDto;
import com.rinattzak.userservice.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("api/v1/users")
@Tag(name = "User controller")
public interface UserController {

    @Operation(
        operationId = "registration",
        summary = "Endpoint for registration user",
        tags = {"User controller"},
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserRequestDto.class,
                    description = "dto information for user"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
        }
    )

    @PostMapping("/registration")
    ResponseEntity<Void> registration(@RequestBody UserRequestDto userRequestDto);

    @Operation(
        operationId = "delete",
        summary = "Endpoint for delete user",
        tags = {"User controller"},
        responses = {
            @ApiResponse(responseCode = "201", description = "No content", content = {
                @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
        }
    )

    @DeleteMapping ("/delete")
    ResponseEntity<Void> delete(@RequestParam Long userId);

    @Operation(
        operationId = "update",
        summary = "Endpoint for update user",
        tags = {"User controller"},
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
        }
    )

    @PutMapping("/update")
    ResponseEntity<Void> update(@RequestBody UserRequestDto userRequestDto);

    @Operation(
        operationId = "getInfo",
        summary = "Endpoint for getting info about user",
        tags = {"User controller"},
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class,
                    description = "dto information for user"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
        }
    )

    @GetMapping("/get-info")
    ResponseEntity<UserResponseDto> getInfo(@RequestParam Long userId);

    @Operation(
        operationId = "getListOfUsers",
        summary = "Endpoint for getting list of users",
        tags = {"User controller"},
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = User.class,
                    description = "information about user"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
        }
    )

    @GetMapping("/get-all")
    ResponseEntity<List<UserResponseDto>> getListOfUsers();
}
