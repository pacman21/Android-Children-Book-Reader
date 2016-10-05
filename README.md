# Android-Children-Book-Reader

-------------------------------
Objective: 
-------------------------------

Allow parents to buy and sell children’s’ e-books on our app. The e-book will allow someone, anyone in the world, to record their voice reading a book where the child can replay.

-------------------------------
Requirements:
------------------------------- 

The books should be originals, created by me or someone, as well as uploadable, where PDFs could be uploaded and then recorded. 

The audio should be recorded per page. 

The audio needs to be hosted on a webserver. Once you download the audio, you should then be able to play on demand, instead of streaming the same audio every time you play. 

-------------------------------
Problems:
-------------------------------

How can we send an invite to record audio for a certain textbook? Encrypted URL might work?

-------------------------------
Technical Doc:
-------------------------------

User must create a user account. The user account will be created by entering their email address and their password as this will be the only required information to create an account. 

After the user creates an account, they then must be able to login using their email and password credentials. 

After the user has logged in, they should be able to buy books, read books they already own, or upload books via PDF. 

If the user selects to buy a book, then the user may be able to search by Author, Keyword or Title.
After the search results are displayed. Each book will be displayed with the Title, Author, Keywords, and Price. Once the book is clicked on, it will show all of the same information along with a description and the ability to buy the book. 

Once the user chooses to buy the book, then an insert to the database must be made. 

Record is then inserted to UserBooks with all of the required information. 

After the book is purchased, the purchaser can send the book to someone to record audio for the book. The audio would be recorded and then sent up to the server and inserted into the database. The book must be recorded per page.

