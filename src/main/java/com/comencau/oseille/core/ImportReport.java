package com.comencau.oseille.core;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

public class ImportReport {

	private BigDecimal newSolde;
	
	private List<Transaction> transactions;

	public BigDecimal getNewSolde() {
		return newSolde;
	}

	public void setNewSolde(BigDecimal newSolde) {
		this.newSolde = newSolde;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
