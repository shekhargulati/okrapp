# okrapp

Install Java using Jabba

```
curl -sL https://github.com/shyiko/jabba/raw/master/install.sh | bash && . ~/.jabba/jabba.sh
```

```
jabba install zulu@1.16.0-0
```

You can install Intellij from [here](https://www.jetbrains.com/idea/nextversion/)

To build the project run the following command.

```
./gradlew clean install
```

Structure of the project is below.

```
okrapp
    backend
        core
        api
    frontend
```