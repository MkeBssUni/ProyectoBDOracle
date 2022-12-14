alter session set "_ORACLE_SCRIPT"=true;

/* Espacio de trabajo */

CREATE TABLESPACE CINEMAREVIEW
DATAFILE 'CinemaReview.dbf' SIZE 100M;

/* Usuarios */

CREATE USER usuariop IDENTIFIED BY password
default tablespace CINEMAREVIEW QUOTA
300M ON CINEMAREVIEW;

CREATE USER usuariog IDENTIFIED BY password
DEFAULT TABLESPACE CINEMAREVIEW
QUOTA 300M ON CINEMAREVIEW;

/* Roles */

CREATE ROLE freeUser; 
CREATE ROLE premiumUser; 

/* Concedemos permisos */

GRANT ALL PRIVILEGES TO premiumUser; 
GRANT SELECT ANY TABLE TO freeUser; 
GRANT CREATE SESSION TO freeUser;

/* Asignamos los roles */

GRANT freeUser TO usuariog;
GRANT premiumUser TO usuariop;

/* TABLAS */

CREATE TABLE CATEGORIAS (
    ID INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
    NOMBRE VARCHAR(100) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE PELICULAS (
    ID INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
    TITULO VARCHAR(100) NOT NULL,
    DIRECTOR VARCHAR(250) NOT NULL,
    PRODUCTOR VARCHAR(250) NOT NULL,
    ESCRITOR VARCHAR(250) NOT NULL,
    ESTRENO INT NOT NULL,
    CALIFICACION FLOAT NOT NULL,
    ID_CATEGORIA INT,
        PRIMARY KEY (ID),
        FOREIGN KEY (ID_CATEGORIA) REFERENCES CATEGORIAS(ID)
);

CREATE TABLE FAVORITAS (
    ID INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
    TITULO VARCHAR(100) NOT NULL,
    CALIFICACION FLOAT NOT NULL,
        PRIMARY KEY (ID)
);

CREATE TABLE WATCHLIST (
    ID INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
    TITULO VARCHAR(100) NOT NULL,
        PRIMARY KEY (ID)
);

/* ÍNDICES */

CREATE INDEX INDICE_CATEGORIA_PELICULAS ON PELICULAS(ID, ID_CATEGORIA);
CREATE INDEX INDICE_CALIFICACION_PELICULAS ON PELICULAS(ID, CALIFICACION);
CREATE INDEX INDICE_CREDITOS ON PELICULAS(DIRECTOR, PRODUCTOR, ESCRITOR);

/* VISTAS */

CREATE OR REPLACE VIEW CONSULTA_PELICULAS AS SELECT
    PELICULAS.ID AS ID_PELICULA,
    PELICULAS.TITULO AS TITULO,
    PELICULAS.DIRECTOR AS DIRECTOR,
    PELICULAS.PRODUCTOR AS PRODUCTOR,
    PELICULAS.ESCRITOR AS ESCRITOR,
    PELICULAS.ESTRENO AS ESTRENO,
    PELICULAS.CALIFICACION AS CALIFICACION,
    PELICULAS.ID AS ID_CATEGORIA,
    CATEGORIAS.NOMBRE AS CATEGORIA
    FROM PELICULAS INNER JOIN CATEGORIAS ON CATEGORIAS.ID= PELICULAS.ID_CATEGORIA;
   
/* TRIGGERS */

CREATE OR REPLACE TRIGGER FAVORITAS_INSERT AFTER INSERT ON PELICULAS
FOR EACH ROW
BEGIN
    IF :NEW.CALIFICACION >= 8 THEN
        INSERT INTO FAVORITAS(TITULO, CALIFICACION) VALUES(:NEW.TITULO, :NEW.CALIFICACION);
    END IF;
END;

CREATE OR REPLACE TRIGGER FAVORITAS_UPDATE AFTER UPDATE OF CALIFICACION ON PELICULAS
FOR EACH ROW
DECLARE
CONTADOR INT;
BEGIN
    SELECT COUNT(*) INTO CONTADOR FROM FAVORITAS WHERE TITULO = :OLD.TITULO;
    IF CONTADOR > 0 THEN
        IF :NEW.CALIFICACION < 8 THEN
            DELETE FAVORITAS WHERE TITULO = :OLD.TITULO;
        ELSE
            UPDATE FAVORITAS SET CALIFICACION = :NEW.CALIFICACION WHERE TITULO = :OLD.TITULO;
        END IF;
    ELSE
        INSERT INTO FAVORITAS(TITULO, CALIFICACION) VALUES(:NEW.TITULO, :NEW.CALIFICACION);
    END IF;
END;

CREATE OR REPLACE TRIGGER ELIMINAR_CATEGORIAS AFTER DELETE ON CATEGORIAS
FOR EACH ROW
BEGIN
    DELETE PELICULAS WHERE ID_CATEGORIA = ID;
    DELETE FAVORITAS WHERE TITULO = TITULO;
END;

/* PROCEDIMIENTOS ALMACENADOS */

CREATE OR REPLACE PROCEDURE PAWATCHLIST(VTITULO IN VARCHAR) AS
CONTADOR INT := 0;
BEGIN
    SELECT COUNT(*) INTO CONTADOR FROM WATCHLIST WHERE TITULO = VTITULO;
    IF CONTADOR > 0 THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Ya tienes esa película en tu watchlist');
    ELSE
        INSERT INTO WATCHLIST(TITULO) VALUES(VTITULO);
        DBMS_OUTPUT.PUT_LINE('Película registrada en tu watchlist corresctamente');
    END IF;
END;