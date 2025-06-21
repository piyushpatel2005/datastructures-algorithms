# LeetCode SQL Challenges 2

## Problem 1045: Customrers Who Bought All Products

Table: `Customer`

| Column Name | Type    |
|:------------|:--------|
| customer_id | int     |
| product_key | int     |

This table may contain duplicates rows.
`customer_id` is not NULL.
`product_key` is a foreign key (reference column) to `Product` table.

Table: `Product`

| Column Name | Type    |
|:------------|:--------|
| product_key | int     |

`product_key` is the primary key (column with unique values) for this table.

Write a solution to report the customer `id`s from the `Customer` table that bought all the products in the `Product` table.
Return the result table in any order.
The result format is in the following example.

### Example 1:

**Input:**
`Customer` table:

| customer_id | product_key |
|:------------|:------------|
| 1           | 5           |
| 2           | 6           |
| 3           | 5           |
| 3           | 6           |
| 1           | 6           |

`Product` table:

| product_key |
|:------------|
| 5           |
| 6           |

**Output:**

| customer_id |
|:------------|
| 1           |
| 3           |

**Explanation:**
The customers who bought all the products (5 and 6) are customers with IDs 1 and 3.

```sql
SELECT customer_id 
FROM Customer c, Product p
WHERE c.product_key = p.product_key 
GROUP BY customer_id
HAVING COUNT(DISTINCT c.product_key) = (
    SELECT COUNT(*) 
    FROM Product 
)
```

## Problem 1050: Actors and Directors Who Cooperated At Least Three Times

Table: `ActorDirector`

| Column Name | Type    |
|:------------|:--------|
| actor_id    | int     |
| director_id | int     |
| timestamp   | int     |

`timestamp` is the primary key column for this table.

Write a SQL query for a report that provides the pairs (`actor_id`, `director_id`) where the actor has cooperated with the director at least three times.

Return the result table in any order.
The query result format is in the following example.

### Example 1:

**Input:**
ActorDirector table:

| actor_id    | director_id | timestamp   |
|:------------|:------------|:------------|
| 1           | 1           | 0           |
| 1           | 1           | 1           |
| 1           | 1           | 2           |
| 1           | 2           | 3           |
| 1           | 2           | 4           |
| 2           | 1           | 5           |
| 2           | 1           | 6           |

**Output:**

| actor_id    | director_id |
|:------------|:------------|
| 1           | 1           |

**Explanation:** The only pair is (1, 1) where they cooperated exactly 3 times.

```sql
SELECT actor_id, director_id FROM ActorDirector
    GROUP BY actor_id, director_id
    HAVING COUNT(1) >= 3;
```

## Problem 1068: Product Sales Analysis |

Table: `Sales`

| Column Name | Type  |
|:------------|:------|
| sale_id     | int   |
| product_id  | int   |
| year        | int   |
| quantity    | int   |
| price       | int   |

(`sale_id`, `year`) is the primary key (combination of columns with unique values) of this table.
`product_id` is a foreign key (reference column) to `Product` table.
Each row of this table shows a sale on the product `product_id` in a certain `year`.
Note that the `price` is per unit.

Table: `Product`

| Column Name  | Type     |
|:-------------|:---------|
| product_id   | int      |
| product_name | varchar  |

`product_id` is the primary key (column with unique values) of this table.
Each row of this table indicates the product name of each product.

Write a solution to report the `product_name`, `year`, and `price` for each `sale_id` in the Sales table.

Return the resulting table in any order.
The result format is in the following example.

### Example 1:

**Input:**
`Sales` table:

| sale_id | product_id | year | quantity | price  |
|:--------|:-----------|:-----|:---------|:-------|
| 1       | 100        | 2008 | 10       | 5000   |
| 2       | 100        | 2009 | 12       | 5000   |
| 7       | 200        | 2011 | 15       | 9000   |

`Product` table:

| product_id | product_name |
|:-----------|:-------------|
| 100        | Nokia        |
| 200        | Apple        |
| 300        | Samsung      |

**Output:**

| product_name | year  | price  |
|:-------------|:------|:-------|
| Nokia        | 2008  | 5000   |
| Nokia        | 2009  | 5000   |
| Apple        | 2011  | 9000   |

**Explanation:**
From sale_id = 1, we can conclude that Nokia was sold for 5000 in the year 2008.
From sale_id = 2, we can conclude that Nokia was sold for 5000 in the year 2009.
From sale_id = 7, we can conclude that Apple was sold for 9000 in the year 2011.

```sql
SELECT product_name, year, price FROM Sales
    INNER JOIN
    Product ON Sales.product_id = Product.product_id
    ORDER BY year ASC;
```

## Problem 1069: Product Sales Analysis II

Table: `Sales`

| Column Name | Type  |
|:------------|:------|
| sale_id     | int   |
| product_id  | int   |
| year        | int   |
| quantity    | int   |
| price       | int   |

(`sale_id`, `year`) is the primary key (combination of columns with unique values) of this table.
`product_id` is a foreign key (reference column) to Product table.
Each row of this table shows a sale on the product `product_id` in a certain year.
Note that the price is per unit.

Table: `Product`

| Column Name  | Type    |
|:-------------|:--------|
| product_id   | int     |
| product_name | varchar |

`product_id` is the primary key (column with unique values) of this table.
Each row of this table indicates the product name of each product.

Write a solution that reports the total `quantity` sold for every product id.

Return the resulting table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Sales` table:

| sale_id | product_id | year | quantity | price |
|:--------|:-----------|:-----|:---------|:------|
| 1       | 100        | 2008 | 10       | 5000  |
| 2       | 100        | 2009 | 12       | 5000  |
| 7       | 200        | 2011 | 15       | 9000  |

`Product` table:

| product_id | product_name |
|:-----------|:-------------|
| 100        | Nokia        |
| 200        | Apple        |
| 300        | Samsung      |

**Output:**

| product_id   | total_quantity |
|:-------------|:---------------|
| 100          | 22             |
| 200          | 15             |

```sql
SELECT s.product_id, SUM(quantity) total_quantity
    FROM Sales s
    JOIN Product p ON s.product_id = p.product_id
    GROUP BY s.product_id;
```

## Problem 1070: Product Sales Analysis III

Table: `Sales`

| Column Name | Type  |
|:------------|:------|
| sale_id     | int   |
| product_id  | int   |
| year        | int   |
| quantity    | int   |
| price       | int   |

(`sale_id`, `year`) is the primary key (combination of columns with unique values) of this table.
`product_id` is a foreign key (reference column) to Product table.
Each row of this table shows a sale on the product `product_id` in a certain `year`.
Note that the price is per unit.

Table: `Product`

| Column Name  | Type    |
|:-------------|:--------|
| product_id   | int     |
| product_name | varchar |

`product_id` is the primary key (column with unique values) of this table.
Each row of this table indicates the product name of each product.

Write a solution to select the `product_id`, `year`, `quantity`, and `price` for the first year of every product sold.

Return the resulting table in any order.
The result format is in the following example.

### Example 1:

**Input:**
`Sales` table:

| sale_id | product_id | year | quantity | price |
|:--------|:-----------|:-----|:---------|:------|
| 1       | 100        | 2008 | 10       | 5000  |
| 2       | 100        | 2009 | 12       | 5000  |
| 7       | 200        | 2011 | 15       | 9000  |

`Product` table:

| product_id | product_name |
|:-----------|:-------------|
| 100        | Nokia        |
| 200        | Apple        |
| 300        | Samsung      |

**Output:**

| product_id | first_year | quantity | price |
|:-----------|:-----------|:---------|:------|
| 100        | 2008       | 10       | 5000  |
| 200        | 2011       | 15       | 9000  |

```sql
SELECT product_id, year AS first_year, quantity, price
    FROM Sales
    WHERE (product_id, year) IN (
        SELECT product_id, MIN(year) year
            FROM Sales
            GROUP BY product_id
    );
    
WITH product_partitions AS (
    SELECT product_id, year AS first_year, quantity, price,
        DENSE_RANK() OVER (PARTITION BY product_id ORDER BY year) row_num
        FROM Sales
) SELECT product_id, first_year, quantity, price
    FROM product_partitions
    WHERE row_num = 1;
```

## Problem 1075: Project Employees I

Table: `Project`

| Column Name | Type    |
|:------------|:--------|
| project_id  | int     |
| employee_id | int     |

(`project_id`, `employee_id`) is the primary key of this table.
`employee_id` is a foreign key to Employee table.
Each row of this table indicates that the employee with `employee_id` is working on the project with `project_id`.

Table: `Employee`

| Column Name      | Type    |
|:-----------------|---------|
| employee_id      | int     |
| name             | varchar |
| experience_years | int     |

`employee_id` is the primary key of this table. It's guaranteed that `experience_years` is not `NULL`.
Each row of this table contains information about one employee.

Write an SQL query that reports the average experience years of all the employees for each project, rounded to `2` digits.

Return the result table in any order.
The query result format is in the following example.

### Example 1:

**Input:**
`Project` table:

| project_id  | employee_id |
|:------------|-------------|
| 1           | 1           |
| 1           | 2           |
| 1           | 3           |
| 2           | 1           |
| 2           | 4           |

`Employee` table:

| employee_id | name   | experience_years |
|:------------|:-------|:-----------------|
| 1           | Khaled | 3                |
| 2           | Ali    | 2                |
| 3           | John   | 1                |
| 4           | Doe    | 2                |

**Output:**

| project_id  | average_years |
|:------------|---------------|
| 1           | 2.00          |
| 2           | 2.50          |

Explanation: The average experience years for the first project is `(3 + 2 + 1) / 3 = 2.00` and for the second project is `(3 + 2) / 2 = 2.50`

```sql
SELECT p.project_id, ROUND(AVG(e.experience_years), 2) average_years FROM Project p
    JOIN Employee e ON p.employee_id = e.employee_id
    GROUP BY p.project_id
```

## Problem 1076: Project Employees II

Table: `Project`

| Column Name | Type    |
|:------------|:--------|
| project_id  | int     |
| employee_id | int     |

(`project_id`, `employee_id`) is the primary key (combination of columns with unique values) of this table.
`employee_id` is a foreign key (reference column) to `Employee` table.
Each row of this table indicates that the employee with `employee_id` is working on the project with `project_id`.

Table: `Employee`

| Column Name      | Type    |
|:-----------------|---------|
| employee_id      | int     |
| name             | varchar |
| experience_years | int     |

`employee_id` is the primary key (column with unique values) of this table.
Each row of this table contains information about one employee.

Write a solution to report all the projects that have the most employees.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Project` table:

| project_id  | employee_id |
|:------------|-------------|
| 1           | 1           |
| 1           | 2           |
| 1           | 3           |
| 2           | 1           |
| 2           | 4           |

`Employee` table:

| employee_id | name   | experience_years |
|:------------|:-------|:-----------------|
| 1           | Khaled | 3                |
| 2           | Ali    | 2                |
| 3           | John   | 1                |
| 4           | Doe    | 2                |

**Output:**

| project_id  |
|:------------|
| 1           |

**Explanation:** The first project has 3 employees while the second one has 2.

```sql
SELECT project_id FROM Project 
    GROUP BY project_id
    HAVING COUNT(employee_id) = (
        SELECT COUNT(employee_id) FROM Project 
            GROUP BY project_id 
            ORDER BY 1 DESC 
            LIMIT 1
    )
```

## Problem 1077: Project Employees III

Table: `Project`

| Column Name | Type    |
|:------------|:--------|
| project_id  | int     |
| employee_id | int     |

(`project_id`, `employee_id`) is the primary key (combination of columns with unique values) of this table.
`employee_id` is a foreign key (reference column) to Employee table.
Each row of this table indicates that the employee with `employee_id` is working on the project with `project_id`.

Table: `Employee`

| Column Name      | Type    |
|:-----------------|---------|
| employee_id      | int     |
| name             | varchar |
| experience_years | int     |

`employee_id` is the primary key (column with unique values) of this table.
Each row of this table contains information about one employee.

Write a solution to report the most experienced employees in each project. In case of a tie, report all employees with the maximum number of experience years.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Project` table:

| project_id  | employee_id |
|:------------|-------------|
| 1           | 1           |
| 1           | 2           |
| 1           | 3           |
| 2           | 1           |
| 2           | 4           |

`Employee` table:

| employee_id | name   | experience_years |
|:------------|:-------|:-----------------|
| 1           | Khaled | 3                |
| 2           | Ali    | 2                |
| 3           | John   | 3                |
| 4           | Doe    | 2                |

**Output:**

| project_id  | employee_id   |
|:------------|---------------|
| 1           | 1             |
| 1           | 3             |
| 2           | 1             |

**Explanation:** Both employees with id `1` and `3` have the most experience among the employees of the first project. For 
the second project, the employee with id `1` has the most experience.

```sql
SELECT project_id, employee_id FROM (
    SELECT p.project_id, e.employee_id, e.name, e.experience_years, 
        DENSE_RANK() OVER (PARTITION BY p.project_id ORDER BY e.experience_years DESC) AS 'dense_rank' 
        FROM Project p
        JOIN Employee e ON p.employee_id = e.employee_id
    ) cte
        WHERE cte.dense_rank=1;
