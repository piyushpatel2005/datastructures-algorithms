 # Leetcode SQL Challenge 1

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

## Problem 177. Nth Highest Salary

Table: `Employee`

| Column Name | Type  |
|:------------|:------|
| id          | int   |
| salary      | int   |

`id` is the primary key (column with unique values) for this table.
Each row of this table contains information about the `salary` of an employee.
Write a solution to find the nth highest `salary` from the Employee table. If there is no nth highest salary, return `null`.

The result format is in the following example.

### Example 1:

**Input:**

`Employee` table:

| id | salary |
|:---|:-------|
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |

n = 2

**Output:**

| getNthHighestSalary(2) |
|:-----------------------|
| 200                    |

### Example 2:

**Input:**
`Employee` table:

| id | salary  |
|:---|:--------|
| 1  | 100     |
+----+--------+
n = 2

**Output:**

| getNthHighestSalary(2) |
|:-----------------------|
| null                   |

```sql
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  DECLARE M INT;
  SET M = N - 1;
  RETURN (
       SELECT DISTINCT salary FROM Employee ORDER BY salary DESC
        LIMIT 1 OFFSET M
  );
END
```

## Problem 178: Rank Scores

Table: `Scores`

| Column Name | Type     |
|:------------|:---------|
| id          | int      |
| score       | decimal  |

`id` is the primary key (column with unique values) for this table.
Each row of this table contains the `score` of a game. Score is a floating point value with two decimal places.

Write a solution to find the rank of the scores. The ranking should be calculated according to the following rules:

    The scores should be ranked from the highest to the lowest.
    If there is a tie between two scores, both should have the same ranking.
    After a tie, the next ranking number should be the next consecutive integer value. In other words, there should be no holes between ranks.

Return the result table ordered by score in descending order.

The result format is in the following example.

### Example 1:

**Input:**
`Scores` table:

| id | score |
|:---|:------|
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |

**Output:**

| score | rank  |
|:------|:------|
| 4.00  | 1     |
| 4.00  | 1     |
| 3.85  | 2     |
| 3.65  | 3     |
| 3.65  | 3     |
| 3.50  | 4     |

```sql
SELECT score, DENSE_RANK() OVER (ORDER BY score DESC) AS 'rank' FROM scores ORDER BY score DESC;
```

## Problem 180: Consecutive Numbers

Table: `Logs`

| Column Name | Type     |
|:------------|:---------|
| id          | int      |
| num         | varchar  |

In SQL, `id` is the primary key for this table.
`id` is an autoincrement column.

Find all numbers that appear at least three times consecutively.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Logs` table:

| id | num  |
|:---|:-----|
| 1  | 1    |
| 2  | 1    |
| 3  | 1    |
| 4  | 2    |
| 5  | 1    |
| 6  | 2    |
| 7  | 2    |

**Output:**

| ConsecutiveNums |
|:----------------|
| 1               |

**Explanation:** 1 is the only number that appears consecutively for at least three times.

```sql
WITH cte AS (
    SELECT num, 
        LEAD(num, 1) OVER () num_lead,
        LAG(num, 1) OVER () num_lag
        FROM logs
)
SELECT DISTINCT num AS ConsecutiveNums FROM cte WHERE num = num_lead AND num = num_lag;


WITH cte AS (
    SELECT num, 
        LEAD(num, 1) OVER (ORDER BY id) num_lead,
        LAG(num, 1) OVER (ORDER BY id) num_lag
        FROM logs
)
SELECT DISTINCT num AS ConsecutiveNums FROM cte WHERE num = num_lead AND num = num_lag;
```

## Problem 181: Employees Earning More Than Their Managers

Table: `Employee`

| Column Name | Type     |
|:------------|:---------|
| id          | int      |
| name        | varchar  |
| salary      | int      |
| managerId   | int      |

`id` is the primary key (column with unique values) for this table.
Each row of this table indicates the ID of an employee, their `name`, `salary`, and `managerId`-the ID of their manager.

Write a solution to find the employees who earn more than their managers.
Return the result table in any order.
The result format is in the following example.

###Example 1:

**Input:** `Employee` table:

| id | name   | salary | managerId |
|:---|:-------|:-------|:----------|
| 1  | Joe    | 70000  | 3         |
| 2  | Henry  | 80000  | 4         |
| 3  | Sam    | 60000  | Null      |
| 4  | Max    | 90000  | Null      |

**Output:**

| Employee |
|:---------|
| Joe      |

**Explanation:** Joe is the only employee who earns more than his manager.

```sql
SELECT emp.name Employee FROM Employee emp
JOIN Employee man
ON man.id = emp.managerId
WHERE emp.salary > man.salary;


SELECT
    a.Name AS 'Employee'
FROM
    Employee AS a,
    Employee AS b
WHERE
    a.ManagerId = b.Id
        AND a.Salary > b.Salary
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

## Problem 184. Department Highest Salary

Table: `Employee`

| Column Name  | Type    |
|:-------------|:--------|
| id           | int     |
| name         | varchar |
| salary       | int     |
| departmentId | int     |

`id` is the primary key (column with unique values) for this table.
`departmentId` is a foreign key (reference columns) of the ID from the Department table.
Each row of this table indicates the ID, name, and salary of an employee. It also contains the ID of their department.

Table: `Department`

| Column Name | Type     |
|:------------|:---------|
| id          | int      |
| name        | varchar  |

`id` is the primary key (column with unique values) for this table. It is guaranteed that department name is not `NULL`.
Each row of this table indicates the ID of a department and its name.

Write a solution to find employees who have the highest salary in each of the departments.
Return the result table in any order.
The result format is in the following example.

### Example 1:

**Input:** `Employee` table:

| id | name  | salary  | departmentId |
|:---|:------|:--------|:-------------|
| 1  | Joe   | 70000   | 1            |
| 2  | Jim   | 90000   | 1            |
| 3  | Henry | 80000   | 2            |
| 4  | Sam   | 60000   | 2            |
| 5  | Max   | 90000   | 1            |

`Department` table:

| id  | name  |
|:----|:------|
| 1   | IT    |
| 2   | Sales |

**Output:**

| Department | Employee  | Salary |
|:-----------|:----------|:-------|
| IT         | Jim       | 90000  |
| Sales      | Henry     | 80000  |
| IT         | Max       | 90000  |

**Explanation:** Max and Jim both have the highest salary in the IT department and Henry has the highest salary in the Sales department.

```sql
WITH cte AS (
    SELECT id, name, salary, departmentId, 
        DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) AS ranking
        FROM Employee
)
SELECT d.name Department, cte.name Employee, cte.salary Salary FROM cte
JOIN Department d
ON cte.departmentId = d.id
WHERE ranking = 1;


SELECT d.name Department,
    e.name Employee,
    e.salary Salary
    FROM Employee e JOIN Department d
    ON e.departmentId = d.id
    WHERE (e.departmentId, e.salary) IN (
        SELECT departmentId, MAX(salary) FROM Employee
        GROUP BY DepartmentId
    );
```

## Problem 185. Department Top Three Salaries

Table: `Employee`

| Column Name  | Type     |
|:-------------|:---------|
| id           | int      |
| name         | varchar  |
| salary       | int      |
| departmentId | int      |

`id` is the primary key (column with unique values) for this table.
`departmentId` is a foreign key (reference column) of the ID from the Department table.

Each row of this table indicates the ID, name, and salary of an employee. It also contains the ID of their department.

Table: `Department`

| Column Name | Type    |
|:------------|:--------|
| id          | int     |
| name        | varchar |

