-- Запит на отримання списку клієнтів з інформацією про їх об'єкти та їх угоди
SELECT c.First_name, c.Second_name, o.Street, o.Street_num, a.Agreement_date, a.Agreement_price
FROM Client c
JOIN Agreement a ON c.Client_id = a.Client_id
JOIN Object o ON a.Object_id = o.Object_id;

-- Отримати середню ціну об'єктів нерухомості на кожній вулиці
SELECT Street, ROUND(AVG(Price::numeric), 2) AS Average_Price
FROM Object
GROUP BY Street;


--Отримати загальну кількість об'єктів нерухомості та кількість погоджених угод для кожного клієнта
SELECT c.Client_id, c.First_name, c.Second_name, COUNT(o.Object_id) AS Total_Objects, COUNT(a.Agreement_id) AS Total_Agreements
FROM Client c
LEFT JOIN Agreement a ON c.Client_id = a.Client_id
LEFT JOIN Object o ON a.Object_id = o.Object_id
GROUP BY c.Client_id, c.First_name, c.Second_name;


--Отримати кількість об'єктів нерухомості на кожній вулиці, упорядкованих за спаданням
SELECT Street, COUNT(Object_id) AS Total_Objects
FROM Object
GROUP BY Street
ORDER BY Total_Objects DESC;



--Інформація про клієнтів, а саме про кількість їх угод та сумарну вартість складання цих угод. А також сумма об'єктів, які вони узгодили 
SELECT 
    c.Client_id,
    c.First_name,
    c.Second_name,
    COUNT(o.Object_id) AS Total_Objects,
    COUNT(a.Agreement_id) AS Total_Agreements,
    COALESCE(SUM(a.Agreement_price), 0) AS Total_Agreement_Price,
    SUM(o.Price) AS Total_House_Price
FROM 
    Client c
LEFT JOIN 
    Agreement a ON c.Client_id = a.Client_id
LEFT JOIN 
    Object o ON a.Object_id = o.Object_id
GROUP BY 
    c.Client_id, c.First_name, c.Second_name
ORDER BY 
    Total_Agreement_Price DESC;
	