```

## Problem 1082: Sales Analysis I

Table: `Product`

| Column Name  | Type    |
|:-------------|---------|
| product_id   | int     |
| product_name | varchar |
| unit_price   | int     |

`product_id` is the primary key (column with unique values) of this table.
Each row of this table indicates the name and the price of each product.

Table: `Sales`

| Column Name | Type    |
|:------------|---------|
| seller_id   | int     |
| product_id  | int     |
| buyer_id    | int     |
| sale_date   | date    |
| quantity    | int     |
| price       | int     |

This table can have repeated rows.
`product_id` is a foreign key (reference column) to the `Product` table.
Each row of this table contains some information about one sale.

Write a solution that reports the best seller by total sales price, If there is a tie, report them all.

Return the result table in any order.
The result format is in the following example.

### Example 1:

**Input:**
`Product` table:

| product_id | product_name | unit_price |
|:-----------|:-------------|------------|
| 1          | S8           | 1000       |
| 2          | G4           | 800        |
| 3          | iPhone       | 1400       |

`Sales` table:

| seller_id | product_id | buyer_id | sale_date  | quantity | price |
|:----------|:-----------|----------|------------|----------|-------|
| 1         | 1          | 1        | 2019-01-21 | 2        | 2000  |
| 1         | 2          | 2        | 2019-02-17 | 1        | 800   |
| 2         | 2          | 3        | 2019-06-02 | 1        | 800   |
| 3         | 3          | 4        | 2019-05-13 | 2        | 2800  |

**Output:**

| seller_id   |
|:------------|
| 1           |
| 3           |

**Explanation:** Both sellers with id 1 and 3 sold products with the most total price of 2800.

```sql
WITH sales_by_seller AS (
    SELECT seller_id, SUM(price) total_sales FROM Sales GROUP BY seller_id
)
SELECT seller_id FROM sales_by_seller WHERE total_sales = (SELECT MAX(total_sales) FROM sales_by_seller);
```

## Problem 1083: Sales Analysis II

Table: `Product`

| Column Name  | Type    |
|:-------------|---------|
| product_id   | int     |
| product_name | varchar |
| unit_price   | int     |

`product_id` is the primary key (column with unique values) of this table.
Each row of this table indicates the name and the price of each product.

Table: `Sales`

| Column Name | Type    |
|:------------|---------|
| seller_id   | int     |
| product_id  | int     |
| buyer_id    | int     |
| sale_date   | date    |
| quantity    | int     |
| price       | int     |

This table might have repeated rows.
`product_id` is a foreign key (reference column) to the `Product` table.
`buyer_id` is never NULL.
`sale_date` is never NULL.
Each row of this table contains some information about one sale.

Write a solution to report the buyers who have bought `S8` but not `iPhone`. Note that `S8` and `iPhone` are products presented in the `Product` table.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Product` table:

| product_id | product_name | unit_price |
|:-----------|:-------------|------------|
| 1          | S8           | 1000       |
| 2          | G4           | 800        |
| 3          | iPhone       | 1400       |

`Sales` table:

| seller_id | product_id | buyer_id | sale_date  | quantity | price |
|:----------|:-----------|----------|------------|----------|-------|
| 1         | 1          | 1        | 2019-01-21 | 2        | 2000  |
| 1         | 2          | 2        | 2019-02-17 | 1        | 800   |
| 2         | 1          | 3        | 2019-06-02 | 1        | 800   |
| 3         | 3          | 3        | 2019-05-13 | 2        | 2800  |

**Output:**

| buyer_id    |
|:------------|
| 1           |

**Explanation:** The buyer with id `1` bought an 'S8' but did not buy an 'iPhone'. The buyer with id `3` bought both.

```sql
WITH product_sales AS (
    SELECT p.product_id, p.product_name, s.buyer_id FROM Product p
        JOIN Sales s ON p.product_id = s.product_id
) SELECT DISTINCT buyer_id FROM product_sales
    WHERE product_name = 'S8' AND buyer_id NOT IN (
        SELECT DISTINCT buyer_id FROM product_sales WHERE product_name = 'iPhone'
    );
```

## Problem 1084: Sales Analysis III

Table: `Product`

| Column Name  | Type    |
|:-------------|---------|
| product_id   | int     |
| product_name | varchar |
| unit_price   | int     |

`product_id` is the primary key (column with unique values) of this table.
Each row of this table indicates the name and the price of each product.

Table: `Sales`

| Column Name | Type    |
|:------------|---------|
| seller_id   | int     |
| product_id  | int     |
| buyer_id    | int     |
| sale_date   | date    |
| quantity    | int     |
| price       | int     |

This table can have duplicate rows.
`product_id` is a foreign key (reference column) to the `Product` table.
Each row of this table contains some information about one sale.

Write a solution to report the products that were only sold in the first quarter of 2019. That is, between `2019-01-01` and `2019-03-31` inclusive.

Return the result table in any order.
The result format is in the following example.

### Example 1:

**Input:**
`Product` table:

| product_id | product_name | unit_price |
|:-----------|:-------------|------------|
| 1          | S8           | 1000       |
| 2          | G4           | 800        |
| 3          | iPhone       | 1400       |

`Sales` table:

| seller_id | product_id | buyer_id | sale_date  | quantity | price |
|:----------|:-----------|----------|------------|----------|-------|
| 1         | 1          | 1        | 2019-01-21 | 2        | 2000  |
| 1         | 2          | 2        | 2019-02-17 | 1        | 800   |
| 2         | 2          | 3        | 2019-06-02 | 1        | 800   |
| 3         | 3          | 4        | 2019-05-13 | 2        | 2800  |

**Output:**

| product_id  | product_name |
|:------------|:-------------|
| 1           | S8           |

**Explanation:**
The product with id `1` was only sold in the spring of 2019.
The product with id `2` was sold in the spring of 2019 but was also sold after the spring of 2019.
The product with id `3` was sold after spring 2019.
We return only product `1` as it is the product that was only sold in the spring of 2019.

```sql
SELECT DISTINCT p.product_id, p.product_name FROM Product p
    JOIN Sales s ON p.product_id = s.product_id
    GROUP BY p.product_id
    HAVING MIN(sale_date) >= '2019-01-01' AND MAX(sale_date) <= '2019-03-31';
```

In this case, we need only products which were sold in the first quarter and not any other quarter. So, simple 
`WHERE` clause may not work here.

## Problem 1097: Game Play Analysis V

Table: `Activity`

| Column Name  | Type    |
|:-------------|---------|
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |

(`player_id`, `event_date`) is the primary key (combination of columns with unique values) of this table.
This table shows the activity of players of some games.
Each row is a record of a player who logged in and played a number of games (possibly 0) before logging out on someday using some device.

The install date of a player is the first login day of that player.

We define day one retention of some date `x` to be the number of players whose install date is `x` and they logged back in on the day right after `x`, divided by the number of players whose install date is `x`, rounded to `2` decimal places.

Write a solution to report for each install date, the number of players that installed the game on that day, and the day one retention.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Activity` table:

| player_id | device_id | event_date | games_played |
|:----------|:----------|------------|--------------|
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-03-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-01 | 0            |
| 3         | 4         | 2016-07-03 | 5            |

**Output:**

| install_dt | installs | Day1_retention |
|:-----------|----------|----------------|
| 2016-03-01 | 2        | 0.50           |
| 2017-06-25 | 1        | 0.00           |

**Explanation:**
Player 1 and 3 installed the game on 2016-03-01 but only player 1 logged back in on 2016-03-02 so the day 1 retention of 2016-03-01 is 1 / 2 = 0.50
Player 2 installed the game on 2017-06-25 but didn't log back in on 2017-06-26 so the day 1 retention of 2017-06-25 is 0 / 1 = 0.00

The steps to solution include below
1. Identify the first login date for each player

```sql
WITH first_logins AS (
    SELECT player_id,
    MIN(event_date) AS first_login_date
    FROM Activity 
    GROUP BY player_id
) SELECT * FROM first_logins;
```


2. Identify the players who logged back in on the day after their first login date.

```sql
consec_login_info AS (SELECT
    F.player_id,
    (CASE
      WHEN A.player_id IS NULL THEN 0
      ELSE 1
    END) AS logged_in_consecutively,
    F.first_login_date
  FROM
    first_logins F
    LEFT JOIN Activity A ON F.player_id = A.player_id
    AND F.first_login = DATE_SUB(A.event_date, INTERVAL 1 DAY)
    ) SELECT * FROM consec_login_info;
```

3. Group the information by first login date to calculate the day 1 retention and number of installs.

```sql
SELECT
  C.first_login AS install_dt,
  COUNT(C.player_id) AS installs,
  ROUND(
    SUM(C.logged_in_consecutively)
    / COUNT(C.player_id)
  , 2) AS Day1_Retention
FROM
  consec_login_info C
GROUP BY
  C.first_login;
```

The complete solution to this problem is below.

```sql
WITH first_logins AS (
  SELECT
    A.player_id,
    MIN(A.event_date) AS first_login
  FROM
    Activity A
  GROUP BY
    A.player_id
), consec_login_info AS (
  SELECT
    F.player_id,
    (CASE
      WHEN A.player_id IS NULL THEN 0
      ELSE 1
    END) AS logged_in_consecutively,
    F.first_login
  FROM
    first_logins F
    LEFT JOIN Activity A ON F.player_id = A.player_id
    AND F.first_login = DATE_SUB(A.event_date, INTERVAL 1 DAY)
)
SELECT
  C.first_login AS install_dt,
  COUNT(C.player_id) AS installs,
  ROUND(
    SUM(C.logged_in_consecutively)
    / COUNT(C.player_id)
  , 2) AS Day1_Retention
FROM
  consec_login_info C
GROUP BY
  C.first_login;
```

## Problem 1098: Unpopular Books

Table: `Books`

| Column Name    | Type    |
|:---------------|---------|
| book_id        | int     |
| name           | varchar |
| available_from | date    |

`book_id` is the primary key (column with unique values) of this table.

Table: `Orders`

| Column Name    | Type    |
|:---------------|---------|
| order_id       | int     |
| book_id        | int     |
| quantity       | int     |
| dispatch_date  | date    |

`order_id` is the primary key (column with unique values) of this table.
`book_id` is a foreign key (reference column) to the Books table.

Write a solution to report the books that have sold **less than 10 copies** in the last year, excluding books that have 
been available for less than one month from today. Assume today is `2019-06-23`.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Books` table:

| book_id | name               | available_from |
|:--------|--------------------|----------------|
| 1       | "Kalila And Demna" | 2010-01-01     |
| 2       | "28 Letters"       | 2012-05-12     |
| 3       | "The Hobbit"       | 2019-06-10     |
| 4       | "13 Reasons Why"   | 2019-06-01     |
| 5       | "The Hunger Games" | 2008-09-21     |

`Orders` table:

| order_id | book_id | quantity | dispatch_date |
|:---------|---------|----------|---------------|
| 1        | 1       | 2        | 2018-07-26    |
| 2        | 1       | 1        | 2018-11-05    |
| 3        | 3       | 8        | 2019-06-11    |
| 4        | 4       | 6        | 2019-06-05    |
| 5        | 4       | 5        | 2019-06-20    |
| 6        | 5       | 9        | 2009-02-02    |
| 7        | 5       | 8        | 2010-04-13    |

**Output:**

| book_id   | name               |
|:----------|--------------------|
| 1         | "Kalila And Demna" |
| 2         | "28 Letters"       |
| 5         | "The Hunger Games" |

```sql
SELECT DISTINCT b.book_id, b.name FROM Books b
    LEFT JOIN Orders o ON b.book_id = o.book_id
    WHERE b.available_from < DATE_SUB('2019-06-23', INTERVAL 1 MONTH)
    GROUP BY b.book_id
    HAVING SUM(CASE WHEN dispatch_date BETWEEN DATE_SUB('2019-06-23', INTERVAL 1 YEAR) AND '2019-06-23' THEN quantity 
    ELSE 0 END) < 10;
```

## Problem 1107: New Users Daily Count

Table: `Traffic`

| Column Name   | Type    |
|:--------------|---------|
| user_id       | int     |
| activity      | enum    |
| activity_date | date    |

This table may have duplicate rows.
The `activity` column is an ENUM (category) type of ('login', 'logout', 'jobs', 'groups', 'homepage').

Write a solution to reports for every date within at most 90 days from today, the number of users that logged in for the first time on that date. Assume today is 2019-06-30.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Traffic` table:

| user_id | activity | activity_date |
|:--------|----------|---------------|
| 1       | login    | 2019-05-01    |
| 1       | homepage | 2019-05-01    |
| 1       | logout   | 2019-05-01    |
| 2       | login    | 2019-06-21    |
| 2       | logout   | 2019-06-21    |
| 3       | login    | 2019-01-01    |
| 3       | jobs     | 2019-01-01    |
| 3       | logout   | 2019-01-01    |
| 4       | login    | 2019-06-21    |
| 4       | groups   | 2019-06-21    |
| 4       | logout   | 2019-06-21    |
| 5       | login    | 2019-03-01    |
| 5       | logout   | 2019-03-01    |
| 5       | login    | 2019-06-21    |
| 5       | logout   | 2019-06-21    |

**Output:**

| login_date | user_count  |
|:-----------|:------------|
| 2019-05-01 | 1           |
| 2019-06-21 | 2           |

**Explanation:**
Note that we only care about dates with non zero user count.
The user with id 5 first logged in on 2019-03-01 so he's not counted on 2019-06-21.

```sql
WITH first_logins AS (
    SELECT *, ROW_NUMBER() OVER (PARTITION BY user_id ORDER BY activity_date) 'first_login' 
        FROM Traffic WHERE activity='login'
) SELECT activity_date login_date, 
    COUNT(1) user_count 
    FROM first_logins 
        WHERE first_login=1 AND activity_date >= DATE_SUB('2019-06-30', INTERVAL 90 
   DAY)
        GROUP BY login_date;
