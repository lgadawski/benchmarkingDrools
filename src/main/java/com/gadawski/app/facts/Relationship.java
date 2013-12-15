package com.gadawski.app.facts;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author l.gadawski@gmail.com
 * 
 */
@Entity
@Table(name = "RELATIONSHIPS")
public class Relationship implements Serializable {
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 9071494352489720733L;
    /**
     * Entity ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RelationshipID", unique = true, updatable = false, nullable = false)
    private Long relationshipID;
    /**
     * 
     */
//    @ManyToOne
//    @JoinColumn(name = "JoinNodeID")
    @Column(name = "JoinNodeID", nullable = false)
    private Long joinNodeID;
    /**
     * 
     */
    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;
    /**
     * 
     */
    @ManyToOne
    @JoinColumn(name = "CarID")
    private Car car;
    /**
     * 
     */
    @ManyToOne
    @JoinColumn(name = "HouseID")
    private House house;

    /**
     * For persistence purpose.
     */
    public Relationship() {
    }

    /**
     * @return the joinNodeID
     */
    public Long getJoinNode() {
        return joinNodeID;
    }

    /**
     * @param joinNodeID
     *            the joinNodeID to set
     */
    public void setJoinNode(Long joinNodeID) {
        this.joinNodeID = joinNodeID;
    }

    /**
     * @return the customerID
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customerID
     *            the customerID to set
     */
    public void setCustomer(Customer customerID) {
        this.customer = customerID;
    }

    /**
     * @return the carID
     */
    public Car getCar() {
        return car;
    }

    /**
     * @param carID
     *            the carID to set
     */
    public void setCar(Car carID) {
        this.car = carID;
    }

    /**
     * @return the houseID
     */
    public House getHouse() {
        return house;
    }

    /**
     * @param houseID
     *            the houseID to set
     */
    public void setHouse(House houseID) {
        this.house = houseID;
    }
}
