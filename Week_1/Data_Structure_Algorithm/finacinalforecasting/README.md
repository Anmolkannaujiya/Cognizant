# Exercise 7: Financial Forecasting

## Objective

The objective of this exercise is to understand the concept of **recursion** and apply it to solve a financial forecasting problem. The program predicts the future value of an investment based on an annual growth rate using a recursive algorithm.

---

## Problem Statement

Financial forecasting is commonly used to estimate the future value of investments, savings, or business revenue based on historical growth rates.

In this exercise, a recursive algorithm is implemented to calculate the future value of an investment after a specified number of years. This demonstrates how recursion can simplify problems that involve repeated calculations.

---

## Concepts Covered

- Recursion
- Base Case and Recursive Case
- Time Complexity Analysis
- Space Complexity Analysis
- Object-Oriented Programming in Java

---

## What is Recursion?

Recursion is a programming technique in which a method calls itself to solve a problem by breaking it into smaller instances of the same problem.

Every recursive solution consists of two parts:

- **Base Case:** Stops the recursion.
- **Recursive Case:** Calls the same method with a smaller problem.

### Example

Suppose an investment grows by **10% every year**.

```
Initial Amount = ₹10,000
Growth Rate = 10%
Years = 3
```

The recursive calculation follows this pattern:

```
Year 0 → 10000

Year 1 → 10000 × 1.10 = 11000

Year 2 → 11000 × 1.10 = 12100

Year 3 → 12100 × 1.10 = 13310
```

Instead of using a loop, the recursive function repeatedly calls itself until the number of years becomes zero.

---

## Formula Used

```
Future Value = Present Value × (1 + Growth Rate)^Years
```

The recursive implementation computes this formula by multiplying the current value with the annual growth factor during each recursive call.

---

## Project Structure

```
Exercise7_FinancialForecasting/
│
├── FinancialForecast.java
├── Main.java
└── README.md
```

---

## Implementation

### FinancialForecast Class

This class contains the recursive method responsible for calculating the future value of the investment.

The method accepts:

- Present Value
- Annual Growth Rate
- Number of Years

The recursion continues until the number of years reaches zero, which acts as the base case.

### Main Class

The main class:

- Initializes the investment details.
- Calls the recursive forecasting method.
- Displays the calculated future value.

---

## Sample Input

```
Present Value : 10000

Growth Rate   : 10%

Years         : 5
```

---

## Sample Output

```
Future Value = 16105.10
```

---

## Time Complexity

Each recursive call processes one year.

```
T(n) = T(n - 1) + O(1)
```

Therefore,

**Time Complexity:** `O(n)`

---

## Space Complexity

Each recursive call remains on the call stack until the computation is complete.

**Space Complexity:** `O(n)`

---

## Optimization

The recursive solution is simple and easy to understand, but every recursive call occupies memory on the call stack.

For a very large number of years, an **iterative solution** can be used to reduce the space complexity from **O(n)** to **O(1)** while maintaining the same time complexity of **O(n)**.

If a recursive problem contains repeated calculations (such as Fibonacci numbers), techniques like **Memoization** or **Dynamic Programming** can further improve performance by avoiding redundant computations. Although this forecasting problem does not involve overlapping subproblems, these optimization techniques are valuable for more complex recursive algorithms.

---

## Conclusion

This exercise demonstrates how recursion can be used to solve financial forecasting problems in a clean and structured manner.

The implementation successfully predicts the future value of an investment using annual growth rates while reinforcing the concepts of recursion, base cases, and recursive problem-solving. It also highlights the importance of analyzing time and space complexity when choosing an algorithm.

---

## Learning Outcomes

After completing this exercise, I learned:

- The fundamentals of recursion.
- How recursive methods solve problems by reducing them into smaller subproblems.
- The importance of defining a proper base case.
- How to analyze recursive algorithms using time and space complexity.
- When recursion is appropriate and when an iterative solution may be a better alternative.