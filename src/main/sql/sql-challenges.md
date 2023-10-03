 # Leetcode SQL Challenge 1

## Problem 595: Big countries
SQL Schema
Table: `World`

| Column Name | Type    |
|:------------|:--------|
| name        | varchar |
| continent   | varchar |
| area        | int     |
| population  | int     |
| gdp         | int     |

`name` is the primary key column for this table.
Each row of this table gives information about the name of a country, the continent to which it belongs, its area, the population, and its GDP value.


A country is big if:

it has an `area` of at least three million (i.e., 3000000 km2), or
it has a `population` of at least twenty-five million (i.e., 25000000).
Write an SQL query to report the name, population, and area of the big countries.

Return the result table in any order.

The query result format is in the following example.



### Example 1:

**Input:**
World table:

| name        | continent | area    | population | gdp          |
|------------|:----------|:---------|:------------|:--------------|
| Afghanistan | Asia      | 652230  | 25500100   | 20343000000  |
| Albania     | Europe    | 28748   | 2831741    | 12960000000  |
| Algeria     | Africa    | 2381741 | 37100000   | 188681000000 |
| Andorra     | Europe    | 468     | 78115      | 3712000000   |
| Angola      | Africa    | 1246700 | 20609294   | 100990000000 |

** Output: **

| name        | population | area    |
|:------------|:-----------|:--------|
| Afghanistan | 25500100   | 652230  |
| Algeria     | 37100000   | 2381741 |



```sql
SELECT name, population, area FROM World WHERE area >= 3000000 OR population >= 25000000;
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

## Problem 584: Find Customer Referee

Table: `Customer`

| Column Name | Type    |
|:------------|:--------|
| id          | int     |
| name        | varchar |
| referee_id  | int     |

`id` is the primary key column for this table.
Each row of this table indicates the id of a customer, their name, and the id of the customer who referred them.


Write an SQL query to report the IDs of the customer that are not referred by the customer with id = 2.

Return the result table in any order.

The query result format is in the following example.

### Example 1:

**Input:**
Customer table:

| id | name | referee_id |
|:---|:-----|:-----------|
| 1  | Will | null       |
| 2  | Jane | null       |
| 3  | Alex | 2          |
| 4  | Bill | null       |
| 5  | Zack | 1          |
| 6  | Mark | 2          |

**Output:**

| name |
|:-----|
| Will |
| Jane |
| Bill |
| Zack |

```sql
SELECT name FROM Customer WHERE referee_id IS NULL OR referee_id != 2;
```

## Problem 183: Customers Who Never Order

Table: `Customers`

| Column Name | Type    |
|:------------|:--------|
| id          | int     |
| name        | varchar |

`id` is the primary key column for this table.
Each row of this table indicates the ID and name of a customer.

Table: `Orders`

| Column Name | Type |
|:------------|:-----|
| id          | int  |
| customerId  | int  |

`id` is the primary key column for this table.
`customerId` is a foreign key of the ID from the Customers table.
Each row of this table indicates the ID of an order and the ID of the customer who ordered it.

Write an SQL query to report all customers who never order anything.
Return the result table in any order.

The query result format is in the following example.
###Example 1:

**Input:**
Customers table:

| id | name  |
|:---|:------|
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |

Orders table:

| id | customerId |
|:---|:-----------|
| 1  | 3          |
| 2  | 1          |

Output:

| Customers |
|-----------|
| Henry     |
| Max       |

```sql
SELECT c.name AS Customers FROM Customers c
LEFT OUTER JOIN
Orders o 
ON c.id = o.customerId
WHERE o.customerId IS NULL;

select name as Customers from Customers where id not in (select customerId from Orders)
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

## Problem 627: Swap Salary

Table: `Salary`

| Column Name | Type     |
|:------------|:---------|
| id          | int      |
| name        | varchar  |
| sex         | ENUM     |
| salary      | int      |

`id` is the primary key for this table.
The `sex` column is ENUM value of type ('m', 'f').
The table contains information about an employee.

Write an SQL query to swap all 'f' and 'm' values (i.e., change all 'f' values to 'm' and vice versa) with a single update statement and no intermediate temporary tables.

Note that you must write a single update statement, do not write any select statement for this problem.

The query result format is in the following example.

###Example 1:

**Input:**
Salary table:

| id | name | sex | salary |
|:---|:-----|:----|:-------|
| 1  | A    | m   | 2500   |
| 2  | B    | f   | 1500   |
| 3  | C    | m   | 5500   |
| 4  | D    | f   | 500    |

**Output:**

| id | name | sex | salary |
|:---|:-----|:----|:-------|
| 1  | A    | f   | 2500   |
| 2  | B    | m   | 1500   |
| 3  | C    | f   | 5500   |
| 4  | D    | m   | 500    |

