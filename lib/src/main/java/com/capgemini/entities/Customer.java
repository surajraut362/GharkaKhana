package com.capgemini.entities;

 

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

 

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

 

@Component /* it will detect our custom beans */
@Scope(scopeName = "prototype") /* keeping this as prototype */
@Entity /* Creating table */
@Table(name = "Customer") /* Specifying table name */
public class Customer {

    @Id /* defining primary key */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* generating value automatically */
    @Column(name = "Customer_Id") /* specifying column name */
    private int customerId;

    /*
     * specifying column name, giving length and giving constraint as not null
     */
    @Column(name = "First_Name", length = 25, nullable = false)
    private String firstName;

    /*
     * specifying column name, giving length and giving constraint as not null
     */
    @Column(name = "Last_Name", length = 25, nullable = false)
    private String lastName;

    /*
     * specifying column name, giving length and giving constraint as not null
     */
    @Column(name = "Contact_No", length = 10, nullable = false)
    private long contactNo;

    /*
     * specifying column name, giving length and giving constraint as not null
     */
    @Column(name = "User_Name", length = 25, nullable = false)
    private String userName;

    /*
     * specifying column name, giving length and giving constraint as not null
     */
    @Column(name = "Password", length = 10, nullable = false)
    private String password;
    
    @Column(name="EMAIL_ID",length=50,nullable=false)
    private String emailId;

    public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Order> customerOrder;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private CustomerAddress customerAddress;

    /* creating parameterized constructor */
    public Customer(String firstName, String lastName, long contactNo, String userName, String password,
            List<Order> customerOrder, CustomerAddress customerAddress) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.userName = userName;
        this.password = password;
        this.customerOrder = customerOrder;
        this.customerAddress = customerAddress;
    }

    // creating default constructor
    public Customer() {
    }

    // creating getters and setters
   
    public int getCustomerId() {
        return customerId;
    }

    public List<Order> getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(List<Order> customerOrder) {
		this.customerOrder = customerOrder;
	}

	public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    /* creating tostring method */
    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", contactNo=" + contactNo + ", userName=" + userName + ", password=" + password + ", customerOrder="
                + customerOrder + ", customerAddress=" + customerAddress + "]";
    }

}