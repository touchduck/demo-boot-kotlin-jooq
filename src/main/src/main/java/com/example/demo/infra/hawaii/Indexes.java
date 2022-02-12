/*
 * This file is generated by jOOQ.
 */
package com.example.demo.infra.hawaii;


import com.example.demo.infra.hawaii.tables.Memos;
import com.example.demo.infra.hawaii.tables.UserProfiles;
import com.example.demo.infra.hawaii.tables.Users;

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
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index MEMOS_PKEY = Indexes0.MEMOS_PKEY;
    public static final Index USER_PROFILES_PKEY = Indexes0.USER_PROFILES_PKEY;
    public static final Index USERS_PKEY = Indexes0.USERS_PKEY;
    public static final Index USERS_USERNAME_UNIQUE = Indexes0.USERS_USERNAME_UNIQUE;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index MEMOS_PKEY = Internal.createIndex("memos_pkey", Memos.MEMOS, new OrderField[]{Memos.MEMOS.ID}, true);
        public static Index USER_PROFILES_PKEY = Internal.createIndex("user_profiles_pkey", UserProfiles.USER_PROFILES, new OrderField[]{UserProfiles.USER_PROFILES.ID}, true);
        public static Index USERS_PKEY = Internal.createIndex("users_pkey", Users.USERS, new OrderField[]{Users.USERS.ID}, true);
        public static Index USERS_USERNAME_UNIQUE = Internal.createIndex("users_username_unique", Users.USERS, new OrderField[]{Users.USERS.USERNAME}, true);
    }
}