`id` is the primary key (column with unique values) for this table.

Each row of this table indicates the ID of a department and its name.

A company's executives are interested in seeing who earns the most money in each of the company's departments. A high earner in a department is an employee who has a salary in the top three unique salaries for that department.

Write a solution to find the employees who are high earners in each of the departments.
Return the result table in any order.
The result format is in the following example.

### Example 1:

**Input:** `Employee` table:

| id | name  | salary  | departmentId |
|:---|:------|:--------|:-------------|
| 1  | Joe   | 85000   | 1            |
| 2  | Henry | 80000   | 2            |
| 3  | Sam   | 60000   | 2            |
| 4  | Max   | 90000   | 1            |
| 5  | Janet | 69000   | 1            |
| 6  | Randy | 85000   | 1            |
| 7  | Will  | 70000   | 1            |

`Department` table:

| id | name   |
|:---|:-------|
| 1  | IT     |
| 2  | Sales  |

**Output:**

| Department | Employee  | Salary |
|:-----------|:----------|:-------|
| IT         | Max       | 90000  |
| IT         | Joe       | 85000  |
| IT         | Randy     | 85000  |
| IT         | Will      | 70000  |
| Sales      | Henry     | 80000  |
| Sales      | Sam       | 60000  |

**Explanation:**
In the **IT** department:
- Max earns the highest unique salary
- Both Randy and Joe earn the second-highest unique salary
- Will earns the third-highest unique salary

In the **Sales** department:
- Henry earns the highest salary
- Sam earns the second-highest salary
- There is no third-highest salary as there are only two employees

```sql
WITH cte AS (
    SELECT id, name, salary, departmentId, DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) AS 'rank'
        FROM Employee
)
SELECT d.name Department, cte.name Employee, cte.salary Salary
    FROM cte JOIN Department d
    ON cte.departmentId = d.id
    WHERE cte.rank <=3
    ORDER BY d.name, cte.rank ASC;
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
## Problem 262. Trips and Users

Table: `Trips`

| Column Name | Type     |
|:------------|:---------|
| id          | int      |
| client_id   | int      |
| driver_id   | int      |
| city_id     | int      |
| status      | enum     |
| request_at  | date     |     

`id` is the primary key (column with unique values) for this table.
The table holds all taxi trips. Each trip has a unique `id`, while `client_id` and `driver_id` are foreign keys to the `users_id` at the `Users` table.
`Status` is an ENUM (category) type of ('completed', 'cancelled_by_driver', 'cancelled_by_client').

Table: `Users`

| Column Name | Type     |
|:------------|:---------|
| users_id    | int      |
| banned      | enum     |
| role        | enum     |

`users_id` is the primary key (column with unique values) for this table.
The table holds all users. Each user has a unique `users_id`, and `role` is an ENUM type of ('client', 'driver', 'partner').
`banned` is an ENUM (category) type of ('Yes', 'No').

The cancellation rate is computed by dividing the number of canceled (by client or driver) requests with unbanned users by the total number of requests with unbanned users on that day.

Write a solution to find the cancellation rate of requests with unbanned users (both client and driver must not be banned) each day between "2013-10-01" and "2013-10-03". Round Cancellation Rate to two decimal points.

Return the result table in any order. The result format is in the following example.

### Example 1:

**Input:**
`Trips` table:

| id | client_id | driver_id | city_id | status              | request_at |
|:---|:----------|:----------|:--------|:--------------------|:-----------|
| 1  | 1         | 10        | 1       | completed           | 2013-10-01 |
| 2  | 2         | 11        | 1       | cancelled_by_driver | 2013-10-01 |
| 3  | 3         | 12        | 6       | completed           | 2013-10-01 |
| 4  | 4         | 13        | 6       | cancelled_by_client | 2013-10-01 |
| 5  | 1         | 10        | 1       | completed           | 2013-10-02 |
| 6  | 2         | 11        | 6       | completed           | 2013-10-02 |
| 7  | 3         | 12        | 6       | completed           | 2013-10-02 |
| 8  | 2         | 12        | 12      | completed           | 2013-10-03 |
| 9  | 3         | 10        | 12      | completed           | 2013-10-03 |
| 10 | 4         | 13        | 12      | cancelled_by_driver | 2013-10-03 |

`Users` table:

| users_id | banned | role   |
|:---------|:-------|:-------|
| 1        | No     | client |
| 2        | Yes    | client |
| 3        | No     | client |
| 4        | No     | client |
| 10       | No     | driver |
| 11       | No     | driver |
| 12       | No     | driver |
| 13       | No     | driver |

**Output:**

| Day        | Cancellation Rate |
|:-----------|:------------------|
| 2013-10-01 | 0.33              |
| 2013-10-02 | 0.00              |
| 2013-10-03 | 0.50              |

**Explanation:** 

On 2013-10-01:
- There were 4 requests in total, 2 of which were canceled.
- However, the request with `id=2` was made by a banned client (`User_Id=2`), so it is ignored in the calculation.
- Hence there are 3 unbanned requests in total, 1 of which was canceled.
- The Cancellation Rate is `(1 / 3) = 0.33`

On 2013-10-02:
- There were 3 requests in total, 0 of which were canceled.
- The request with `Id=6` was made by a banned client, so it is ignored.
- Hence there are 2 unbanned requests in total, 0 of which were canceled.
- The Cancellation Rate is `(0 / 2) = 0.00`
  
On 2013-10-03:
- There were 3 requests in total, 1 of which was canceled.
- The request with `Id=8` was made by a banned client, so it is ignored.
- Hence there are 2 unbanned request in total, 1 of which were canceled.
- The Cancellation Rate is `(1 / 2) = 0.50`

```sql
WITH TripStatus AS (
  SELECT Request_at AS Day, T.status != 'completed' AS cancelled 
  FROM Trips T 
    JOIN Users C ON Client_Id = C.Users_Id AND C.Banned = 'No' 
    JOIN Users D ON Driver_Id = D.Users_Id AND D.Banned = 'No' 
  WHERE 
    Request_at BETWEEN '2013-10-01' AND '2013-10-03'
) 
SELECT Day,ROUND(SUM(cancelled) / COUNT(cancelled), 2) AS 'Cancellation Rate' 
FROM TripStatus 
GROUP BY Day;


SELECT request_at AS Day, ROUND(SUM(status != 'completed') / COUNT(*), 2) AS 'Cancellation Rate' 
FROM Trips 
  LEFT JOIN Users AS Clients ON Trips.client_id = Clients.users_id 
  LEFT JOIN Users AS Drivers ON Trips.driver_id = Drivers.users_id 
WHERE 
  Clients.banned = 'No' 
  AND Drivers.banned = 'No' 
  AND request_at BETWEEN '2013-10-01' AND '2013-10-03' 
GROUP BY Day
```

## Problem 511: Game Play Analysis I

Table: `Activity`

| Column Name  | Type    |
|:-------------|:--------|
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |

(`player_id`, `event_date`) is the primary key of this table.
This table shows the activity of players of some games.
Each row is a record of a player who logged in and played a number of games (possibly 0) before logging out on someday using some device.

Write an SQL query to report the first login date for each player.

Return the result table in any order.

The query result format is in the following example.

### Example 1:

**Input:**
`Activity` table:

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

## Problem 512. Game Play Analysis II

Table: `Activity`

| Column Name  | Type    |
|:-------------|:--------|
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |

(`player_id`, `event_date`) is the primary key (combination of columns with unique values) of this table.
This table shows the activity of players of some games.
Each row is a record of a player who logged in and played a number of games (possibly 0) before logging out on someday using some device.

Write a solution to report the device that is first logged in for each player. Return the result table in any order. The result format is in the following example.

### Example 1:

**Input:**
`Activity` table:

| player_id | device_id | event_date | games_played |
|:----------|:----------|:-----------|:-------------|
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-05-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |

**Output:**

| player_id | device_id |
|:----------|:----------|
| 1         | 2         |
| 2         | 3         |
| 3         | 1         |

```sql
SELECT player_id, device_id FROM Activity
    WHERE (player_id, event_date) IN (
        SELECT player_id, MIN(event_date) AS event_date FROM Activity
        GROUP BY player_id
        ORDER BY player_id
    );
    

