# bank_management_system_using_JDBC
The given Core Java and MySQL project is a basic bank management system implemented using the State Bank of India (SBI) as an example. The project consists of three packages: 'com.braindata.bankmanagement.model' (containing the 'Account' class), 'com.braindata.bankmanagement.service' (containing the 'Rbi' interface), and 'com.braindata.bankmanagement.serviceImpl' (containing the 'Sbi' class implementing the 'Rbi' interface).

The 'Account' class represents the attributes of a bank account, such as account number, name, mobile number, Aadhaar number, gender, age, and balance.

The 'Rbi' interface defines various banking operations like creating an account, inserting data into an account, displaying account details, depositing money, withdrawing money, checking balance, deleting an account, and showing transaction history.

The 'Sbi' class implements these banking operations by interacting with a MySQL database. It includes methods to create a table for accounts, insert account data, display account details, deposit and withdraw money, check balance, delete an account, and show transaction history.

The 'Test' class in the 'com.braindata.bankmanagement.client' package serves as the main class for testing the functionalities of the bank management system. It provides a menu-driven interface to interact with the implemented operations.

Overall, the project focuses on the fundamental operations of a bank, allowing users to create accounts, perform transactions, and manage their accounts.
