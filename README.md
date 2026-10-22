# PC Hardware Application

## 1. Introduction

This project is the implementation of a PC parts shop, where users can browse through available components, purchase any quantity they want, and view previously placed orders along with their status.  
The application is designed for both **regular users** and **administrators (employees)**.

---

## 2. Implemented Features

### User (Database Access)

- Login verification (user / admin)
- Registering a new user
- Input format validation:
  - Username
  - Email
  - Password

---

### Order (Database Access)

- Placing a new order for the currently logged-in user
- Viewing:
  - Personal orders (user)
  - All orders (admin)
- Updating the status of any order (admin only)

---

### Part (Database Access)

- Base component containing common attributes
- Represents parts stored in the database based on availability
- Lookup by:
  - ID
  - Name
- Automatic stock update after a purchase
- Restocking functionality
- Adding new components to the inventory

---

### Components (Database Access)

#### CPU, GPU, Motherboard

- Component-specific attributes
- Validation of additional specifications for each component
- Storage of additional data in the database

---

### Input Validator

Helper functions used to validate inputs:

- Boolean values
- Strings
- Doubles
- Integers

---
<img width="556" height="701" alt="UML_Diagram" src="https://github.com/user-attachments/assets/6be9f20a-38a8-401e-9ff2-1caf0421d699" />

## 3. GUI

**Technology:** JavaFX with SceneBuilder

### Features

1. Login and account registration forms
<img width="1080" height="383" alt="image" src="https://github.com/user-attachments/assets/4bdb328b-95a7-4863-9aa2-c7b604590d99" />



2. Orders management:
   - Users can view their placed orders
   - Admins can view all orders and update their status
<img width="1080" height="383" alt="image" src="https://github.com/user-attachments/assets/bded35ea-dda0-4776-8840-4cccf5bfb600" />


3. Product browsing and purchasing interface
<img width="1080" height="383" alt="image" src="https://github.com/user-attachments/assets/f18cf104-b60a-485c-9d16-efbb2fe969fb" />

---

## 4. Admin Features

- Adding products to stock
- Adding completely new items to the inventory  
  *(Admin only)*
<img width="1080" height="383" alt="image" src="https://github.com/user-attachments/assets/3bc2671d-1da6-4080-a3c4-dc3e1247c6c4" />

---
