# InvestmentPortfolio
InvestmentPortfolio is an application which had 2 major use cases.

1. A client provides the advisor his/her risk preference (1 being very risk averse, and 10 being
insensitive to risk).The financial advisor has a few pre-defined portfolios which allocates a client’s money in certain proportions across
multiple investment categories to balance risk.Recommends a portfolio based on input.

2.User tells the advisor for how much money they currently have allocated to each of the 5 investment
categories.If the user's current allocation is not ideal for their risk level as displayed in part 1 above,
recommend an allocation transference to correspond with suggested allocation.The transfer change should be minimum.




Getting Started

Running from an IDE

1. Download the zip file of the project
2. Eclipse users can select Import… → Existing Maven Projects from the File menu.
3. You can run a Spring Boot application from your IDE as a simple Java application 
   Run class org.springframework.boot.SpringApplication.InvestmentPortfolioApp as a Java application.
   It Deploys and runs the application on Tomcat port : 9090 , configured in application.properties

Using the Maven Plugin

1. Download the zip file of the project
2. The Spring Boot Maven plugin includes a run goal that can be used to quickly compile and run your application. 
    Applications run in an exploded form, as they do in your IDE.
3. Use command 
mvn spring-boot:run


NOTE : If you followed any of above steps. Then inbuilt tomcat of spring boot will start on port 9090 and now you are ready to test.


Prerequisites
1. Java 8
2. Maven Plugin


Testing the application

Using PostMan Client
1. you can go to https://www.getpostman.com/ and get the latest post man.
2. You can use my collection or can hit sevice with new request.(https://www.getpostman.com/collections/bb990c2eb67588207849)
3. Below are the testing screenshots



Request
<img width="1082" alt="screen shot 2018-10-29 at 3 33 41 am" src="https://user-images.githubusercontent.com/37640949/47644662-0395e800-db2c-11e8-88dd-9afbb07206d1.png">

response

<img width="1072" alt="screen shot 2018-10-29 at 3 39 50 am" src="https://user-images.githubusercontent.com/37640949/47644779-553e7280-db2c-11e8-9475-286a16d510aa.png">


Request 

<img width="1072" alt="screen shot 2018-10-29 at 3 39 50 am" src="https://user-images.githubusercontent.com/37640949/47644820-730bd780-db2c-11e8-9150-d2b5eebda076.png">


response


<img width="1088" alt="screen shot 2018-10-29 at 3 41 40 am" src="https://user-images.githubusercontent.com/37640949/47644879-9767b400-db2c-11e8-9f93-e43517342bb9.png">


Using Rest Client 

1. I have created a rest client in test folder
com.info.rest.client.RestApiClient

Request :

private static void getPortFolioByRiskPreference(int riskPreference) {
		final String uri = "http://localhost:9090/portfolio/" + riskPreference;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		System.out.println(result);}


Response :

<200 OK,{"bondsAmount":9000.0,"largeCapAmount":2250.0,"midCapAmount":2250.0,"foreignAmount":1500.0,"smallCapAmount":0.0,"results":["Transfer 5000.0 from Small Cap to Bonds","Transfer 1750.0 from Mid Cap to Bonds","Transfer 750.0 from Large Cap to Bonds","Transfer 500.0 from Foreign to Bonds"],"totalInvestMent":15000.0},{Content-Type=[application/json;charset=UTF-8], Transfer-Encoding=[chunked], Date=[Mon, 29 Oct 2018 10:46:50 GMT]}>




Using ab - Apache HTTP server benchmarking tool to test that the max user it can support is 2





request : v-jays-Mac:InsurancePortfolio vijay$ ab -v 2 -c 5 -n 5 http://localhost:9090/portfolio/1

response : From below response you can see that for some of the requests it is giving 401 because already the user limit reached to 2

This is ApacheBench, Version 2.3 <$Revision: 1826891 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/


---
GET /portfolio/1 HTTP/1.0
Host: localhost:9090
User-Agent: ApacheBench/2.3
Accept: */*


---
LOG: header received:
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Date: Mon, 29 Oct 2018 10:44:16 GMT
Connection: close

{"bondsShare":80.0,"largeCapShare":20.0,"midCapShare":0.0,"foreignShare":0.0,"smallCapShare":0.0}
LOG: header received:
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Date: Mon, 29 Oct 2018 10:44:16 GMT
Connection: close

{"bondsShare":80.0,"largeCapShare":20.0,"midCapShare":0.0,"foreignShare":0.0,"smallCapShare":0.0}
LOG: header received:
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Date: Mon, 29 Oct 2018 10:44:16 GMT
Connection: close

{"bondsShare":80.0,"largeCapShare":20.0,"midCapShare":0.0,"foreignShare":0.0,"smallCapShare":0.0}
LOG: header received:
HTTP/1.1 401 
Content-Type: application/json;charset=UTF-8
Date: Mon, 29 Oct 2018 10:44:16 GMT
Connection: close

{"timestamp":1540809856078,"status":401,"error":"Unauthorized","message":"Reached max number of users : ","path":"/portfolio/1"}
WARNING: Response code not 2xx (401)
LOG: header received:
HTTP/1.1 401 
Content-Type: application/json;charset=UTF-8
Date: Mon, 29 Oct 2018 10:44:16 GMT
Connection: close

{"timestamp":1540809856078,"status":401,"error":"Unauthorized","message":"Reached max number of users : ","path":"/portfolio/1"}
WARNING: Response code not 2xx (401)
..done


Server Software:        
Server Hostname:        localhost
Server Port:            9090

Document Path:          /portfolio/1
Document Length:        97 bytes

Concurrency Level:      5
Time taken for tests:   0.007 seconds
Complete requests:      5
Failed requests:        2
   (Connect: 0, Receive: 0, Length: 2, Exceptions: 0)
Non-2xx responses:      2
Total transferred:      1142 bytes
HTML transferred:       547 bytes
Requests per second:    704.23 [#/sec] (mean)
Time per request:       7.100 [ms] (mean)
Time per request:       1.420 [ms] (mean, across all concurrent requests)
Transfer rate:          157.08 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.0      0       0
Processing:     2    3   0.6      4       4
Waiting:        2    3   0.6      3       3
Total:          2    3   0.6      4       4
WARNING: The median and mean for the processing time are not within a normal deviation
        These results are probably not that reliable.
WARNING: The median and mean for the total time are not within a normal deviation
        These results are probably not that reliable.

Percentage of the requests served within a certain time (ms)
  50%      4
  66%      4
  75%      4
  80%      4
  90%      4
  95%      4
  98%      4
  99%      4
 100%      4 (longest request)


Authors
Vijay Pandey
See also the list of contributors who participated in this project.