WITH cte AS (
    SELECT player_id, device_id, 
        ROW_NUMBER() OVER (PARTITION BY player_id ORDER BY event_date) AS 'row_num' 
        FROM Activity
) 
SELECT player_id, device_id FROM cte WHERE row_num = 1 ORDER BY player_id;
```

## Problem 534. Game Play Analysis III

Table: `Activity`

| Column Name  | Type    |
|:-------------|:--------|
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |

(`player_id`, `event_date`) is the primary key (column with unique values) of this table.
This table shows the activity of players of some games.
Each row is a record of a player who logged in and played a number of games (possibly 0) before logging out on someday using some device.

Write a solution to report for each player and date, how many games played so far by the player. That is, the total number of games played by the player until that date. Check the example for clarity. Return the result table in any order. The result format is in the following example.

### Example 1:

**Input:** `Activity` table:

| player_id | device_id | event_date | games_played |
|:----------|:----------|:-----------|:-------------|
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-05-02 | 6            |
| 1         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |

**Output:**

| player_id | event_date | games_played_so_far  |
|:----------|:-----------|:---------------------|
| 1         | 2016-03-01 | 5                    |
| 1         | 2016-05-02 | 11                   |
| 1         | 2017-06-25 | 12                   |
| 3         | 2016-03-02 | 0                    |
| 3         | 2018-07-03 | 5                    |

**Explanation:**
For the player with id 1, 5 + 6 = 11 games played by 2016-05-02, and 5 + 6 + 1 = 12 games played by 2017-06-25.
For the player with id 3, 0 + 5 = 5 games played by 2018-07-03.
Note that for each player we only care about the days when the player logged in.

```sql
SELECT a1.player_id, a1.event_date, SUM(a2.games_played) games_played_so_far FROM Activity a1, Activity a2
    WHERE a1.event_date >= a2.event_date AND a1.player_id = a2.player_id
    GROUP BY a1.player_id, a1.event_date;
    

SELECT player_id, event_date, 
    SUM(games_played) OVER (PARTITION BY player_id ORDER BY event_date) AS games_played_so_far
    FROM Activity;
```

## Problem 550. Game Play Analysis IV

Table: `Activity`

| Column Name  | Type    |
|:-------------|:--------|
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |

(`player_id`, `event_date`) is the primary key (combination of columns with unique values) of this table.
This table shows the activity of players of some games.
Each row is a record of a player who logged in and played a number of games (possibly 0) before logging out on someday using some device.

Write a solution to report the fraction of players that logged in again on the day after the day they first logged in, rounded to 2 decimal places. In other words, you need to count the number of players that logged in for at least two consecutive days starting from their first login date, then divide that number by the total number of players. The result format is in the following example.

### Example 1:

**Input:** `Activity` table:

| player_id | device_id | event_date | games_played |
|:----------|:----------|:-----------|:-------------|
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-03-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |

**Output:**

| fraction  |
|:----------|
| 0.33      |

**Explanation:**
Only the player with id 1 logged back in after the first day he had logged in so the answer is 1/3 = 0.33

```sql
WITH first_logins AS (
    SELECT player_id, MIN(event_date) AS first_login_date 
        FROM Activity GROUP BY player_id
),
logins AS (
SELECT COUNT(a.player_id) AS num_logins FROM first_logins a, Activity b
WHERE a.player_id = b.player_id AND DATEDIFF(b.event_date, a.first_login_date) = 1
)
SELECT ROUND(
    (SELECT num_logins FROM logins) /
    (SELECT COUNT(player_id) FROM first_logins), 2
) AS fraction;
```

## Problem 569. Median Employee Salary

Table: `Employee`

| Column Name  | Type     |
|:-------------|:---------|
| id           | int      |
| company      | varchar  |
| salary       | int      |

`id` is the primary key (column with unique values) for this table.
Each row of this table indicates the `company` and the `salary` of one employee.

Write a solution to find the rows that contain the median `salary` of each company. While calculating the median, when you sort the salaries of the company, break the ties by `id`. Return the result table in any order. The result format is in the following example.

### Example 1:

**Input:** `Employee` table:

| id | company  | salary |
|:---|:---------|:-------|
| 1  | A        | 2341   |
| 2  | A        | 341    |
| 3  | A        | 15     |
| 4  | A        | 15314  |
| 5  | A        | 451    |
| 6  | A        | 513    |
| 7  | B        | 15     |
| 8  | B        | 13     |
| 9  | B        | 1154   |
| 10 | B        | 1345   |
| 11 | B        | 1221   |
| 12 | B        | 234    |
| 13 | C        | 2345   |
| 14 | C        | 2645   |
| 15 | C        | 2645   |
| 16 | C        | 2652   |
| 17 | C        | 65     |

**Output:**

| id | company  | salary |
|:---|:---------|:-------|
| 5  | A        | 451    |
| 6  | A        | 513    |
| 12 | B        | 234    |
| 9  | B        | 1154   |
| 14 | C        | 2645   |

**Explanation:**
For company A, the rows sorted are as follows:

| id | company | salary  |
|:---|:--------|:--------|
| 3  | A       | 15      |
| 2  | A       | 341     |
| 5  | A       | 451     | <-- median
| 6  | A       | 513     | <-- median
| 1  | A       | 2341    |
| 4  | A       | 15314   |

For company B, the rows sorted are as follows:

| id | company  | salary |
|:---|:---------|:-------|
| 8  | B        | 13     |
| 7  | B        | 15     |
| 12 | B        | 234    | <-- median
| 11 | B        | 1221   | <-- median
| 9  | B        | 1154   |
| 10 | B        | 1345   |

For company C, the rows sorted are as follows:

| id | company | salary  |
|:---|:--------|:--------|
| 17 | C       | 65      |
| 13 | C       | 2345    |
| 14 | C       | 2645    | <-- median
| 15 | C       | 2645    |
| 16 | C       | 2652    |

Follow up: Could you solve it without using any built-in or window functions?

```sql
WITH temp AS (
SELECT 
    id, company, salary, 
    ROW_NUMBER() OVER (PARTITION BY company ORDER BY salary) AS 'row_num', 
    count(1) OVER (PARTITION BY company) AS 'cnt'
FROM Employee
)
SELECT id, company, salary
    FROM temp WHERE row_num >= cnt / 2 AND row_num <= (cnt / 2) + 1;

-- slower but without window functions   
SELECT
  id,
  Company,
  Salary
FROM Employee e
WHERE  ABS((SELECT COUNT(*) FROM Employee e1 WHERE e.company = e1.company AND e.Salary >= e1.Salary) -
           (SELECT COUNT(*) FROM Employee e2 WHERE e.company = e2.company AND e.Salary <= e2.Salary)) <= 1