**Explanation:**
(1, A) and (3, C) were changed from 'm' to 'f'.
(2, B) and (4, D) were changed from 'f' to 'm'.

```sql
UPDATE Salary 
    SET sex = IF(sex = 'm', 'f', 'm');

UPDATE Salary 
    SET sex = CASE WHEN sex = 'm' THEN 'f' ELSE 'm' END;
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

## Problem 511: Game Play Analysis I

Table: `Activity`

| Column Name  | Type    |
|:-------------|:--------|
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |

(player_id, event_date) is the primary key of this table.
This table shows the activity of players of some games.
Each row is a record of a player who logged in and played a number of games (possibly 0) before logging out on someday using some device.

Write an SQL query to report the first login date for each player.

Return the result table in any order.

The query result format is in the following example.

### Example 1:

**Input:**
Activity table:

| player_id | device_id | event_date | games_played |
|:----------|:----------|:-----------|:-------------|
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-05-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |

**Output:**

| player_id | first_login |
|:----------|:------------|
| 1         | 2016-03-01  |
| 2         | 2017-06-25  |
| 3         | 2016-03-02  |

```sql
SELECT player_id, MIN(event_date) AS first_login
    FROM Activity
    GROUP BY player_id
    ORDER BY player_id;
```

## Problem 182: Duplicate Emails

Table: `Person`

| Column Name | Type    |
|:------------|:--------|
| id          | int     |
| email       | varchar |

`id` is the primary key column for this table.
Each row of this table contains an email. The emails will not contain uppercase letters.

Write an SQL query to report all the duplicate emails.

Return the result table in any order.
The query result format is in the following example.

### Example 1:

**Input:**
Person table:

| id | email   |
|:---|:--------|
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |

**Output:**

| Email   |
|:-------:|
| a@b.com |

**Explanation:** a@b.com is repeated two times.

```sql
SELECT email FROM Person
    GROUP BY email
    HAVING COUNT(email) > 1;
```

## Problem 1050: Actors and Directors Who Cooperated At Least Three Times

Table: `ActorDirector`

| Column Name | Type    |
|:------------|:--------|
| actor_id    | int     |
| director_id | int     |
| timestamp   | int     |

timestamp is the primary key column for this table.

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

## Problem 175: Combine Two Tables

Table: `Person`

| Column Name | Type    |
|:------------|:--------|
| personId    | int     |
| lastName    | varchar |
| firstName   | varchar |

`personId` is the primary key column for this table.
This table contains information about the ID of some persons and their first and last names.

Table: `Address`

| Column Name | Type    |
|:------------|:--------|
| addressId   | int     |
| personId    | int     |
| city        | varchar |
| state       | varchar |

`addressId` is the primary key column for this table.
Each row of this table contains information about the city and state of one person with ID = PersonId.

Write an SQL query to report the first name, last name, city, and state of each person in the Person table. If the address of a `personId` is not present in the `Address` table, report null instead.

Return the result table in any order.

The query result format is in the following example.

### Example 1:

**Input:**
Person table:

| personId | lastName | firstName |
|:---------|:---------|:----------|
| 1        | Wang     | Allen     |
| 2        | Alice    | Bob       |

Address table:

| addressId | personId | city          | state      |
|:----------|:---------|:--------------|:-----------|
| 1         | 2        | New York City | New York   |
| 2         | 3        | Leetcode      | California |

**Output:**

| firstName | lastName | city          | state    |
|:----------|:---------|:--------------|:---------|
| Allen     | Wang     | Null          | Null     |
| Bob       | Alice    | New York City | New York |

**Explanation:**
There is no address in the address table for the `personId = 1` so we return null in their `city` and `state`.
`addressId = 1` contains information about the address of `personId = 2`.

```sql
SELECT firstName, lastName, city, state FROM Person
    LEFT OUTER JOIN Address ON Person.personId = Address.personId;
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

## Problem 607: Sales Person

Table:  SalesPerson 

| Column Name     | Type    |
|:----------------|:--------|
| sales_id        | int     |
| name            | varchar |
| salary          | int     |
| commission_rate | int     |
| hire_date       | date    |

`sales_id` is the primary key column for this table.
Each row of this table indicates the name and the ID of a salesperson alongside their salary, commission rate, and hire date.

Table: `Company`

| Column Name | Type    |
|:------------|:--------|
| com_id      | int     |
| name        | varchar |
| city        | varchar |

`com_id` is the primary key column for this table.
Each row of this table indicates the name and the ID of a company and the city in which the company is located.

Table: `Orders`

| Column Name | Type |
|:------------|:-----|
| order_id    | int  |
| order_date  | date |
| com_id      | int  |
| sales_id    | int  |
| amount      | int  |

