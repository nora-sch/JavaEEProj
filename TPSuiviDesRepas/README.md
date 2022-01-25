

**WebContent/META-INF/context.xml example**

```java
<?xml version="1.0" encoding="UTF-8"?>
<Context>

<Resource 
name="jdbc/pool_cnx"
driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver"
type="javax.sql.DataSource"

url="jdbc:sqlserver://localhost;databasename=DB_NAME"
username="username"
password="password"

maxTotal="100"
maxIdle="30"
maxWaitMillis="10000"

/>

</Context>
```

**Creation of the tables in SQL**

```java
CREATE TABLE REPAS
(
	id int identity(1,1) CONSTRAINT PK_REPAS PRIMARY KEY,
	date_repas date NOT NULL,
	heure_repas time NOT NULL
);

CREATE TABLE ALIMENTS
(
	id int identity(1,1) CONSTRAINT PK_ALIMENTS PRIMARY KEY,
	nom varchar(50) NOT NULL,
	id_repas int NOT NULL
);

ALTER TABLE ALIMENTS ADD CONSTRAINT
	FK_ALIMENTS_REPAS FOREIGN KEY(id_repas) REFERENCES REPAS(id);
```