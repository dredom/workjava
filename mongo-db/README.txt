
Mongo DB

http://docs.mongodb.org/manual/tutorial/install-mongodb-on-os-x/
 brew update
 brew install mongodb
 brew update mongodb
  mongod --config /usr/local/etc/mongod.conf

Start server:
 mongod

Command line:
 mongo
 
database
  collections (~ table-ish)
    documents
      fields key:val
      

http://docs.mongodb.org/manual/tutorial/control-access-to-mongodb-with-authentication/

Java:  boolean auth = db.authenticate(myUserName, myPassword);

http://www.mongodb.org/display/DOCS/Java+Tutorial