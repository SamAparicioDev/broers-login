package com.brears.login.services;

import com.brears.login.models.EmailDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
     String sendEmail(EmailDTO emailDTO);

}
