# greynoise4j
For the purpose of me trying to understand
the Greynoise API, I implemented it (kind of) in
Java (because I like Java's objects).

## Disclaimer
I don't have an enterprise api key so I have
***zero*** clue if any of those methods work.

## Building
Normally I host my libraries on some sort of repository.
Due to the nature of this project, I'm not, as of currently, deploying it to central.
```
$ mvn clean package
or
$ ./mvnw clean package
```

## Basic Usage
```java
// This creates a community client without an API key.
// However, an api key may be provided inside this method.
GreynoiseClient client = GreynoiseClient.community();

// Get the host information of the ip address 8.8.8.8, and when
// the promise resolves, print the data.
client.getHostInformation("8.8.8.8").onSuccess(System.out::println)
```

## Enterprise Usage
```java
// It is required that you have an API key to use the enterprise client.
// If the key is empty, the library will fail to create the client.
String apiKey = System.getenv("ENTERPRISE_KEY");
GreynoiseClient client = GreynoiseClient.enterprise(apiKey);
client.getQuickHostInformation("1.1.1.1", "8.8.8.8", ...);
```