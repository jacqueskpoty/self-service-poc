package com.example.poc.adapter.out.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository of the AccountDocument
 * Used to communicate with the Mongo DB for query purposes
 * It's part of the OUT Persistence Adapter and is tightly coupled with the account document
 */
public interface AccountDocumentRepository extends MongoRepository<AccountDocument, String> {
}
