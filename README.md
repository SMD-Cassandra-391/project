# project
Cassandra database for Call Detail Records 

# Maven
This project utilizes the Maven build tool:
http://en.wikipedia.org/wiki/Apache_Maven

Maven will produce a jar file of our program in the target directory upon `mvn install`. This will allow for ease of 
use interaction with our program(s) via the command line. We will not be using the more advanced features of Maven.
We will keep it simple and try to develop familiarity with this useful buil tool that is commonly found in open source and professional software.

Installing maven on an ubuntu machine is straightforward:
http://stackoverflow.com/questions/15630055/how-to-install-maven-3-on-ubuntu-14-10-14-04-lts-13-10-13-04-12-10-12-04-by-usin

Eclipse also has a maven plugin that is of use:
http://maven.apache.org/eclipse-plugin.html

#Package Discipline 

Please keep package discipline as this will allow for easier managment of the code base. As an example I create a
Hello World Java class. I will create a new package in the src directory of 
`big-data` named: 
`main.java.com.ualberta.cmput391.W15.<GitHub user name>.helloWorld`

If I feel the need to write Junit tests for this class, then I will also create a package: 

`test.java.com.ualberta.cmput391.W15.<GitHub user name>.helloWorld`

#Other Languages
If you feel the need or want to use other languages, I'm sure we can incoporate files of another type in this GitHub project, but we'll need to ensure that these files are kept out of the `big-data` directory. Personally I'm planning on writing a node.js server to serve commands to the database, so ideas such as this are not out of the question. 

#Git Discipline 
I've added a `git ignore` file, but I'm not sure it's foolproof. If you see a bunch of untracked files that shoul
definetly not be pushed to the server, then this link might be of interest:
http://stackoverflow.com/questions/11542687/git-how-to-ignore-all-present-untracked-files

Lets try to have pride in this repository and keep it clean. 

#Contact Information
I'm not going to publish contact information for all group members. If a group member wants to add their contact information, they are free to do so. 

>Steven Myers:
>slmyers@ualberta.ca

