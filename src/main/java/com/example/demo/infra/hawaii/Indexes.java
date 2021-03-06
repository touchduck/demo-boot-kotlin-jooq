/*
 * This file is generated by jOOQ.
 */
package com.example.demo.infra.hawaii;


import com.example.demo.infra.hawaii.tables.FlywaySchemaHistory;
import com.example.demo.infra.hawaii.tables.Memo;
import com.example.demo.infra.hawaii.tables.User;
import com.example.demo.infra.hawaii.tables.UserProfile;

import javax.annotation.processing.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index FLYWAY_SCHEMA_HISTORY_PK = Indexes0.FLYWAY_SCHEMA_HISTORY_PK;
    public static final Index FLYWAY_SCHEMA_HISTORY_S_IDX = Indexes0.FLYWAY_SCHEMA_HISTORY_S_IDX;
    public static final Index MEMOS_PKEY = Indexes0.MEMOS_PKEY;
    public static final Index USERS_PKEY = Indexes0.USERS_PKEY;
    public static final Index USERS_USERNAME_UNIQUE = Indexes0.USERS_USERNAME_UNIQUE;
    public static final Index USER_PROFILES_PKEY = Indexes0.USER_PROFILES_PKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index FLYWAY_SCHEMA_HISTORY_PK = Internal.createIndex("flyway_schema_history_pk", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, new OrderField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
        public static Index FLYWAY_SCHEMA_HISTORY_S_IDX = Internal.createIndex("flyway_schema_history_s_idx", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, new OrderField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.SUCCESS }, false);
        public static Index MEMOS_PKEY = Internal.createIndex("memos_pkey", Memo.MEMO, new OrderField[] { Memo.MEMO.ID }, true);
        public static Index USERS_PKEY = Internal.createIndex("users_pkey", User.USER, new OrderField[] { User.USER.ID }, true);
        public static Index USERS_USERNAME_UNIQUE = Internal.createIndex("users_username_unique", User.USER, new OrderField[] { User.USER.USERNAME }, true);
        public static Index USER_PROFILES_PKEY = Internal.createIndex("user_profiles_pkey", UserProfile.USER_PROFILE, new OrderField[] { UserProfile.USER_PROFILE.ID }, true);
    }
}