GROUP BY Company, Salary
```

## Problem 570. Managers with at Least 5 Direct Reports

Table: `Employee`

| Column Name | Type     |
|:------------|:---------|
| id          | int      |
| name        | varchar  |
| department  | varchar  |
| managerId   | int      |

`id` is the primary key (column with unique values) for this table.
Each row of this table indicates the `name` of an employee, their `department`, and the id of their manager. If `managerId` is null, then the employee does not have a manager.No employee will be the manager of themself.

Write a solution to find managers with at least five direct reports. Return the result table in any order. The result format is in the following example.

### Example 1:

**Input:** `Employee` table:

| id  | name  | department | managerId  |
|:----|:------|:-----------|:-----------|
| 101 | John  | A          | None       |
| 102 | Dan   | A          | 101        |
| 103 | James | A          | 101        |
| 104 | Amy   | A          | 101        |
| 105 | Anne  | A          | 101        |
| 106 | Ron   | B          | 101        |

**Output:**

| name |
|:-----|
| John |

```sql
WITH cte AS (
SELECT managerId, COUNT(1) AS count
    FROM Employee 
    GROUP BY managerId
)
SELECT name FROM Employee
JOIN cte
ON Employee.id = cte.managerId
WHERE cte.count >=5;
```

## Problem 571. Find Median Given Frequency of Numbers

Table: `Numbers`

| Column Name | Type |
|:------------|:-----|
| num         | int  |
| frequency   | int  |

`num` is the primary key (column with unique values) for this table.
Each row of this table shows the frequency of a number in the database. The median is the value separating the higher half from the lower half of a data sample.

Write a solution to report the median of all the numbers in the database after decompressing the `Numbers` table. Round the median to one decimal point.
The result format is in the following example.

### Example 1:

**Input:** `Numbers` table:

| num | frequency  |
|:----|:-----------|
| 0   | 7          |
| 1   | 1          |
| 2   | 3          |
| 3   | 1          |

**Output:**

| median |
|:-------|
| 0.0    |

**Explanation:**
If we decompress the Numbers table, we will get `[0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 3]`, so the median is `(0 + 0) / 2 = 0`.

```sql
WITH cte AS (
    SELECT num, frequency, 
        SUM(frequency) OVER (ORDER BY num) running_sum,
        (SUM(frequency) OVER ()) / 2 AS 'median_num'
        FROM Numbers
    ) 
    SELECT AVG(num) AS median FROM cte
    WHERE median_num BETWEEN (running_sum - frequency) AND running_sum;
``` 

## Problem 574: Winning Candidate

Table: `Candidate`

| Column Name | Type     |
|:------------|:---------|
| id          | int      |
| name        | varchar  |

`id` is the column with unique values for this table.
Each row of this table contains information about the `id` and the `name` of a candidate.

Table: `Vote`

| Column Name | Type  |
|:------------|:------|
| id          | int   |
| candidateId | int   |

`id` is an auto-increment primary key (column with unique values).
`candidateId` is a foreign key (reference column) to `id` from the `Candidate` table.
Each row of this table determines the candidate who got the ith vote in the elections.

Write a solution to report the `name` of the winning candidate (i.e., the candidate who got the largest number of votes).

The test cases are generated so that exactly one candidate wins the elections.

The result format is in the following example.

### Example 1:

**Input:**
`Candidate` table:

| id | name  |
|:---|:------|
| 1  | A     |
| 2  | B     |
| 3  | C     |
| 4  | D     |
| 5  | E     |

`Vote` table:

| id | candidateId |
|----|:------------|
| 1  | 2           |
| 2  | 4           |
| 3  | 3           |
| 4  | 2           |
| 5  | 5           |

**Output:**

| name |
|:-----|
| B    |

**Explanation:**
Candidate B has 2 votes. Candidates C, D, and E have 1 vote each.
The winner is candidate B.

```sql
SELECT name FROM (
    SELECT name, COUNT(1) votes FROM Candidate
    JOIN Vote
    ON Candidate.id = Vote.candidateId
    GROUP BY name ORDER BY votes DESC LIMIT 1
) tmp;
```

## Problem 577. Employee Bonus

Table: `Employee`

| Column Name | Type     |
|:------------|:---------|
| empId       | int      |
| name        | varchar  |
| supervisor  | int      |
| salary      | int      |

`empId` is the column with unique values for this table.
Each row of this table indicates the `name` and the ID of an employee in addition to their `salary` and the id of their manager.

Table: `Bonus`

| Column Name | Type  |
|:------------|:------|
| empId       | int   |
| bonus       | int   |

`empId` is the column of unique values for this table.
`empId` is a foreign key (reference column) to `empId` from the `Employee` table.
Each row of this table contains the id of an employee and their respective `bonus`.

Write a solution to report the `name` and `bonus` amount of each employee with a `bonus` less than 1000.
Return the result table in any order. The result format is in the following example.

### Example 1:

**Input:** `Employee` table:

| empId | name    | supervisor | salary |
|:------|:--------|:-----------|:-------|
| 3     | Brad    | null       | 4000   |
| 1     | John    | 3          | 1000   |
| 2     | Dan     | 3          | 2000   |
| 4     | Thomas  | 3          | 4000   |

`Bonus` table:

| empId | bonus  |
|:------|:-------|
| 2     | 500    |
| 4     | 2000   |

**Output:**

| name | bonus  |
|:-----|:-------|
| Brad | null   |
| John | null   |
| Dan  | 500    |

```sql
SELECT e.name, b.bonus FROM  Employee e 
LEFT OUTER JOIN Bonus b
ON e.empId = b.empId
WHERE b.bonus < 1000 OR b.bonus IS NULL;
```

## Problem 578. Get Highest Answer Rate Question

Table: `SurveyLog`

| Column Name | Type  |
|:------------|:------|
| id          | int   |
| action      | ENUM  |
| question_id | int   |
| answer_id   | int   |
| q_num       | int   |
| timestamp   | int   |

This table may contain duplicate rows.
`action` is an ENUM (category) of the type: "show", "answer", or "skip".
Each row of this table indicates the user with ID = id has taken an action with the question `question_id` at time timestamp.
If the action taken by the user is "answer", `answer_id` will contain the id of that answer, otherwise, it will be `null`.
`q_num` is the numeral order of the question in the current session.

The answer rate for a question is the number of times a user answered the question by the number of times a user showed the question.

Write a solution to report the question that has the highest answer rate. If multiple questions have the same maximum answer rate, report the question with the smallest `question_id`.

The result format is in the following example.

### Example 1:

**Input:**
`SurveyLog` table:

| id | action | question_id | answer_id | q_num | timestamp |
|:---|:-------|:------------|:----------|:------|:----------|
| 5  | show   | 285         | null      | 1     | 123       |
| 5  | answer | 285         | 124124    | 1     | 124       |
| 5  | show   | 369         | null      | 2     | 125       |
| 5  | skip   | 369         | null      | 2     | 126       |

**Output:**

| survey_log |
|:-----------|
| 285        |

**Explanation:**
Question 285 was showed 1 time and answered 1 time. The answer rate of question 285 is 1.0
Question 369 was showed 1 time and was not answered. The answer rate of question 369 is 0.0
Question 285 has the highest answer rate.

```sql
SELECT question_id AS survey_log
FROM SurveyLog
GROUP BY question_id
ORDER BY (
    COUNT(answer_id) / COUNT(CASE WHEN action = 'show' THEN question_id ELSE NULL END)) DESC, question_id ASC
