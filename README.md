# br.utils

Shared Java utilities used by Sankhya custom Java modules (Módulos Java):

| Class | Purpose |
|-------|---------|
| `br.utils.sendGet` | HTTP GET with delay + JSON response as String |
| `br.utils.sendPost` | HTTP POST (`Content-Type: application/json`) with delay |
| `br.utils.sendPut` | HTTP PUT with delay |
| `br.utils.strings` | Null-safe string helpers (`validateString`, `isStringUsable`) |

## Why a separate package

Sankhya loads classes from installed Java modules. Install **br.utils once**; other modules compile against it and do **not** need to re-export these classes inside every module JAR.

## Install on Sankhya

1. Build the JAR (Maven or Eclipse export).
2. Upload `br.utils-1.0.0.jar` on **Sankhya → Módulos Java**.
3. Install/activate the module **before** modules that depend on it.

## Use in another Eclipse project

1. Project → Properties → Java Build Path → Libraries → Add External JARs.
2. Select `br.utils-1.0.0.jar` (or the local `_libs/br.utils.jar`).
3. Import as usual:

```java
import br.utils.sendPost;
import br.utils.strings;

String body = sendPost.send(url, payload);
String safe = strings.validateString(value);
```

When exporting that project’s Sankhya module JAR, **exclude** `br.utils` (it is already installed on the server).

## Maven (publish / consume)

> **Note:** [mvnrepository.com](https://mvnrepository.com) is a **read-only index**.  
> You do **not** upload there. You publish to **Maven Central**; Maven Central is then indexed on mvnrepository automatically (usually within a few days).

### Coordinates

| Field | Value |
|-------|--------|
| `groupId` | `io.github.gui1949` |
| `artifactId` | `utils` |
| `version` | `1.0.0` |

```xml
<dependency>
  <groupId>io.github.gui1949</groupId>
  <artifactId>utils</artifactId>
  <version>1.0.0</version>
</dependency>
```

```groovy
implementation 'io.github.gui1949:utils:1.0.0'
```

### Publish to Maven Central (one-time setup)

1. Create an account: [central.sonatype.com](https://central.sonatype.com)
2. **Verify the namespace** `io.github.gui1949` via GitHub verification (user `Gui1949`).
3. Create a **GPG key** and publish the public key to a keyserver (e.g. `keyserver.ubuntu.com`).
4. Put credentials in `~/.m2/settings.xml` (Central Portal token user/password).
5. From this project folder:

```bash
mvn clean verify
```

6. Publish the staged artifacts via the Central Portal UI or `mvn deploy` (with the Central Publishing Maven plugin / OSSRH-compatible profile configured in `settings.xml`).
7. Wait for approval/indexing. Then search:
   - <https://mvnrepository.com/artifact/io.github.gui1949/utils>
   - <https://central.sonatype.com/artifact/io.github.gui1949/utils>

### Build locally (no publish)

```bash
mvn clean package
# target/utils-1.0.0.jar
```

Eclipse-only (no Maven): export **br.utils** as a JAR file → put it in `_libs/br.utils.jar` → reference that path from other projects.

## Notes

- `send*` methods include a random `Thread.sleep` (500–1000 ms) before the request and a short sleep after reading the response. Keep that behavior in mind for batch jobs.
- Java target: **8+** (compatible with typical Sankhya server runtimes).
- License: MIT (see `pom.xml`).
