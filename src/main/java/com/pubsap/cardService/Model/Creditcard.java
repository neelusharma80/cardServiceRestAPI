package com.pubsap.cardService.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Creditcard")
public class Creditcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cardnumber")
    private String cardnumber;

    @Column(name = "cardname")
    private String cardname;

    @Column(name = "cardlimit")
    private Double cardlimit = 0.0;

    public Creditcard() {

    }

    public Creditcard(String cardnumber, String cardname, Double cardlimit) {
        this.cardnumber = cardnumber;
        this.cardname = cardname;
        this.cardlimit = cardlimit;
    }

    public long getId() {
        return id;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public Double getCardlimit() {
        return cardlimit;
    }

    public void setCardlimit(Double cardlimit) {
        this.cardlimit = cardlimit;
    }

    @Override
    public String toString() {
        return "CreditCard [id=" + id + ", cardnumber=" + cardnumber + ", cardname=" + cardname + ", cardlimit=" + cardlimit + "]";
    }

}