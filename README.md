# kotlin-config

![Travis (.com) branch](https://img.shields.io/travis/com/davidsth/kotlin-config/master.svg?style=flat-square)
[![License](https://img.shields.io/badge/license-MIT-blue.svg?style=flat-square)](LICENSE)


The goal is to provide a nice way to provide configuration to plain kotlin/java projects
similar to how spring does it configuration.

## Usage

Still in progress. However the goal is to use it as follows:

Given a yml property file:
```yaml
# application-default.yml
name: MyApp
language: kotlin
id: 1

```
In any class that you'd like to "inject" a configuration, use the annotation as follows

```
@Config
class MarvelUniverse {
    init {
        println("name: ${MarvelUniverse_Config.name}")
        println("language: ${MarvelUniverse_Config.language}")
    }
}

fun main() {
    MarvelUniverse()
}

// outputs

name: MyApp
language: kotlin
id: 1

```

After compilation, a source file is generated to be used within the class named `ClassName_Config`.

Annotation processing has limitations where you cannot modify an existing class, so it's not
a true "injection". The processor just creates a static output Class.

---

