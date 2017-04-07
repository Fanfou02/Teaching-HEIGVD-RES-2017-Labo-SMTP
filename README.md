# Labo-SMTP

## Description
This project is based on the RES course teached by Olivier Liechti.
The main idea is to implement a software that uses the SMTP protocol to create pranks. The prank creates forged emails and sends them to a given list of victims.

## Program

### Files
First of all the `config.properties` file provides the basic information such as the SMTP server address and the port used to spread the fake mails and also the number of groups and the Cc witness used for the prank.

The `messages.utf8` file provides the body of the pranks messages.

The `victims.utf8` file provides the list of victims.


### Implementation
1. The `ConfigManager.java` class is used to parse the files and store the informations in different data structures.
2. The `Group.java`, `Mail.java` and `Person.java` are used to store the previous informations.
3. The `Prank.java` and `PrankGenerator.java` classes are used to generate the prank. It defines a mail sender and a list of recievers and choose a message from the list.
4. The `SmtpClient.java`class simulates the behaviour of a client communicating with the SMTP server. It follows the strict syntax of the protocol and provides the previous defined informations, such as the sender, the recievers, the subject and the message body.

## Test with the MockMock SMTP server
All the fonctionalities of the program as been tested with a local MockMock server to simulate a real SMTP server without polluting the students mails.
