--
-- PostgreSQL database dump
--

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- Name: tamandare; Type: SCHEMA; Schema: -; Owner: tamandare
--

CREATE SCHEMA tamandare;


ALTER SCHEMA tamandare OWNER TO tamandare;

SET search_path = tamandare, pg_catalog;

--
-- Name: sq_url_id; Type: SEQUENCE; Schema: tamandare; Owner: tamandare
--

CREATE SEQUENCE sq_url_id
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE tamandare.sq_url_id OWNER TO tamandare;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: url; Type: TABLE; Schema: tamandare; Owner: tamandare; Tablespace: 
--

CREATE TABLE url (
    id bigint DEFAULT nextval('sq_url_id'::regclass) NOT NULL,
    url character(255),
    title character(255),
    keywords text,
    status bigint,
    UNIQUE(url)
);


ALTER TABLE tamandare.url OWNER TO tamandare;

--
-- PostgreSQL database dump complete
--

