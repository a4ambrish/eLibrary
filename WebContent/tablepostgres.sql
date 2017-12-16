--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4.4
-- Started on 2017-12-17 05:06:07

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 176 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2017 (class 0 OID 0)
-- Dependencies: 176
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 16867)
-- Name: e_book; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE e_book (
    callno character varying(4000) NOT NULL,
    name character varying(4000),
    author character varying(4000),
    publisher character varying(4000),
    quantity integer,
    issued integer
);


ALTER TABLE e_book OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16883)
-- Name: e_issuebook; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE e_issuebook (
    callno character varying(4000) NOT NULL,
    studentid character varying(4000) NOT NULL,
    studentname character varying(4000),
    studentmobile numeric,
    issueddate date,
    returnstatus character varying(4000)
);


ALTER TABLE e_issuebook OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16889)
-- Name: elib_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE elib_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE elib_seq OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16875)
-- Name: e_librarian; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE e_librarian (
    id integer DEFAULT nextval('elib_seq'::regclass) NOT NULL,
    name character varying(4000),
    password character varying(4000),
    email character varying(4000),
    mobile character varying(20)
);


ALTER TABLE e_librarian OWNER TO postgres;

--
-- TOC entry 2006 (class 0 OID 16867)
-- Dependencies: 172
-- Data for Name: e_book; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO e_book VALUES ('2', 'C++', 'shivani', 'packt', 2, 0);
INSERT INTO e_book VALUES ('1', 'java', 'ambrish', 'packt', 2, 0);


--
-- TOC entry 2008 (class 0 OID 16883)
-- Dependencies: 174
-- Data for Name: e_issuebook; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO e_issuebook VALUES ('1', '1', 'Tanvi', 9877665544, '2017-12-10', 'yes');


--
-- TOC entry 2007 (class 0 OID 16875)
-- Dependencies: 173
-- Data for Name: e_librarian; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO e_librarian VALUES (4, 'raj kumar 2', '123', 'raj@gmail.com', '9653233333');
INSERT INTO e_librarian VALUES (3, 'Shivani Manav', '123', 'shivani.respond@gmail.com', '9654330944');


--
-- TOC entry 2018 (class 0 OID 0)
-- Dependencies: 175
-- Name: elib_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('elib_seq', 4, true);


--
-- TOC entry 1894 (class 2606 OID 16874)
-- Name: e_book_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY e_book
    ADD CONSTRAINT e_book_pk PRIMARY KEY (callno);


--
-- TOC entry 1896 (class 2606 OID 16882)
-- Name: e_librarian_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY e_librarian
    ADD CONSTRAINT e_librarian_pk PRIMARY KEY (id);


--
-- TOC entry 2016 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-12-17 05:06:08

--
-- PostgreSQL database dump complete
--

