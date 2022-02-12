/*
 * This file is generated by jOOQ.
 */
package com.example.demo.infra.hawaii.tables;


import com.example.demo.infra.hawaii.Indexes;
import com.example.demo.infra.hawaii.Keys;
import com.example.demo.infra.hawaii.Public;
import com.example.demo.infra.hawaii.tables.records.UserProfilesRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class UserProfiles extends TableImpl<UserProfilesRecord> {

    /**
     * The reference instance of <code>public.user_profiles</code>
     */
    public static final UserProfiles USER_PROFILES = new UserProfiles();
    private static final long serialVersionUID = 615296107;
    /**
     * The column <code>public.user_profiles.id</code>.
     */
    public final TableField<UserProfilesRecord, UUID> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");
    /**
     * The column <code>public.user_profiles.user_id</code>.
     */
    public final TableField<UserProfilesRecord, UUID> USER_ID = createField(DSL.name("user_id"), org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");
    /**
     * The column <code>public.user_profiles.tel</code>.
     */
    public final TableField<UserProfilesRecord, String> TEL = createField(DSL.name("tel"), org.jooq.impl.SQLDataType.VARCHAR(512).nullable(false), this, "");
    /**
     * The column <code>public.user_profiles.created_at</code>.
     */
    public final TableField<UserProfilesRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");
    /**
     * The column <code>public.user_profiles.updated_at</code>.
     */
    public final TableField<UserProfilesRecord, LocalDateTime> UPDATED_AT = createField(DSL.name("updated_at"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");
    /**
     * The column <code>public.user_profiles.deleted_at</code>.
     */
    public final TableField<UserProfilesRecord, LocalDateTime> DELETED_AT = createField(DSL.name("deleted_at"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * Create a <code>public.user_profiles</code> table reference
     */
    public UserProfiles() {
        this(DSL.name("user_profiles"), null);
    }

    /**
     * Create an aliased <code>public.user_profiles</code> table reference
     */
    public UserProfiles(String alias) {
        this(DSL.name(alias), USER_PROFILES);
    }

    /**
     * Create an aliased <code>public.user_profiles</code> table reference
     */
    public UserProfiles(Name alias) {
        this(alias, USER_PROFILES);
    }

    private UserProfiles(Name alias, Table<UserProfilesRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserProfiles(Name alias, Table<UserProfilesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> UserProfiles(Table<O> child, ForeignKey<O, UserProfilesRecord> key) {
        super(child, key, USER_PROFILES);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserProfilesRecord> getRecordType() {
        return UserProfilesRecord.class;
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.USER_PROFILES_PKEY);
    }

    @Override
    public UniqueKey<UserProfilesRecord> getPrimaryKey() {
        return Keys.USER_PROFILES_PKEY;
    }

    @Override
    public List<UniqueKey<UserProfilesRecord>> getKeys() {
        return Arrays.<UniqueKey<UserProfilesRecord>>asList(Keys.USER_PROFILES_PKEY);
    }

    @Override
    public List<ForeignKey<UserProfilesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<UserProfilesRecord, ?>>asList(Keys.USER_PROFILES__FK_USER_PROFILES_USER_ID_ID);
    }

    public Users users() {
        return new Users(this, Keys.USER_PROFILES__FK_USER_PROFILES_USER_ID_ID);
    }

    @Override
    public UserProfiles as(String alias) {
        return new UserProfiles(DSL.name(alias), this);
    }

    @Override
    public UserProfiles as(Name alias) {
        return new UserProfiles(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserProfiles rename(String name) {
        return new UserProfiles(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserProfiles rename(Name name) {
        return new UserProfiles(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<UUID, UUID, String, LocalDateTime, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
