/*
 * This file is generated by jOOQ.
 */
package com.example.demo.infra.hawaii.tables.daos;


import com.example.demo.infra.hawaii.tables.Memos;
import com.example.demo.infra.hawaii.tables.records.MemoRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.annotation.processing.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.12.3"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class MemosDao extends DAOImpl<MemoRecord, com.example.demo.infra.hawaii.tables.pojos.Memos, UUID> {

    /**
     * Create a new MemosDao without any configuration
     */
    public MemosDao() {
        super(Memo.MEMO, com.example.demo.infra.hawaii.tables.pojos.Memos.class);
    }

    /**
     * Create a new MemosDao with an attached configuration
     */
    public MemosDao(Configuration configuration) {
        super(Memo.MEMO, com.example.demo.infra.hawaii.tables.pojos.Memos.class, configuration);
    }

    @Override
    public UUID getId(com.example.demo.infra.hawaii.tables.pojos.Memos object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.Memos> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(Memo.MEMO.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.Memos> fetchById(UUID... values) {
        return fetch(Memo.MEMO.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.example.demo.infra.hawaii.tables.pojos.Memos fetchOneById(UUID value) {
        return fetchOne(Memo.MEMO.ID, value);
    }

    /**
     * Fetch records that have <code>user_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.Memos> fetchRangeOfUserId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(Memo.MEMO.USER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.Memos> fetchByUserId(UUID... values) {
        return fetch(Memo.MEMO.USER_ID, values);
    }

    /**
     * Fetch records that have <code>title BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.Memos> fetchRangeOfTitle(String lowerInclusive, String upperInclusive) {
        return fetchRange(Memo.MEMO.TITLE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>title IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.Memos> fetchByTitle(String... values) {
        return fetch(Memo.MEMO.TITLE, values);
    }

    /**
     * Fetch records that have <code>body BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.Memos> fetchRangeOfBody(String lowerInclusive, String upperInclusive) {
        return fetchRange(Memo.MEMO.BODY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>body IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.Memos> fetchByBody(String... values) {
        return fetch(Memo.MEMO.BODY, values);
    }

    /**
     * Fetch records that have <code>created_at BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.Memos> fetchRangeOfCreatedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Memo.MEMO.CREATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_at IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.Memos> fetchByCreatedAt(LocalDateTime... values) {
        return fetch(Memo.MEMO.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>updated_at BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.Memos> fetchRangeOfUpdatedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Memo.MEMO.UPDATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_at IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.Memos> fetchByUpdatedAt(LocalDateTime... values) {
        return fetch(Memo.MEMO.UPDATED_AT, values);
    }

    /**
     * Fetch records that have <code>deleted_at BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.Memos> fetchRangeOfDeletedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Memo.MEMO.DELETED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>deleted_at IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.Memos> fetchByDeletedAt(LocalDateTime... values) {
        return fetch(Memo.MEMO.DELETED_AT, values);
    }
}
