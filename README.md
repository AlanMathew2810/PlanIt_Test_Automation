
#PlanIt_Test_Automation

#First I created a Maven project in the eclipse and then added the following maven dependencies in the pom.xml file

#1.	Cucumber JAVA

#2.	TestNG

#3.	Cucumber TestNG

#4.	Selenium JAVA

#5.	Maven Cucumber Report


#Then I created a folder named ‘Driver’ under src/test/resources and added ChromeDriver to this folder


#Next, I created a folder ‘Features’ under src/test/resources and created a .feature file inside the folder. 
Here I wrote scenario of inputs, actions and outcomes in plain English language called Gherkin.


#After that, I created a package named ‘StepDefinition’ under src/test/java and wrote the step definition for each steps in the feature file. 


#I used selenium PageFactory,  page object model concept and TestNG concept to create my framework. 
I created a java class for each page in the Jupiter Toys website. In this java classes, I identified the WebElements and wrote methods. 
These methods are the actions to be performed.


#At last I created a runner class under ‘StepDefinition’ named ‘TestRunner.java’ to link the feature file with the step definition file.


#Used the HTML report to show the results of the test execution. 


#I used BDD with Cucumber, TestNG, PageFactory concepts to develop this frameworks.  

 


