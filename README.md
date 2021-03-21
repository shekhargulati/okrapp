# okrapp

[![OKRApp CI](https://github.com/shekhargulati/okrapp/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/shekhargulati/okrapp/actions/workflows/ci.yml)


> **I am building it live on Twitch https://www.twitch.tv/shekhargulati. You can follow me on Twitch if you want to be notified when I do my next stream.**

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

To read more refer to [architecture section](./docs/architecture.md)
