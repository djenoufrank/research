# Computer Project 55301 - 48982

# Members

- Frank Djenou 55301
- Ruzindana-Rukundo jules 48982



# Compiling and running commands

- Compile the program: `cd` into the project folder, then run `mvn clean compile`.

- Run the Server from the project folder): `mvn exec:java@server`

- Run a Client (from the project folder, you can run multiple clients by opening multiple terminals): `mvn exec:java@client`.

#  CLI Commands for the Client

- List of available commands: `help`.

- List every connected client: `list`.

- List my stories conversation with friend: `history@friendName`  (example: `history@jules`).

- Delete a contact: `delete@<friendName>` (example: `delete@frank`).

- Add a new contact: `add@<friendName>` (example: `add@frank`).

- List your friends contacts: `friends`.

- Send a message to a contact: `<message>@<friendName>` (example: `hello brother@frank`).

# Database

The sqlite database is located in `/data/sqlite/chatServerDB.db`, it can browsed using DB Browser(GUI TOOL) for example
or the CLI:more information about the sqlite cli commands here:
[SQLITE](https://www.sqlite.org/cli.html/) 



## Users already in DATABASE(login & password)

| login         | passwords    
| ------------- |-------------|
| jules         |samsung| 
| frank        |xiaomi   |   
| pha           | pha      |   


# check List 


1. Do I properly ensure confidentiality?
yes

• Are sensitive data transmitted and stored securely?
YES
• Are sensitive requests sent to the server transmitted securely?
YES

• Does a system administrator have access to the sensible data of some arbitrary user?
No

2. Do I properly ensure integrity of stored data?
YES

3. Do I properly ensure non-repudiation?
• Do I use signature, certificates, a proper authority?
NO
4. Do I use a proper and strong authentication scheme?
YES
5. Do my security features rely on secrecy, beyond credentials?
NO

6. Am I vulnerable to injection?
• URL, SQL, Javascript and dedicated parser injections
NO(usage of PreparedStatement)

7. Am I vulnerable to data remanence attacks?
NO

8. Am I vulnerable to replay attacks?
NO

9. Am I vulnerable to fraudulent request forgery?
NO

10. Am I monitoring enough user activity so that I can immediately detect malicious intents,
or analyse an attack a posteriori?
NO

• Do I simply reject invalid entries, or do I analyse them?
  WE SIMPLY REJECT INVALID ENTRIES

11. Am I using components with known vulnerabilities?
NOT THAT i KNOW OF

12. Is my system updated?
NO
 




