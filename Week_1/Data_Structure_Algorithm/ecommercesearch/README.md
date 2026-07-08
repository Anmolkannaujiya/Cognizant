# Exercise 2: E-commerce Platform Search Function

## Objective

The goal of this exercise is to understand and implement two fundamental searching algorithms—**Linear Search** and **Binary Search**—and compare their performance. This exercise also introduces the concept of **Big O notation**, which helps analyze the efficiency of algorithms.

---

## Problem Statement

Imagine you are developing the search feature of an e-commerce platform where thousands of products are available. Customers expect search results to appear instantly, so choosing an efficient searching algorithm is important.

In this exercise, we implement both Linear Search and Binary Search to find a product by its name and compare their performance.

---

## Concepts Covered

- Big O Notation
- Linear Search
- Binary Search
- Arrays
- Time Complexity Analysis
- Object-Oriented Programming in Java

---

## Big O Notation

Big O notation is used to describe how the execution time of an algorithm grows as the size of the input increases.

For example, if an algorithm has a time complexity of **O(n)**, the running time increases linearly with the number of elements. If it has a time complexity of **O(log n)**, the increase is much slower, making it more efficient for large datasets.

---

## Search Algorithms

### 1. Linear Search

Linear Search checks each product one by one until the required product is found or the end of the array is reached.

This algorithm works on both sorted and unsorted data but becomes slower as the number of products increases.

### Example

Products:

```
Laptop
Phone
Mouse
Keyboard
Monitor
```

Searching for **Mouse**

```
Laptop   ✗
Phone    ✗
Mouse    ✓
```

The algorithm checks each product sequentially until it finds the match.

---

### 2. Binary Search

Binary Search is a much faster searching algorithm but it only works on **sorted data**.

Instead of checking every element, it repeatedly divides the search space into two halves until the product is found.

### Example

Sorted Products:

```
Keyboard
Laptop
Monitor
Mouse
Phone
```

Searching for **Mouse**

```
Step 1
Middle = Monitor

Mouse > Monitor
Search Right Half

-------------------

Step 2
Middle = Mouse

Found
```

Instead of checking every product, Binary Search eliminates half of the remaining products after each comparison.

---

## Project Structure

```
Exercise2_EcommerceSearch/
│
├── Product.java
├── SearchAlgorithms.java
├── Main.java
└── README.md
```

---

## Implementation

### Product Class

The `Product` class represents an item available on the e-commerce platform.

Each product contains:

- Product ID
- Product Name
- Category

### SearchAlgorithms Class

This class contains the implementations of:

- Linear Search
- Binary Search

### Main Class

The main class:

- Creates sample product objects
- Performs Linear Search
- Sorts the array
- Performs Binary Search
- Displays the search result

---

## Sample Output

```
Linear Search Found : 104 Mouse Electronics

Binary Search Found : 104 Mouse Electronics
```

---

## Time Complexity Comparison

| Algorithm | Best Case | Average Case | Worst Case |
|-----------|-----------|--------------|------------|
| Linear Search | O(1) | O(n) | O(n) |
| Binary Search | O(1) | O(log n) | O(log n) |

---

## Comparison

### Linear Search

**Advantages**

- Easy to implement
- Works on unsorted data
- Suitable for small datasets

**Disadvantages**

- Slow for large datasets

---

### Binary Search

**Advantages**

- Very fast for large datasets
- Efficient because it reduces the search space by half after every comparison

**Disadvantages**

- Requires the data to be sorted before searching

---

## Conclusion

In this exercise, both Linear Search and Binary Search were successfully implemented in Java.

While Linear Search is simple and works for any array, Binary Search provides significantly better performance for large datasets, provided that the data is sorted beforehand. Since e-commerce platforms typically maintain sorted or indexed product data, Binary Search is a more suitable choice for efficient product searching.

---

## Learning Outcomes

After completing this exercise, I learned:

- How Big O notation helps compare algorithm efficiency.
- The working principles of Linear Search and Binary Search.
- Why Binary Search requires sorted data.
- How to implement searching algorithms using Java classes and arrays.
- How to analyze and compare algorithm performance based on time complexity.