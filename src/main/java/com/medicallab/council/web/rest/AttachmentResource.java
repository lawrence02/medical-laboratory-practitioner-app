package com.medicallab.council.web.rest;

import com.medicallab.council.domain.Attachment;
import com.medicallab.council.service.AttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link io.github.vicpermir.domain.Attachment}.
 */
@RestController
@RequestMapping("/api")
public class AttachmentResource {

    private final Logger log = LoggerFactory.getLogger(AttachmentResource.class);

    private static final String ENTITY_NAME = "attachment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private AttachmentService imageService;

    @PostMapping("/attachments")
    public void uploadImage(@RequestParam("file") Attachment file) {
        log.debug("Request to save MultipartFile : {}", file);
        //imageService.saveAttachment(file);
    }
}