`order_id` is the primary key column for this table.
`com_id` is a foreign key to `com_id` from the Company table.
`sales_id` is a foreign key to `sales_id` from the SalesPerson table.
Each row of this table contains information about one order. This includes the ID of the company, the ID of the salesperson, the date of the order, and the amount paid.



Write an SQL query to report the names of all the salespersons who did not have any orders related to the company with the name "RED".

Return the result table in any order.
The query result format is in the following example.

### Example 1:

**Input:**

SalesPerson table:

| sales_id | name | salary | commission_rate | hire_date  |
|:---------|:-----|:-------|:----------------|:-----------|
| 1        | John | 100000 | 6               | 4/1/2006   |
| 2        | Amy  | 12000  | 5               | 5/1/2010   |
| 3        | Mark | 65000  | 12              | 12/25/2008 |
| 4        | Pam  | 25000  | 25              | 1/1/2005   |
| 5        | Alex | 5000   | 10              | 2/3/2007   |

Company table:

| com_id | name   | city     |
|:-------|:-------|:---------|
| 1      | RED    | Boston   |
| 2      | ORANGE | New York |
| 3      | YELLOW | Boston   |
| 4      | GREEN  | Austin   |

Orders table:

| order_id | order_date | com_id | sales_id | amount |
|:---------|:-----------|:-------|:---------|:-------|
| 1        | 1/1/2014   | 3      | 4        | 10000  |
| 2        | 2/1/2014   | 4      | 5        | 5000   |
| 3        | 3/1/2014   | 1      | 1        | 50000  |
| 4        | 4/1/2014   | 1      | 4        | 25000  |

**Output:**

| name |
|:-----|
| Amy  |
| Mark |
| Alex |

**Explanation:**
According to orders 3 and 4 in the Orders table, it is easy to tell that only salesperson John and Pam have sales to company RED, so we report all the other names in the table salesperson.

```sql
SELECT name FROM SalesPerson WHERE sales_id NOT IN 
    (SELECT sales_id FROM Orders
        LEFT JOIN Company ON Orders.com_id = Company.com_id
        WHERE Company.name = 'RED');
```

## Problem 586: Customers Placing The Largest Number Of Orders

Table:  Orders  

| Column Name     | Type     |
|:----------------|:---------|
| order_number    | int      |
| customer_number | int      |

`order_number` is the primary key for this table.
This table contains information about the order ID and the customer ID.

Write an SQL query to find the `customer_number` for the customer who has placed the largest number of orders.

The test cases are generated so that exactly one customer will have placed more orders than any other customer.

The query result format is in the following example.

### Example 1:

**Input:**

Orders table:

| order_number | customer_number |
|:-------------|:----------------|
| 1            | 1               |
| 2            | 2               |
| 3            | 3               |
| 4            | 3               |

**Output:**

| customer_number |
|:----------------|
| 3               |

**Explanation:**
The customer with number 3 has two orders, which is greater than either customer 1 or 2 because each of them only has one order.
So the result is `customer_number` 3.

```sql
SELECT customer_number FROM Orders
    GROUP BY customer_number
    ORDER BY COUNT(order_number) DESC
    LIMIT 1;
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

## Problem 196: Delete Duplicate Emails

Table: `Person`

| Column Name | Type    |
|:------------|:--------|
| id          | int     |
| email       | varchar |

`id` is the primary key column for this table.
Each row of this table contains an email. The emails will not contain uppercase letters.


Write an SQL query to delete all the duplicate emails, keeping only one unique email with the smallest `id`. Note that you are supposed to write a DELETE statement and not a SELECT one.

Return the result table in any order.

The query result format is in the following example.

### Example 1:

**Input:**
Person table:

| id | email            |
|:---|:-----------------|
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |

**Output:**

| id | email            |
|:---|:-----------------|
| 1  | john@example.com |
| 2  | bob@example.com  |

**Explanation:** john@example.com is repeated two times. We keep the row with the smallest Id = 1.

```sql
DELETE p FROM Person p, Person q WHERE p.email = q.email AND p.id > q.id; 
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

## Problem 608: Tree Node

Table: `Tree`

| Column Name | Type |
|:------------|:-----|
| id          | int  |
| p_id        | int  |

`id` is the primary key column for this table.
Each row of this table contains information about the `id` of a node and the `id` of its parent node in a tree.
The given structure is always a valid tree.
Each node in the tree can be one of three types:

“Leaf”: if the node is a leaf node.
“Root”: if the node is the root of the tree.
“Inner”: If the node is neither a leaf node nor a root node.

Write a SQL query to report the type of each node in the tree.

Return the result table ordered by id in ascending order.
The query result format is in the following example.

