INSERT INTO orders (order_status, total_price) VALUES
('PENDING', 150.00),
('CONFIRMED', 320.50),
('CANCELLED', 275.75),
('CONFIRMED', 499.99),
('PENDING', 180.00),
('CANCELLED', 560.00),
('CONFIRMED', 290.30),
('PENDING', 610.00),
('CANCELLED', 199.99),
('CONFIRMED', 440.25),
('PENDING', 310.00),
('CANCELLED', 525.75),
('CONFIRMED', 410.00),
('PENDING', 330.10),
('CANCELLED', 678.00),
('CONFIRMED', 720.60),
('PENDING', 260.00),
('CANCELLED', 485.95),
('CONFIRMED', 390.00),
('PENDING', 810.40);


INSERT INTO order_item (product_id, quantity, order_id) VALUES
(101, 2, 1),
(102, 1, 1),

(103, 1, 2),
(104, 3, 2),

(105, 2, 3),

(106, 4, 4),
(107, 1, 4),
(108, 1, 4),

(109, 3, 5),

(110, 2, 6),
(111, 1, 6),

(112, 2, 7),

(113, 1, 8),
(114, 2, 8),

(115, 1, 9),

(116, 3, 10),
(117, 2, 10),

(118, 2, 11),

(119, 1, 12),

(120, 4, 13),
(121, 1, 13),

(122, 3, 14),

(123, 2, 15),

(124, 1, 16),
(125, 2, 16),

(126, 2, 17),

(127, 1, 18),

(128, 3, 19),
(129, 1, 19),

(130, 2, 20);
