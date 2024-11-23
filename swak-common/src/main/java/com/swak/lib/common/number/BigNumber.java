package com.swak.lib.common.number;

import cn.hutool.core.util.NumberUtil;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 扩展BigDecimal 支持计算
 * 支持 各种格式 直接转成 BigDecimal 计算
 *
 * @author: ljq
 * @date: 2024/11/23
 */
@Getter
public class BigNumber {

    public static final BigNumber ZERO = BigNumber.of(0);

    public static final BigNumber ONE = BigNumber.of(1);

    private BigDecimal value;

    private BigNumber(BigDecimal value) {
        this.value = value;
    }

    public static BigNumber of(Number value) {
        BigNumber bigNumber = new BigNumber(NumberUtil.toBigDecimal(value));
        return bigNumber;
    }

    public static BigNumber of(String value) {
        BigNumber bigNumber = new BigNumber(NumberUtil.toBigDecimal(value));
        return bigNumber;
    }

    public BigNumber add(Number number) {
        value = NumberUtil.add(value, number);
        return this;
    }

    public BigNumber sub(Number number) {
        value = NumberUtil.sub(value, number);
        return this;
    }

    public BigNumber sub(String number) {
        value = NumberUtil.sub(value, NumberUtil.toBigDecimal(number));
        return this;
    }

    public BigNumber add(String number) {
        value = NumberUtil.add(value, NumberUtil.toBigDecimal(number));
        return this;
    }

    public BigNumber mul(Number... numbers) {
        value = NumberUtil.mul(value, NumberUtil.mul(numbers));
        return this;
    }

    public BigNumber mul(String... numbers) {
        value = NumberUtil.mul(value, NumberUtil.mul(numbers));
        return this;
    }

    public BigNumber div(Number number, int scale) {
        value = NumberUtil.div(value, number, scale, RoundingMode.HALF_UP);
        return this;
    }

    public BigNumber div(String number, int scale) {
        value = NumberUtil.div(value, NumberUtil.toBigDecimal(number), scale, RoundingMode.HALF_UP);
        return this;
    }

    public BigNumber divScale2(String number) {
        value = NumberUtil.div(value, NumberUtil.toBigDecimal(number), 2, RoundingMode.HALF_UP);
        return this;
    }

    public BigNumber divScale2(Number number) {
        value = NumberUtil.div(value, number, 2, RoundingMode.HALF_UP);
        return this;
    }

    public BigNumber round2HalfUp() {
        value.setScale(2, BigDecimal.ROUND_HALF_UP);
        return this;
    }


    public static void main(String[] args) {

        double totalCost = 10000.01;

        double currentPrice = 100;
        int minShares = 100;

        BigNumber bigNumber = BigNumber.of(totalCost).add(currentPrice).mul(minShares).round2HalfUp();
        System.out.println(bigNumber.getValue().toString());

    }
}
