package de.moebel.model;

public class Computer
{
    private final Integer CPU;
    private final Integer RAM;
    private final Integer HDD;


    public Computer(Integer CPU, Integer RAM, Integer HDD)
    {
        this.CPU = CPU;
        this.RAM = RAM;
        this.HDD = HDD;
    }


    public Integer getCPU()
    {
        return CPU;
    }


    public Integer getRAM()
    {
        return RAM;
    }


    public Integer getHDD()
    {
        return HDD;
    }
}
