package de.moebel.service;

import de.moebel.model.Computer;
import java.util.Arrays;

public class ServerPlanner
{
    /***
     *
     * @param serverType
     * @param virtualMachines
     * @return
     */
    public int calculate(Computer serverType, Computer... virtualMachines)
    {
        int serverCount = 1;
        int currentCPU = serverType.getCPU();
        int currentRAM = serverType.getRAM();
        int currentHDD = serverType.getHDD();

        Computer[] sortedVirtualMachines = virtualMachines.clone();
        Arrays.sort(sortedVirtualMachines, (Computer left, Computer right) -> compare(left, right));

        for (Computer computer : sortedVirtualMachines)
        {
            if (serverType.getCPU() < computer.getCPU() || serverType.getRAM() < computer.getRAM() || serverType.getHDD() < computer.getHDD())
            {
                continue;
            }

            if (currentCPU < computer.getCPU() || currentRAM < computer.getRAM() || currentHDD < computer.getHDD())
            {
                serverCount++;
                currentCPU = serverType.getCPU();
                currentRAM = serverType.getRAM();
                currentHDD = serverType.getHDD();
            }

            currentCPU -= computer.getCPU();
            currentRAM -= computer.getRAM();
            currentHDD -= computer.getHDD();
        }

        return serverCount;
    }


    /***
     *
     * @param left
     * @param right
     * @return
     */
    private int compare(Computer left, Computer right)
    {
        int cpuCompare = left.getCPU().compareTo(right.getCPU());
        int ramCompare = left.getRAM().compareTo(right.getRAM());
        int hddCompare = left.getHDD().compareTo(right.getHDD());

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

    }
}

