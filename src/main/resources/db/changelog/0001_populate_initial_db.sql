
--changeset pedro_tenorio:populating TABLES

INSERT INTO users (username, email) VALUES ('john_doe', 'john.doe@example.com');
INSERT INTO users (username, email) VALUES ('jane_smith', 'jane.smith@example.com');
INSERT INTO users (username, email) VALUES ('alex_jones', 'alex.jones@example.com');

INSERT INTO books (title, author, genre, synopsis) VALUES ('To Kill a Mockingbird', 'Harper Lee', 'Fiction', 'The unforgettable novel of a childhood in a sleepy Southern town and the crisis of conscience that rocked it.');
INSERT INTO books (title, author, genre, synopsis) VALUES ('1984', 'George Orwell', 'Dystopian', 'A dystopian novel set in Airstrip One, a province of the superstate Oceania in a world of perpetual war, omnipresent government surveillance, and public manipulation.');
INSERT INTO books (title, author, genre, synopsis) VALUES ('Pride and Prejudice', 'Jane Austen', 'Romance', 'A classic novel of manners and love in early nineteenth-century England.');


INSERT INTO book_copies (book_id, status) VALUES (1, 'BORROWED');
INSERT INTO book_copies (book_id, status) VALUES (1, 'BORROWED');
INSERT INTO book_copies (book_id, status) VALUES (2, 'BORROWED');
INSERT INTO book_copies (book_id, status) VALUES (3, 'AVAILABLE');

INSERT INTO reservations (user_id, book_copy_id, reservation_date, due_date, active) VALUES (1, 1, '2024-04-28', '2024-05-05', true);
INSERT INTO reservations (user_id, book_copy_id, reservation_date, due_date, active) VALUES (2, 2, '2024-04-29', '2024-05-06', true);
INSERT INTO reservations (user_id, book_copy_id, reservation_date, due_date, active) VALUES (3, 3, '2024-04-30', '2024-05-07', true);

