package de.moebel.service;

import de.moebel.model.Computer;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ServerPlannerTest
{
    @Test
    public void calculate() throws Exception
    {
        int expectedServerCount = 2;
        Computer[] virtualMachines = {
            new Computer(2, 32, 100),
            new Computer(2, 32, 100),
            new Computer(2, 32, 100)
        };
        Computer serverType = new Computer(2, 32, 100);

        ServerPlanner serverPlanner = new ServerPlanner();

        int serverCount = serverPlanner.calculate(serverType, virtualMachines);

        assertThat(serverCount).isEqualTo(expectedServerCount);

    }

}