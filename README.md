#MobApp

Add MySQL connector to your project library ( https://dev.mysql.com/downloads/connector/j/5.1.html ) .

Create MySQL database "Mobitel" with Table "Kartica" ( serijskiBroj INT PrimaryKey , pin INT , puk INT , brojTelefona VARCHAR)

MobApp project is based on sending messages from one computer(IP address) to another computer with different IP address. Executing the program, each user needs a pin. Entering a pin and telephone number Server will give unique serial number with phonebook. Sending messages is acceptable only if the Server has a recipient number .

Server database is alredy created to simulate Server.

Connecting to server can cause some problems when uploading files larger than approx 1MB. Change in the my.ini file by including the single line under [mysqld] section in your file: max_allowed_packet=500M.
