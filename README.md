Playing around GreenDao
=================================

Demonstrates how to manage database using GreenDao

Introduction
============

The sample shows creation of database and tables incuding the basic queries performed on it.
This takes an example of movie->Actor and movie->director relationships to demonstrates one to one, one to many 
nd many to many relationships.

Add greenDAO to your project
----------------------------
greenDAO is available on Maven Central. Please ensure that you are using the latest versions by [checking here]
http://mvnrepository.com/artifact/de.greenrobot/greendao and http://mvnrepository.com/artifact/de.greenrobot/greendao-generator

Gradle dependency for your Android app:
```
    compile 'de.greenrobot:greendao:2.1.0' 

```

Gradle dependency for your Java generator project:
```
    compile 'de.greenrobot:greendao-generator:2.1.0' 

```