LIMIT 1;
```

## Problem 579. Find Cumulative Salary of an Employee

Table: `Employee`

| Column Name | Type  |
|:------------|:------|
| id          | int   |
| month       | int   |
| salary      | int   |

(`id`, `month`) is the primary key (combination of columns with unique values) for this table.
Each row in the table indicates the salary of an employee in one month during the year 2020.

Write a solution to calculate the cumulative salary summary for every employee in a single unified table.

The cumulative salary summary for an employee can be calculated as follows:

    For each month that the employee worked, sum up the salaries in that month and the previous two months. This is their 3-month sum for that month. If an employee did not work for the company in previous months, their effective salary for those months is 0.
    Do not include the 3-month sum for the most recent month that the employee worked for in the summary.
    Do not include the 3-month sum for any month the employee did not work.

Return the result table ordered by `id` in ascending order. In case of a tie, order it by `month` in descending order.

The result format is in the following example.

### Example 1:

**Input:** `Employee` table:

| id | month | salary |
|:---|:------|:-------|
| 1  | 1     | 20     |
| 2  | 1     | 20     |
| 1  | 2     | 30     |
| 2  | 2     | 30     |
| 3  | 2     | 40     |
| 1  | 3     | 40     |
| 3  | 3     | 60     |
| 1  | 4     | 60     |
| 3  | 4     | 70     |
| 1  | 7     | 90     |
| 1  | 8     | 90     |

**Output:**

| id | month | Salary  |
|:---|:------|:--------|
| 1  | 7     | 90      |
| 1  | 4     | 130     |
| 1  | 3     | 90      |
| 1  | 2     | 50      |
| 1  | 1     | 20      |
| 2  | 1     | 20      |
| 3  | 3     | 100     |
| 3  | 2     | 40      |

**Explanation:**
Employee '1' has five salary records excluding their most recent month '8':
- 90 for month '7'.
- 60 for month '4'.
- 40 for month '3'.
- 30 for month '2'.
- 20 for month '1'.
  So the cumulative `salary` summary for this employee is:

  | id | month | salary  |
  |:---|:------|:--------|
  | 1  | 7     | 90      |  (90 + 0 + 0)
  | 1  | 4     | 130     |  (60 + 40 + 30)
  | 1  | 3     | 90      |  (40 + 30 + 20)
  | 1  | 2     | 50      |  (30 + 20 + 0)
  | 1  | 1     | 20      |  (20 + 0 + 0)
  
  Note that the 3-month sum for month '7' is 90 because they did not work during month '6' or month '5'.

Employee '2' only has one salary record (month '1') excluding their most recent month '2'.

| id | month  | salary |
|:---|:-------|:-------|
| 2  | 1      | 20     |  (20 + 0 + 0)

Employee '3' has two salary records excluding their most recent month '4':
- 60 for month '3'.
- 40 for month '2'.
  So the cumulative salary summary for this employee is:

  | id | month | salary |
  |:---|:------|:-------|
  | 3  | 3     | 100    |  (60 + 40 + 0)
  | 3  | 2     | 40     |  (40 + 0 + 0)
  
```sql
SELECT 
    id,
    month,
    SUM(salary) OVER (PARTITION BY id ORDER BY month RANGE BETWEEN 2 PRECEDING AND CURRENT ROW) AS Salary
FROM Employee
WHERE (id, month) NOT IN (SELECT id, MAX(month) AS month FROM Employee GROUP BY id)
ORDER BY id, month DESC;
```

## Problem 580: Count Student Number in Departments

Table: `Student`

| Column Name  | Type     |
|:-------------|:---------|
| student_id   | int      |
| student_name | varchar  |
| gender       | varchar  |
| dept_id      | int      |

`student_id` is the primary key (column with unique values) for this table.
`dept_id` is a foreign key (reference column) to `dept_id` in the `Department` tables.
Each row of this table indicates the name of a student, their gender, and the id of their department.

Table: `Department`

| Column Name | Type     |
|:------------|:---------|
| dept_id     | int      |
| dept_name   | varchar  |

`dept_id` is the primary key (column with unique values) for this table.
Each row of this table contains the id and the name of a department.

Write a solution to report the respective department name and number of students majoring in each department for all departments in the `Department` table (even ones with no current students).

Return the result table ordered by `student_number` in descending order. In case of a tie, order them by `dept_name` alphabetically.
The result format is in the following example.

### Example 1
**Input:**

`Student` table:

| student_id | student_name  | gender | dept_id |
|:-----------|:--------------|:-------|:--------|
| 1          | Jack          | M      | 1       |
| 2          | Jane          | F      | 1       |
| 3          | Mark          | M      | 2       |

`Department` table:

| dept_id | dept_name   |
|:--------|:------------|
| 1       | Engineering |
| 2       | Science     |
| 3       | Law         |

**Output:**

| dept_name   | student_number  |
|:------------|:----------------|
| Engineering | 2               |
| Science     | 1               |
| Law         | 0               |

```sql
WITH tmp AS (
SELECT dept_name, student_id FROM Student s
RIGHT OUTER JOIN
Department d ON s.dept_id = d.dept_id
) SELECT dept_name, COUNT(student_id) student_number 
FROM tmp GROUP BY dept_name 
ORDER BY student_number DESC, dept_name ASC;
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

## Problem 585: Investments in 2016

Table: `Insurance`

| Column Name | Type   |
|:------------|:-------|
| pid         | int    |
| tiv_2015    | float  |
| tiv_2016    | float  |
| lat         | float  |
| lon         | float  |

`pid` is the primary key (column with unique values) for this table.
Each row of this table contains information about one policy where:
`pid` is the policyholder's policy ID.
`tiv_2015` is the total investment value in 2015 and `tiv_2016` is the total investment value in 2016.
`lat` is the latitude of the policy holder's city. It's guaranteed that lat is not `NULL`.
`lon` is the longitude of the policy holder's city. It's guaranteed that lon is not `NULL`.

Write a solution to report the sum of all total investment values in 2016 `tiv_2016`, for all policyholders who:

- have the same `tiv_2015` value as one or more other policyholders, and
- are not located in the same city as any other policyholder (i.e., the (`lat`, `lon`) attribute pairs must be unique).

Round `tiv_2016` to two decimal places.

The result format is in the following example.

### Example 1:

**Input:**
`Insurance` table:

| pid | tiv_2015  | tiv_2016 | lat | lon |
|:----|:----------|:---------|:----|:----|
| 1   | 10        | 5        | 10  | 10  |
| 2   | 20        | 20       | 20  | 20  |
| 3   | 10        | 30       | 20  | 20  |
| 4   | 10        | 40       | 40  | 40  |

**Output:**

| tiv_2016 |
|:---------|
| 45.00    |

**Explanation:**

The first record in the table, like the last record, meets both of the two criteria.
The `tiv_2015` value 10 is the same as the third and fourth records, and its location is unique.

The second record does not meet any of the two criteria. Its `tiv_2015` is not like any other policyholders and its location is the same as the third record, which makes the third record fail, too.
So, the result is the sum of `tiv_2016` of the first and last record, which is 45.

```sql
WITH tmp AS (
    SELECT *,
    COUNT(*) OVER (PARTITION BY tiv_2015) as tiv_2015_count,
    COUNT(*) OVER (PARTITION BY lat, lon) AS location_count
    FROM Insurance
) SELECT ROUND(SUM(tiv_2016), 2) AS tiv_2016 FROM tmp 
WHERE tiv_2015_count > 1 AND location_count = 1;
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

**Output:**

| name        | population | area    |
|:------------|:-----------|:--------|
| Afghanistan | 25500100   | 652230  |
| Algeria     | 37100000   | 2381741 |

```sql
SELECT name, population, area FROM World WHERE area >= 3000000 OR population >= 25000000;
```

## Problem 596: Classes More Than 5 Students

Table: `Courses`

| Column Name | Type     |
|:------------|:---------|
| student     | varchar  |
| class       | varchar  |

(`student`, `class`) is the primary key (combination of columns with unique values) for this table.
Each row of this table indicates the name of a student and the class in which they are enrolled.

Write a solution to find all the classes that have at least five students.

Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Courses` table:

