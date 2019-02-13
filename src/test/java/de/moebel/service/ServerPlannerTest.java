package de.moebel.service;

import de.moebel.model.Computer;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ServerPlannerTest
{
    @Test
    public void Calculate_IncludeVirtualMachines_ReturnRightAmountOfServers() throws Exception
    {
        int expectedServerCount = 2;
        Computer[] virtualMachines = {
            new Computer(2, 32, 100),
            new Computer(1, 16, 10),
            new Computer(1, 16, 10)
        };
        Computer serverType = new Computer(2, 32, 100);

        ServerPlanner serverPlanner = new ServerPlanner();

        int serverCount = serverPlanner.calculate(serverType, virtualMachines);

        assertThat(serverCount).isEqualTo(expectedServerCount);

    }


    @Test
    public void Calculate_IncludeOversizedCPUVirtualMachine_SkipOverSizedCPUVirtualMachine() throws Exception
    {
        int expectedServerCount = 2;
        Computer[] virtualMachines = {
            new Computer(2, 32, 100),
            new Computer(1, 16, 10),
            new Computer(1, 16, 10),
            new Computer(3, 32, 100),
        };
        Computer serverType = new Computer(2, 32, 100);

        ServerPlanner serverPlanner = new ServerPlanner();

        int serverCount = serverPlanner.calculate(serverType, virtualMachines);

        assertThat(serverCount).isEqualTo(expectedServerCount);

    }


    @Test
    public void Calculate_IncludeOversizedRAMVirtualMachine_SkipOverSizedRAMVirtualMachine() throws Exception
    {
        int expectedServerCount = 2;
        Computer[] virtualMachines = {
            new Computer(2, 32, 100),
            new Computer(1, 16, 10),
            new Computer(1, 16, 10),
            new Computer(3, 64, 100),
        };
        Computer serverType = new Computer(3, 32, 100);

        ServerPlanner serverPlanner = new ServerPlanner();

        int serverCount = serverPlanner.calculate(serverType, virtualMachines);

        assertThat(serverCount).isEqualTo(expectedServerCount);

    }


    @Test
    public void Calculate_IncludeOversizedHDDVirtualMachine_SkipOverSizedHDDVirtualMachine() throws Exception
    {
        int expectedServerCount = 1;
        Computer[] virtualMachines = {
            new Computer(2, 32, 100),
            new Computer(1, 16, 10),
            new Computer(1, 16, 10),
            new Computer(3, 32, 100),
        };
        Computer serverType = new Computer(3, 32, 50);

        ServerPlanner serverPlanner = new ServerPlanner();

        int serverCount = serverPlanner.calculate(serverType, virtualMachines);

        assertThat(serverCount).isEqualTo(expectedServerCount);

    }

    @Test
    public void Calculate_SlightlyOVerflowingRAM_AddNewServer() throws Exception
    {
        int expectedServerCount = 2;
        Computer[] virtualMachines = {
            new Computer(2, 32, 100),
            new Computer(1, 16, 10),
            new Computer(1, 17, 10),
            new Computer(3, 32, 100),
        };
        Computer serverType = new Computer(3, 32, 50);

        ServerPlanner serverPlanner = new ServerPlanner();

        int serverCount = serverPlanner.calculate(serverType, virtualMachines);

        assertThat(serverCount).isEqualTo(expectedServerCount);

    }



}