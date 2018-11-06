# Create base schema

# --- !Ups

CREATE TABLE price (
  id SERIAL PRIMARY KEY UNIQUE,
  trading_post TEXT NOT NULL,
  material TEXT NOT NULL,
  price NUMERIC NOT NULL,
  is_buy BOOLEAN NOT NULL,
  updated_at timestamp NOT NULL DEFAULT now()
);

# --- !Downs

DROP TABLE price;