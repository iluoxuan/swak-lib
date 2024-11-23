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
public class BigNumber extends Number implements Comparable<BigNumber> {

    public static final BigNumber ZERO = BigNumber.of(0);

    public static final BigNumber ONE = BigNumber.of(1);

    public static final int scale4 = 4;

    public static final int scale2 = 2;

    private BigDecimal value;

    private BigNumber(BigDecimal value) {
        this.value = value;
    }

    public static BigNumber of(Number value) {
        BigNumber bigNumber = new BigNumber(BigNumber.toBigDecimal(value));
        return bigNumber;
    }

    private static BigDecimal toBigDecimal(Number value) {
        if (value instanceof BigNumber) {
            BigNumber bigNumber = (BigNumber) value;
            return bigNumber.getValue();
        }
        return NumberUtil.toBigDecimal(value);
    }

    public static BigNumber of(String value) {
        BigNumber bigNumber = new BigNumber(NumberUtil.toBigDecimal(value));
        return bigNumber;
    }

    public BigNumber add(Number number) {
        BigDecimal newValue = NumberUtil.add(value, BigNumber.toBigDecimal(number));
        return BigNumber.of(newValue);
    }

    public BigNumber sub(Number number) {
        BigDecimal newValue = NumberUtil.sub(value, BigNumber.toBigDecimal(number));
        return BigNumber.of(newValue);
    }

    public BigNumber sub(String number) {
        BigDecimal newValue = NumberUtil.sub(value, NumberUtil.toBigDecimal(number));
        return BigNumber.of(newValue);
    }

    public BigNumber add(String number) {
        BigDecimal newValue = NumberUtil.add(value, NumberUtil.toBigDecimal(number));
        return BigNumber.of(newValue);
    }

    public BigNumber mul(Number number) {
        BigDecimal newValue = NumberUtil.mul(value, BigNumber.toBigDecimal(number));
        return BigNumber.of(newValue);
    }

    public BigNumber mul(String... numbers) {
        BigDecimal newValue = NumberUtil.mul(value, NumberUtil.mul(numbers));
        return BigNumber.of(newValue);
    }

    public BigNumber div(Number number, int scale) {
        BigDecimal newValue = NumberUtil.div(value, BigNumber.toBigDecimal(number), scale, RoundingMode.HALF_UP);
        return BigNumber.of(newValue);
    }

    public BigNumber div(String number, int scale) {
        BigDecimal newValue = NumberUtil.div(value, NumberUtil.toBigDecimal(number), scale, RoundingMode.HALF_UP);
        return BigNumber.of(newValue);
    }

    public BigNumber divScale2(String number) {
        BigDecimal newValue = NumberUtil.div(value, NumberUtil.toBigDecimal(number), scale2, RoundingMode.HALF_UP);
        return BigNumber.of(newValue);
    }

    public BigNumber divScale2(Number number) {
        BigDecimal newValue = NumberUtil.div(value, BigNumber.toBigDecimal(number), scale2, RoundingMode.HALF_UP);
        return BigNumber.of(newValue);
    }

    public BigNumber round2HalfUp() {
        BigDecimal newValue = this.value.setScale(scale2, BigDecimal.ROUND_HALF_UP);
        return BigNumber.of(newValue);
    }

    public BigNumber roundHalfUp(int scale) {
        BigDecimal newValue = this.value.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return BigNumber.of(newValue);
    }

    @Override
    public int intValue() {
        return value.intValue();
    }

    @Override
    public long longValue() {
        return value.longValue();
    }

    @Override
    public float floatValue() {
        return value.floatValue();
    }

    @Override
    public double doubleValue() {
        return value.doubleValue();
    }

    public String toString() {
        return value.toString();
    }

    @Override
    public int compareTo(BigNumber o) {
        return value.compareTo(o.value);
    }

    public static void main(String[] args) {

        double totalCost = 10000.01;

        double currentPrice = 100;
        int minShares = 100;

        BigNumber bigNumber = BigNumber.of(totalCost).add(currentPrice).mul(currentPrice).round2HalfUp();
        System.out.println(bigNumber.getValue().toString());


        BigDecimal a = new BigDecimal(100);
        BigDecimal c = a.divide(new BigDecimal(3), 2, RoundingMode.HALF_UP);
        System.out.println(a);
        System.out.println(c);
    }
}
