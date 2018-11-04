# Create base schema

# --- !Ups

CREATE TABLE price (
  id SERIAL PRIMARY KEY UNIQUE,
  base TEXT NOT NULL,
  astatine NUMERIC,
  hydrogen NUMERIC,
  fluorine NUMERIC,
  iodine NUMERIC,
  chlorine NUMERIC,
  agricium NUMERIC,
  gold NUMERIC,
  tungsten NUMERIC,
  aluminum NUMERIC,
  titanium NUMERIC,
  agriculturesupplies NUMERIC,
  quartz NUMERIC,
  corundum NUMERIC,
  diamond NUMERIC,
  beryl NUMERIC,
  laranite NUMERIC,
  processedsood NUMERIC,
  waste NUMERIC,
  stims NUMERIC,
  distilledspirits NUMERIC,
  medicalsupplies NUMERIC,
  scrap NUMERIC,
  alutruciatoxine NUMERIC,
  widow NUMERIC,
);

# --- !Downs

DROP TABLE price;