```

Using `MIN()` and `HAVING` clause.

```sql
WITH first_logins AS (SELECT user_id, MIN(activity_date) login_date
    FROM Traffic
    WHERE activity='login'
    GROUP BY user_id
    HAVING login_date BETWEEN DATE_SUB('2019-06-30', INTERVAL 90 DAY) AND '2019-06-30'
) SELECT login_date, COUNT(DISTINCT user_id) user_count
    FROM first_logins
    GROUP BY login_date ORDER BY login_date;
```

## Problem 1112: Highest Grade For Each Student

Table: `Enrollments`

| Column Name   | Type    |
|:--------------|---------|
| student_id    | int     |
| course_id     | int     |
| grade         | int     |

(`student_id`, `course_id`) is the primary key (combination of columns with unique values) of this table.
grade is never `NULL`.

Write a solution to find the highest grade with its corresponding course for each student. In case of a tie, you should find the course with the smallest `course_id`.

Return the result table ordered by `student_id` in ascending order.

The result format is in the following example.

### Example 1:

**Input:** `Enrollments` table:

| student_id | course_id | grade |
|:-----------|-----------|-------|
| 2          | 2         | 95    |
| 2          | 3         | 95    |
| 1          | 1         | 90    |
| 1          | 2         | 99    |
| 3          | 1         | 80    |
| 3          | 2         | 75    |
| 3          | 3         | 82    |

**Output:**

| student_id | course_id | grade |
|:-----------|-----------|-------|
| 1          | 2         | 99    |
| 2          | 2         | 95    |
| 3          | 3         | 82    |

```sql
WITH rankings AS (
    SELECT student_id, course_id, grade, 
        RANK() OVER (PARTITION BY student_id ORDER BY grade DESC, course_id) 'ranking' 
        FROM Enrollments
) SELECT student_id, course_id, grade
    FROM rankings WHERE ranking=1;
```

## Problem 1113: Reported Posts

Table: `Actions`

| Column Name   | Type    |
|:--------------|---------|
| user_id       | int     |
| post_id       | int     |
| action_date   | date    |
| action        | enum    |
| extra         | varchar |

This table may have duplicate rows.
The `action` column is an ENUM (category) type of ('view', 'like', 'reaction', 'comment', 'report', 'share').
The `extra` column has optional information about the `action`, such as a reason for the report or a type of reaction.
`extra` is never `NULL`.

Write a solution to report the number of posts reported yesterday for each report reason. Assume today is `2019-07-05`.

Return the result table in any order.
The result format is in the following example.

### Example 1:

**Input:**
`Actions` table:

| user_id | post_id | action_date | action | extra  |
|:--------|---------|-------------|--------|--------|
| 1       | 1       | 2019-07-01  | view   | null   |
| 1       | 1       | 2019-07-01  | like   | null   |
| 1       | 1       | 2019-07-01  | share  | null   |
| 2       | 4       | 2019-07-04  | view   | null   |
| 2       | 4       | 2019-07-04  | report | spam   |
| 3       | 4       | 2019-07-04  | view   | null   |
| 3       | 4       | 2019-07-04  | report | spam   |
| 4       | 3       | 2019-07-02  | view   | null   |
| 4       | 3       | 2019-07-02  | report | spam   |
| 5       | 2       | 2019-07-04  | view   | null   |
| 5       | 2       | 2019-07-04  | report | racism |
| 5       | 5       | 2019-07-04  | view   | null   |
| 5       | 5       | 2019-07-04  | report | racism |

**Output:**

| report_reason | report_count |
|:--------------|--------------|
| spam          | 1            |
| racism        | 2            |

**Explanation:** Note that we only care about report reasons with non-zero number of reports.

```sql
SELECT extra AS report_reason, COUNT(DISTINCT post_id) AS report_count
    FROM Actions
    WHERE action='report' AND action_date >= DATE_SUB('2019-07-05', INTERVAL 1 DAY) AND action_date < '2019-07-05'
    GROUP BY report_reason;
```

## Problem 1126: Active Businesses

Table: `Events`

| Column Name   | Type    |
|:--------------|---------|
| business_id   | int     |
| event_type    | varchar |
| occurrences   | int     |

(`business_id`, `event_type`) is the primary key (combination of columns with unique values) of this table.
Each row in the table logs the info that an event of some type occurred at some business for a number of times.

The average activity for a particular `event_type` is the average occurrences across all companies that have this event.

An active business is a business that has more than one `event_type` such that their occurrences is strictly greater than the average activity for that event.

Write a solution to find all active businesses.
Return the result table in any order.
The result format is in the following example.

### Example 1:

**Input:**
`Events` table:

| business_id | event_type | occurrences |
|:------------|------------|-------------|
| 1           | reviews    | 7           |
| 3           | reviews    | 3           |
| 1           | ads        | 11          |
| 2           | ads        | 7           |
| 3           | ads        | 6           |
| 1           | page views | 3           |
| 2           | page views | 12          |

**Output:**

| business_id |
|:------------|
| 1           |

**Explanation:**  
The average activity for each event can be calculated as follows:
- 'reviews': (7+3)/2 = 5
- 'ads': (11+7+6)/3 = 8
- 'page views': (3+12)/2 = 7.5
  The business with `id=1` has `7` 'reviews' events (more than 5) and `11` 'ads' events (more than 8), so it is an active business.

```sql
WITH avg_occurrences AS (
    SELECT event_type, SUM(occurrences) / COUNT(1) AS avg_occurrences
        FROM Events
        GROUP BY event_type
) SELECT e.business_id FROM avg_occurrences a
    JOIN Events e ON a.event_type = e.event_type
    WHERE e.occurrences > a.avg_occurrences
    GROUP BY business_id
    HAVING COUNT(business_id) > 1;
```

## Problem 1127: User Purchase Platform

Table: `Spending`

| Column Name | Type    |
|:------------|---------|
| user_id     | int     |
| spend_date  | date    |
| platform    | enum    |
| amount      | int     |

The table logs the history of the spending of users that make purchases from an online shopping website that has a desktop and a mobile application.
(`user_id`, `spend_date`, `platform`) is the primary key (combination of columns with unique values) of this table.
The `platform` column is an ENUM (category) type of ('desktop', 'mobile').

Write a solution to find the total number of users and the total amount spent using the mobile only, the desktop only, and both mobile and desktop together for each date.

Return the result table in any order.
The result format is in the following example.

### Example 1:

**Input:**
`Spending` table:

| user_id | spend_date | platform | amount |
|:--------|------------|----------|--------|
| 1       | 2019-07-01 | mobile   | 100    |
| 1       | 2019-07-01 | desktop  | 100    |
| 2       | 2019-07-01 | mobile   | 100    |
| 2       | 2019-07-02 | mobile   | 100    |
| 3       | 2019-07-01 | desktop  | 100    |
| 3       | 2019-07-02 | desktop  | 100    |

**Output:**

| spend_date | platform | total_amount | total_users |
|:-----------|----------|--------------|-------------|
| 2019-07-01 | desktop  | 100          | 1           |
| 2019-07-01 | mobile   | 100          | 1           |
| 2019-07-01 | both     | 200          | 1           |
| 2019-07-02 | desktop  | 100          | 1           |
| 2019-07-02 | mobile   | 100          | 1           |
| 2019-07-02 | both     | 0            | 0           |
+------------+----------+--------------+-------------+
Explanation:
On 2019-07-01, user 1 purchased using both desktop and mobile, user 2 purchased using mobile only and user 3 purchased using desktop only.
On 2019-07-02, user 2 purchased using mobile only, user 3 purchased using desktop only and no one purchased using both platforms.

```sql
WITH cte AS (
    SELECT user_id, spend_date,
        SUM(CASE platform WHEN 'mobile' THEN amount ELSE 0 END) mobile_amount,
        SUM(CASE platform WHEN 'desktop' THEN amount ELSE 0 END) desktop_amount
        FROM Spending
        GROUP BY user_id, spend_date
) SELECT spend_date, 'desktop' platform,
    SUM(CASE WHEN desktop_amount > 0 AND mobile_amount = 0  THEN desktop_amount ELSE 0 END) total_amount,
    SUM(CASE WHEN desktop_amount > 0 AND mobile_amount = 0 THEN 1 ELSE 0 END) total_users
    FROM cte
    GROUP BY spend_date
UNION ALL
SELECT spend_date, 'mobile' platform,
    SUM(CASE WHEN mobile_amount > 0 AND desktop_amount = 0 THEN mobile_amount ELSE 0 END) total_amount,
    SUM(CASE WHEN mobile_amount > 0 AND desktop_amount = 0 THEN 1 ELSE 0 END) total_users
    FROM cte
    GROUP BY spend_date
UNION ALL
SELECT spend_date, 'both' platform,
    SUM(CASE WHEN desktop_amount > 0 AND mobile_amount > 0 THEN mobile_amount + desktop_amount ELSE 0 END) total_amount,
    SUM(CASE WHEN desktop_amount > 0 AND mobile_amount > 0 THEN 1 ELSE 0 END) total_users
    FROM cte
    GROUP BY spend_date;
```

## Problem 1132: Reported Posts II

Table: `Actions`

| Column Name   | Type    |
|:--------------|---------|
| user_id       | int     |
| post_id       | int     |
| action_date   | date    |
| action        | enum    |
| extra         | varchar |

This table may have duplicate rows.
The `action` column is an ENUM (category) type of ('view', 'like', 'reaction', 'comment', 'report', 'share').
The `extra` column has optional information about the action, such as a reason for the report or a type of reaction.



Table: `Removals`

| Column Name   | Type    |
|:--------------|---------|
| post_id       | int     |
| remove_date   | date    |

`post_id` is the primary key (column with unique values) of this table.
Each row in this table indicates that some post was removed due to being reported or as a result of an admin review.

Write a solution to find the average daily percentage of posts that got removed after being reported as spam, rounded to 2 decimal places.

The result format is in the following example.

### Example 1:

**Input:**
`Actions` table:

| user_id | post_id | action_date | action | extra  |
|:--------|---------|-------------|--------|--------|
| 1       | 1       | 2019-07-01  | view   | null   |
| 1       | 1       | 2019-07-01  | like   | null   |
| 1       | 1       | 2019-07-01  | share  | null   |
| 2       | 2       | 2019-07-04  | view   | null   |
| 2       | 2       | 2019-07-04  | report | spam   |
| 3       | 4       | 2019-07-04  | view   | null   |
| 3       | 4       | 2019-07-04  | report | spam   |
| 4       | 3       | 2019-07-02  | view   | null   |
| 4       | 3       | 2019-07-02  | report | spam   |
| 5       | 2       | 2019-07-03  | view   | null   |
| 5       | 2       | 2019-07-03  | report | racism |
| 5       | 5       | 2019-07-03  | view   | null   |
| 5       | 5       | 2019-07-03  | report | racism |

`Removals` table:

| post_id | remove_date |
|:--------|-------------|
| 2       | 2019-07-20  |
| 3       | 2019-07-18  |

**Output:**

| average_daily_percent |
|:----------------------|
| 75.00                 |

**Explanation:**
- The percentage for 2019-07-04 is 50% because only one post of two spam reported posts were removed.
- The percentage for 2019-07-02 is 100% because one post was reported as spam and it was removed.
- The other days had no spam reports so the average is (50 + 100) / 2 = 75%
Note that the output is only one number and that we do not care about the remove dates.

```sql
WITH daily_average AS (
    SELECT 
        action_date, COUNT(DISTINCT CASE WHEN r.post_id IS NOT NULL THEN r.post_id END) /  COUNT(DISTINCT a.post_id) AS daily_avg
        FROM Actions a LEFT JOIN Removals r
        ON a.post_id = r.post_id
        WHERE a.extra = 'spam'
        GROUP BY a.action_date
) SELECT ROUND(AVG(daily_avg) * 100, 2) AS average_daily_percent
    FROM daily_average;
```

## Problem 1141: User Activity for the Past 30 Days I

Table: `Activity`

| Column Name   | Type    |
|:--------------|---------|
| user_id       | int     |
| session_id    | int     |
| activity_date | date    |
| activity_type | enum    |

This table may have duplicate rows.
The `activity_type` column is an ENUM (category) of type ('open_session', 'end_session', 'scroll_down', 'send_message').
The table shows the user activities for a social media website.
Note that each session belongs to exactly one user.

Write a solution to find the daily active user count for a period of 30 days ending `2019-07-27` inclusively. A user was active on someday if they made at least one activity on that day.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Activity` table:

| user_id | session_id | activity_date | activity_type |
|:--------|------------|---------------|---------------|
| 1       | 1          | 2019-07-20    | open_session  |
| 1       | 1          | 2019-07-20    | scroll_down   |
| 1       | 1          | 2019-07-20    | end_session   |
| 2       | 4          | 2019-07-20    | open_session  |
| 2       | 4          | 2019-07-21    | send_message  |
| 2       | 4          | 2019-07-21    | end_session   |
| 3       | 2          | 2019-07-21    | open_session  |
| 3       | 2          | 2019-07-21    | send_message  |
| 3       | 2          | 2019-07-21    | end_session   |
| 4       | 3          | 2019-06-25    | open_session  |
| 4       | 3          | 2019-06-25    | end_session   |

**Output:**

| day        | active_users |
|:-----------|--------------|
| 2019-07-20 | 2            |
| 2019-07-21 | 2            |

**Explanation:** Note that we do not care about days with zero active users.

