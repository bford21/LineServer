# Line Server API Assignment

### How​ ​does​ ​your​ ​system​ ​work?​ ​(if​ ​not​ ​addressed​ ​in​ ​comments​ ​in​ ​source) 
	
The first thing you need to do in order to get my system to work is build it. You can do this by running the build.sh script located in the root directory. This script uses Maven to build, package, and run unit tests.

Once it has successfully completed building and all unit tests have passed, the next step is to run the application. To launch the API you can call the run.sh script also located in the root directory. This script takes a single command line parameter, which is the location of the file you’d like to use. I’ve included a test.txt file in the root directory for convenience.

The first thing that happens when run.sh is initiated is some pre-processing on the file provided. I decided the best way to do this would be to iterate through the file line by line and put each lines contents into a hashmap. The key is the line number and the value is the contents of the line. This way when a user queries the REST endpoint for a line it can quickly retrieve the data from the hashmap rather than iterate through the text file. Of course the only downside to this approach is that you pay a one time cost upfront for the file to be stored in memory. After that though each REST request is very quick. 

Once run.sh has successfully been executed you can begin making calls to the API by going to [http://localhost:8080/lines/{line-number}]() in any web browser. Fill in “{line-number}” with the line number you’d like to retrieve. Successful calls will return a 200 and the contents of the line. Calls to a line number that does not exist will result in a HTTP status code 413 being returned. 


### What​ ​do​ ​we​ ​need​ ​to​ ​build​ ​your​ ​system?

In order to build my Line Server API you need the following things on your machine.
- [Maven 3.0 or later](https://maven.apache.org/download.cgi)
- [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html)
- [Git](https://git-scm.com/downloads)

1. Use Git to clone my repository:
	
        git clone https://github.com/bford21/LineServer.git

2. Call build.sh located in the root directory

	    build.sh

3. Call run.sh located in the root directory

		run.sh test.txt


### How​ ​will​ ​your​ ​system​ ​perform​ ​with​ ​a​ ​1​ ​GB​ ​file?​ ​a​ ​10​ ​GB​ ​file?​ ​a​ ​100​ ​GB​ ​file?
	
How my system will perform with a 1 GB, 10 GB and 100 GB file completely depends on the environment in which my application is running. Upon starting the application the first thing I do is parse the text file into memory in a hashmap. This is to facilitate the fast retrieval of lines when a user makes a request. However the system my application is running on must have enough available free memory to handle storing the file. For smaller files this is no problem but a 100 GB text file will certainly cause issues if the service is deployed on my laptop. Just like opening the file and viewing it in notepad would cause system memory issues. 
That’s why it’s important to look at the environment the service is deployed to. In a cloud based environment with the ability to scale memory to meet the needs of the system this would be less of an issue. 

Of course the other approach here would be to not parse the text file into memory and upon each request actually go loop through the text file until reaching the requested line. This however would be extremely slow especially given a file that’s 100 GB, but you would not have to worry about memory issues.

### How​ ​will​ ​your​ ​system​ ​perform​ ​with​ ​100​ ​users?​ ​10000​ ​users?​ ​1000000​ ​users? 

My system will perform well with a various number of different users hitting the endpoint at the same time. This is all thanks to Spring Boot and how it handles HTTP requests. In Spring every web request spawns a new thread, opposed to each connection having it’s own thread. This means that these threads have a short lifespan and are asynchronous, which means many can be spun up simultaneously and work independently without stepping on each other.

Of course your bottleneck will become the system this service is running on. If it were running on my laptop for instance it would have trouble handling a million simultaneous requests. That’s why if I were to deploy this service to production I would run it in a cloud environment where it could easily scale to meet the demands of the users.    

### What​ ​documentation,​ ​websites,​ ​papers,​ ​etc​ ​did​ ​you​ ​consult​ ​in​ ​doing​ ​this assignment? 
	
I used the following sites while completing this assignment.
1. [Spring.io](https://spring.io/docs/reference)
2. [Maven](https://maven.apache.org/guides/)
3. [Java JDK 9](https://docs.oracle.com/javase/9/) 

### What​ ​third-party​ ​libraries​ ​or​ ​other​ ​tools​ ​does​ ​the​ ​system​ ​use?​ ​How​ ​did​ ​you choose​ ​each​ ​library​ ​or​ ​framework​ ​you​ ​used? 
	
I chose to use use the Spring Boot framework for this application. I chose Spring Boot because it is a framework designed to get you up and running as quickly as possible with minimal upfront configuration. This seemed ideal for me given the time frame I had to complete this assignment. I found there was tons of documentation for the framework as well which made the decision to use it even easier. 

### How​ ​long​ ​did​ ​you​ ​spend​ ​on​ ​this​ ​exercise?​ ​If​ ​you​ ​had​ ​unlimited​ ​more​ ​time​ ​to spend​ ​on​ ​this,​ ​how​ ​would​ ​you​ ​spend​ ​it​ ​and​ ​how​ ​would​ ​you​ ​prioritize​ ​each​ ​item? 

I spent roughly 5 days on this exercise. I worked for an hr or 2 each day. If I had unlimited time I would have definitely focused on making this API production ready. I would spend some time going over how I handled exceptions making sure I’m returning human readable error messages and not stack traces. Also making sure that whatever error messages I do return do not give away my implementation. I’d also implement a logging system where I can log events such as errors, warnings and debug messages.   

There is also additional work that could be done around testing. I have some basic unit tests but if I had unlimited time I would spend time writing some end to end acceptance tests. Ideally there would also be a CI pipeline for this project where these types of tests would run.

I think having a nice UI would also be something I would work on in order to improve this project. Right now the API returns the value of the line requested and prints it out to a plain white page. Displaying this data in a more creative way would be something I would explore.
### If​ ​you​ ​were​ ​to​ ​critique​ ​your​ ​code,​ ​what​ ​would​ ​you​ ​have​ ​to​ ​say​ ​about​ ​it? 

If I were to critique my code I would say that it took a good approach architecturally, but it’s a little rough around the edges. It could use some work in terms of how I handle exceptions and handle edge cases such as if a user provides a file that does not exist.  

### What​ ​techniques​ ​would​ ​you​ ​use​ ​to​ ​achieve​ ​maximum​ ​uptime?​ ​​ ​Scenarios​ ​to​ ​consider: code​ ​deployment,​ ​server​ ​failures,​ ​disk​ ​failures,​ ​etc… 
	
In order to achieve maximum uptime I would deploy this service to a cloud platform such as Microsoft Azure or Amazon AWS. I would deploy it as a microservice and use a microservice orchestration agent like service fabric or Amazon Elastic Container Service. These orchestration agents would take care of things like load balancing and making sure that my service is redundant and can withstand outages. 

Deploying to a cloud service would also mitigate any risk associated with handling large files. My program processes the entire text file provided by the user and stores it in a hashmap. In the case of extremely large files, this could cause problems if this service was running on a machine with limited memory capacity. If it were running on a cloud service however we could easily scale in order to meet the needs of any file size.   

### How​ ​extensible​ ​is​ ​your​ ​service​ ​/​ ​architecture?​ ​Can​ ​we​ ​add​ ​more​ ​endpoints​ ​for​ ​additional features? 

My service is very extensible. It would be extremely easy to add an additional endpoint or handle different requests such as a PUT or a POST. You would simply need to create a new controller or edit the existing LineController and add new methods with request mappings to handle the route and type of request you want.  

