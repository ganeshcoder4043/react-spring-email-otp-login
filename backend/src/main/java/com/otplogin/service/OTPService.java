package com.otplogin.service;

import com.otplogin.entity.User;
import com.otplogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OTPService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public boolean sendOTP(String email) {
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setName(email.split("@")[0]);
                    return userRepository.save(newUser);
                });

        String otp = String.format("%06d", new Random().nextInt(999999));

        user.setOtp(otp);
        user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
        user.setVerified(false);
        userRepository.save(user);

        emailService.sendOtpEmail(email, otp, user.getName());
        return true;
    }

    public boolean verifyOTP(String email, String otp) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null || user.getOtp() == null) return false;

        boolean isValid = user.getOtp().equals(otp) &&
                user.getOtpExpiryTime().isAfter(LocalDateTime.now());

        if (isValid) {
            user.setVerified(true);
            user.setOtp(null);
            user.setOtpExpiryTime(null);
            userRepository.save(user);
        }

        return isValid;
    }
}