INSERT INTO public."user" (id, username, password_hash, nickname, first_name, last_name, authorities, is_enabled,
                          "email ", "email_confirmed ", "phone_number ", phone_number_confirmed, two_factor_enabled,
                          lockout_end, lockout_enabled, access_failed_count, created_at, updated_at, deleted_at)
VALUES ('ff154a84-47d6-438f-a73e-c89a2f5b1632', 'kuma1@gmail.com',
        '{bcrypt}$2a$10$I3djm4.hfYZwQqHtBo2Z6OMoDyBcQEpxI8EVsVv2FxQznuqRmh9iO', 'くまさん1', null, null, 'ROLE_ADMIN', true,
        null, false, null, false, false, null, false, 0, '2021-02-14 04:13:33.303000', '2021-02-14 04:13:33.304000',
        null);
