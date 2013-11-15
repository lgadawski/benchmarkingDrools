package com.gadawski.app.facts;

import java.math.BigDecimal;

public class Person {
	/**
	 * Ranges of each attributes for generating random people.
	 */
	public static final int MIN_AGE = 12;
	public static final int MAX_AGE = 90;
	public static final float MIN_INCOME = 1200;
	public static final float MAX_INCOME = 12000;
	public static final float MIN_CASH = 0;
	public static final float MAX_CASH = 500000; 
			
	private int age;
	private BigDecimal income;
	private BigDecimal cash;
	private boolean married;

	public Person(int age, BigDecimal income, BigDecimal cash, boolean married) {
		this.setAge(age);
		this.setIncome(income);
		this.setCash(cash);
		this.setMarried(married);
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", income=" + income + ", cash=" + cash
				+ ", married=" + married + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((cash == null) ? 0 : cash.hashCode());
		result = prime * result + ((income == null) ? 0 : income.hashCode());
		result = prime * result + (married ? 1231 : 1237);
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
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (cash == null) {
			if (other.cash != null)
				return false;
		} else if (!cash.equals(other.cash))
			return false;
		if (income == null) {
			if (other.income != null)
				return false;
		} else if (!income.equals(other.income))
			return false;
		if (married != other.married)
			return false;
		return true;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}
	
}
