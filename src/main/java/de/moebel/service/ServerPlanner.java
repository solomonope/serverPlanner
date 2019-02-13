package de.moebel.service;

import de.moebel.model.Computer;
import java.util.Arrays;

public class ServerPlanner
{
    public int calculate(Computer serverType, Computer... virtualMachines)
    {
        int serverCount = 1;
        int currentCPU = serverType.getCpu();
        int currentRAM = serverType.getRam();
        int currentHDD = serverType.getHdd();

        Computer[] sortedVirtualMachines = virtualMachines.clone();

        Arrays.sort(sortedVirtualMachines, (Computer left, Computer right) ->
        {
            int cpuCompare = left.getCpu().compareTo(right.getCpu());
            int ramCompare = left.getRam().compareTo(right.getRam());
            int hddCompare = left.getHdd().compareTo(right.getHdd());

            if (cpuCompare == 0)
            {
                if (ramCompare == 0)
                {
                    return ((hddCompare == 0) ? ramCompare : hddCompare);
                }
                else
                {
                    return ramCompare;
                }
            }
            else
            {
                return cpuCompare;
            }

        });

        for (Computer computer : sortedVirtualMachines)
        {
            if (serverType.getCpu() < computer.getCpu() || serverType.getRam() < computer.getRam() || serverType.getHdd() < computer.getHdd())
            {
                continue;
            }

            if (!(currentCPU >= computer.getCpu() && currentRAM >= computer.getRam() && currentHDD >= computer.getHdd()))
            {
                serverCount++;
                currentCPU = serverType.getCpu();
                currentRAM = serverType.getRam();
                currentHDD = serverType.getHdd();
            }

            currentCPU -= computer.getCpu();
            currentRAM -= computer.getRam();
            currentHDD -= computer.getHdd();
        }

        return serverCount;
    }
}
