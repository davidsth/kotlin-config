# kotlin-config

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

```kotlin
@Config
class Bot {
    println(Bot_Config)
}

```

After compilation, a source file is generated to be used within the class named `ClassName_Config`.

Annotation processing has limitations where you cannot modify an existing class, so it's not
a true "injection". The processor just creates a static output Class.

---

