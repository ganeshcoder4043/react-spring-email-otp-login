package com.otplogin.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void sendOtpEmail(String to, String otp, String name) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("Your OTP Code - Secure Login");
            helper.setFrom("your-email@gmail.com");

            String htmlContent = buildOtpEmailTemplate(name, otp);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            System.out.println("OTP sent to: " + to);

        } catch (Exception e) {
            System.err.println("Email failed: " + e.getMessage());
        }
    }

    private String buildOtpEmailTemplate(String name, String otp) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body { font-family: 'Segoe UI', Arial, sans-serif; background: #f5f7fa; }
                    .container { max-width: 450px; margin: 0 auto; background: white; 
                                padding: 30px; border-radius: 15px; text-align: center;
                                box-shadow: 0 4px 20px rgba(0,0,0,0.1); }
                    .otp-code { font-size: 52px; letter-spacing: 12px; color: #4F46E5; 
                               font-weight: bold; margin: 25px 0; background: #f0f4ff; 
                               padding: 15px; border-radius: 8px; }
                    .expiry { color: #ef4444; font-weight: bold; }
                    .footer { margin-top: 25px; color: #6b7280; font-size: 12px; }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2>🔐 Secure Login OTP</h2>
                    <p>Hello <strong>%s</strong>,</p>
                    <p>Use this code to complete your login:</p>
                    <div class="otp-code">%s</div>
                    <p>This code expires in <span class="expiry">5 minutes</span>.</p>
                    <p>If you didn't request this, please ignore.</p>
                    <div class="footer">© 2024 Secure Login System</div>
                </div>
            </body>
            </html>
            """.formatted(name, otp);
    }
}