```sql
SELECT activity_date day, COUNT(DISTINCT user_id) active_users
    FROM Activity
    WHERE activity_date > DATE_SUB('2019-07-27', INTERVAL 30 DAY) AND activity_date <= '2019-07-27'
    GROUP BY activity_date;
```

## Problem 1142: User Activity for the Past 30 Days II

Table: `Activity`

| Column Name   | Type    |
|:--------------|---------|
| user_id       | int     |
| session_id    | int     |
| activity_date | date    |
| activity_type | enum    |

This table may have duplicate rows.
The `activity_type` column is an ENUM (category) of type ('open_session', 'end_session', 'scroll_down', 'send_message').
The table shows the user activities for a social media website.
Note that each session belongs to exactly one user.

Write a solution to find the average number of sessions per user for a period of 30 days ending `2019-07-27` inclusively, rounded to 2 decimal places. The sessions we want to count for a user are those with at least one activity in that time period.

The result format is in the following example.

### Example 1:

**Input:**
`Activity` table:

| user_id | session_id | activity_date | activity_type |
|:--------|------------|---------------|---------------|
| 1       | 1          | 2019-07-20    | open_session  |
| 1       | 1          | 2019-07-20    | scroll_down   |
| 1       | 1          | 2019-07-20    | end_session   |
| 2       | 4          | 2019-07-20    | open_session  |
| 2       | 4          | 2019-07-21    | send_message  |
| 2       | 4          | 2019-07-21    | end_session   |
| 3       | 2          | 2019-07-21    | open_session  |
| 3       | 2          | 2019-07-21    | send_message  |
| 3       | 2          | 2019-07-21    | end_session   |
| 3       | 5          | 2019-07-21    | open_session  |
| 3       | 5          | 2019-07-21    | scroll_down   |
| 3       | 5          | 2019-07-21    | end_session   |
| 4       | 3          | 2019-06-25    | open_session  |
| 4       | 3          | 2019-06-25    | end_session   |

**Output:**

| average_sessions_per_user |
|:--------------------------|
| 1.33                      |

**Explanation:** User 1 and 2 each had 1 session in the past 30 days while user 3 had 2 sessions so the average is `(1 + 
1 + 2) / 3 = 1.33`.

```sql
SELECT IFNULL(
        ROUND(
            COUNT(DISTINCT session_id) / COUNT(DISTINCT user_id), 2
        ),
    0.00) AS average_sessions_per_user 
    FROM Activity
    WHERE activity_date > DATE_SUB('2019-07-27', INTERVAL 30 DAY) AND activity_date < '2019-07-27';
```

## Problem 1148: Article Views I

Table: `Views`

| Column Name   | Type    |
|:--------------|:--------|
| article_id    | int     |
| author_id     | int     |
| viewer_id     | int     |
| view_date     | date    |

There is no primary key for this table, it may have duplicate rows.
Each row of this table indicates that some viewer viewed an article (written by some author) on some date.
Note that equal `author_id` and `viewer_id` indicate the same person.

Write an SQL query to find all the authors that viewed at least one of their own articles.

Return the result table sorted by `id` in ascending order.

The query result format is in the following example.

### Example 1:

**Input:**
Views table:

| article_id | author_id | viewer_id | view_date  |
|:-----------|:----------|:----------|:-----------|
| 1          | 3         | 5         | 2019-08-01 |
| 1          | 3         | 6         | 2019-08-02 |
| 2          | 7         | 7         | 2019-08-01 |
| 2          | 7         | 6         | 2019-08-02 |
| 4          | 7         | 1         | 2019-07-22 |
| 3          | 4         | 4         | 2019-07-21 |
| 3          | 4         | 4         | 2019-07-21 |

**Output:**

| id   |
|:----:|
| 4    |
| 7    |


```sql
SELECT DISTINCT author_id AS id 
    FROM Views 
    WHERE author_id = viewer_id 
    ORDER BY author_id;
```

## Problem 1149: Article Views II

Table: `Views`

| Column Name   | Type    |
|:--------------|:--------|
| article_id    | int     |
| author_id     | int     |
| viewer_id     | int     |
| view_date     | date    |

This table may have duplicate rows.
Each row of this table indicates that some viewer viewed an article (written by some author) on some date.
Note that equal `author_id` and `viewer_id` indicate the same person.

Write a solution to find all the people who viewed more than one article on the same date.

Return the result table sorted by `id` in ascending order.

The result format is in the following example.

### Example 1:

**Input:**
`Views` table:

| article_id | author_id | viewer_id | view_date  |
|:-----------|:----------|:----------|:-----------|
| 1          | 3         | 5         | 2019-08-01 |
| 3          | 4         | 5         | 2019-08-01 |
| 1          | 3         | 6         | 2019-08-02 |
| 2          | 7         | 7         | 2019-08-01 |
| 2          | 7         | 6         | 2019-08-02 |
| 4          | 7         | 1         | 2019-07-22 |
| 3          | 4         | 4         | 2019-07-21 |
| 3          | 4         | 4         | 2019-07-21 |

**Output:**

| id   |
|:----:|
| 5    |
| 6    |

```sql
SELECT DISTINCT viewer_id id 
    FROM Views
    GROUP BY view_date, viewer_id
    HAVING COUNT(DISTINCT article_id) > 1
    ORDER BY id
```

## Problem 1158: Market Analysis I

Table: `Users`

| Column Name    | Type    |
|:---------------|:--------|
| user_id        | int     |
| join_date      | date    |
| favorite_brand | varchar |

`user_id` is the primary key of this table.
This table has the info of the users of an online shopping website where users can sell and buy items.

Table: `Orders`

| Column Name   | Type    |
|:--------------|:--------|
| order_id      | int     |
| order_date    | date    |
| item_id       | int     |
| buyer_id      | int     |
| seller_id     | int     |

`order_id` is the primary key of this table.
`item_id` is a foreign key to the Items table.
`buyer_id` and `seller_id` are foreign keys to the Users table.

Table: `Items`

| Column Name   | Type    |
|:--------------|:--------|
| item_id       | int     |
| item_brand    | varchar |

`item_id` is the primary key of this table.

Write an SQL query to find for each user, the join date and the number of orders they made as a buyer in 2019.

Return the result table in any order.

The query result format is in the following example.

### Example 1:

**Input:**

`Users` table:

| user_id | join_date  | favorite_brand |
|:--------|:-----------|:---------------|
| 1       | 2018-01-01 | Lenovo         |
| 2       | 2018-02-09 | Samsung        |
| 3       | 2018-01-19 | LG             |
| 4       | 2018-05-21 | HP             |

`Orders` table:

| order_id | order_date | item_id | buyer_id | seller_id |
|:---------|:-----------|:--------|:---------|:----------|
| 1        | 2019-08-01 | 4       | 1        | 2         |
| 2        | 2018-08-02 | 2       | 1        | 3         |
| 3        | 2019-08-03 | 3       | 2        | 3         |
| 4        | 2018-08-04 | 1       | 4        | 2         |
| 5        | 2018-08-04 | 1       | 3        | 4         |
| 6        | 2019-08-05 | 2       | 2        | 4         |

`Items` table:

| item_id | item_brand |
|:--------|:-----------|
| 1       | Samsung    |
| 2       | Lenovo     |
| 3       | LG         |
| 4       | HP         |

**Output:**

| buyer_id  | join_date  | orders_in_2019 |
|:----------|:-----------|:---------------|
| 1         | 2018-01-01 | 1              |
| 2         | 2018-02-09 | 2              |
| 3         | 2018-01-19 | 0              |
| 4         | 2018-05-21 | 0              |

```sql
SELECT u.user_id AS buyer_id, u.join_date, COUNT(a.order_date) AS orders_in_2019 FROM Users u
    LEFT OUTER JOIN (SELECT buyer_id, order_date FROM Orders WHERE YEAR(order_date) = 2019) AS a
    ON u.user_id = a.buyer_id
    GROUP BY u.user_id
    ORDER BY u.user_id;
```

## Problem 1159: Market Analysis II

Table: `Users`

| Column Name    | Type    |
|:---------------|:--------|
| user_id        | int     |
| join_date      | date    |
| favorite_brand | varchar |

`user_id` is the primary key (column with unique values) of this table.
This table has the info of the users of an online shopping website where users can sell and buy items.

Table: `Orders`

| Column Name   | Type    |
|:--------------|:--------|
| order_id      | int     |
| order_date    | date    |
| item_id       | int     |
| buyer_id      | int     |
| seller_id     | int     |

`order_id` is the primary key (column with unique values) of this table.
`item_id` is a foreign key (reference column) to the Items table.
`buyer_id` and `seller_id` are foreign keys to the Users table.

Table: `Items`

| Column Name   | Type    |
|:--------------|:--------|
| item_id       | int     |
| item_brand    | varchar |

`item_id` is the primary key (column with unique values) of this table.

Write a solution to find for each user whether the brand of the second item (by date) they sold is their favorite brand. If a user sold less than two items, report the answer for that user as 'no'. It is guaranteed that no seller sells more than one item in a day.

Return the result table in any order.
The result format is in the following example.

### Example 1:

**Input:**
`Users` table:

| user_id | join_date  | favorite_brand |
|:--------|:-----------|:---------------|
| 1       | 2019-01-01 | Lenovo         |
| 2       | 2019-02-09 | Samsung        |
| 3       | 2019-01-19 | LG             |
| 4       | 2019-05-21 | HP             |

`Orders` table:

| order_id | order_date | item_id | buyer_id | seller_id |
|:---------|:-----------|:--------|:---------|:----------|
| 1        | 2019-08-01 | 4       | 1        | 2         |
| 2        | 2019-08-02 | 2       | 1        | 3         |
| 3        | 2019-08-03 | 3       | 2        | 3         |
| 4        | 2019-08-04 | 1       | 4        | 2         |
| 5        | 2019-08-04 | 1       | 3        | 4         |
| 6        | 2019-08-05 | 2       | 2        | 4         |

`Items` table:

| item_id | item_brand |
|:--------|:-----------|
| 1       | Samsung    |
| 2       | Lenovo     |
| 3       | LG         |
| 4       | HP         |

**Output:**

| seller_id | 2nd_item_fav_brand |
|:----------|:-------------------|
| 1         | no                 |
| 2         | yes                |
| 3         | yes                |
| 4         | no                 |

**Explanation:**
The answer for the user with id 1 is no because they sold nothing.
The answer for the users with id 2 and 3 is yes because the brands of their second sold items are their favorite brands.
The answer for the user with id 4 is no because the brand of their second sold item is not their favorite brand.

```sql
WITH cte AS (
    SELECT o.seller_id, i.item_brand
        FROM (
            SELECT seller_id, item_id, ROW_NUMBER() OVER (PARTITION BY seller_id ORDER BY order_date) order_row
            FROM Orders) o 
            JOIN Items i ON i.item_id = o.item_id
            WHERE order_row = 2
) SELECT u.user_id seller_id, 
    CASE WHEN u.favorite_brand = item_brand THEN 'yes' ELSE 'no' END AS 2nd_item_fav_brand
    FROM Users u LEFT JOIN cte 
    ON u.user_id = cte.seller_id
```

## Problem 1164: Product Price at a Given Date

Table: `Products`

| Column Name   | Type    |
|:--------------|:--------|
| product_id    | int     |
| new_price     | int     |
| change_date   | date    |

(`product_id`, `change_date`) is the primary key (combination of columns with unique values) of this table.
Each row of this table indicates that the price of some product was changed to a new price at some date.

Write a solution to find the prices of all products on `2019-08-16`. Assume the price of all products before any change is 10.

Return the result table in any order.
The result format is in the following example.

### Example 1:

**Input:**
`Products` table:

| product_id | new_price | change_date |
|:-----------|:----------|:------------|
| 1          | 20        | 2019-08-14  |
| 2          | 50        | 2019-08-14  |
| 1          | 30        | 2019-08-15  |
| 1          | 35        | 2019-08-16  |
| 2          | 65        | 2019-08-17  |
| 3          | 20        | 2019-08-18  |

**Output:**

| product_id | price |
|:-----------|:------|
| 2          | 50    |
| 1          | 35    |
| 3          | 10    |

```sql
WITH cte AS (
    SELECT product_id, FIRST_VALUE(new_price) OVER (PARTITION BY product_id ORDER BY change_date DESC) new_price FROM Products 
        WHERE change_date <= '2019-08-16'
), cte2 AS (
    SELECT DISTINCT product_id, new_price FROM cte
)
SELECT DISTINCT p.product_id, 
    CASE WHEN cte2.new_price IS NOT NULL THEN cte2.new_price ELSE 10 END AS price 
    FROM Products p 
    LEFT JOIN cte2
    ON p.product_id = cte2.product_id
```

## Problem 1173: Immediate Food Delivery I

Table: `Delivery`

| Column Name                 | Type    |
|:----------------------------|:--------|
| delivery_id                 | int     |
| customer_id                 | int     |
| order_date                  | date    |
| customer_pref_delivery_date | date    |

`delivery_id` is the primary key (column with unique values) of this table.
The table holds information about food delivery to customers that make orders at some date and specify a preferred delivery date (on the same order date or after it).

If the customer's preferred delivery date is the same as the order date, then the order is called **immediate**; 
otherwise,
it 
is called **scheduled**.

Write a solution to find the percentage of immediate orders in the table, **rounded to 2 decimal places**.

The result format is in the following example.

### Example 1:

**Input:**
`Delivery` table:

