package com.example.poc.adapter.out.persistence.repository;

import com.example.poc.adapter.out.persistence.model.AccountDocument;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AccountDocumentRepository extends MongoRepository<AccountDocument, String> {
}
