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