| delivery_id | customer_id | order_date | customer_pref_delivery_date |
|:------------|:------------|:-----------|:----------------------------|
| 1           | 1           | 2019-08-01 | 2019-08-02                  |
| 2           | 5           | 2019-08-02 | 2019-08-02                  |
| 3           | 1           | 2019-08-11 | 2019-08-11                  |
| 4           | 3           | 2019-08-24 | 2019-08-26                  |
| 5           | 4           | 2019-08-21 | 2019-08-22                  |
| 6           | 2           | 2019-08-11 | 2019-08-13                  |

**Output:**

| immediate_percentage |
|:---------------------|
| 33.33                |

**Explanation:** The orders with delivery id 2 and 3 are immediate while the others are scheduled.

```sql
SELECT ROUND(SUM(CASE WHEN order_date = customer_pref_delivery_date THEN 1 ELSE 0 END) /
    COUNT(1) * 100, 2) AS immediate_percentage 
    FROM Delivery;
```

## Probblem 1174: Immediate Food Delivery II

Table: `Delivery`

| Column Name                 | Type    |
|:----------------------------|:--------|
| delivery_id                 | int     |
| customer_id                 | int     |
| order_date                  | date    |
| customer_pref_delivery_date | date    |

`delivery_id` is the primary key (column with unique values) of this table.
The table holds information about food delivery to customers that make orders at some date and specify a preferred delivery date (on the same order date or after it).

If the customer's preferred delivery date is the same as the order date, then the order is called **immediate**;
otherwise,
it
is called **scheduled**.

The **first order** of a customer is the order with the earliest order date that the customer made. It is guaranteed 
that a customer has precisely one first order.

Write a solution to find the percentage of immediate orders in the first orders of all customers, **rounded to 2 
decimal places**.

The result format is in the following example.

### Example 1:

**Input:**
`Delivery` table:

| delivery_id | customer_id | order_date | customer_pref_delivery_date |
|:------------|:------------|:-----------|:----------------------------|
| 1           | 1           | 2019-08-01 | 2019-08-02                  |
| 2           | 2           | 2019-08-02 | 2019-08-02                  |
| 3           | 1           | 2019-08-11 | 2019-08-12                  |
| 4           | 3           | 2019-08-24 | 2019-08-24                  |
| 5           | 3           | 2019-08-21 | 2019-08-22                  |
| 6           | 2           | 2019-08-11 | 2019-08-13                  |
| 7           | 4           | 2019-08-09 | 2019-08-09                  |

**Output:**

| immediate_percentage |
|:---------------------|
| 50.00                |

**Explanation:**
- The customer id 1 has a first order with delivery id 1 and it is scheduled.
- The customer id 2 has a first order with delivery id 2 and it is immediate.
- The customer id 3 has a first order with delivery id 5 and it is scheduled.
- The customer id 4 has a first order with delivery id 7 and it is immediate.
Hence, half the customers have immediate first orders.

```sql
SELECT ROUND(AVG(order_date = customer_pref_delivery_date) * 100, 2)  AS immediate_percentage
FROM Delivery
WHERE (customer_id, order_date) IN (
  SELECT customer_id, MIN(order_date) AS order_date
  FROM Delivery
  GROUP BY customer_id
);
```

```sql
WITH cte AS (
    SELECT order_date, customer_pref_delivery_date, ROW_NUMBER() OVER (PARTITION BY customer_id order by order_date) order_number FROM Delivery
) SELECT ROUND(SUM(CASE WHEN order_date = customer_pref_delivery_date THEN 1 ELSE 0 END) /
    COUNT(1) * 100, 2) immediate_percentage
    FROM cte WHERE order_number = 1;
```

## Problem 1179: Reformat Department Table

Table: `Department`

| Column Name | Type    |
|:------------|:--------|
| id          | int     |
| revenue     | int     |
| month       | varchar |

In SQL,(`id`, `month`) is the primary key of this table.
The table has information about the revenue of each department per month.
The month has values in ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"].

Reformat the table such that there is a department id column and a revenue column for each month.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Department` table:

| id   | revenue | month |
|:-----|:--------|:------|
| 1    | 8000    | Jan   |
| 2    | 9000    | Jan   |
| 3    | 10000   | Feb   |
| 1    | 7000    | Feb   |
| 1    | 6000    | Mar   |

**Output:**

| id   | Jan_Revenue | Feb_Revenue | Mar_Revenue | ... | Dec_Revenue |
|:-----|:------------|:------------|:------------|-----|:------------|
| 1    | 8000        | 7000        | 6000        | ... | null        |
| 2    | 9000        | null        | null        | ... | null        |
| 3    | null        | 10000       | null        | ... | null        |

**Explanation:** The revenue from Apr to Dec is `null`.
Note that the result table has 13 columns (1 for the department id + 12 for the months).

```sql
SELECT id,
    SUM( IF (month = 'Jan', revenue, null) ) AS Jan_Revenue,
    SUM( IF (month = 'Feb', revenue, null) ) AS Feb_Revenue,
    SUM( IF (month = 'Mar', revenue, null) ) AS Mar_Revenue,
    SUM( IF (month = 'Apr', revenue, null) ) AS Apr_Revenue,
    SUM( IF (month = 'May', revenue, null) ) AS May_Revenue,
    SUM( IF (month = 'Jun', revenue, null) ) AS Jun_Revenue,
    SUM( IF (month = 'Jul', revenue, null) ) AS Jul_Revenue,
    SUM( IF (month = 'Aug', revenue, null) ) AS Aug_Revenue,
    SUM( IF (month = 'Sep', revenue, null) ) AS Sep_Revenue,
    SUM( IF (month = 'Oct', revenue, null) ) AS Oct_Revenue,
    SUM( IF (month = 'Nov', revenue, null) ) AS Nov_Revenue,
    SUM( IF (month = 'Dec', revenue, null) ) AS Dec_Revenue
    FROM Department
    GROUP By id;
```

## Problem 1193: Monthly Transactions I

Table: `Transactions`

| Column Name   | Type    |
|:--------------|:--------|
| id            | int     |
| country       | varchar |
| state         | enum    |
| amount        | int     |
| trans_date    | date    |

`id` is the primary key of this table.
The table has information about incoming transactions.
The state column is an enum of type ["approved", "declined"].

Write an SQL query to find for each month and country, the number of transactions and their total amount, the number of approved transactions and their total amount.

Return the result table in any order.

The query result format is in the following example.

### Example 1:

**Input:**
`Transactions` table:

| id   | country | state    | amount | trans_date |
|:-----|:--------|:---------|:-------|:-----------|
| 121  | US      | approved | 1000   | 2018-12-18 |
| 122  | US      | declined | 2000   | 2018-12-19 |
| 123  | US      | approved | 2000   | 2019-01-01 |
| 124  | DE      | approved | 2000   | 2019-01-07 |

**Output:**

| month    | country | trans_count | approved_count | trans_total_amount | approved_total_amount |
|:---------|:--------|:------------|:---------------|:-------------------|:----------------------|
| 2018-12  | US      | 2           | 1              | 3000               | 1000                  |
| 2019-01  | US      | 1           | 1              | 2000               | 2000                  |
| 2019-01  | DE      | 1           | 1              | 2000               | 2000                  |

```sql
SELECT 
    DATE_FORMAT(trans_date, "%Y-%m") AS month, 
    country, 
    COUNT(1) AS trans_count, 
    SUM(IF(state = "approved", 1, 0)) AS approved_count, 
    SUM(amount) AS trans_total_amount, 
    SUM(IF(state = "approved", amount, 0 )) approved_total_amount 
    FROM Transactions
    GROUP BY DATE_FORMAT(trans_date, "%Y-%m"), country;
```

## Problem 1194: Tournament Winners

Table: `Players`

| Column Name | Type  |
|:------------|:------|
| player_id   | int   |
| group_id    | int   |

`player_id` is the primary key (column with unique values) of this table.
Each row of this table indicates the group of each player.

Table: `Matches`

| Column Name   | Type    |
|:--------------|:--------|
| match_id      | int     |
| first_player  | int     |
| second_player | int     |
| first_score   | int     |
| second_score  | int     |

`match_id` is the primary key (column with unique values) of this table.
Each row is a record of a match, `first_player` and `second_player` contain the `player_id` of each match.
`first_score` and `second_score` contain the number of points of the `first_player` and `second_player` respectively.
You may assume that, in each match, players belong to the same group.

The winner in each group is the player who scored the maximum total points within the group. In the case of a tie, the lowest `player_id` wins.

Write a solution to find the winner in each group.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Players` table:

| player_id | group_id   |
|:----------|:-----------|
| 15        | 1          |
| 25        | 1          |
| 30        | 1          |
| 45        | 1          |
| 10        | 2          |
| 35        | 2          |
| 50        | 2          |
| 20        | 3          |
| 40        | 3          |

`Matches` table:

| match_id   | first_player | second_player | first_score | second_score |
|:-----------|:-------------|:--------------|:------------|:-------------|
| 1          | 15           | 45            | 3           | 0            |
| 2          | 30           | 25            | 1           | 2            |
| 3          | 30           | 15            | 2           | 0            |
| 4          | 40           | 20            | 5           | 2            |
| 5          | 35           | 50            | 1           | 1            |

**Output:**

| group_id  | player_id  |
|:----------|:-----------|
| 1         | 15         |
| 2         | 35         |
| 3         | 40         |


```sql
WITH player_scores AS (
  SELECT 
    first_player AS player_id, 
    first_score AS score 
  FROM matches 
  UNION ALL 
  SELECT 
    second_player AS player_id, 
    second_score AS score 
  FROM matches
), 
total_scores AS (
  SELECT 
    player_id, 
    SUM(score) AS total_score 
  FROM player_scores 
  GROUP BY player_id
)
SELECT 
  DISTINCT group_id, 
  FIRST_VALUE(total_scores.player_id) OVER (
    PARTITION BY group_id 
    ORDER BY 
      total_score DESC, 
      total_scores.player_id
  ) AS player_id
FROM 
  total_scores
  LEFT JOIN players 
  ON total_scores.player_id = players.player_id;
```

## Problem 1204: Last Person to Fit in the Bus

Table: `Queue`

| Column Name | Type    |
|:------------|:--------|
| person_id   | int     |
| person_name | varchar |
| weight      | int     |
| turn        | int     |

`person_id` column contains unique values.
This table has the information about all people waiting for a bus.
The `person_id` and turn columns will contain all numbers from `1` to `n,` where `n` is the number of rows in the table.
`turn` determines the order of which the people will board the bus, where `turn=1` denotes the first person to board and `turn=n` denotes the last person to board.
`weight` is the weight of the person in kilograms.

There is a queue of people waiting to board a bus. However, the bus has a weight limit of 1000 kilograms, so there may be some people who cannot board.

Write a solution to find the `person_name` of **the last person** that can fit on the bus without exceeding the weight 
limit. The test cases are generated such that the first person does not exceed the weight limit.

Note that only one person can board the bus at any given turn.

The result format is in the following example.

### Example 1:

**Input:**
`Queue` table:

| person_id | person_name | weight | turn |
|:----------|:------------|:-------|:-----|
| 5         | Alice       | 250    | 1    |
| 4         | Bob         | 175    | 5    |
| 3         | Alex        | 350    | 2    |
| 6         | John Cena   | 400    | 3    |
| 1         | Winston     | 500    | 6    |
| 2         | Marie       | 200    | 4    |

**Output:**

| person_name |
|:------------|
| John Cena   |

**Explanation:** The folowing table is ordered by the turn for simplicity.

| Turn | ID | Name      | Weight | Total Weight | Note |
|:-----|:---|:----------|:-------|:-------------|:-----|
| 1    | 5  | Alice     | 250    | 250          |      |
| 2    | 3  | Alex      | 350    | 600          |      |
| 3    | 6  | John Cena | 400    | 1000         | (last person to board) |
| 4    | 2  | Marie     | 200    | 1200         | (cannot board) |
| 5    | 4  | Bob       | 175    | ___          |      |
| 6    | 1  | Winston   | 500    | ___          |      |

```sql
WITH cte AS (
    SELECT person_name, 
        SUM(weight) OVER (ORDER BY turn) 'total_weight'
        FROM Queue
) SELECt person_name
    FROM cte
    WHERE total_weight <=1000
    ORDER BY total_weight DESC
    LIMIT 1;
```

## Problem 1205: Monthly Transactions II

Table: `Transactions`

| Column Name    | Type    |
|:---------------|:--------|
| id             | int     |
| country        | varchar |
| state          | enum    |
| amount         | int     |
| trans_date     | date    |

`id` is the column of unique values of this table.
The table has information about incoming transactions.
The state column is an ENUM (category) of type ["approved", "declined"].

Table: `Chargebacks`

| Column Name    | Type    |
|:---------------|:--------|
| trans_id       | int     |
| trans_date     | date    |

Chargebacks contains basic information regarding incoming chargebacks from some transactions placed in `Transactions` table.
`trans_id` is a foreign key (reference column) to the `id` column of `Transactions` table.
Each chargeback corresponds to a transaction made previously even if they were not approved.

Write a solution to find for each month and country: the number of approved transactions and their total amount, the number of chargebacks, and their total amount.

