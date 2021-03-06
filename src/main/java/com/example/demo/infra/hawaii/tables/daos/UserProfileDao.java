/*
 * This file is generated by jOOQ.
 */
package com.example.demo.infra.hawaii.tables.daos;


import com.example.demo.infra.hawaii.tables.UserProfile;
import com.example.demo.infra.hawaii.tables.records.UserProfileRecord;

import java.time.LocalDate;
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
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserProfileDao extends DAOImpl<UserProfileRecord, com.example.demo.infra.hawaii.tables.pojos.UserProfile, UUID> {

    /**
     * Create a new UserProfileDao without any configuration
     */
    public UserProfileDao() {
        super(UserProfile.USER_PROFILE, com.example.demo.infra.hawaii.tables.pojos.UserProfile.class);
    }

    /**
     * Create a new UserProfileDao with an attached configuration
     */
    public UserProfileDao(Configuration configuration) {
        super(UserProfile.USER_PROFILE, com.example.demo.infra.hawaii.tables.pojos.UserProfile.class, configuration);
    }

    @Override
    public UUID getId(com.example.demo.infra.hawaii.tables.pojos.UserProfile object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(UserProfile.USER_PROFILE.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchById(UUID... values) {
        return fetch(UserProfile.USER_PROFILE.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.example.demo.infra.hawaii.tables.pojos.UserProfile fetchOneById(UUID value) {
        return fetchOne(UserProfile.USER_PROFILE.ID, value);
    }

    /**
     * Fetch records that have <code>user_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchRangeOfUserId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(UserProfile.USER_PROFILE.USER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchByUserId(UUID... values) {
        return fetch(UserProfile.USER_PROFILE.USER_ID, values);
    }

    /**
     * Fetch records that have <code>first_name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchRangeOfFirstName(String lowerInclusive, String upperInclusive) {
        return fetchRange(UserProfile.USER_PROFILE.FIRST_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>first_name IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchByFirstName(String... values) {
        return fetch(UserProfile.USER_PROFILE.FIRST_NAME, values);
    }

    /**
     * Fetch records that have <code>last_name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchRangeOfLastName(String lowerInclusive, String upperInclusive) {
        return fetchRange(UserProfile.USER_PROFILE.LAST_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>last_name IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchByLastName(String... values) {
        return fetch(UserProfile.USER_PROFILE.LAST_NAME, values);
    }

    /**
     * Fetch records that have <code>birthday BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchRangeOfBirthday(LocalDate lowerInclusive, LocalDate upperInclusive) {
        return fetchRange(UserProfile.USER_PROFILE.BIRTHDAY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>birthday IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchByBirthday(LocalDate... values) {
        return fetch(UserProfile.USER_PROFILE.BIRTHDAY, values);
    }

    /**
     * Fetch records that have <code>tel BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchRangeOfTel(String lowerInclusive, String upperInclusive) {
        return fetchRange(UserProfile.USER_PROFILE.TEL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>tel IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchByTel(String... values) {
        return fetch(UserProfile.USER_PROFILE.TEL, values);
    }

    /**
     * Fetch records that have <code>sex BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchRangeOfSex(Short lowerInclusive, Short upperInclusive) {
        return fetchRange(UserProfile.USER_PROFILE.SEX, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>sex IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchBySex(Short... values) {
        return fetch(UserProfile.USER_PROFILE.SEX, values);
    }

    /**
     * Fetch records that have <code>created_at BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchRangeOfCreatedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(UserProfile.USER_PROFILE.CREATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_at IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchByCreatedAt(LocalDateTime... values) {
        return fetch(UserProfile.USER_PROFILE.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>updated_at BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchRangeOfUpdatedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(UserProfile.USER_PROFILE.UPDATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_at IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchByUpdatedAt(LocalDateTime... values) {
        return fetch(UserProfile.USER_PROFILE.UPDATED_AT, values);
    }

    /**
     * Fetch records that have <code>deleted_at BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchRangeOfDeletedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(UserProfile.USER_PROFILE.DELETED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>deleted_at IN (values)</code>
     */
    public List<com.example.demo.infra.hawaii.tables.pojos.UserProfile> fetchByDeletedAt(LocalDateTime... values) {
        return fetch(UserProfile.USER_PROFILE.DELETED_AT, values);
    }
}
