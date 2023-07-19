INSERT INTO users (password, first_name, last_name, age, contact_id)
VALUES
    ('password1', 'Oleg', 'Ivanov', 35, 1),
    ('password2', 'Maxim', 'Letov', 30, 2),
    ('password3', 'Rinat', 'Zakirov', 29, 3),
    ('password4', 'Ruslan', 'Lopuhov', 22, 4),
    ('password5', 'Alisa', 'Koshkina', 19, 5);

INSERT INTO contacts (user_id, phone_number, email, address)
VALUES
    (1, '79289448734', 'email1@example.com', 'Address1'),
    (2, '79656536654', 'email2@example.com', 'Address2'),
    (3, '79287557782', 'email3@example.com', 'Address3'),
    (4, '79285335543', 'email4@example.com', 'Address4'),
    (5, '79284889472', 'email5@example.com', 'Address5');
