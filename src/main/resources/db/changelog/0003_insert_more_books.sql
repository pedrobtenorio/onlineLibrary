
--changeset pedro_tenorio:inserting more books
INSERT INTO books (title, author, genre, synopsis)
VALUES ('The Great Gatsby', 'F. Scott Fitzgerald', 'Classic', 'A story of the decadent Jazz Age, where lavish parties and excess conceal darker truths about love and society.'); --id 4

INSERT INTO books (title, author, genre, synopsis)
VALUES ('The Catcher in the Rye', 'J.D. Salinger', 'Coming-of-age', 'Holden Caulfield''s journey through New York City captures teenage alienation and rebellion.'); --id 5

INSERT INTO books (title, author, genre, synopsis)
VALUES ('The Hobbit', 'J.R.R. Tolkien', 'Fantasy', 'Bilbo Baggins embarks on a quest to reclaim a stolen treasure from the dragon Smaug, encountering trolls, elves, and goblins along the way.'); --id 6

INSERT INTO books (title, author, genre, synopsis)
VALUES ('The Road', 'Cormac McCarthy', 'Post-apocalyptic', 'A father and son''s journey through a bleak, ash-covered landscape in search of survival and hope.'); --id 7

INSERT INTO books (title, author, genre, synopsis)
VALUES ('Moby-Dick', 'Herman Melville', 'Adventure', 'The epic tale of Captain Ahab''s obsessive quest for revenge against the elusive white whale, Moby Dick.'); --id 8

INSERT INTO books (title, author, genre, synopsis)
VALUES ('Frankenstein', 'Mary Shelley', 'Gothic Horror', 'A groundbreaking novel exploring themes of creation, ambition, and the consequences of playing god.'); --id 9

INSERT INTO books (title, author, genre, synopsis)
VALUES ('The Handmaid''s Tale', 'Margaret Atwood', 'Dystopian', 'In a totalitarian society, fertile women are forced into servitude as "handmaids" to bear children for the ruling class.'); --id 10


INSERT INTO book_copies (book_id, status) VALUES (4, 'AVAILABLE');
INSERT INTO book_copies (book_id, status) VALUES (5, 'AVAILABLE');
INSERT INTO book_copies (book_id, status) VALUES (6, 'AVAILABLE');
INSERT INTO book_copies (book_id, status) VALUES (7, 'AVAILABLE');
INSERT INTO book_copies (book_id, status) VALUES (8, 'AVAILABLE');
INSERT INTO book_copies (book_id, status) VALUES (9, 'AVAILABLE');
INSERT INTO book_copies (book_id, status) VALUES (10, 'AVAILABLE');
INSERT INTO book_copies (book_id, status) VALUES (10, 'AVAILABLE');
INSERT INTO book_copies (book_id, status) VALUES (4, 'AVAILABLE');
INSERT INTO book_copies (book_id, status) VALUES (6, 'AVAILABLE');
INSERT INTO book_copies (book_id, status) VALUES (7, 'AVAILABLE');
INSERT INTO book_copies (book_id, status) VALUES (3, 'AVAILABLE');

-- Reviews for book ID 1
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (1, 1, 4, 'Great book!');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (2, 1, 5, 'Absolutely loved it.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (3, 1, 4, 'Well-written and engaging.');

-- Reviews for book ID 2
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (1, 2, 3, 'Interesting plot.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (2, 2, 2, 'Didn''t meet expectations.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (3, 2, 4, 'Enjoyed the characters.');

-- Reviews for book ID 3
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (1, 3, 5, 'One of my favorites!');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (2, 3, 5, 'Beautifully written.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (3, 3, 4, 'Captivating storyline.');

-- Reviews for book ID 4
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (1, 4, 3, 'Decent read.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (2, 4, 2, 'Not my cup of tea.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (3, 4, 3, 'Okay, but nothing special.');

-- Reviews for book ID 5
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (1, 5, 4, 'Kept me hooked.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (2, 5, 5, 'Couldn''t put it down!');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (3, 5, 4, 'Highly recommended.');

-- Reviews for book ID 6
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (1, 6, 5, 'Fantastic journey.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (2, 6, 4, 'Imaginative and thrilling.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (3, 6, 5, 'A must-read for fantasy fans.');

-- Reviews for book ID 7
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (1, 7, 4, 'Compelling storyline with unexpected twists.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (2, 7, 2, 'Disappointing ending.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (3, 7, 5, 'Couldn''t stop reading.');

-- Reviews for book ID 8
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (1, 8, 5, 'Classic romance at its finest.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (2, 8, 4, 'Charming characters and witty dialogue.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (3, 8, 3, 'Slow-paced but enjoyable.');

-- Reviews for book ID 9
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (1, 9, 4, 'Thought-provoking and beautifully written.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (2, 9, 5, 'A masterpiece of Gothic literature.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (3, 9, 4, 'Richly atmospheric and haunting.');

-- Reviews for book ID 10
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (1, 10, 3, 'Interesting concept but slow execution.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (2, 10, 2, 'Didn''t connect with the characters.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (3, 10, 4, 'Unique dystopian setting.');

-- Additional reviews for book ID 10
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (1, 10, 4, 'Intriguing and thought-provoking.');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (2, 10, 5, 'Absolutely brilliant!');
INSERT INTO reviews (user_id, book_id, rating, comment) VALUES (3, 10, 3, 'Mixed feelings about the ending.');