| student | class     |
|:--------|:----------|
| A       | Math      |
| B       | English   |
| C       | Math      |
| D       | Biology   |
| E       | Math      |
| F       | Computer  |
| G       | Math      |
| H       | Math      |
| I       | Math      |

**Output:**

| class   |
|:--------|
| Math    |

**Explanation:**

- Math has 6 students, so we include it.
- English has 1 student, so we do not include it.
- Biology has 1 student, so we do not include it.
- Computer has 1 student, so we do not include it.

```sql
WITH tmp AS (
    SELECT class, count(1) AS count FROM Courses GROUP BY class 
) SELECT class FROM tmp WHERE count >= 5;

SELECT class FROM Courses GROUP BY class HAVING Count(1) >= 5;
```

## Problem 597: Friend Requests I: Overall Acceptance Rate

Table: `FriendRequest`

| Column Name    | Type    |
|:---------------|:--------|
| sender_id      | int     |
| send_to_id     | int     |
| request_date   | date    |

This table may contain duplicates (In other words, there is no primary key for this table in SQL).
This table contains the ID of the user who sent the request, the ID of the user who received the request, and the date of the request.

Table: `RequestAccepted`

| Column Name    | Type    |
|:---------------|:--------|
| requester_id   | int     |
| accepter_id    | int     |
| accept_date    | date    |

This table may contain duplicates (In other words, there is no primary key for this table in SQL).
This table contains the ID of the user who sent the request, the ID of the user who received the request, and the date when the request was accepted.

Find the overall acceptance rate of requests, which is the number of acceptance divided by the number of requests. Return the answer rounded to 2 decimals places.

**Note:**

- The accepted requests are not necessarily from the table `friend_request`. In this case, Count the total accepted requests (no matter whether they are in the original requests), and divide it by the number of requests to get the acceptance rate.
- It is possible that a sender sends multiple requests to the same receiver, and a request could be accepted more than once. In this case, the ‘duplicated’ requests or acceptances are only counted once.
- If there are no requests at all, you should return 0.00 as the accept_rate.

The result format is in the following example.

### Example 1:

**Input:**
`FriendRequest` table:

| sender_id | send_to_id  | request_date |
|:----------|:------------|:-------------|
| 1         | 2           | 2016/06/01   |
| 1         | 3           | 2016/06/01   |
| 1         | 4           | 2016/06/01   |
| 2         | 3           | 2016/06/02   |
| 3         | 4           | 2016/06/09   |

`RequestAccepted` table:

| requester_id | accepter_id  | accept_date |
|:-------------|:-------------|:------------|
| 1            | 2            | 2016/06/03  |
| 1            | 3            | 2016/06/08  |
| 2            | 3            | 2016/06/08  |
| 3            | 4            | 2016/06/09  |
| 3            | 4            | 2016/06/10  |

**Output:**

| accept_rate |
|:------------|
| 0.8         |

**Explanation:**
There are 4 unique accepted requests, and there are 5 requests in total. So the rate is 0.80.

**Follow up:**

- Could you find the acceptance rate for every month? 
- Could you find the cumulative acceptance rate for every day?

```sql
SELECT ROUND(IFNULL(
    (SELECT COUNT(1) FROM (
        SELECT DISTINCT requester_id, accepter_id FROM RequestAccepted
    ) AS A)
    /
    (SELECT COUNT(1) FROM (
        SELECT DISTINCT sender_id, send_to_id FROM FriendRequest
    ) AS B), 0), 2) AS accept_rate;
```

## Problem 601: Human Traffic of Stadium

Table: `Stadium`

| Column Name   | Type    |
|:--------------|:--------|
| id            | int     |
| visit_date    | date    |
| people        | int     |

`visit_date` is the column with unique values for this table.
Each row of this table contains the visit date and visit id to the stadium with the number of people during the visit.
As the `id` increases, the date increases as well.

Write a solution to display the records with three or more rows with consecutive id's, and the number of people is greater than or equal to `100` for each.
Return the result table ordered by `visit_date` in ascending order.

The result format is in the following example.

### Example 1:

**Input:**
`Stadium` table:

| id   | visit_date | people    |
|:-----|:-----------|:----------|
| 1    | 2017-01-01 | 10        |
| 2    | 2017-01-02 | 109       |
| 3    | 2017-01-03 | 150       |
| 4    | 2017-01-04 | 99        |
| 5    | 2017-01-05 | 145       |
| 6    | 2017-01-06 | 1455      |
| 7    | 2017-01-07 | 199       |
| 8    | 2017-01-09 | 188       |

**Output:**

| id   | visit_date | people    |
|:-----|:-----------|:----------|
| 5    | 2017-01-05 | 145       |
| 6    | 2017-01-06 | 1455      |
| 7    | 2017-01-07 | 199       |
| 8    | 2017-01-09 | 188       |

**Explanation:**
The four rows with ids 5, 6, 7, and 8 have consecutive ids and each of them has >= 100 people attended. Note that row 8 was included even though the `visit_date` was not the next day after row 7.
The rows with ids 2 and 3 are not included because we need at least three consecutive ids.

```sql
WITH cte AS (
    SELECT *,
        LEAD(id, 1) OVER (ORDER BY id) AS next_id,
        LEAD(id, 2) OVER (ORDER BY id) AS second_id,
        LAG(id, 1) OVER (ORDER BY id) AS previous_id,
        LAG(id, 2) OVER (ORDER BY id) AS second_last_id
    FROM Stadium
    WHERE people >= 100
) SELECT id, visit_date, people FROM cte
    WHERE ((next_id - id = 1 AND id - previous_id = 1)
    OR (second_id - next_id = 1 AND next_id - id = 1)
    OR (id - previous_id = 1 AND previous_id - second_last_id = 1))
    ORDER BY visit_date;
```

## Problem 602: Friend Requests II: Who has Most Friends

Table: `RequestAccepted`

| Column Name    | Type    |
|:---------------|:--------|
| requester_id   | int     |
| accepter_id    | int     |
| accept_date    | date    |

(`requester_id`, `accepter_id`) is the primary key (combination of columns with unique values) for this table.
This table contains the ID of the user who sent the request, the ID of the user who received the request, and the date when the request was accepted.

Write a solution to find the people who have the most friends and the most friends number.
The test cases are generated so that only one person has the most friends.

The result format is in the following example.

### Example 1:

**Input:**
`RequestAccepted` table:

| requester_id | accepter_id | accept_date  |
|:-------------|:------------|:-------------|
| 1            | 2           | 2016/06/03   |
| 1            | 3           | 2016/06/08   |
| 2            | 3           | 2016/06/08   |
| 3            | 4           | 2016/06/09   |

**Output:**

| id | num |
|:---|:----|
| 3  | 3   |

**Explanation:**
The person with id 3 is a friend of people 1, 2, and 4, so he has three friends in total, which is the most number than any others.

**Follow up:** In the real world, multiple people could have the same most number of friends. Could you find all these 
people in this case?

