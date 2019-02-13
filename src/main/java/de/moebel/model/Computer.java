package de.moebel.model;

public class Computer
{
    private final Integer cpu;
    private final Integer ram;
    private final Integer hdd;


    public Computer(Integer cpu, Integer ram, Integer hdd)
    {
        this.cpu = cpu;
        this.ram = ram;
        this.hdd = hdd;
    }


    public Integer getCpu()
    {
        return cpu;
    }


    public Integer getRam()
    {
        return ram;
    }


    public Integer getHdd()
    {
        return hdd;
    }
}
