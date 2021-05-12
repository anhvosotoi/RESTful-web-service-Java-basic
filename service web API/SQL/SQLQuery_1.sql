CREATE DATABASE ngocanhiot
GO

-- USE master
-- GO
-- ALTER DATABASE ngocanhiot
-- SET SINGLE_USER WITH ROLLBACK IMMEDIATE
-- GO
-- DROP DATABASE ngocanhiot
-- GO

USE ngocanhiot
GO

-- Create tables
CREATE TABLE users (
    uname VARCHAR(50),
    passwd VARCHAR(50) NOT NULL,
    acctype BIT NOT NULL DEFAULT 0, -- 1: admin | 0: devices

    PRIMARY KEY(uname)
)
GO

CREATE TABLE devices (
    cid INT IDENTITY(1,1),
    devicename NVARCHAR(50) NOT NULL,
    devicetype BIT NOT NULL, -- 0: input | 1: output
    datatype BIT NOT NULL, --0: digital | 1: analog
    bitvalue BIT NOT NULL DEFAULT 0,
    decimalvalue FLOAT NOT NULL DEFAULT 0,

    PRIMARY KEY(cid)
)
GO
