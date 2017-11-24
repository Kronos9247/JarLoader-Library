# JarLoader-Library
This is a jar loader libary that helps programmers to load runnable jar files

**Example Code:**
```java
URL stream = CurrentClass.class.getResource("/plu-in.jar");

try {
  JarArchive archive = JarLoader.load(stream);
  archive.main();
} catch (IOException | URISyntaxException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
  e.printStackTrace();
}
```