```sql
WITH all_users AS (
    SELECT requester_id AS id FROM RequestAccepted
    UNION ALL
    SELECT accepter_id AS id FROM RequestAccepted
) SELECT id, num FROM
    (SELECT id, COUNT(id) AS num,
    RANK() OVER (ORDER BY COUNT(id) DESC) AS rnk
    FROM all_users
    GROUP BY id
    ) tmp
    WHERE rnk = 1;
```

## Problem 603: Consecutive Available Seats

Table: `Cinema`

| Column Name | Type  |
|:------------|:------|
| seat_id     | int   |
| free        | bool  |

`seat_id` is an auto-increment column for this table.
Each row of this table indicates whether the `i`th seat is free or not. `1` means free while `0` means occupied.

Find all the consecutive available seats in the cinema.

Return the result table ordered by seat_id in ascending order.
The test cases are generated so that more than two seats are consecutively available.

The result format is in the following example.

### Example 1:

**Input:**
`Cinema` table:

| seat_id | free  |
|:--------|:------|
| 1       | 1     |
| 2       | 0     |
| 3       | 1     |
| 4       | 1     |
| 5       | 1     |

**Output:**

| seat_id |
|:--------|
| 3       |
| 4       |
| 5       |

```sql
WITH tmp AS (
    SELECT seat_id, 
    LAG(free) OVER (ORDER BY seat_id) AS previous, 
    LEAD(free) OVER (ORDER BY seat_id) AS next, 
    free FROM
    Cinema
) SELECT seat_id FROM tmp
 WHERE (previous = 1 AND free) OR (next = 1 AND free);
 
-- Using Self join
SELECT DISTINCT a.seat_id FROM
Cinema a JOIN Cinema b
ON (a.seat_id - b.seat_id) IN (-1, 1) AND a.free = TRUE AND b.free = TRUE
ORDER BY a.seat_id;
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

## Problem 610: Triangle Judgement

Table: `Triangle`

| Column Name | Type  |
|:------------|:------|
| x           | int   |
| y           | int   |
| z           | int   |

In SQL, `(x, y, z)` is the primary key column for this table.
Each row of this table contains the lengths of three line segments.

Report for every three line segments whether they can form a triangle.
Return the result table in any order.

The result format is in the following example.

### Example 1:

**Input:**
`Triangle` table:

| x  | y  | z  |
|:---|:---|:---|
| 13 | 15 | 30 |
| 10 | 20 | 15 |

**Output:**

| x  | y  | z   | triangle |
|:---|:---|:----|:---------|
| 13 | 15 | 30  | No       |
| 10 | 20 | 15  | Yes      |

For three sides to be able to create a triangle, the sum of any two sides must be greater than the third side of the 
triangle. That is `x + y > z` condition must be satisfied for each sides.
That is, it should hold true all three conditions below.

```
x + y > z
y + z > x
z + x > y
```

```sql
SELECT x, y, z,
CASE WHEN (x + y > z) AND (y + z > x) AND (z + x > y) THEN 'Yes'
ELSE 'No' END AS triangle
FROM Triangle;
```

## Problem 612: Shortest Distance in a Plane

Table: `Point2D`

| Column Name | Type  |
|:------------|:------|
| x           | int   |
| y           | int   |

`(x, y)` is the primary key column (combination of columns with unique values) for this table.
Each row of this table indicates the position of a point on the X-Y plane.

The distance between two points `p1(x1, y1)` and `p2(x2, y2)` is `sqrt((x2 - x1)2 + (y2 - y1)2)`.

Write a solution to report the shortest distance between any two points from the `Point2D` table. Round the distance to two decimal points.
The result format is in the following example.

### Example 1:

**Input:**
`Point2D` table:

| x  | y   |
|:---|:----|
| -1 | -1  |
| 0  | 0   |
| -1 | -2  |

**Output:**

| shortest |
|:---------|
| 1.00     |

**Explanation:** The shortest distance is 1.00 from point (-1, -1) to (-1, 2).

```sql
SELECT 
ROUND(MIN(SQRT((POW(p1.x - p2.x, 2) + POW(p1.y - p2.y, 2)))), 2) AS shortest 
FROM Point2D p1
JOIN Point2D p2
ON p1.x != p2.x OR p1.y != p2.y;
```

## Problem 613: Shortest Distance in a Line

Table: `Point`

| Column Name | Type |
|:------------|:-----|
| x           | int  |

In SQL, `x` is the primary key column for this table.
Each row of this table indicates the position of a point on the X-axis.

Find the shortest distance between any two points from the `Point` table.
The result format is in the following example.

### Example 1:

**Input:**
`Point` table:

| x  |
|:---|
| -1 |
| 0  |
| 2  |

**Output:**

| shortest |
|:---------|
| 1        |

**Explanation:** The shortest distance is between points -1 and 0 which is |(-1) - 0| = 1.

**Follow up:** How could you optimize your solution if the Point table is ordered in ascending order?

```sql
SELECT MIN(ABS(p1.x - p2.x)) AS shortest
FROM Point p1
JOIN Point p2
ON p1.x != p2.x;
```

## Problem 614: Second Degree Follower

Table: `Follow`

| Column Name |  Type   |
|:------------|:--------|
| followee    | varchar |
| follower    | varchar |

(`followee`, `follower`) is the primary key (combination of columns with unique values) for this table.
Each row of this table indicates that the user follower follows the user followee on a social network.
There will not be a user following themselves.

A second-degree follower is a user who:

    - follows at least one user, and
    - is followed by at least one user.

Write a solution to report the second-degree users and the number of their followers.

Return the result table ordered by follower in alphabetical order.

The result format is in the following example.

### Example 1:

**Input:**
`Follow` table:

| followee | follower |
|:---------|:---------|
| Alice    | Bob      |
| Bob      | Cena     |
| Bob      | Donald   |
| Donald   | Edward   |

**Output:**

| follower | num |
|:---------|:----|
| Bob      | 2   |
| Donald   | 1   |

**Explanation:**
User Bob has 2 followers. Bob is a second-degree follower because he follows Alice, so we include him in the result table.
User Donald has 1 follower. Donald is a second-degree follower because he follows Bob, so we include him in the result table.
User Alice has 1 follower. Alice is not a second-degree follower because she does not follow anyone, so we do not include her in the result table.

```sql
SELECT a.follower, COUNT(DISTINCT b.follower) AS num FROM Follow a
JOIN Follow b
ON a.follower = b.followee
GROUP BY a.follower;
```

## Problem 615: Average Salary: Departments vs Company

Table: `Salary`

| Column Name | Type |
|:------------|:-----|
| id          | int  |
| employee_id | int  |
| amount      | int  |
| pay_date    | date |

In SQL, `id` is the primary key column for this table.
Each row of this table indicates the salary of an employee in one month.
`employee_id` is a foreign key (reference column) from the Employee table.

Table: `Employee`

| Column Name   | Type |
|:--------------|:-----|
| employee_id   | int  |
| department_id | int  |

In SQL, `employee_id` is the primary key column for this table.
Each row of this table indicates the department of an employee.

Find the comparison result (higher/lower/same) of the average salary of employees in a department to the company's average salary.

Return the result table in any order.

The result format is in the following example.
### Example 1:

**Input:**
`Salary` table:

| id | employee_id | amount | pay_date   |
|:---|:------------|:-------|:-----------|
| 1  | 1           | 9000   | 2017/03/31 |
| 2  | 2           | 6000   | 2017/03/31 |
| 3  | 3           | 10000  | 2017/03/31 |
| 4  | 1           | 7000   | 2017/02/28 |
| 5  | 2           | 6000   | 2017/02/28 |
| 6  | 3           | 8000   | 2017/02/28 |

`Employee` table:

| employee_id | department_id |
|:------------|:--------------|
| 1           | 1             |
| 2           | 2             |
| 3           | 2             |

**Output:**

| pay_month | department_id | comparison |
|:----------|:--------------|:-----------|
| 2017-02   | 1             | same       |
| 2017-03   | 1             | higher     |
| 2017-02   | 2             | same       |
| 2017-03   | 2             | lower      |

**Explanation:**

In March, the company's average salary is (9000+6000+10000)/3 = 8333.33...
The average salary for department '1' is 9000, which is the salary of employee_id '1' since there is only one employee in this department. So the comparison result is 'higher' since 9000 > 8333.33 obviously.
The average salary of department '2' is (6000 + 10000)/2 = 8000, which is the average of employee_id '2' and '3'. So the comparison result is 'lower' since 8000 < 8333.33.

With the same formula for the average salary comparison in February, the result is 'same' since both the department 
'1' and '2' have the same average salary with the company, which is 7000.

```sql
SELECT 
  department_salary.pay_month, department_id, 
  CASE 
    WHEN department_avg > company_avg THEN 'higher' 
    WHEN department_avg < company_avg THEN 'lower' 
    ELSE 'same' 
  END AS comparison 
