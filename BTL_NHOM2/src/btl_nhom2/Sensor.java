/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_nhom2;

/**
 *
 * @author Hoàng
 */
public class Sensor {
    private double transmissionRange, discretePhenomenonSensingRange; //bán kính truyền và bán kính quét của sensor, đơn vị met
    private double  startupEnergy;//nặng lượng khởi đầu
    private double phenomenonSpeed;//tốc độ, đơn vị m/s
    private double macProtocol;//địa chỉ mac
    private double bandwidth; //băng thông, đơn vị Mbps
    private double transmitPower, receivePower;//năng lượng truyền và năng lương nhận,đơn vị W
    final int packageSize=100;//kích thước gói được cố định là 100 bit
    public double getTransmissionRange() {
        return transmissionRange;
    }
    public double getDiscretePhenomenonSensingRange() {
        return discretePhenomenonSensingRange;
    }

    public Sensor(double transmissionRange, double discretePhenomenonSensingRange, double startupEnergy, double phenomenonSpeed, double macProtocol, double bandwidth, double transmitPower, double receivePower) {
        this.transmissionRange = transmissionRange;
        this.discretePhenomenonSensingRange = discretePhenomenonSensingRange;
        this.startupEnergy = startupEnergy;
        this.phenomenonSpeed = phenomenonSpeed;
        this.macProtocol = macProtocol;
        this.bandwidth = bandwidth;
        this.transmitPower = transmitPower;
        this.receivePower = receivePower;
    }

    public Sensor() {
    }

    public double getBandwidth() {
        return bandwidth;
    }

    public int getPackageSize() {
        return packageSize;
    }

    public Sensor(double transmissionRange, double discretePhenomenonSensingRange, double bandwidth) {
        this.transmissionRange = transmissionRange;
        this.discretePhenomenonSensingRange = discretePhenomenonSensingRange;
        this.bandwidth = bandwidth;
    }
    
}