### Example 1:
**Input:**

Tree table:

| id | p_id |
|:---|:-----|
| 1  | null |
| 2  | 1    |
| 3  | 1    |
| 4  | 2    |
| 5  | 2    |

Output:

| id | type   |
|:---|:-------|
| 1 | Root   |
| 2 | Inner  |
| 3 | Leaf   |
| 4 | Leaf   |
| 5 | Leaf   |

**Explanation:**

Node 1 is the root node because its parent node is `null` and it has child nodes 2 and 3.
Node 2 is an inner node because it has parent node 1 and child node 4 and 5.
Nodes 3, 4, and 5 are leaf nodes because they have parent nodes and they do not have child nodes.

### Example 2:
**Input:**

Tree table:

| id | p_id |
|:---|:-----|
| 1  | null |

**Output:**

| id | type  |
|:---|:------|
| 1  | Root  |

**Explanation:** If there is only one node on the tree, you only need to output its root attributes.

```sql
SELECT id, 
    CASE WHEN p_id IS NULL THEN 'Root'
    WHEN id IN (SELECT p_id FROM tree) THEN 'Inner'
    ELSE 'Leaf'
    END AS type
    FROM tree
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

## Problem 176: Second Highest Salary

Table: `Employee`

| Column Name | Type  |
|:------------|:------|
| id          | int   |
| salary      | int   |

`id` is the primary key column for this table.
Each row of this table contains information about the salary of an employee.
Write a SQL query to report the second highest salary from the Employee table. 
If there is no second highest salary, the query should report `null`.

The query result format is in the following example.

### Example 1:

**Input:**

Employee table:

| id | salary  |
|:---|:--------|
| 1  | 100     |
| 2  | 200     |
| 3  | 300     |

**Output:**

| SecondHighestSalary |
|:---------------------|
| 200                 |

### Example 2:

**Input:**

Employee table:

| id | salary |
|:---|:-------|
| 1  | 100    |

Output:

| SecondHighestSalary |
|:---------------------|
| null                |

```sql
SELECT IFNULL(
        (SELECT DISTINCT(Salary) FROM Employee
            ORDER BY Salary DESC
            LIMIT 1
            OFFSET 1),
        NULL
    ) SecondHighestSalary;
```

## Problem 197: Rising Temperature

Table: `Weather`

| Column Name   | Type    |
|:--------------|:--------|
| id            | int     |
| recordDate    | date    |
| temperature   | int     |

`id` is the primary key for this table.
This table contains information about the temperature on a certain day.
Write a SQL query to find all dates’ `Id` with higher temperatures compared to its previous dates (yesterday).

Return the result table in any order.
The query result format is in the following example.

### Example 1:

**Input:**

Weather table:

| id | recordDate | temperature |
|:---|:-----------|:------------|
| 1  | 2015-01-01 | 10          |
| 2  | 2015-01-02 | 25          |
| 3  | 2015-01-03 | 20          |
| 4  | 2015-01-04 | 30          |

**Output:**

| id |
|----|
| 2  |
| 4  |

**Explanation:**

In 2015-01-02, the temperature was higher than the previous day (10 -> 25).
In 2015-01-04, the temperature was higher than the previous day (20 -> 30).

```sql
SELECT
    Weather.id AS 'Id'
FROM Weather
JOIN Weather w
    ON DATEDIFF(Weather.RecordDate, w.RecordDate) = 1
    AND Weather.Temperature > w.Temperature;

SELECT w2.id
    FROM Weather w1, Weather w2
    WHERE w2.temperature > w1.temperature 
        AND DATEDIFF(w2.recordDate, w1.recordDate) = 1;

SElECT w1.id
FROM Weather w1, Weather w2
WHERE w1.Temperature > w2.Temperature
    AND TO_DAYS(w1.RecordDate) - TO_DAYS(w2.RecordDate) = 1 
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

## Problem 1068: Product Sales Analyais |

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
Each row of this table shows a sale on the product product_id in a certain year.
Note that the `price` is per unit.

Table: Product

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
Sales table:

| sale_id | product_id | year | quantity | price  |
|:--------|:-----------|:-----|:---------|:-------|
| 1       | 100        | 2008 | 10       | 5000   |
| 2       | 100        | 2009 | 12       | 5000   |
| 7       | 200        | 2011 | 15       | 9000   |

Product table:

| product_id | product_name |
|:-----------|:-------------|
| 100        | Nokia        |
| 200        | Apple        |
| 300        | Samsung      |

**Output:**

| product_name | year  | price |
|:-------------|:------+-------|
| Nokia        | 2008  | 5000  |
| Nokia        | 2009  | 5000  |
| Apple        | 2011  | 9000  |

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