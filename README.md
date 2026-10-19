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
<img width="1080" height="382" alt="image" src="https://github.com/user-attachments/assets/7c9dc2bf-e32a-41bf-91c5-46ed2cb06bdb" />

2. Orders management:
   - Users can view their placed orders
   - Admins can view all orders and update their status
<img width="1080" height="383" alt="image" src="https://github.com/user-attachments/assets/c5824a00-733d-422a-abf1-10c80d643292" />

3. Product browsing and purchasing interface
<img width="1064" height="383" alt="image" src="https://github.com/user-attachments/assets/72e5367c-707b-4e52-a26f-b40aeb186d50" />

---

## 4. Admin Features

- Adding products to stock
- Adding completely new items to the inventory  
  *(Admin only)*
<img width="1084" height="381" alt="image" src="https://github.com/user-attachments/assets/b063e494-2a4e-4207-b24b-f5a0b49d939e" />

---
