# kotlin-config

[![Build Status](https://img.shields.io/travis/davidsth/kotlin-config.svg?style=flat-square)](https://travis-ci.com/davidsth/kotlin-config)
[![License](https://img.shields.io/badge/license-MIT-blue.svg?style=flat-square&logo=appveyor)](LICENSE)


The goal is to provide a nice way to provide configuration to plain kotlin/java projects
similar to how spring does it configuration.

## Usage

Still in progress. However the goal is to use it as follows:

Given a yml property file:
```yaml
# application-default.yml
name: MyApp
token: TOKEN

```
In any class that you'd like to "inject" a configuration, use the annotation as follows

```
@Config
class Bot {
    println(Bot_Config)
}

```

After compilation, a source file is generated to be used within the class named `ClassName_Config`.

Annotation processing has limitations where you cannot modify an existing class, so it's not
a true "injection". The processor just creates a static output Class.

---

