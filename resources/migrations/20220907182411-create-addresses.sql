CREATE TABLE IF NOT EXISTS addresses (
  id      INTEGER GENERATED ALWAYS AS IDENTITY,
  country VARCHAR(125) NOT NULL,
  city    VARCHAR(125) NOT NULL,
  street  VARCHAR(125) NOT NULL,
  number  VARCHAR(125) NOT NULL,
  PRIMARY KEY(id)
);