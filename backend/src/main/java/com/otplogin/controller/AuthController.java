package com.otplogin.controller;

import com.otplogin.dto.ApiResponse;
import com.otplogin.dto.LoginRequest;
import com.otplogin.dto.OTPRequest;
import com.otplogin.service.OTPService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OTPService otpService;

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse> sendOTP(@Valid @RequestBody LoginRequest request) {
        boolean sent = otpService.sendOTP(request.getEmail());

        if (sent) {
            return ResponseEntity.ok(new ApiResponse(true, "OTP sent successfully", null));
        }
        return ResponseEntity.badRequest().body(new ApiResponse(false, "Failed to send OTP", null));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse> verifyOTP(@Valid @RequestBody OTPRequest request) {
        boolean verified = otpService.verifyOTP(request.getEmail(), request.getOtp());

        if (verified) {
            return ResponseEntity.ok(new ApiResponse(true, "Login successful", null));
        }
        return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid or expired OTP", null));
    }
}