create table if not exists chat
(
    uuid              text primary key not null,
    title             text             not null,
    description       text             not null,
    modified_datetime integer          not null,
    icon              text
);
create index if not EXISTS chat_modified_datetime_index on chat (modified_datetime);

create table if not exists message
(
    uuid              text    not null,
    chat_uuid         text    not null,
    message_text      text    not null,
    sent_at           integer not null,
    modified_datetime integer not null,
    primary key (uuid, chat_uuid)
);
create index if not EXISTS message_modified_datetime_index on message (modified_datetime);

create table if not exists sync
(
    sync_id       integer primary key autoincrement,
    sync_datetime integer
);

/* FTS tables */

create virtual table chat_fts using fts5
(
    uuid,
    title,
    description
);

create virtual table message_fts using fts5
(
    uuid,
    chat_uuid,
    message
);

/* FTS triggers */

create trigger if not exists chat_fts_insert_trigger
    after insert
    on chat
    for each row
begin
    insert into chat_fts (uuid, title, description) values (NEW.uuid, NEW.title, NEW.description);
end;

create trigger if not exists chat_fts_update_trigger
    after update
    on chat
    for each row
begin
    update chat_fts set uuid = NEW.uuid, title = NEW.title, description = NEW.description where uuid = OLD.uuid;
end;

create trigger if not exists chat_fts_delete_trigger
    after delete
    on chat
    for each row
begin
    delete from chat_fts where uuid = OLD.uuid;
end;

create trigger if not exists message_fts_insert_trigger
    after insert
    on message
    for each row
begin
    insert into message_fts (uuid, chat_uuid, message) values (NEW.uuid, NEW.chat_uuid, NEW.message_text);
end;

create trigger if not exists message_fts_update_trigger
    after update
    on message
    for each row
begin
    update message_fts
    set uuid      = NEW.uuid,
        chat_uuid = NEW.chat_uuid,
        message   = NEW.message_text
    where uuid = OLD.uuid
      and chat_uuid = OLD.chat_uuid;
end;

create trigger if not exists message_fts_delete_trigger
    after delete
    on message
    for each row
begin
    delete from message_fts where uuid = OLD.uuid and chat_uuid = OLD.chat_uuid;
end;