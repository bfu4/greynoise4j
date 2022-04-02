# greynoise4j
For the purpose of me trying to understand
the Greynoise API, I implemented it (kind of) in
Java (because I like Java's objects).

## Usage
```java
// This creates a community client without an API key.
// However, an api key may be provided inside this method.
GreynoiseClient client = GreynoiseClient.community();

// Get the host information of the ip address 8.8.8.8, and when
// the promise resolves, print the data.
client.getHostInformation("8.8.8.8").onSuccess(System.out::println)
```