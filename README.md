# br.utils

Lightweight Java HTTP helpers (like a tiny Axios) plus null-safe string utilities. Works with any project.

## Install

### Maven

```xml
<dependency>
  <groupId>io.github.gui1949</groupId>
  <artifactId>utils</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'io.github.gui1949:utils:1.0.0'
```

### JAR

Download `utils-1.0.0.jar` from [Maven Central](https://central.sonatype.com/artifact/io.github.gui1949/utils) (or build with `mvn clean package`) and add it to your classpath.

## Requirements

- Java 8+
- No other dependencies

## Quick start

```java
import br.utils.sendGet;
import br.utils.sendPost;
import br.utils.sendPut;
import br.utils.strings;

public class Example {
    public static void main(String[] args) throws Exception {
        // GET
        String body = sendGet.send("https://api.example.com/items");

        // POST (JSON)
        String created = sendPost.send(
            "https://api.example.com/items",
            "{\"name\":\"x\",\"qty\":1}"
        );

        // PUT
        String updated = sendPut.send(
            "https://api.example.com/items/1",
            "{\"name\":\"y\"}"
        );

        // String helpers
        String safe = strings.validateString(null); // → ""
        boolean ok  = new strings().isStringUsable("abc"); // → true
    }
}
```

## API

### `br.utils.sendGet`

| Method | Description |
|--------|-------------|
| `public static String send(String url) throws Exception` | HTTP GET. Returns response body as `String`. |

### `br.utils.sendPost`

| Method | Description |
|--------|-------------|
| `public static String send(String url, String data) throws Exception` | HTTP POST with `Content-Type: application/json`. `data` is the request body. Returns response body as `String`. |

### `br.utils.sendPut`

| Method | Description |
|--------|-------------|
| `public static String send(String url, String data) throws Exception` | HTTP PUT with `Content-Type: application/json`. `data` is the request body. Returns response body as `String`. |

### `br.utils.strings`

| Method | Description |
|--------|-------------|
| `public static String validateString(Object data)` | `data.toString()`; on `null` or error returns `""`. |
| `public Boolean isStringUsable(String s)` | `true` if `s` is not `null` and not empty. |

## Behavior notes

- All `send*` methods set `User-Agent: Mozilla/5.0`.
- `send*` methods add a short random delay (**500–1000 ms**) before the request and a brief pause (**500 ms**) after reading the response. Keep that in mind for batch jobs.
- Spaces in the URL are removed before the request.
- Non-2xx responses: `send*` throws (reads `getInputStream()` only).

## License

MIT