Note: In your solution, given the month and country, *ignore rows with all zeros*.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Transactions` table:

| id  | country | state    | amount | trans_date |
|:----|:--------|:---------|:-------|:-----------|
| 101 | US      | approved | 1000   | 2019-05-18 |
| 102 | US      | declined | 2000   | 2019-05-19 |
| 103 | US      | approved | 3000   | 2019-06-10 |
| 104 | US      | declined | 4000   | 2019-06-13 |
| 105 | US      | approved | 5000   | 2019-06-15 |

`Chargebacks` table:

| trans_id | trans_date |
|:---------|:-----------|
| 102      | 2019-05-29 |
| 101      | 2019-06-30 |
| 105      | 2019-09-18 |

**Output:**

| month   | country | approved_count | approved_amount | chargeback_count | chargeback_amount |
|:--------|:--------|:----------------|:----------------|:------------------|:------------------|
| 2019-05 | US      | 1              | 1000            | 1                | 2000              |
| 2019-06 | US      | 2              | 8000            | 1                | 1000              |
| 2019-09 | US      | 0              | 0               | 1                | 5000              |

```sql
WITH approved_trans AS (
    SELECT 
        DATE_FORMAT(trans_date, '%Y-%m') AS month, country,
        COUNT(id) AS approved_count,
        SUM(amount) AS approved_amount
        FROM Transactions t
        WHERE t.state='approved'
        GROUP BY 1, 2
), chargebacks_trans AS (
    SELECT DATE_FORMAT(c.trans_date, '%Y-%m') AS month, t.country,
    COUNT(c.trans_id) AS chargeback_count,
    SUM(t.amount) AS chargeback_amount
    FROM Chargebacks c
    JOIN Transactions t ON c.trans_id = t.id
    GROUP BY 1, 2
), unioned AS (
    SELECT month, country, approved_count, approved_amount, 0 AS chargeback_count, 0 AS chargeback_amount
        FROM approved_trans
        UNION ALL
        SELECT month, country, 0 AS approved_count, 0 AS approved_amount, chargeback_count, chargeback_amount
        FROM chargebacks_trans
) SELECt month, country,
    SUM(approved_count) AS approved_count,
    SUM(approved_amount) AS approved_amount,
    SUM(chargeback_count) AS chargeback_count,
    SUM(chargeback_amount) AS chargeback_amount
    FROM unioned
    GROUP BY 1, 2;
```

## Problem 1211: Queries Quality and Percentage

Table: `Queries`

| Column Name | Type    |
|:------------|:--------|
| query_name  | varchar |
| result      | varchar |
| position    | int     |
| rating      | int     |

This table may have duplicate rows.
This table contains information collected from some queries on a database.
The `position` column has a value from `1` to `500`.
The `rating` column has a value from 1 to 5. Query with `rating` less than 3 is a poor query.

We define query quality as:

    The average of the ratio between query rating and its position.

We also define poor query percentage as:

    The percentage of all queries with rating less than 3.

Write a solution to find each `query_name`, the `quality` and `poor_query_percentage`.

Both `quality` and `poor_query_percentage` should be **rounded to 2 decimal places**.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Queries` table:

| query_name | result            | position | rating |
|:-----------|:------------------|:---------|:-------|
| Dog        | Golden Retriever  | 1        | 5      |
| Dog        | German Shepherd   | 2        | 5      |
| Dog        | Mule              | 200      | 1      |
| Cat        | Shirazi           | 5        | 2      |
| Cat        | Siamese           | 3        | 3      |
| Cat        | Sphynx            | 7        | 4      |

**Output:**

| query_name | quality | poor_query_percentage |
|:-----------|:--------|:----------------------|
| Dog        | 2.50    | 33.33                 |
| Cat        | 0.66    | 33.33                 |

**Explanation:**
- Dog queries `quality` is `((5 / 1) + (5 / 2) + (1 / 200)) / 3 = 2.50`
- Dog queries `poor_query_percentage` is `(1 / 3) * 100 = 33.33`
- Cat queries `quality` equals `((2 / 5) + (3 / 3) + (4 / 7)) / 3 = 0.66`
- Cat queries `poor_query_percentage` is `(1 / 3) * 100 = 33.33`

```sql
SELECT query_name,
    ROUND(SUM(rating/position) / COUNT(1), 2) AS quality,
    ROUND(SUM(CASE WHEN rating < 3 THEN 1 ELSE 0 END) / COUNT(1) * 100, 2) AS poor_query_percentage
    FROM Queries
    GROUP BY query_name
```

## Problem 1212: Team Scores in Football Tournament

Table: `Teams`

| Column Name   | Type     |
|:--------------|:---------|
| team_id       | int      |
| team_name     | varchar  |

`team_id` is the column with unique values of this table.
Each row of this table represents a single football team.

Table: `Matches`

| Column Name   | Type    |
|:--------------|:--------|
| match_id      | int     |
| host_team     | int     |
| guest_team    | int     |
| host_goals    | int     |
| guest_goals   | int     |

- `match_id` is the column of unique values of this table.
- Each row is a record of a finished match between two different teams.
- Teams `host_team` and `guest_team` are represented by their IDs in the Teams table (`team_id`), and they scored 
  `host_goals` and `guest_goals` goals, respectively.

You would like to compute the scores of all teams after all matches. Points are awarded as follows:

- A team receives three points if they win a match (i.e., Scored more goals than the opponent team).
- A team receives one point if they draw a match (i.e., Scored the same number of goals as the opponent team).
- A team receives no points if they lose a match (i.e., Scored fewer goals than the opponent team).

Write a solution that selects the `team_id`, `team_name` and `num_points` of each team in the tournament after all described matches.

Return the result table ordered by `num_points` in **decreasing order**. In case of a tie, order the records by `team_id` 
in increasing order.

The result format is in the following example.

### Example 1:

**Input:**
`Teams` table:

| team_id   | team_name    |
|:----------|:-------------|
| 10        | Leetcode FC  |
| 20        | NewYork FC   |
| 30        | Atlanta FC   |
| 40        | Chicago FC   |
| 50        | Toronto FC   |

`Matches` table:

| match_id   | host_team    | guest_team    | host_goals  | guest_goals  |
|:-----------|:-------------|:--------------|:------------|:-------------|
| 1          | 10           | 20            | 3           | 0            |
| 2          | 30           | 10            | 2           | 2            |
| 3          | 10           | 50            | 5           | 1            |
| 4          | 20           | 30            | 1           | 0            |
| 5          | 50           | 30            | 1           | 0            |

**Output:**

| team_id    | team_name    | num_points    |
|:-----------|:-------------|:--------------|
| 10         | Leetcode FC  | 7             |
| 20         | NewYork FC   | 3             |
| 50         | Toronto FC   | 3             |
| 30         | Atlanta FC   | 1             |
| 40         | Chicago FC   | 0             |

```sql
WITH host_points AS (
    SELECT team_id AS team_id, team_name,
    CASE WHEN host_goals > guest_goals THEN 3 
            WHEN host_goals = guest_goals THEN 1
            ELSE 0 END
    AS num_points
    FROM Teams t
    LEFT JOIN Matches m
    ON t.team_id = m.host_team
), guest_points AS (
    SELECT guest_team AS team_id, team_name,
    CASE WHEN host_goals < guest_goals THEN 3
        WHEN host_goals = guest_goals THEN 1
        ELSE 0 END
    AS num_points
    FROM Teams t
    JOIN Matches m
    ON t.team_id = m.guest_team
) SELECT team_id, team_name,
    SUM(num_points) AS num_points
    FROM (
        SELECT team_id, team_name, num_points 
            FROM host_points
            UNION ALL
            SELECT team_id, team_name, num_points
                FROM guest_points
    ) t
    GROUP BY t.team_id
    ORDER BY num_points DESC, team_id;
```

## Problem 1225: Report Contiguous Dates

Table: `Failed`

| Column Name  | Type    |
|:-------------|:--------|
| fail_date    | date    |
+--------------+---------+
- `fail_date` is the primary key (column with unique values) for this table.
This table contains the days of failed tasks.

Table: `Succeeded`

| Column Name  | Type    |
|:-------------|:--------|
| success_date | date    |

- `success_date` is the primary key (column with unique values) for this table.
This table contains the days of succeeded tasks.

A system is running one task every day. Every task is independent of the previous tasks. The tasks can fail or succeed.

Write a solution to report the `period_state` for each continuous interval of days in the period from `2019-01-01` to `2019-12-31`.

`period_state` is 'failed' if tasks in this interval failed or 'succeeded' if tasks in this interval succeeded. Interval of days are retrieved as `start_date` and `end_date`.

Return the result table ordered by `start_date`.

The result format is in the following example.

### Example 1:

**Input:**
`Failed` table:

| fail_date         |
|:------------------|
| 2018-12-28        |
| 2018-12-29        |
| 2019-01-04        |
| 2019-01-05        |

`Succeeded` table:

| success_date      |
|:------------------|
| 2018-12-30        |
| 2018-12-31        |
| 2019-01-01        |
| 2019-01-02        |
| 2019-01-03        |
| 2019-01-06        |

**Output:**

| period_state | start_date   | end_date     |
|:-------------|:-------------|:-------------|
| succeeded    | 2019-01-01   | 2019-01-03   |
| failed       | 2019-01-04   | 2019-01-05   |
| succeeded    | 2019-01-06   | 2019-01-06   |

**Explanation:**
- The report ignored the system state in 2018 as we care about the system in the period 2019-01-01 to 2019-12-31.
- From 2019-01-01 to 2019-01-03 all tasks succeeded and the system state was "succeeded".
- From 2019-01-04 to 2019-01-05 all tasks failed and the system state was "failed".
- From 2019-01-06 to 2019-01-06 all tasks succeeded and the system state was "succeeded".

```sql
WITH cte AS (
    SELECT fail_date AS status_date, 'failed' AS status,  RANK() OVER (ORDER BY fail_date) AS ranking
        FROM Failed
        WHERE fail_date >= '2019-01-01' AND fail_date <= '2019-12-31'
        UNION
        SELECT success_date, 'succeeded' AS status, RANK() OVER (ORDER BY success_date) AS ranking
        FROM Succeeded
        WHERE success_date >= '2019-01-01' AND success_date <= '2019-12-31'
), cte2 AS (SELECT status_date,
    RANK() OVER (ORDER BY status_date) AS overall_ranking,
    status,
    ranking,
    (RANK() OVER (ORDER BY status_date) - ranking) AS inverse_ranking
    FROM cte
) SELECT status AS period_state, 
    MIN(status_date) AS start_date,
    MAX(Status_date) AS end_date
    FROM cte2
    GROUP BY inverse_ranking, status
    ORDER BY start_date;
```

## Problem 1241: Number of Comments per Post

Table: `Submissions`

| Column Name   | Type     |
|:--------------|----------|
| sub_id        | int      |
| parent_id     | int      |

This table may have duplicate rows.
Each row can be a post or comment on the post.
`parent_id` is null for posts.
`parent_id` for comments is `sub_id` for another post in the table.

Write a solution to find the number of comments per post. The result table should contain `post_id` and its corresponding `number_of_comments`.

The `Submissions` table may contain duplicate comments. You should count the number of unique comments per post.

The `Submissions` table may contain duplicate posts. You should treat them as one post.

The result table should be ordered by `post_id` in ascending order.

The result format is in the following example.

### Example 1:

**Input:**
`Submissions` table:

| sub_id  | parent_id  |
|:--------|:-----------|
| 1       | Null       |
| 2       | Null       |
| 1       | Null       |
| 12      | Null       |
| 3       | 1          |
| 5       | 2          |
| 3       | 1          |
| 4       | 1          |
| 9       | 1          |
| 10      | 2          |
| 6       | 7          |

**Output:**

| post_id | number_of_comments |
|:--------|:-------------------|
| 1       | 3                  |
| 2       | 2                  |
| 12      | 0                  |

**Explanation:**
- The post with id 1 has three comments in the table with id 3, 4, and 9. The comment with id 3 is repeated in the 
table, we counted it only once.
- The post with id 2 has two comments in the table with id 5 and 10.
- The post with id 12 has no comments in the table.
- The comment with id 6 is a comment on a deleted post with id 7 so we ignored it.

```sql
WITH posts AS (
    SELECT DISTINCT sub_id  FROM Submissions WHERE parent_id is NULL
) 
SELECT p.sub_id AS post_id, COUNT(DISTINCT c.sub_id) AS number_of_comments
    FROM posts p
    LEFT  JOIN Submissions c
    ON p.sub_id = c.parent_id
    GROUP BY p.sub_id
    ORDER BY p.sub_id;
```

## Problem 1251: Average Selling Price

Table: `Prices`

| Column Name   | Type    |
|:--------------|:--------|
| product_id    | int     |
| start_date    | date    |
| end_date      | date    |
| price         | int     |

- (`product_id`, `start_date`, `end_date`) is the primary key (combination of columns with unique values) for this table.
- Each row of this table indicates the price of the `product_id` in the period from `start_date` to `end_date`.
- For each `product_id` there will be no two overlapping periods. That means there will be no two intersecting periods 
  for the same `product_id`.

Table: `UnitsSold`

| Column Name   | Type    |
|:--------------|:--------|
| product_id    | int     |
| purchase_date | date    |
| units         | int     |

This table may contain duplicate rows.
Each row of this table indicates the date, units, and `product_id` of each product sold.

