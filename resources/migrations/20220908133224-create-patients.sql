CREATE TABLE IF NOT EXISTS patients (
  id         INTEGER GENERATED ALWAYS AS IDENTITY,
  address_id INTEGER NOT NULL,
  fname      VARCHAR(63) NOT NULL,
  mname      VARCHAR(63),
  lname      VARCHAR(63) NOT NULL,
  gender     VARCHAR(15) NOT NULL,
  birth_date DATE NOT NULL,
  insurance  VARCHAR(255) NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT unique_patient UNIQUE(fname, mname, lname, birth_date),
  CONSTRAINT fk_address FOREIGN KEY(address_id) REFERENCES addresses(id)
)
