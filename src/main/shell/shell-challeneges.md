# Shell Challenges

## Problem 192. Word Frequency

Write a bash script to calculate the
frequency
of each word in a text file words.txt.

For simplicity sake, you may assume:

    `words.txt` contains only lowercase characters and space ' ' characters.
    Each word must consist of lowercase characters only.
    Words are separated by one or more whitespace characters.

### Example:

Assume that `words.txt` has the following content:

```
the day is sunny the the
the sunny is is
```

Your script should output the following, sorted by descending frequency:

```
the 4
is 3
sunny 2
day 1
```

**Note:**

    Don't worry about handling ties, it is guaranteed that each word's frequency count is unique.
    Could you write it in one-line using Unix pipes?

```bash
cat words.txt | tr ' ' '\n' | grep '\w'  | sort | uniq -c | sort -r | awk -F ' ' '{print $2,$1}'
```

## Problem 193. Valid Phone Numbers

Given a text file `file.txt` that contains a list of phone numbers (one per line), write a one-liner bash script to print all valid phone numbers.
You may assume that a valid phone number must appear in one of the following two formats: (xxx) xxx-xxxx or xxx-xxx-xxxx. (x means a digit)
You may also assume each line in the text file must not contain leading or trailing white spaces.

### Example:

Assume that `file.txt` has the following content:

```
987-123-4567
123 456 7890
(123) 456-7890
```

Your script should output the following valid phone numbers:

```
987-123-4567
(123) 456-7890
```

```bash
cat file.txt | egrep "^(\\([0-9]{3}[\\)][ ]{1}|[0-9]{3}-)[0-9]{3}-[0-9]{4}$"

grep -E '^(\([0-9]{3}\) |[0-9]{3}-)[0-9]{3}-[0-9]{4}$' file.txt
```

## Problem 194. Transpose File

Given a text file `file.txt`, transpose its content.
You may assume that each row has the same number of columns, and each field is separated by the ' ' character.

### Example:

If `file.txt` has the following content:

```
name age
alice 21
ryan 30
```

Output the following:

```
name alice ryan
age 21 30
```

```bash
cols=$(head -1 file.txt | wc -w) # number of columns
for i in $(seq 1 "$cols"); do
    echo $(cut -d ' ' -f "$i" file.txt)
done
```

## Problem 195. Tenth Line 

Given a text file `file.txt`, print just the 10th line of the file.

### Example:

Assume that `file.txt` has the following content:

```
Line 1
Line 2
Line 3
Line 4
Line 5
Line 6
Line 7
Line 8
Line 9
Line 10
```

Your script should output the tenth line, which is:

```
Line 10
```

**Note:**

1. If the file contains less than 10 lines, what should you output?
2. There's at least three different solutions. Try to explore all possibilities.

```bash
awk 'NR==10' file.txt

sed -n 10p file.txt
```