Write a solution to find the average selling price for each product. `average_price` should be rounded to 2 decimal places. If a product does not have any sold units, its average selling price is assumed to be 0.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Prices` table:

| product_id | start_date | end_date   | price  |
|:-----------|:-----------|:-----------|--------|
| 1          | 2019-02-17 | 2019-02-28 | 5      |
| 1          | 2019-03-01 | 2019-03-22 | 20     |
| 2          | 2019-02-01 | 2019-02-20 | 15     |
| 2          | 2019-02-21 | 2019-03-31 | 30     |

`UnitsSold` table:

| product_id | purchase_date | units |
|:-----------|:--------------|-------|
| 1          | 2019-02-25    | 100   |
| 1          | 2019-03-01    | 15    |
| 2          | 2019-02-10    | 200   |
| 2          | 2019-03-22    | 30    |

**Output:**

| product_id | average_price |
|:-----------|:--------------|
| 1          | 6.96          |
| 2          | 16.96         |

**Explanation:**
- Average selling price = Total Price of Product / Number of products sold.
- Average selling price for product 1 = ((100 * 5) + (15 * 20)) / 115 = 6.96
- Average selling price for product 2 = ((200 * 15) + (30 * 30)) / 230 = 16.96

```sql
SELECT p.product_id, IFNULL(
        ROUND(SUM(p.price * u.units)/SUM(u.units), 2),
        0) AS average_price
    FROM Prices p
    LEFT JOIN UnitsSold u
    ON p.product_id = u.product_id
    AND u.purchase_date >= p.start_date AND u.purchase_date <= p.end_date
    GROUP BY p.product_id
```

## Problem 1264: Page Recommendations

Table: `Friendship`

| Column Name   | Type    |
|:--------------|:--------|
| user1_id      | int     |
| user2_id      | int     |

(`user1_id`, `user2_id`) is the primary key (combination of columns with unique values) for this table.
Each row of this table indicates that there is a friendship relation between `user1_id` and `user2_id`.

Table: `Likes`

| Column Name | Type    |
|:------------|:--------|
| user_id     | int     |
| page_id     | int     |

(`user_id`, `page_id`) is the primary key (combination of columns with unique values) for this table.
Each row of this table indicates that `user_id` likes `page_id`.

Write a solution to recommend pages to the user with `user_id = 1` using the pages that your friends liked. It should not recommend pages you already liked.

Return result table in any order without duplicates.

The result format is in the following example.

### Example 1:

**Input:**
`Friendship` table:

| user1_id | user2_id |
|:---------|:---------|
| 1        | 2        |
| 1        | 3        |
| 1        | 4        |
| 2        | 3        |
| 2        | 4        |
| 2        | 5        |
| 6        | 1        |

`Likes` table:

| user_id | page_id |
|:--------|:--------|
| 1       | 88      |
| 2       | 23      |
| 3       | 24      |
| 4       | 56      |
| 5       | 11      |
| 6       | 33      |
| 2       | 77      |
| 3       | 77      |
| 6       | 88      |

**Output:**

| recommended_page |
|:-----------------|
| 23               |
| 24               |
| 56               |
| 33               |
| 77               |

**Explanation:**
User one is friend with users 2, 3, 4 and 6.
Suggested pages are 23 from user 2, 24 from user 3, 56 from user 3 and 33 from user 6.
Page 77 is suggested from both user 2 and user 3.
Page 88 is not suggested because user 1 already likes it.

```sql
SELECT DISTINCT page_id recommended_page FROM Likes
WHERE user_id IN (
    SELECT 
        CASE 
            WHEN user1_id = 1 THEN user2_id 
            WHEN user2_id=1 THEN user1_id
        END AS user_id 
        FROM Friendship WHERE user1_id = 1 OR user2_id = 1
    )
    AND page_id NOT IN (
        SELECT page_id FROM Likes WHERE user_id = 1 
    );
```

## Problem 1270: All People Report to the Given Manager

Table: `Employees`

| Column Name   | Type    |
|:--------------|:--------|
| employee_id   | int     |
| employee_name | varchar |
| manager_id    | int     |

- `employee_id` is the column of unique values for this table.
- Each row of this table indicates that the employee with ID `employee_id` and name `employee_name` reports his work to 
  his/her direct manager with `manager_id`
- The head of the company is the employee with `employee_id = 1`.

Write a solution to find employee_id of all employees that **directly or indirectly** report their work to the head of 
the company.

The indirect relation between managers will not exceed three managers as the company is small.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Employees` table:

| employee_id | employee_name | manager_id |
|:------------|:--------------|------------|
| 1           | Boss          | 1          |
| 3           | Alice         | 3          |
| 2           | Bob           | 1          |
| 4           | Daniel        | 2          |
| 7           | Luis          | 4          |
| 8           | Jhon          | 3          |
| 9           | Angela        | 8          |
| 77          | Robert        | 1          |

**Output:**

| employee_id |
|:------------|
| 2           |
| 77          |
| 4           |
| 7           |

**Explanation:**
- The head of the company is the employee with employee_id 1.
- The employees with employee_id 2 and 77 report their work directly to the head of the company.
- The employee with employee_id 4 reports their work indirectly to the head of the company `4 --> 2 --> 1`.
- The employee with employee_id 7 reports their work indirectly to the head of the company `7 --> 4 --> 2 --> 1`.
- The employees with employee_id 3, 8, and 9 do not report their work to the head of the company directly or indirectly.

```sql
WITH RECURSIVE cte AS (
    SELECT employee_id, manager_id
    FROM Employees
    WHERE manager_id = 1 AND employee_id != 1
    UNION ALL
    SELECT e.employee_id, e.manager_id
    FROM Employees e
    JOIN cte ON e.manager_id = cte.employee_id
)
SELECT employee_id
FROM cte;
```

## Problem 1378: Replace Employee ID with The Unique Identifier

Table: `Employees`

| Column Name   | Type     |
|:--------------|:---------|
| id            | int      |
| name          | varchar  |

- `id` is the primary key (column with unique values) for this table.
  Each row of this table contains the `id` and the `name` of an employee in a company.

Table: EmployeeUNI

| Column Name   | Type    |
|:--------------|:--------|
| id            | int     |
| unique_id     | int     |

(`id`, `unique_id`) is the primary key (combination of columns with unique values) for this table.
Each row of this table contains the id and the corresponding unique id of an employee in the company.

Write a solution to show the unique ID of each user, If a user does not have a unique ID replace just show `null`.

Return the result table in any order.
The result format is in the following example.

### Example 1:

**Input:**
Employees table:

| id | name     |
|:---|:---------|
| 1  | Alice    |
| 7  | Bob      |
| 11 | Meir     |
| 90 | Winston  |
| 3  | Jonathan |

EmployeeUNI table:

| id | unique_id |
|:---|:----------|
| 3  | 1         |
| 11 | 2         |
| 90 | 3         |

**Output:**

| unique_id | name      |
|:----------|:----------|
| null      | Alice     |
| null      | Bob       |
| 2         | Meir      |
| 3         | Winston   |
| 1         | Jonathan  |

**Explanation:**
Alice and Bob do not have a unique ID, We will show `null` instead.
The unique ID of Meir is 2.
The unique ID of Winston is 3.
The unique ID of Jonathan is 1.

```sql
SELECT b.unique_id, a.name FROM Employees a
    LEFT JOIN
    EmployeeUNI b
    ON a.id = b.id;
```

## Problem 1407: Top Travellers

Table: `Users`

| Column Name   | Type    |
|:--------------|:--------|
| id            | int     |
| name          | varchar |

`id` is the primary key for this table.
`name` is the name of the user.

Table: `Rides`

| Column Name   | Type    |
|:--------------|:--------|
| id            | int     |
| user_id       | int     |
| distance      | int     |

`id` is the primary key for this table.
`user_id` is the id of the user who traveled the distance "distance".

Write an SQL query to report the distance traveled by each user.

Return the result table ordered by `travelled_distance` in descending order, if two or more users traveled the same distance, order them by their `name` in ascending order.

The query result format is in the following example.

### Example 1:

**Input:**

Users table:

| id   | name      |
|:-----|:----------|
| 1    | Alice     |
| 2    | Bob       |
| 3    | Alex      |
| 4    | Donald    |
| 7    | Lee       |
| 13   | Jonathan  |
| 19   | Elvis     |

Rides table:

| id   | user_id  | distance |
|:-----|:---------|:---------|
| 1    | 1        | 120      |
| 2    | 2        | 317      |
| 3    | 3        | 222      |
| 4    | 7        | 100      |
| 5    | 13       | 312      |
| 6    | 19       | 50       |
| 7    | 7        | 120      |
| 8    | 19       | 400      |
| 9    | 7        | 230      |

**Output:**

| name     | travelled_distance |
|:---------|:-------------------|
| Elvis    | 450                |
| Lee      | 450                |
| Bob      | 317                |
| Jonathan | 312                |
| Alex     | 222                |
| Alice    | 120                |
| Donald   | 0                  |

**Explanation:**
Elvis and Lee traveled 450 miles, Elvis is the top traveler as his name is alphabetically smaller than Lee.
Bob, Jonathan, Alex, and Alice have only one ride and we just order them by the total distances of the ride.
Donald did not have any rides, the distance traveled by him is 0.

```sql
SELECT u.name,
    CASE WHEN SUM(r.distance) IS NOT NULL THEN SUM(r.distance) ELSE 0 END AS travelled_distance 
    FROM Users u
    LEFT OUTER JOIN Rides r ON u.id = r.user_id
    GROUP BY u.id
    ORDER BY travelled_distance DESC, name ASC;
```

## Problem 1484: Group Sold Products By The Date

Table `Activities`:

| Column Name | Type    |
|:------------|:--------|
| sell_date   | date    |
| product     | varchar |

There is no primary key for this table, it may contain duplicates.
Each row of this table contains the product name and the date it was sold in a market.

Write an SQL query to find for each date the number of different products sold and their names.

The sold products names for each date should be sorted lexicographically.

Return the result table ordered by `sell_date`.

The query result format is in the following example.

### Example 1:

**Input:**
Activities table:

| sell_date  | product     |
|:-----------|:-----------|
| 2020-05-30 | Headphone  |
| 2020-06-01 | Pencil     |
| 2020-06-02 | Mask       |
| 2020-05-30 | Basketball |
| 2020-06-01 | Bible      |
| 2020-06-02 | Mask       |
| 2020-05-30 | T-Shirt    |

**Output:**

| sell_date  | num_sold | products                     |
|:-----------|:---------|:-----------------------------|
| 2020-05-30 | 3        | Basketball,Headphone,T-shirt |
| 2020-06-01 | 2        | Bible,Pencil                 |
| 2020-06-02 | 1        | Mask                         |

**Explanation:**
For 2020-05-30, Sold items were (Headphone, Basketball, T-shirt), we sort them lexicographically and separate them by a comma.
For 2020-06-01, Sold items were (Pencil, Bible), we sort them lexicographically and separate them by a comma.
For 2020-06-02, the Sold item is (Mask), we just return it.

```sql
SELECT sell_date, COUNT(DISTINCT product) num_sold, GROUP_CONCAT(DISTINCT product ORDER BY product ASC) AS products
FROM Activities
GROUP BY sell_date;
```

## Problem 1527: Patients with a condition

Table: `Patients`

| Column Name  | Type    |
|:-------------|:--------|
| patient_id   | int     |
| patient_name | varchar |
| conditions   | varchar |

`patient_id` is the primary key for this table.
`conditions` contains 0 or more code separated by spaces.
This table contains information of the patients in the hospital.

Write an SQL query to report the `patient_id`, `patient_name` all conditions of patients who have Type I Diabetes. Type I Diabetes always starts with DIAB1 prefix

Return the result table in any order.

The query result format is in the following example.

###Example 1:

**Input:**
Patients table:

| patient_id | patient_name | conditions   |
|:-----------|:-------------|:-------------|
| 1          | Daniel       | YFEV COUGH   |
| 2          | Alice        |              |
| 3          | Bob          | DIAB100 MYOP |
| 4          | George       | ACNE DIAB100 |
| 5          | Alain        | DIAB201      |

**Output:**

| patient_id | patient_name | conditions   |
|:-----------|:-------------|:-------------|
| 3          | Bob          | DIAB100 MYOP |
| 4          | George       | ACNE DIAB100 |

**Explanation:**

Bob and George both have a condition that starts with DIAB1.

```sql
SELECT patient_id, patient_name, conditions
    FROM Patients
    WHERE conditions LIKE '% DIAB1%' OR conditions LIKE 'DIAB1%'
```

# Problem 1581: Customer Who Visited but Did Not Make Any Transactions

Table: `Visits`

| Column Name | Type    |
|:------------|:--------|
| visit_id    | int     |
| customer_id | int     |

`visit_id` is the primary key for this table.
This table contains information about the customers who visited the mall.

Table: `Transactions`

| Column Name    | Type    |
|:---------------|:--------|
| transaction_id | int     |
| visit_id       | int     |
| amount         | int     |

`transaction_id` is the primary key for this table.
This table contains information about the transactions made during the visit_id.

Write an SQL query to find the IDs of the users who visited without making any transactions and the number of times they made these types of visits.

Return the result table sorted in any order.

The query result format is in the following example.

### Example 1:

**Input:**
Visits

| visit_id | customer_id |
|:---------|:------------|
| 1        | 23          |
| 2        | 9           |
| 4        | 30          |
| 5        | 54          |
| 6        | 96          |
| 7        | 54          |
| 8        | 54          |

Transactions

| transaction_id | visit_id | amount |
|:---------------|:---------|:-------|
| 2              | 5        | 310    |
| 3              | 5        | 300    |
| 9              | 5        | 200    |
| 12             | 1        | 910    |
| 13             | 2        | 970    |

**Output:**

| customer_id | count_no_trans |
|:------------|:---------------|
| 54          | 2              |
| 30          | 1              |
| 96          | 1              |

