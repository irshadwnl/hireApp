package com.example.onboarding.service;

import com.example.onboarding.entity.Candidate;
import com.example.onboarding.entity.CandidateDocument;
import com.example.onboarding.repository.CandidateDocumentRepository;
import com.example.onboarding.repository.CandidateRepository;
import com.example.onboarding.util.FileStorageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final CandidateRepository candidateRepo;
    private final CandidateDocumentRepository docRepo;
    private final FileStorageUtil fileStorageUtil;

    public String uploadDocument(Long candidateId, MultipartFile file, String docType) {
        Candidate candidate = candidateRepo.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        try {
            String path = fileStorageUtil.saveFile(file);

            CandidateDocument doc = new CandidateDocument();
            doc.setCandidate(candidate);
            doc.setDocumentType(docType);
            doc.setFileUrl(path);
            doc.setVerified(false);

            docRepo.save(doc);
            return "Document uploaded: " + path;
        } catch (IOException e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }

    public String verifyDocument(Long docId) {
        CandidateDocument doc = docRepo.findById(docId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        doc.setVerified(true);
        docRepo.save(doc);
        return "Document marked as verified.";
    }
}

