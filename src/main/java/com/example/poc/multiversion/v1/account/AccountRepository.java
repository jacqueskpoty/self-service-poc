package com.example.poc.multiversion.v1.account;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AccountRepository extends MongoRepository<Account, String> {}
