/*
 * This file is generated by jOOQ.
 */
package com.example.demo.infra.hawaii;


import com.example.demo.infra.hawaii.tables.FlywaySchemaHistory;
import com.example.demo.infra.hawaii.tables.Memos;
import com.example.demo.infra.hawaii.tables.UserProfiles;
import com.example.demo.infra.hawaii.tables.Users;
import com.example.demo.infra.hawaii.tables.records.FlywaySchemaHistoryRecord;
import com.example.demo.infra.hawaii.tables.records.MemosRecord;
import com.example.demo.infra.hawaii.tables.records.UserProfilesRecord;
import com.example.demo.infra.hawaii.tables.records.UsersRecord;
import org.jooq.ForeignKey;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;

import javax.annotation.processing.Generated;


/**
 * A class modelling foreign key relationships and constraints of tables of
 * the <code>public</code> schema.
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.12.3"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = UniqueKeys0.FLYWAY_SCHEMA_HISTORY_PK;
    public static final UniqueKey<MemosRecord> MEMOS_PKEY = UniqueKeys0.MEMOS_PKEY;
    public static final UniqueKey<UserProfilesRecord> USER_PROFILES_PKEY = UniqueKeys0.USER_PROFILES_PKEY;
    public static final UniqueKey<UsersRecord> USERS_PKEY = UniqueKeys0.USERS_PKEY;
    public static final UniqueKey<UsersRecord> USERS_USERNAME_UNIQUE = UniqueKeys0.USERS_USERNAME_UNIQUE;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<MemosRecord, UsersRecord> MEMOS__FK_MEMOS_USER_ID_ID = ForeignKeys0.MEMOS__FK_MEMOS_USER_ID_ID;
    public static final ForeignKey<UserProfilesRecord, UsersRecord> USER_PROFILES__FK_USER_PROFILES_USER_ID_ID = ForeignKeys0.USER_PROFILES__FK_USER_PROFILES_USER_ID_ID;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, "flyway_schema_history_pk", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK);
        public static final UniqueKey<MemosRecord> MEMOS_PKEY = Internal.createUniqueKey(Memos.MEMOS, "memos_pkey", Memos.MEMOS.ID);
        public static final UniqueKey<UserProfilesRecord> USER_PROFILES_PKEY = Internal.createUniqueKey(UserProfiles.USER_PROFILES, "user_profiles_pkey", UserProfiles.USER_PROFILES.ID);
        public static final UniqueKey<UsersRecord> USERS_PKEY = Internal.createUniqueKey(Users.USERS, "users_pkey", Users.USERS.ID);
        public static final UniqueKey<UsersRecord> USERS_USERNAME_UNIQUE = Internal.createUniqueKey(Users.USERS, "users_username_unique", Users.USERS.USERNAME);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<MemosRecord, UsersRecord> MEMOS__FK_MEMOS_USER_ID_ID = Internal.createForeignKey(com.example.demo.infra.hawaii.Keys.USERS_PKEY, Memos.MEMOS, "memos__fk_memos_user_id_id", Memos.MEMOS.USER_ID);
        public static final ForeignKey<UserProfilesRecord, UsersRecord> USER_PROFILES__FK_USER_PROFILES_USER_ID_ID = Internal.createForeignKey(com.example.demo.infra.hawaii.Keys.USERS_PKEY, UserProfiles.USER_PROFILES, "user_profiles__fk_user_profiles_user_id_id", UserProfiles.USER_PROFILES.USER_ID);
    }
}
