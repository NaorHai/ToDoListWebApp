CREATE TABLE IF NOT EXISTS ToDo.User
(
    userId VARCHAR(128) PRIMARY KEY NOT NULL,
    email VARCHAR(50) NOT NULL,
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    creationDate DATE NOT NULL
);
CREATE UNIQUE INDEX User_userId_uindex ON User (userId);


CREATE TABLE IF NOT EXISTS ToDo.Item
(
    itemId VARCHAR(128) PRIMARY KEY NOT NULL,
    userId VARCHAR(128) NOT NULL,
    title VARCHAR(50) NOT NULL,
    content VARCHAR(200),
    creationDate DATE,
    foreign key (userId) references User(userId)
);
CREATE UNIQUE INDEX Item_itemId_uindex ON Item (itemId);



INSERT INTO ToDo.User(userId, email, firstName, lastName, creationDate) VALUES
    (UUID(), 'pap@pap.com', 'pap', 'ushe', NOW()),
    (UUID(), 'naor@naor.com', 'na', 'or', NOW()),
    (UUID(), 'terry@terry.com', 'ter', 'ry', NOW()),
    (UUID(), 'tal@pap.tal', 'ta', 'l', NOW()),
    (UUID(), 'shlomi@shlomi.com', 'shl', 'omi', NOW()),
    (UUID(), 'ktzv@ktzv.com', 'kt', 'zv', NOW());