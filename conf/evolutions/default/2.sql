# HaploThings schema

# --- !Ups

ALTER TABLE HaploSaid
ADD COLUMN who_said varchar(50) NOT NULL DEFAULT 'Haplo'

# --- !Downs

ALTER TABLE HaploSaid
DROP COLUMN who_said

