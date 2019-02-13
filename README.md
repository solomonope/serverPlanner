# ServerPlanner

### About

### Build
requires gradle 4.0 and above

```
gradle build
```


### Sample usage
```
import de.moebel.model.Computer
import de.moebel.service.Server.ServerPlanner

public class Program{
public static void main(String ... args){

        Computer[] virtualMachines = {
            new Computer(2, 32, 100),
            new Computer(1, 16, 10),
            new Computer(1, 16, 10)
        };
        Computer serverType = new Computer(2, 32, 100);

        ServerPlanner serverPlanner = new ServerPlanner();

        int serverCount = serverPlanner.calculate(serverType, virtualMachines);

}

}
```