**Explanation:**
Customer with `id = 23` visited the mall once and made one transaction during the visit with `transaction_id = 12`.
Customer with `id = 9` visited the mall once and made one transaction during the visit with `transaction_id = 13`.
Customer with `id = 30` visited the mall once and did not make any transactions.
Customer with `id = 54` visited the mall three times. During 2 visits they did not make any transactions, and during one visit they made 3 transactions.
Customer with `id = 96` visited the mall once and did not make any transactions.
As we can see, users with IDs 30 and 96 visited the mall one time without making any transactions. Also, user 54 visited the mall twice and did not make any transactions.

```sql
SELECT customer_id, COUNT(1) AS count_no_trans FROM 
    (SELECT transaction_id, visit_id, customer_id FROM Visits
        LEFT JOIN Transactions ON Transactions.visit_id = Visits.visit_id 
        WHERE transaction_id IS NULL) t
    GROUP BY customer_id
    ORDER BY count_no_transactions;

SELECT customer_id, COUNT(v.visit_id) AS count_no_trans FROM
    Visits v
    LEFT JOIN Transactions t ON t.visit_id = v.visit_id
    WHERE t.visit_id IS NULL
    GROUP BY v.customer_id;
```

## Problem 1667: Fix Names in a Table

Table: `Users`

| Column Name    | Type    |
|:---------------|:--------|
| user_id        | int     |
| name           | varchar |

`user_id` is the primary key for this table.
This table contains the ID and the name of the user. The name consists of only lowercase and uppercase characters.

Write an SQL query to fix the names so that only the first character is uppercase and the rest are lowercase.

Return the result table ordered by `user_id`.

The query result format is in the following example.

###Example 1:

**Input:**
Users table:

| user_id | name  |
|:--------|:------|
| 1       | aLice |
| 2       | bOB   |

**Output:**

| user_id | name  |
|:--------|:------|
| 1       | Alice |
| 2       | Bob   |

```sql
SELECT user_id, CONCAT(UPPER(LEFT(name, 1)), LOWER(SUBSTRING(name, 2, LENGTH(name)))) AS name 
    FROM Users ORDER BY user_id;

SELECT user_id, CONCAT(UPPER(LEFT(name, 1)), LOWER(SUBSTRING(name, 2))) AS name 
    FROM Users ORDER BY user_id;
```

## Problem 1683: Invalid Tweets

Table: `Tweets`

| Column Name    | Type     |
|:---------------|:---------|
| tweet_id       | int      |
| content        | varchar  |

`tweet_id` is the primary key for this table.
This table contains all the tweets in a social media app.
Write a SQL query to find the IDs of the invalid tweets. The tweet is invalid if the number of characters used in the
content of the tweet is strictly greater than 15.

Return the result table in any order.
The query result format is in the following example.

### Example 1:

**Input:**

Tweets table:

| tweet_id | content                          |
|:---------|:---------------------------------|
| 1        | Vote for Biden                   |
| 2        | Let us make America great again! |

**Output:**

| tweet_id |
|:---------|
| 2        |

**Explanation:**

Tweet 1 has length = 14. It is a valid tweet.
Tweet 2 has length = 32. It is an invalid tweet.

```sql
SELECT tweet_id FROM
    tweets WHERE LENGTH(content) > 15;
```

## Problem 1693: Daily Leads and Partners

Table: `DailySales`

| Column Name | Type    |
|:------------|:--------|
| date_id     | date    |
| make_name   | varchar |
| lead_id     | int     |
| partner_id  | int     |

This table does not have a primary key.
This table contains the date and the name of the product sold and the IDs of the lead and partner it was sold to.
The name consists of only lowercase English letters.

Write an SQL query that will, for each `date_id` and `make_name`, return the number of distinct `lead_id`'s and distinct `partner_id`'s.

Return the result table in any order.

The query result format is in the following example.

## Example 1:

**Input:**
DailySales table:

| date_id   | make_name | lead_id | partner_id |
|:----------|:----------|:--------|:-----------|
| 2020-12-8 | toyota    | 0       | 1          |
| 2020-12-8 | toyota    | 1       | 0          |
| 2020-12-8 | toyota    | 1       | 2          |
| 2020-12-7 | toyota    | 0       | 2          |
| 2020-12-7 | toyota    | 0       | 1          |
| 2020-12-8 | honda     | 1       | 2          |
| 2020-12-8 | honda     | 2       | 1          |
| 2020-12-7 | honda     | 0       | 1          |
| 2020-12-7 | honda     | 1       | 2          |
| 2020-12-7 | honda     | 2       | 1          |

**Output:**

| date_id   | make_name | unique_leads | unique_partners |
|:----------|:----------|:-------------|:----------------|
| 2020-12-8 | toyota    | 2            | 3               |
| 2020-12-7 | toyota    | 1            | 2               |
| 2020-12-8 | honda     | 2            | 2               |
| 2020-12-7 | honda     | 3            | 2               |

**Explanation:**
For 2020-12-8, toyota gets leads = [0, 1] and partners = [0, 1, 2] while honda gets leads = [1, 2] and partners = [1, 2].
For 2020-12-7, toyota gets leads = [0] and partners = [1, 2] while honda gets leads = [0, 1, 2] and partners = [1, 2].

```sql
SELECT date_id, make_name, COUNT(DISTINCT lead_id) AS unique_leads, COUNT(DISTINCT partner_id) AS unique_partners
    FROM DailySales
    GROUP BY date_id, make_name;
```

## Problem 1729: Find Followers Count

Table: `Followers`

| Column Name | Type |
|:------------|:-----|
| user_id     | int  |
| follower_id | int  |

(user_id, follower_id) is the primary key for this table.
This table contains the IDs of a user and a follower in a social media app where the follower follows the user.

Write an SQL query that will, for each user, return the number of followers.
Return the result table ordered by `user_id`.

The query result format is in the following example.

### Example 1:

**Input:**

Followers table:

| user_id | follower_id |
|:--------|:------------|
| 0       | 1           |
| 1       | 0           |
| 2       | 0           |
| 2       | 1           |

**Output:**

| user_id | followers_count|
|:--------|:---------------|
| 0       | 1              |
| 1       | 1              |
| 2       | 2              |

**Explanation:**
The followers of 0 are {1}
The followers of 1 are {0}
The followers of 2 are {0,1}

```sql
SELECT user_id, COUNT(follower_id) AS followers_count
    FROM Followers
    GROUP BY user_id
    ORDER BY user_id;
```

## Problem 1757: Recyclable and Low Fat Products
Table: Products

| Column Name | Type    |
|:------------|:--------|
| product_id  | int     |
| low_fats    | enum    |
| recyclable  | enum    |

`product_id` is the primary key for this table.
`low_fats` is an ENUM of type ('Y', 'N') where 'Y' means this product is low fat and 'N' means it is not.
`recyclable` is an ENUM of types ('Y', 'N') where 'Y' means this product is recyclable and 'N' means it is not.


Write an SQL query to find the ids of products that are both low fat and recyclable.

Return the result table in any order.

The query result format is in the following example.

### Example 1:

**Input:**
Products table:

| product_id  | low_fats | recyclable |
|:------------|:---------|:-----------|
| 0           | Y        | N          |
| 1           | Y        | Y          |
| 2           | N        | Y          |
| 3           | Y        | Y          |
| 4           | N        | N          |

**Output:**

| product_id  |
|:------------|
| 1           |
| 3           |

Explanation: Only products 1 and 3 are both low fat and recyclable.

```sql
SELECT product_id FROM Products WHERE low_fats = 'Y' AND recyclable = 'Y';
```

## Problem 1795: Rearrange Products Table

Table: `Products`

| Column Name | Type    |
|:------------|:--------|
| product_id  | int     |
| store1      | int     |
| store2      | int     |
| store3      | int     |

`product_id` is the primary key for this table.
Each row in this table indicates the product's price in 3 different stores: store1, store2, and store3.
If the product is not available in a store, the price will be `null` in that store's column.
Write an SQL query to rearrange the Products table so that each row has (`product_id`, `store`, `price`). If a product is not available in a store, do not include a row with that `product_id` and `store` combination in the result table.

Return the result table in any order.

The query result format is in the following example.

### Example 1:

```
        1
       / \
      2   3
    /  \
   4    6
```

**Input:**

`Products` table:

| product_id | store1 | store2 | store3  |
|:-----------|:-------|:-------|:--------|
| 0          | 95     | 100    | 105     |
| 1          | 70     | null   | 80      |

**Output:**

| product_id | store  | price |
|:-----------|:-------+|:------|
| 0          | store1 | 95    |
| 0          | store2 | 100   |
| 0          | store3 | 105   |
| 1          | store1 | 70    |
| 1          | store3 | 80    |

**Explanation:**

Product 0 is available in all three stores with prices 95, 100, and 105 respectively.
Product 1 is available in store1 with price 70 and store3 with price 80. The product is not available in store2.

```sql
SELECT product_id, 'store1' AS store, store1 AS price 
    FROM products
    WHERE store1 IS NOT NULL
UNION
SELECT product_id, 'store2' AS store, store2 AS price 
    FROM products
    WHERE store2 IS NOT NULL
UNION
SELECT product_id, 'store3' AS store, store3 AS price
    FROM products
    WHERE store3 IS NOT NULL;
```

## Problem 1873: Calculate Special Bonus

Table: `Employees`


| Column Name | Type    |
|:------------|:--------|
| employee_id | int     |
| name        | varchar |
| salary      | int     |

`employee_id` is the primary key for this table.
Each row of this table indicates the employee ID, employee name, and salary.


Write an SQL query to calculate the bonus of each employee. The bonus of an employee is 100% of their salary if the ID of the employee is an odd number and the employee name does not start with the character 'M'. The bonus of an employee is 0 otherwise.

Return the result table ordered by `employee_id`.

The query result format is in the following example.
###Example 1:

**Input:**
Employees table:

| employee_id | name    | salary |
|:------------|:--------|:-------|
| 2           | Meir    | 3000   |
| 3           | Michael | 3800   |
| 7           | Addilyn | 7400   |
| 8           | Juan    | 6100   |
| 9           | Kannon  | 7700   |

**Output:**

| employee_id | bonus |
|:------------|:------|
| 2           | 0     |
| 3           | 0     |
| 7           | 7400  |
| 8           | 0     |
| 9           | 7700  |

**Explanation:**
The employees with IDs 2 and 8 get 0 bonus because they have an even employee_id.
The employee with ID 3 gets 0 bonus because their name starts with 'M'.
The rest of the employees get a 100% bonus.

```sql
SELECT employee_id, IF (MOD(employee_id, 2) != 0 AND LEFT(name, 1) != 'M', salary, 0) AS bonus FROM Employees;

SELECT employee_id, CASE WHEN employee_id % 2 != 0 AND SUBSTRING(name, 1, 1) != 'M' THEN salary ELSE 0 END AS bonus FROM Employees;
```

## Problem 1965: Employees with Missing Information

Table: `Employees`

| Column Name | Type    |
|:------------|:--------|
| employee_id | int     |
| name        | varchar |

`employee_id` is the primary key for this table.
Each row of this table indicates the name of the employee whose ID is `employee_id`.


Table: `Salaries`

| Column Name | Type    |
|:------------|:--------|
| employee_id | int     |
| salary      | int     |

`employee_id` is the primary key for this table.
Each row of this table indicates the salary of the employee whose ID is `employee_id`.

Write an SQL query to report the IDs of all the employees with missing information. The information of an employee is missing if:

The employee's name is missing, or
The employee's salary is missing.
Return the result table ordered by `employee_id` in ascending order.

The query result format is in the following example.

###Example 1:

**Input:**
Employees table:

| employee_id | name     |
|:------------|:---------|
| 2           | Crew     |
| 4           | Haven    |
| 5           | Kristian |

Salaries table:

| employee_id | salary |
|:------------|:-------|
| 5           | 76071  |
| 1           | 22517  |
| 4           | 63539  |

**Output:**

| employee_id |
|:------------|
| 1           |
| 2           |

**Explanation:**
Employees 1, 2, 4, and 5 are working at this company.
The name of employee 1 is missing.
The salary of employee 2 is missing.

```sql
-- Doesn't work on MySQL
-- We don't have Full outer join in MYSQL - https://stackoverflow.com/questions/4796872/how-can-i-do-a-full-outer-join-in-mysql
SELECT employee_id FROM (
SELECT employee_id, name, salary FROM Employees
    FULL OUTER JOIN Salaries
    ON Employees.employee_id = Salaries.employee_id
) tmp WHERE tmp.name IS NULL OR tmp.salary IS NULL
ORDER BY employee_id; 

SELECT e.employee_id FROM Employees e
    LEFT OUTER JOIN Salaries s
    ON e.employee_id = s.employee_id WHERE s.salary IS NULL
UNION ALL
SELECT s.employee_id FROM Employees e
    RIGHT OUTER JOIN Salaries s
    ON e.employee_id = s.employee_id WHERE e.name IS NULL
ORDER BY employee_id;

SELECT CASE WHEN e.employee_id IS NULL THEN s.employee_id
    ELSE e.employee_id
    END AS employee_id
    FROM Employees e
    FULL OUTER JOIN Salaries s ON e.employee_id = s.employee_id
    WHERE e.name IS NULL OR s.salary IS NULL
    ORDER BY employee_id;
    
-- Simple option
SELECT employee_id FROM Employees 
    WHERE employee_id NOT IN (SELECT employee_id FROM Salaries)
UNION
SELECT employee_id FROM Salaries
    WHERE employee_id NOT IN (SELECT employee_id FROM Employees)
ORDER BY employee_id;
```
