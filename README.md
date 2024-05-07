# VCommandParser

A free and open source command parsing library for Paper, orignally designed for use on the [VihnCraft Minecraft Server](http://vihncraft.com).

## Usage

Some example code showing registering a command is shown below. You must still add your command in `plugin.yml`.

```java
import org.bukkit.plugin.java.JavaPlugin;
import com.vihncraft.libs.vcommandparser.command.VCommand;
import com.vihncraft.libs.vcommandparser.command.VCommandSender;
import com.vihncraft.libs.vcommandparser.args.IntegerArgument;


public final class MyPlugin extends JavaPlugin {
    
    public void onEnable() {
        
        // Create the command 'mycommand', allowing both the player & console to run it.
        VCommand myCommand = new VCommand("mycommand", VCommandSender.Both, new MyCommandExecutor());
        
        // Add an integer argument to the command
        myCommand.addArgument(new IntegerArgument());
        
        // Register the command to the plugin.
        myCommand.register(this);
        
    }
    
}
```
```java
import com.vihncraft.libs.vcommandparser.command.VCommandContext;
import com.vihncraft.libs.vcommandparser.command.VCommandExecutor;

public class MyCommandExecutor implements VCommandExecutor {
    
    public void onExecute(VCommandContext commandContext) {
        Integer myInteger = commandContext.getArguments().getIntArg(0); // Get the integer argument we added earlier
        commandContext.sendMessage(String.format("You entered the number %d.", myInteger)); // You can also send components!
    }
    
}
```

## Adding as a dependency

Most plugins that I am aware of use Maven, and as such I will be providing instructions for Maven. If you are using Gradle, you will need to infer how to do this yourself.

### `pom.xml`
```xml
<repositories>
    <repository>
        <id>vihncraft</id>
        <url>https://repo.vihncraft.com/repository/maven-releases/</url>
    </repository>
</repositories>
```
```xml
<dependencies>
    <dependency>
        <groupId>com.vihncraft.libs</groupId>
        <artifactId>VCommandParser</artifactId>
        <version>1.0.0</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```