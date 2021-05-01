package com.example.poc.multiversion.v2.adapter.out.persistence.account;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountEntityRepository extends MongoRepository<AccountEntity, String> {
}
