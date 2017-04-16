#### Sample Service

An example of an application that supports the following
deployment modes **without any modification**:

* Standalone
* Server (WildFly)

###### Standalone mode

```
$ mvn clean install
$ java -jar target/sample-service-0.1.war
$ curl http://localhost:8080/test/Bond
{"name":"Hello, Mr. Bond","date":[2017,6,13,14,21,20,127000000]}
```

###### Server mode (WildFly)

```
$ mvn clean install
$ cp target/sample-service-0.1.war /opt/wildfly-10.0.0.Final/standalone/deployments
$ /opt/wildfly-10.0.0.Final/bin/standalone.sh &
$ curl http://localhost:8080/sample-service-0.1/test/Bond
{"name":"Hello, Mr. Bond","date":[2017,6,13,14,21,20,127000000]}
```