FROM 
  (
    SELECT 
      department_id, 
      AVG(amount) AS department_avg, 
      DATE_FORMAT(pay_date, '%Y-%m') AS pay_month 
    FROM 
      Salary 
      JOIN Employee ON Salary.employee_id = Employee.employee_id 
    GROUP BY 
      department_id, 
      pay_month
  ) AS department_salary 
  JOIN (
    SELECT 
      AVG(amount) AS company_avg, 
      DATE_FORMAT(pay_date, '%Y-%m') AS pay_month 
    FROM 
      Salary 
    GROUP BY 
      DATE_FORMAT(pay_date, '%Y-%m')
  ) AS company_salary ON department_salary.pay_month = company_salary.pay_month;
```

## Problem 618: Students Report By Geography

Table: `Student`

| Column Name | Type    |
|:------------|:--------|
| name        | varchar |
| continent   | varchar |

This table may contain duplicate rows.
Each row of this table indicates the `name` of a student and the `continent` they came from.

A school has students from Asia, Europe, and America.

Write a solution to pivot the continent column in the `Student` table so that each name is sorted alphabetically and displayed underneath its corresponding continent. The output headers should be America, Asia, and Europe, respectively.

The test cases are generated so that the student number from America is not less than either Asia or Europe.

The result format is in the following example.

### Example 1:

**Input:**
`Student` table:

| name   | continent |
|:-------|:----------|
| Jane   | America   |
| Pascal | Europe    |
| Xi     | Asia      |
| Jack   | America   |

**Output:**

| America | Asia | Europe |
|:--------|:-----|:-------|
| Jack    | Xi   | Pascal |
| Jane    | null | null   |

**Follow up:** If it is unknown which continent has the most students, could you write a solution to generate the 
student report?

```sql
SELECT 
    MAX(CASE WHEN continent = 'America' THEN name END) AS America,
    MAX(CASE WHEN continent = 'Asia' THEN name END) AS Asia,
    MAX(CASE WHEN continent = 'Europe' THEN name END) AS Europe
    FROM
    (SELECT *, ROW_NUMBER() OVER (PARTITION BY continent ORDER BY name) AS row_num
        FROM Student) t
        GROUP BY row_num;
```

## Problem 619: Biggest Single Number

Table: `MyNumbers`

| Column Name | Type |
|:------------|:-----|
| num         | int  |

This table may contain duplicates (In other words, there is no primary key for this table in SQL).
Each row of this table contains an integer.

A single number is a number that appeared only once in the MyNumbers table.

Find the largest single number. If there is no single number, report null.

The result format is in the following example.

### Example 1:

**Input:**
`MyNumbers` table:

| num |
|-----|
| 8   |
| 8   |
| 3   |
| 3   |
| 1   |
| 4   |
| 5   |
| 6   |

**Output:**

| num |
|-----|
| 6   |

**Explanation:**
The single numbers are 1, 4, 5, and 6.
Since 6 is the largest single number, we return it.

### Example 2:

**Input:**
`MyNumbers` table:

| num |
|-----|
| 8   |
| 8   |
| 7   |
| 7   |
| 3   |
| 3   |
| 3   |

**Output:**

| num  |
|------|
| null |

**Explanation:** There are no single numbers in the input table so we return null.

```sql
WITH num_count AS (
    SELECT num, COUNT(1) AS count
    FROM MyNumbers
    GROUP BY num
    ) SELECT MAX(num) AS num FROM num_count
    WHERE count = 1;
```

## Problem 620: Not Boring Movies

Table: `Cinema`

| Column Name    | Type     |
|:---------------|:---------|
| id             | int      |
| movie          | varchar  |
| description    | varchar  |
| rating         | float    |

`id` is the primary key (column with unique values) for this table.
Each row contains information about the `name` of a movie, its `genre`, and its `rating`.
rating is a 2 decimal places float in the range `[0, 10]`

Write a solution to report the movies with an odd-numbered `ID` and a `description` that is not `"boring"`.

Return the result table ordered by `rating` in **descending order**.

The result format is in the following example.

### Example 1:

**Input:**
`Cinema` table:

| id | movie      | description | rating |
|:---|:-----------|:------------|--------|
| 1  | War        | great 3D    | 8.9    |
| 2  | Science    | fiction     | 8.5    |
| 3  | irish      | boring      | 6.2    |
| 4  | Ice song   | Fantacy     | 8.6    |
| 5  | House card | Interesting | 9.1    |

**Output:**

| id | movie      | description | rating |
|:---|:-----------|:------------|--------|
| 5  | House card | Interesting | 9.1    |
| 1  | War        | great 3D    | 8.9    |

**Explanation:**
We have three movies with odd-numbered IDs: 1, 3, and 5. The movie with ID = 3 is boring so we do not include it in the answer.

```sql
SELECT * FROM Cinema
    WHERE id % 2 = 1 AND description != 'boring'
    ORDER BY rating DESC;
```

## Problem 626: Exchange Seats

Table: `Seat`

| Column Name | Type    |
|:------------|:--------|
| id          | int     |
| student     | varchar |

`id` is the primary key (unique value) column for this table.
Each row of this table indicates the name and the ID of a student.
The ID sequence always starts from 1 and increments continuously.

Write a solution to swap the seat `id` of every two consecutive students. If the number of students is odd, the `id` of the last student is not swapped.
Return the result table ordered by id in ascending order.
The result format is in the following example.

### Example 1:

**Input:**
`Seat` table:

| id | student |
|:---|:--------|
| 1  | Abbot   |
| 2  | Doris   |
| 3  | Emerson |
| 4  | Green   |
| 5  | Jeames  |

**Output:**

| id | student |
|:---|:--------|
| 1  | Doris   |
| 2  | Abbot   |
| 3  | Green   |
| 4  | Emerson |
| 5  | Jeames  |

**Explanation:**
Note that if the number of students is odd, there is no need to change the last one's seat.

```sql
WITH cte AS (
    SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS row_num,
        LEAD(student, 1, student) OVER (ORDER BY id) AS next_student,
        LAG(student, 1) OVER (ORDER BY id) AS prev_student
        FROM Seat
) SELECT 
    id, 
    CASE WHEN MOD(row_num, 2) = 1 THEN next_student
    ELSE prev_student 
    END AS student
 FROM cte;
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

