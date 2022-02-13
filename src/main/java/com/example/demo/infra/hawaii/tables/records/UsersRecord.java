/*
 * This file is generated by jOOQ.
 */
package com.example.demo.infra.hawaii.tables.records;


import com.example.demo.infra.hawaii.tables.Users;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record19;
import org.jooq.Row19;
import org.jooq.impl.UpdatableRecordImpl;


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
public class UsersRecord extends UpdatableRecordImpl<UsersRecord> implements Record19<UUID, String, String, String, String, String, String, Boolean, String, Boolean, String, Boolean, Boolean, LocalDateTime, Boolean, Integer, LocalDateTime, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = -150969433;

    /**
     * Setter for <code>public.users.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.users.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.users.username</code>.
     */
    public void setUsername(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.users.username</code>.
     */
    public String getUsername() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.users.password_hash</code>.
     */
    public void setPasswordHash(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.users.password_hash</code>.
     */
    public String getPasswordHash() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.users.nickname</code>.
     */
    public void setNickname(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.users.nickname</code>.
     */
    public String getNickname() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.users.first_name</code>.
     */
    public void setFirstName(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.users.first_name</code>.
     */
    public String getFirstName() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.users.last_name</code>.
     */
    public void setLastName(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.users.last_name</code>.
     */
    public String getLastName() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.users.authorities</code>.
     */
    public void setAuthorities(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.users.authorities</code>.
     */
    public String getAuthorities() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.users.is_enabled</code>.
     */
    public void setIsEnabled(Boolean value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.users.is_enabled</code>.
     */
    public Boolean getIsEnabled() {
        return (Boolean) get(7);
    }

    /**
     * Setter for <code>public.users.email</code>.
     */
    public void setEmail(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.users.email</code>.
     */
    public String getEmail() {
        return (String) get(8);
    }

    /**
     * Setter for <code>public.users.email_confirmed</code>.
     */
    public void setEmailConfirmed(Boolean value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.users.email_confirmed</code>.
     */
    public Boolean getEmailConfirmed() {
        return (Boolean) get(9);
    }

    /**
     * Setter for <code>public.users.phone_number</code>.
     */
    public void setPhoneNumber(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.users.phone_number</code>.
     */
    public String getPhoneNumber() {
        return (String) get(10);
    }

    /**
     * Setter for <code>public.users.phone_number_confirmed</code>.
     */
    public void setPhoneNumberConfirmed(Boolean value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.users.phone_number_confirmed</code>.
     */
    public Boolean getPhoneNumberConfirmed() {
        return (Boolean) get(11);
    }

    /**
     * Setter for <code>public.users.two_factor_enabled</code>.
     */
    public void setTwoFactorEnabled(Boolean value) {
        set(12, value);
    }

    /**
     * Getter for <code>public.users.two_factor_enabled</code>.
     */
    public Boolean getTwoFactorEnabled() {
        return (Boolean) get(12);
    }

    /**
     * Setter for <code>public.users.lockout_end</code>.
     */
    public void setLockoutEnd(LocalDateTime value) {
        set(13, value);
    }

    /**
     * Getter for <code>public.users.lockout_end</code>.
     */
    public LocalDateTime getLockoutEnd() {
        return (LocalDateTime) get(13);
    }

    /**
     * Setter for <code>public.users.lockout_enabled</code>.
     */
    public void setLockoutEnabled(Boolean value) {
        set(14, value);
    }

    /**
     * Getter for <code>public.users.lockout_enabled</code>.
     */
    public Boolean getLockoutEnabled() {
        return (Boolean) get(14);
    }

    /**
     * Setter for <code>public.users.access_failed_count</code>.
     */
    public void setAccessFailedCount(Integer value) {
        set(15, value);
    }

    /**
     * Getter for <code>public.users.access_failed_count</code>.
     */
    public Integer getAccessFailedCount() {
        return (Integer) get(15);
    }

    /**
     * Setter for <code>public.users.created_at</code>.
     */
    public void setCreatedAt(LocalDateTime value) {
        set(16, value);
    }

    /**
     * Getter for <code>public.users.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(16);
    }

    /**
     * Setter for <code>public.users.updated_at</code>.
     */
    public void setUpdatedAt(LocalDateTime value) {
        set(17, value);
    }

    /**
     * Getter for <code>public.users.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(17);
    }

    /**
     * Setter for <code>public.users.deleted_at</code>.
     */
    public void setDeletedAt(LocalDateTime value) {
        set(18, value);
    }

    /**
     * Getter for <code>public.users.deleted_at</code>.
     */
    public LocalDateTime getDeletedAt() {
        return (LocalDateTime) get(18);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record19 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row19<UUID, String, String, String, String, String, String, Boolean, String, Boolean, String, Boolean, Boolean, LocalDateTime, Boolean, Integer, LocalDateTime, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row19) super.fieldsRow();
    }

    @Override
    public Row19<UUID, String, String, String, String, String, String, Boolean, String, Boolean, String, Boolean, Boolean, LocalDateTime, Boolean, Integer, LocalDateTime, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row19) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Users.USERS.ID;
    }

    @Override
    public Field<String> field2() {
        return Users.USERS.USERNAME;
    }

    @Override
    public Field<String> field3() {
        return Users.USERS.PASSWORD_HASH;
    }

    @Override
    public Field<String> field4() {
        return Users.USERS.NICKNAME;
    }

    @Override
    public Field<String> field5() {
        return Users.USERS.FIRST_NAME;
    }

    @Override
    public Field<String> field6() {
        return Users.USERS.LAST_NAME;
    }

    @Override
    public Field<String> field7() {
        return Users.USERS.AUTHORITIES;
    }

    @Override
    public Field<Boolean> field8() {
        return Users.USERS.IS_ENABLED;
    }

    @Override
    public Field<String> field9() {
        return Users.USERS.EMAIL;
    }

    @Override
    public Field<Boolean> field10() {
        return Users.USERS.EMAIL_CONFIRMED;
    }

    @Override
    public Field<String> field11() {
        return Users.USERS.PHONE_NUMBER;
    }

    @Override
    public Field<Boolean> field12() {
        return Users.USERS.PHONE_NUMBER_CONFIRMED;
    }

    @Override
    public Field<Boolean> field13() {
        return Users.USERS.TWO_FACTOR_ENABLED;
    }

    @Override
    public Field<LocalDateTime> field14() {
        return Users.USERS.LOCKOUT_END;
    }

    @Override
    public Field<Boolean> field15() {
        return Users.USERS.LOCKOUT_ENABLED;
    }

    @Override
    public Field<Integer> field16() {
        return Users.USERS.ACCESS_FAILED_COUNT;
    }

    @Override
    public Field<LocalDateTime> field17() {
        return Users.USERS.CREATED_AT;
    }

    @Override
    public Field<LocalDateTime> field18() {
        return Users.USERS.UPDATED_AT;
    }

    @Override
    public Field<LocalDateTime> field19() {
        return Users.USERS.DELETED_AT;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getUsername();
    }

    @Override
    public String component3() {
        return getPasswordHash();
    }

    @Override
    public String component4() {
        return getNickname();
    }

    @Override
    public String component5() {
        return getFirstName();
    }

    @Override
    public String component6() {
        return getLastName();
    }

    @Override
    public String component7() {
        return getAuthorities();
    }

    @Override
    public Boolean component8() {
        return getIsEnabled();
    }

    @Override
    public String component9() {
        return getEmail();
    }

    @Override
    public Boolean component10() {
        return getEmailConfirmed();
    }

    @Override
    public String component11() {
        return getPhoneNumber();
    }

    @Override
    public Boolean component12() {
        return getPhoneNumberConfirmed();
    }

    @Override
    public Boolean component13() {
        return getTwoFactorEnabled();
    }

    @Override
    public LocalDateTime component14() {
        return getLockoutEnd();
    }

    @Override
    public Boolean component15() {
        return getLockoutEnabled();
    }

    @Override
    public Integer component16() {
        return getAccessFailedCount();
    }

    @Override
    public LocalDateTime component17() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime component18() {
        return getUpdatedAt();
    }

    @Override
    public LocalDateTime component19() {
        return getDeletedAt();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getUsername();
    }

    @Override
    public String value3() {
        return getPasswordHash();
    }

    @Override
    public String value4() {
        return getNickname();
    }

    @Override
    public String value5() {
        return getFirstName();
    }

    @Override
    public String value6() {
        return getLastName();
    }

    @Override
    public String value7() {
        return getAuthorities();
    }

    @Override
    public Boolean value8() {
        return getIsEnabled();
    }

    @Override
    public String value9() {
        return getEmail();
    }

    @Override
    public Boolean value10() {
        return getEmailConfirmed();
    }

    @Override
    public String value11() {
        return getPhoneNumber();
    }

    @Override
    public Boolean value12() {
        return getPhoneNumberConfirmed();
    }

    @Override
    public Boolean value13() {
        return getTwoFactorEnabled();
    }

    @Override
    public LocalDateTime value14() {
        return getLockoutEnd();
    }

    @Override
    public Boolean value15() {
        return getLockoutEnabled();
    }

    @Override
    public Integer value16() {
        return getAccessFailedCount();
    }

    @Override
    public LocalDateTime value17() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime value18() {
        return getUpdatedAt();
    }

    @Override
    public LocalDateTime value19() {
        return getDeletedAt();
    }

    @Override
    public UsersRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public UsersRecord value2(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public UsersRecord value3(String value) {
        setPasswordHash(value);
        return this;
    }

    @Override
    public UsersRecord value4(String value) {
        setNickname(value);
        return this;
    }

    @Override
    public UsersRecord value5(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public UsersRecord value6(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public UsersRecord value7(String value) {
        setAuthorities(value);
        return this;
    }

    @Override
    public UsersRecord value8(Boolean value) {
        setIsEnabled(value);
        return this;
    }

    @Override
    public UsersRecord value9(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public UsersRecord value10(Boolean value) {
        setEmailConfirmed(value);
        return this;
    }

    @Override
    public UsersRecord value11(String value) {
        setPhoneNumber(value);
        return this;
    }

    @Override
    public UsersRecord value12(Boolean value) {
        setPhoneNumberConfirmed(value);
        return this;
    }

    @Override
    public UsersRecord value13(Boolean value) {
        setTwoFactorEnabled(value);
        return this;
    }

    @Override
    public UsersRecord value14(LocalDateTime value) {
        setLockoutEnd(value);
        return this;
    }

    @Override
    public UsersRecord value15(Boolean value) {
        setLockoutEnabled(value);
        return this;
    }

    @Override
    public UsersRecord value16(Integer value) {
        setAccessFailedCount(value);
        return this;
    }

    @Override
    public UsersRecord value17(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public UsersRecord value18(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public UsersRecord value19(LocalDateTime value) {
        setDeletedAt(value);
        return this;
    }

    @Override
    public UsersRecord values(UUID value1, String value2, String value3, String value4, String value5, String value6, String value7, Boolean value8, String value9, Boolean value10, String value11, Boolean value12, Boolean value13, LocalDateTime value14, Boolean value15, Integer value16, LocalDateTime value17, LocalDateTime value18, LocalDateTime value19) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UsersRecord
     */
    public UsersRecord() {
        super(Users.USERS);
    }

    /**
     * Create a detached, initialised UsersRecord
     */
    public UsersRecord(UUID id, String username, String passwordHash, String nickname, String firstName, String lastName, String authorities, Boolean isEnabled, String email, Boolean emailConfirmed, String phoneNumber, Boolean phoneNumberConfirmed, Boolean twoFactorEnabled, LocalDateTime lockoutEnd, Boolean lockoutEnabled, Integer accessFailedCount, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(Users.USERS);

        set(0, id);
        set(1, username);
        set(2, passwordHash);
        set(3, nickname);
        set(4, firstName);
        set(5, lastName);
        set(6, authorities);
        set(7, isEnabled);
        set(8, email);
        set(9, emailConfirmed);
        set(10, phoneNumber);
        set(11, phoneNumberConfirmed);
        set(12, twoFactorEnabled);
        set(13, lockoutEnd);
        set(14, lockoutEnabled);
        set(15, accessFailedCount);
        set(16, createdAt);
        set(17, updatedAt);
        set(18, deletedAt);
    }
}
