# HaploThings schema

# --- !Ups

CREATE TABLE HaploSaid (
  id             SERIAL,
  what_said      varchar(1000) NOT NULL,
  context_note   varchar(500),
  created_at     timestamptz DEFAULT current_timestamp,
  PRIMARY KEY ( id )
);

CREATE FUNCTION HaploSaidCreation() RETURNS TRIGGER AS '
  BEGIN
    NEW.created_at := current_timestamp; \
    RETURN NEW; \
  END: \
' LANGUAGE 'plpgsql';


CREATE TRIGGER HaploSaidCreated 
BEFORE INSERT ON HaploSaid
FOR EACH ROW
EXECUTE PROCEDURE HaploSaidCreation();

# --- !Downs

DROP TABLE HaploSaid;
DROP FUNCTION HaploSaidCreation();
