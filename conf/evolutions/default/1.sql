# HaploThings schema

# --- !Ups

CREATE TABLE HaploSaid (
  id             SERIAL,
  what_said      varchar(1000) NOT NULL,
  context_note   varchar(500),
  created_at     timestamptz DEFAULT current_timestamp,
  PRIMARY KEY ( id )
);

# --- !Downs

DROP TABLE HaploSaid;
