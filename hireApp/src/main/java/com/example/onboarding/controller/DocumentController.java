package com.example.onboarding.controller;

import com.example.onboarding.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/{id}/upload-document")
    public ResponseEntity<String> uploadDocument(@PathVariable Long id,
                                                 @RequestParam("file") MultipartFile file,
                                                 @RequestParam("type") String type) {
        return ResponseEntity.ok(documentService.uploadDocument(id, file, type));
    }

    @PutMapping("/verify-document/{docId}")
    public ResponseEntity<String> verifyDocument(@PathVariable Long docId) {
        return ResponseEntity.ok(documentService.verifyDocument(docId));
    }
}
