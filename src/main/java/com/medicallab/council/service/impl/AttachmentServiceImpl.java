package com.medicallab.council.service.impl;

import com.medicallab.council.domain.Attachment;
import com.medicallab.council.domain.Practitioner;
import com.medicallab.council.repository.AttachmentRepository;
import com.medicallab.council.service.AttachmentService;
import java.io.IOException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Implementation for managing
 * {@link com.medicallab.council.domain.Payment}.
 */
@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    private static final Logger LOG = LoggerFactory.getLogger(AttachmentServiceImpl.class);

    private final AttachmentRepository attachmentRepository;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Payment : {}", id);
        attachmentRepository.deleteById(id);
    }

    @Override
    public void saveAttachment(MultipartFile file) {
        LOG.debug("Request to save Image");
        try {
            Attachment imageEntity = new Attachment();
            imageEntity.setAttachmentName(file.getOriginalFilename());
            imageEntity.setAttachmentType(file.getContentType());
            imageEntity.setAttachment(file.getBytes());
            attachmentRepository.save(imageEntity);
        } catch (IOException e) {
            // Handle exception
        }
    }

    @Override
    public Page<Practitioner> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<MultipartFile> findOne(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }
}
