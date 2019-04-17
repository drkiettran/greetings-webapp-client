# Introduction
This is a simple web client project using Spring framework
It is used to call webapp APIs

# Instruction
First build it as a 'fat' jar:

```bash 
mvn clean test package
```

To run it, 

```bash
java -cp webapp_client-jar-with-dependencies.jar com.drkiettran.webapp_client.WebappClient url message
```