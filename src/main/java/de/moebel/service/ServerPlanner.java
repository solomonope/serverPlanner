package de.moebel.service;

import de.moebel.exception.InvalidArgumentException;
import de.moebel.model.Computer;
import java.util.Arrays;

public class ServerPlanner
{
    /**
     *
     * Accepts The serverType of type Computer and an Array of Virtual Machines
     * It returns an integer representing the number of physical servers required
     * to host these virtual machines
     * @param serverType
     * @param virtualMachines
     * @return int
     * @throws InvalidArgumentException if an argument is null
     */
    public int calculate(Computer serverType, Computer... virtualMachines)
    {
        if (serverType == null || virtualMachines == null || virtualMachines.length < 1)
        {
            throw new InvalidArgumentException("The passed Arguments are wrong");
        }
        int serverCount = 1;
        int currentCPU = serverType.getCPU();
        int currentRAM = serverType.getRAM();
        int currentHDD = serverType.getHDD();

        Computer[] sortedVirtualMachines = virtualMachines.clone();
        Arrays.sort(sortedVirtualMachines, (Computer left, Computer right) -> compare(left, right));

        for (Computer computer : sortedVirtualMachines)
        {
            if (serverType.getCPU() < computer.getCPU() || serverType.getRAM() < computer.getRAM() ||
                serverType.getHDD() < computer.getHDD())
            {
                continue;
            }

            if (currentCPU < computer.getCPU() || currentRAM < computer.getRAM() ||
                currentHDD < computer.getHDD())
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


    /**
     * Compares its two Computers for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     * @param left
     * @param right
     * @return int
     * @throws InvalidArgumentException if an argument is null
     */
    private int compare(Computer left, Computer right)
    {
        if (left == null || right == null)
        {
            throw new InvalidArgumentException("The passed Arguments are wrong");
        }

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
