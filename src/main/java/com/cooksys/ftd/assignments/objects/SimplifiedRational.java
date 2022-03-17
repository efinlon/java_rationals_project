package com.cooksys.ftd.assignments.objects;

import java.util.Arrays;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SimplifiedRational implements IRational {

	private int numerator;
	private int denominator;

	/**
	 * Determines the greatest common denominator for the given values
	 *
	 * @param a the first value to consider
	 * @param b the second value to consider
	 * @return the greatest common denominator, or shared factor, of `a` and `b`
	 * @throws IllegalArgumentException if a <= 0 or b < 0
	 */
	public static int gcd(int a, int b) throws IllegalArgumentException {
		if (a <= 0 || b < 0) {
			throw new IllegalArgumentException();
		}
		int small;
		int big;
		if (a < b) {
			small = a;
			big = b;
		} else {
			small = b;
			big = a;
		}
		while (true) {
			int remainder = big % small;
			if (remainder == 0) {
				return small;
			}
			big = small;
			small = remainder;
		}

//    	int smallest;
//    	int gcf = 1;
//    	if(a<b) {
//    		smallest = a;
//    	}else {smallest = b;}
//    	
//    	for(int i = 1; i <= smallest; i++) {
//    		if((a % i == 0) && (b % i == 0)) {
//    			gcf = i;
//    		}
//    	}
//    	return gcf;
	}

	/**
	 * Simplifies the numerator and denominator of a rational value.
	 * <p>
	 * For example: `simplify(10, 100) = [1, 10]` or: `simplify(0, 10) = [0, 1]`
	 *
	 * @param numerator   the numerator of the rational value to simplify
	 * @param denominator the denominator of the rational value to simplify
	 * @return a two element array representation of the simplified numerator and
	 *         denominator
	 * @throws IllegalArgumentException if the given denominator is 0
	 */
	public static int[] simplify(int numerator, int denominator) throws IllegalArgumentException {
		if (denominator == 0) {
			throw new IllegalArgumentException();
		}
		int d = Math.abs(denominator);
		int n = Math.abs(numerator);
		int[] arr = new int[2];
		if (numerator == 0) {
			arr[0] = 0;
			arr[1] = 1;
		} else {
			int factor = gcd(n, d);
			arr[0] = numerator / factor;
			arr[1] = denominator / factor;
		}
		return arr;
	}

	/**
	 * Constructor for rational values of the type:
	 * <p>
	 * `numerator / denominator`
	 * <p>
	 * Simplification of numerator/denominator pair should occur in this method. If
	 * the numerator is zero, no further simplification can be performed
	 *
	 * @param numerator   the numerator of the rational value
	 * @param denominator the denominator of the rational value
	 * @throws IllegalArgumentException if the given denominator is 0
	 */
	public SimplifiedRational(int numerator, int denominator) throws IllegalArgumentException {
		if (denominator == 0) {
			throw new IllegalArgumentException();
		}
		int[] arr = simplify(numerator, denominator);
		this.numerator = arr[0];
		this.denominator = arr[1];
	}

	/**
	 * @return the numerator of this rational number
	 */
	@Override
	public int getNumerator() {
		return this.numerator;
	}

	/**
	 * @return the denominator of this rational number
	 */
	@Override
	public int getDenominator() {
		return this.denominator;
	}

	/**
	 * Specializable constructor to take advantage of shared code between Rational
	 * and SimplifiedRational
	 * <p>
	 * Essentially, this method allows us to implement most of IRational methods
	 * directly in the interface while preserving the underlying type
	 *
	 * @param numerator   the numerator of the rational value to construct
	 * @param denominator the denominator of the rational value to construct
	 * @return the constructed rational value (specifically, a SimplifiedRational
	 *         value)
	 * @throws IllegalArgumentException if the given denominator is 0
	 */
	@Override
	public SimplifiedRational construct(int numerator, int denominator) throws IllegalArgumentException {
		if (denominator == 0) {
			throw new IllegalArgumentException();
		}
		SimplifiedRational s = new SimplifiedRational(numerator, denominator);
		return s;
	}

	/**
	 * @param obj the object to check this against for equality
	 * @return true if the given obj is a rational value and its numerator and
	 *         denominator are equal to this rational value's numerator and
	 *         denominator, false otherwise
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + denominator;
		result = prime * result + numerator;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimplifiedRational other = (SimplifiedRational) obj;
		if (denominator != other.denominator)
			return false;
		if (numerator != other.numerator)
			return false;
		return true;
	}

	/**
	 * If this is positive, the string should be of the form `numerator/denominator`
	 * <p>
	 * If this is negative, the string should be of the form
	 * `-numerator/denominator`
	 *
	 * @return a string representation of this rational value
	 */
	@Override
	public String toString() {
		int n = this.numerator;
		int d = this.denominator;
		return (n < 0 != d < 0 ? "-" : "") + Math.abs(n) + "/" + Math.abs(d);
	}
}
