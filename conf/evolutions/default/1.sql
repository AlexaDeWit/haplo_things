# HaploThings schema

# --- !Ups

CREATE TABLE HaploSaid (
  id             SERIAL,
  what_said      varchar(1000) NOT NULL,
  context_note   varchar(500),
  created_at     timestamptz DEFAULT current_timestamp,
  PRIMARY KEY ( id )
);

CREATE TRIGGER HaploSaidCreated 
AFTER INSERT ON HaploSaid
FOR EACH ROW
EXECUTE PROCEDURE HaploSaidCreation();

CREATE FUNCTION HaploSaidCreation
---     Set created_at to current_timestamp!?



# --- !Downs

DROP TABLE HaploSaid;
