package com.myweb.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "market")
public class Market {
    private int id;
    private Integer fisheryId;
    private String sellStatus;
    private BigDecimal startPrice;
    private BigDecimal stopPrice;
    private BigDecimal sellDuration;
    private String sellStartTime;
    private BigDecimal staticPrice;
    private Integer favorCount;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fishery_id")
    public Integer getFisheryId() {
        return fisheryId;
    }

    public void setFisheryId(Integer fisheryId) {
        this.fisheryId = fisheryId;
    }

    @Basic
    @Column(name = "sell_status")
    public String getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(String sellStatus) {
        this.sellStatus = sellStatus;
    }

    @Basic
    @Column(name = "start_price")
    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    @Basic
    @Column(name = "stop_price")
    public BigDecimal getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(BigDecimal stopPrice) {
        this.stopPrice = stopPrice;
    }

    @Basic
    @Column(name = "sell_duration")
    public BigDecimal getSellDuration() {
        return sellDuration;
    }

    public void setSellDuration(BigDecimal sellDuration) {
        this.sellDuration = sellDuration;
    }

    @Basic
    @Column(name = "sell_start_time")
    public String getSellStartTime() {
        return sellStartTime;
    }

    public void setSellStartTime(String sellStartTime) {
        this.sellStartTime = sellStartTime;
    }

    @Basic
    @Column(name = "static_price")
    public BigDecimal getStaticPrice() {
        return staticPrice;
    }

    public void setStaticPrice(BigDecimal staticPrice) {
        this.staticPrice = staticPrice;
    }

    @Basic
    @Column(name = "favor_count")
    public Integer getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(Integer favorCount) {
        this.favorCount = favorCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Market market = (Market) o;
        return id == market.id &&
                Objects.equals(fisheryId, market.fisheryId) &&
                Objects.equals(sellStatus, market.sellStatus) &&
                Objects.equals(startPrice, market.startPrice) &&
                Objects.equals(stopPrice, market.stopPrice) &&
                Objects.equals(sellDuration, market.sellDuration) &&
                Objects.equals(sellStartTime, market.sellStartTime) &&
                Objects.equals(staticPrice, market.staticPrice) &&
                Objects.equals(favorCount, market.favorCount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fisheryId, sellStatus, startPrice, stopPrice, sellDuration, sellStartTime, staticPrice, favorCount);
    }
}
